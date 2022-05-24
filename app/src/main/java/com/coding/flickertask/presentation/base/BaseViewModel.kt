package com.coding.flickertask.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coding.flickertask.data.model.ResponseGeneric
import kotlinx.coroutines.Job

    /*
        Base interfaces that handle the ui event and deliver the event result
    */
interface BaseViewState
interface BaseEvent
interface BaseResult

abstract class BaseViewModel<
        ViewState : BaseViewState,
        Event : BaseEvent,
        Result : BaseResult> : ViewModel() {

    internal val baseViewState = MutableLiveData<ViewState>()

    val viewState: LiveData<ViewState> get() = baseViewState
    var loadJob: Job? = null


    fun getLocallySavedData(event: Event) {
        deliverEventResult(event)
    }

    suspend fun initiateEvent(event: Event, searchText: String) {
        triggerSearchEvent(event, searchText)
    }

    abstract fun deliverEventResult(event: Event)

    abstract suspend fun triggerSearchEvent(event: Event, searchText: String)

    abstract fun resultToViewState(result: ResponseGeneric<Result>)
}
