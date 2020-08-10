package com.example.alltogetherandroid.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<VM: BaseViewModel>: AppCompatActivity() {

    /**
     * Layout Resource ID
     */
    abstract val layoutSource: Int

    /**
     * ViewModel Inheritance
     */
    abstract val viewModel: VM

    /**
     * ViewModel Function call and Init Layout
     */
    abstract fun viewInit()

    /**
     * ViewModel Data Observing and Update Data
     */
    abstract fun dataInit()

    /**
     * Layout Binding and Listener Binding
     */
    abstract fun finishInit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutSource)

        viewInit()
        dataInit()
        finishInit()
    }
}