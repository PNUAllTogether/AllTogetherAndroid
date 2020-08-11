package com.alltogether.alltogetherandroid.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.alltogether.alltogetherandroid.utils.LoadingIndicator
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<VM: BaseViewModel>: Fragment() {

    private var mLoadingIndicator: Dialog? = null

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingIndicatorObserving()
        viewInit()
        dataInit()
        finishInit()
    }

    fun showLongSnackBar(string: String) {
        Snackbar.make(requireView(), string, Snackbar.LENGTH_LONG).show()
    }

    fun showShortSnackBar(string: String) {
        Snackbar.make(requireView(), string, Snackbar.LENGTH_SHORT).show()
    }

    private fun loadingIndicatorObserving() {
        viewModel.startLoadingIndicatorEvent.observe(viewLifecycleOwner, Observer {
            startLoadingIndicator()
        })
        viewModel.stopLoadingIndicatorEvent.observe(viewLifecycleOwner, Observer {
            stopLoadingIndicator()
        })
    }

    private fun stopLoadingIndicator() {
        mLoadingIndicator?.let {
            if (it.isShowing) it.cancel()
        }
    }

    private fun startLoadingIndicator() {
        stopLoadingIndicator()
        activity?.let {
            if (!it.isFinishing) {
                mLoadingIndicator = LoadingIndicator(activity)
                mLoadingIndicator?.show()
            }
        }
    }
}