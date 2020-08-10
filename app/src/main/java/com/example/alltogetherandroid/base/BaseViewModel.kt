package com.example.alltogetherandroid.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

class BaseViewModel: ViewModel() {

    private val compositeDisposable = CompositeDisposable()

}