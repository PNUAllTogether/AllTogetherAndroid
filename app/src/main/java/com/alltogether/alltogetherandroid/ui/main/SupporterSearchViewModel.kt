package com.alltogether.alltogetherandroid.ui.main

import androidx.lifecycle.LiveData
import com.alltogether.alltogetherandroid.base.BaseViewModel
import com.alltogether.alltogetherandroid.dto.filterBody
import com.alltogether.alltogetherandroid.repository.ServerRepository
import com.alltogether.alltogetherandroid.utils.SingleLiveEvent
import io.reactivex.functions.Consumer

class SupporterSearchViewModel(private val serverRepository: ServerRepository) : BaseViewModel() {
    private val _onSearchFinished: SingleLiveEvent<Any> = SingleLiveEvent()
    val onSearchFinished: LiveData<Any> get() = _onSearchFinished
    private val _onSeachFailed: SingleLiveEvent<Any> = SingleLiveEvent()
    val onSeachFailed: LiveData<Any> get() = _onSeachFailed
    private val _onAddSuccess: SingleLiveEvent<Any> = SingleLiveEvent()
    val onAddSuccess: LiveData<Any> get() = _onAddSuccess
    private val _onAddFailed: SingleLiveEvent<Any> = SingleLiveEvent()
    val onAddFailed: LiveData<Any> get() = _onAddFailed

    var supporterList: ArrayList<filterBody>? = null
    var selectedSupporter : filterBody? = null

    fun searchSupporter(region: String, major: String) {
        apiCall(serverRepository.filterSupporter(region, major),
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

    fun addSupporter(childId: Int, supporterId: Int) {
        apiCall(serverRepository.addSupporter(childId, supporterId),
        onSuccess = Consumer {
            if(it.response == "SUCCESS") {
                _onAddSuccess.call()
            }
            else {
                _onAddFailed.call()
            }
        })
    }

}