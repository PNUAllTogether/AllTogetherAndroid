package com.alltogether.alltogetherandroid.ui.main

import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedFragment : BaseFragment<FeedViewModel>(){
    override val layoutSource: Int
        get() = R.layout.fragment_feed
    override val viewModel: FeedViewModel by viewModel()

    override fun viewInit() {
    }

    override fun dataInit() {
    }

    override fun finishInit() {
    }
}