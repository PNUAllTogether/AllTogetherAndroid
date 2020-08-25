package com.alltogether.alltogetherandroid

import android.util.Log
import com.alltogether.alltogetherandroid.base.BaseViewModel
import com.alltogether.alltogetherandroid.dto.userType

class ActivityViewModel : BaseViewModel() {
    var userID: Int = 0
    var userType: userType? = null

    override fun onCleared() {
        Log.e("LOG", "MainActivityViewModel onCleared")
        super.onCleared()
    }
}