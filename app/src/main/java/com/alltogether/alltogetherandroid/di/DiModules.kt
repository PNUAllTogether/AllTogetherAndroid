package com.alltogether.alltogetherandroid.di

import com.alltogether.alltogetherandroid.main.MainViewModel
import com.alltogether.alltogetherandroid.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelPart = module {
    viewModel { SplashViewModel() }
    viewModel { MainViewModel() }
}