package com.alltogether.alltogetherandroid

import android.app.Application
import com.alltogether.alltogetherandroid.di.viewModelPart
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    private val modules = listOf(
        viewModelPart
    )

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(modules)
        }
    }
}