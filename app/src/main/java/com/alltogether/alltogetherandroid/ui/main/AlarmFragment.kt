package com.alltogether.alltogetherandroid.ui.main

import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlarmFragment : BaseFragment<AlarmViewModel>() {
    override val layoutSource: Int
        get() = R.layout.fragment_alarm
    override val viewModel: AlarmViewModel by viewModel()

    override fun viewInit() {
    }

    override fun dataInit() {
    }

    override fun finishInit() {
    }
}