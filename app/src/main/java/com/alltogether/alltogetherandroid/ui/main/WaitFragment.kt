package com.alltogether.alltogetherandroid.ui.main

import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class WaitFragment : BaseFragment<MainViewModel>() {
    override val viewModel: MainViewModel by sharedViewModel()
    override val layoutSource: Int
        get() = R.layout.fragment_wait_sup

    override fun viewInit() {

    }

    override fun dataInit() {

    }

    override fun finishInit() {

    }

}