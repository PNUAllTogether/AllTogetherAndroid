package com.alltogether.alltogetherandroid.ui.main

import androidx.lifecycle.LiveData
import com.alltogether.alltogetherandroid.base.BaseViewModel
import com.alltogether.alltogetherandroid.dto.filterBody
import com.alltogether.alltogetherandroid.dto.list
import com.alltogether.alltogetherandroid.repository.ServerRepository
import com.alltogether.alltogetherandroid.utils.SingleLiveEvent
import io.reactivex.functions.Consumer

class FeedViewModel(private val serverRepository: ServerRepository) : BaseViewModel() {
    private val _onSearchFinished: SingleLiveEvent<Any> = SingleLiveEvent()
    val onSearchFinished: LiveData<Any> get() = _onSearchFinished
    private val _onSeachFailed: SingleLiveEvent<Any> = SingleLiveEvent()
    val onSeachFailed: LiveData<Any> get() = _onSeachFailed
    private val _onGetCheckListFinished: SingleLiveEvent<Any> = SingleLiveEvent()
    val onGetCheckListFinished: LiveData<Any> get() = _onGetCheckListFinished
    private val _onAddFinished: SingleLiveEvent<Any> = SingleLiveEvent()
    val onAddFinished: LiveData<Any> get() = _onAddFinished
    private val _onDoneFinished: SingleLiveEvent<Any> = SingleLiveEvent()
    val onDoneFinished: LiveData<Any> get() = _onDoneFinished

    var supporterList: ArrayList<filterBody>? = null
    var checkList: ArrayList<list>? = null
    var childId: Int? = null

    fun currentSupporter(childId: Int) {
        this.childId = childId
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

    fun getAllCheckList(childId: Int) {
        apiCall(serverRepository.getAllCheckList(childId),
        onSuccess = Consumer {
            checkList = ArrayList(it.list)
            _onGetCheckListFinished.call()
        })
    }

    fun postCheckList(date: String, content: String) {
        apiCall(serverRepository.addCheckList(childId!!, date, content),
        onSuccess = Consumer {
            getAllCheckList(childId!!)
        })
    }

    fun addDone(itemId: Int, done: Boolean) {
        apiCall(serverRepository.addDone(childId!!, itemId, done),
        onSuccess = Consumer {
            _onDoneFinished.call()
        })
    }
}