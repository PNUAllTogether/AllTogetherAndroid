package com.alltogether.alltogetherandroid.main

import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment: BaseFragment<MainViewModel>() {

    override val layoutSource: Int
        get() = R.layout.fragment_main

    override val viewModel: MainViewModel by viewModel()

    override fun viewInit() {}

    override fun dataInit() {}

    override fun finishInit() {}
}