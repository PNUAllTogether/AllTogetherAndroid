package com.alltogether.alltogetherandroid.ui.main

import androidx.lifecycle.LiveData
import com.alltogether.alltogetherandroid.base.BaseViewModel
import com.alltogether.alltogetherandroid.dto.filterBody
import com.alltogether.alltogetherandroid.repository.ServerRepository
import com.alltogether.alltogetherandroid.utils.SingleLiveEvent
import io.reactivex.functions.Consumer

class FeedViewModel(private val serverRepository: ServerRepository) : BaseViewModel() {
    private val _onSearchFinished: SingleLiveEvent<Any> = SingleLiveEvent()
    val onSearchFinished: LiveData<Any> get() = _onSearchFinished
    private val _onSeachFailed: SingleLiveEvent<Any> = SingleLiveEvent()
    val onSeachFailed: LiveData<Any> get() = _onSeachFailed

    var supporterList: ArrayList<filterBody>? = null

    fun currentSupporter(childId: Int) {
        apiCall(serverRepository.currentSupporter(childId),
        onSuccess = Consumer {
            if(it.response == "EMPTY") {
                _onSeachFailed.call()
            }
            else {
                supporterList = ArrayList(it.search_result)
                _onSearchFinished.call()
            }
        })
    }
}