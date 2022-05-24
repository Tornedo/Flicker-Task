package com.coding.flickertask.presentation.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import com.coding.flickertask.data.repository.PhotoRepository
import com.coding.flickertask.data.repository.LocalStorageRepository
import com.coding.flickertask.data.model.*
import com.coding.flickertask.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PhotoListViewModel @ExperimentalCoroutinesApi constructor(
    private val photoRepository: PhotoRepository,
    private val localStorageRepository: LocalStorageRepository
) : BaseViewModel<PhotoFragmentViewState, PhotoFragmentEvent, Result>() {

    private var currentViewState = PhotoFragmentViewState()
        set(value) {
            field = value
            baseViewState.postValue(value)
        }

    private val userHistory = MutableLiveData<MutableList<String>>()

    val obtainState: LiveData<PhotoFragmentViewState> = viewState

    val userHistoryState: LiveData<MutableList<String>> = userHistory

    private fun fetchData(searchText: String) {
        /*
            Transfer the Loading state to the view show the loading indicator
         */
        resultToViewState(ResponseGeneric.Loading())
        getPhotosFlow(searchText)

        /*
           Saving the search query
        */
        viewModelScope.launch {
            localStorageRepository.savePhoneBook(FetchImageRequest(searchQuery = searchText))
        }
    }

    private fun getPhotosFlow(searchText: String) {
        loadJob?.cancel()
        loadJob = viewModelScope.launch(Dispatchers.IO) {
            photoRepository.getPhotos(searchText)
                .cachedIn(viewModelScope)
                .onEach {}
                .collect { results ->
                    resultToViewState(ResponseGeneric.ResponseData(Result.data(results)))
                }
        }
    }

    private fun getUserLocalHistory() {
        localStorageRepository.getPhoneBook()?.split(",")?.let {
            userHistory.postValue(localStorageRepository.getPhoneBook()?.split(",") as MutableList<String>?)
        }
    }

    override fun deliverEventResult(photoFragmentEvent: PhotoFragmentEvent) {
        when (photoFragmentEvent) {
            is PhotoFragmentEvent.LoadState -> onLoadState(photoFragmentEvent.state)
            is PhotoFragmentEvent.loadLocallySaved -> getUserLocalHistory()
            else -> {}
        }
    }

    private fun onLoadState(state: CombinedLoadStates) {
        /*
            mapping the paging states
         */
        when (state.source.refresh) {
            is LoadState.Error -> {
                val errorState = state.source.append as? LoadState.Error
                    ?: state.source.prepend as? LoadState.Error
                    ?: state.append as? LoadState.Error
                    ?: state.prepend as? LoadState.Error
                errorState?.let {
                    resultToViewState(ResponseGeneric.Error(Result.Error(errorMessage = errorState.error.localizedMessage)))
                }
            }
            is LoadState.Loading -> resultToViewState(ResponseGeneric.Loading())
            else -> {}
        }

    }

    override suspend fun triggerSearchEvent(photoFragmentEvent: PhotoFragmentEvent, searchText: String) {
        when (photoFragmentEvent) {
            is PhotoFragmentEvent.LoadImage, PhotoFragmentEvent.pullToRefreshEvent -> fetchData(searchText)
            else -> {}
        }
    }

    override fun resultToViewState(result: ResponseGeneric<Result>) {
        currentViewState = when (result) {
            /*
                Transfer the Loading to View
             */
            is ResponseGeneric.Loading -> {
                currentViewState.copy(
                    loadingStateVisibility = View.VISIBLE,
                    errorStateVisibility = View.GONE
                )
            }
            /*
                 Transfer the response data to View
              */
            is ResponseGeneric.ResponseData -> {
                when (result.packet) {
                    is Result.data ->
                        currentViewState.copy(
                            page = result.packet.content,
                            loadingStateVisibility = View.GONE,
                            errorStateVisibility = View.GONE
                        )
                    else -> currentViewState.copy()
                }
            }
            /*
               Handle the error state and transfer the state to View
             */
            is ResponseGeneric.Error -> {
                when (result.packet) {
                    is Result.Error ->
                        currentViewState.copy(
                            errorStateVisibility = View.VISIBLE,
                            errorMessage = result.packet.errorMessage,
                            loadingStateVisibility = View.GONE
                        )
                    else -> currentViewState.copy()
                }
            }
        }
    }
}