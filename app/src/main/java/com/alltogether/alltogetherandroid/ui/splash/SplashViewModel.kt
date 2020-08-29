package com.alltogether.alltogetherandroid.ui.splash

import android.util.Log
import androidx.lifecycle.LiveData
import com.alltogether.alltogetherandroid.base.BaseViewModel
import com.alltogether.alltogetherandroid.utils.SingleLiveEvent

class SplashViewModel: BaseViewModel() {
    /* TODO 로그인 상태 체크하여 바로 메인으로 갈지, 로그인화면 띄울지 선택해야 함. */

    private val _splashNavigationMain: SingleLiveEvent<Any> = SingleLiveEvent()
    val splashNavigationMain: LiveData<Any> get() = _splashNavigationMain

    fun getSplashNavigationMode() {
        _splashNavigationMain.call()
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("LOG", "SplashViewModel onCleared")
    }
}