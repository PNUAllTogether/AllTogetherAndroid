package com.alltogether.alltogetherandroid.ui.main

import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommunityFragment : BaseFragment<CommunityViewModel>() {
    override val layoutSource: Int
        get() = R.layout.fragment_community
    override val viewModel: CommunityViewModel by viewModel()

    override fun viewInit() {
    }

    override fun dataInit() {
    }

    override fun finishInit() {
    }
}