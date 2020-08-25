package com.alltogether.alltogetherandroid.ui.main

import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class GiftFragment : BaseFragment<GiftViewModel>(){
    override val layoutSource: Int
        get() = R.layout.fragment_gift
    override val viewModel: GiftViewModel by viewModel()

    override fun viewInit() {
    }

    override fun dataInit() {
    }

    override fun finishInit() {
    }
}