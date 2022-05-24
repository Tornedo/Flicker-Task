package com.coding.flickertask.data.model

import android.view.View
import androidx.paging.CombinedLoadStates
import androidx.paging.PagingData
import com.coding.flickertask.presentation.base.*
    /*
        Update view according the to the various event
     */
data class PhotoFragmentViewState(
    val page: PagingData<PhotoItem>? = null,
    val adapter: List<PhotoItem> = emptyList(),
    val errorMessageResource: Int? = null,
    val errorMessage: String? = null,
    val loadingStateVisibility: Int? = null,
    val errorStateVisibility: Int? = View.GONE
) : BaseViewState

sealed class PhotoFragmentEvent : BaseEvent {
    object pullToRefreshEvent : PhotoFragmentEvent()
    object loadLocallySaved : PhotoFragmentEvent()
    data class LoadState(val state: CombinedLoadStates) : PhotoFragmentEvent()
    object LoadImage : PhotoFragmentEvent()
}

sealed class Result : BaseResult {
    data class Error(val errorMessage: String?) : Result()
    data class data(val content: PagingData<PhotoItem>) : Result()
}