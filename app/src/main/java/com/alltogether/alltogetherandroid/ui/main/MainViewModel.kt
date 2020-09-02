package com.alltogether.alltogetherandroid.ui.main

import androidx.lifecycle.LiveData
import com.alltogether.alltogetherandroid.base.BaseViewModel
import com.alltogether.alltogetherandroid.dto.childInfo
import com.alltogether.alltogetherandroid.repository.ServerRepository
import com.alltogether.alltogetherandroid.utils.SingleLiveEvent
import io.reactivex.functions.Consumer

class MainViewModel(private val serverRepository: ServerRepository) : BaseViewModel() {
    private val _moveTo: SingleLiveEvent<Int> = SingleLiveEvent()
    val moveTo: LiveData<Int> get() = _moveTo
    private val _getChildFinished: SingleLiveEvent<Any> = SingleLiveEvent()
    val getChildFinished: LiveData<Any> get() = _getChildFinished
    private val _noChildWait: SingleLiveEvent<Any> = SingleLiveEvent()
    val noChildWait: LiveData<Any> get() = _noChildWait

    var childInfo : childInfo? = null
    var userID : Int? = null

    fun moveToSpecificPosition(position: Int) {
        _moveTo.postValue(position)
    }

    fun getChildInfo(id : Int) {
        userID = id
        apiCall(serverRepository.getChildInfo(id),
        onSuccess = Consumer {
            if(it.childList == null || it.childList.isEmpty()) {
                _noChildWait.call()
            }
            else {
                childInfo = it.childList[0]
                _getChildFinished.call()
            }

        })
    }
}