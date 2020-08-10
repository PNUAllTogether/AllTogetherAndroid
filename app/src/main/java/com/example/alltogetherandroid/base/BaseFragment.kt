package com.example.alltogetherandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<VM: BaseViewModel>: Fragment() {

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(layoutSource, container, false)
    }

    fun showLongSnackBar(string: String) {
        Snackbar.make(requireView(), string, Snackbar.LENGTH_LONG).show()
    }

    fun showShortSnackBar(string: String) {
        Snackbar.make(requireView(), string, Snackbar.LENGTH_SHORT).show()
    }
}