package com.alltogether.alltogetherandroid.ui.main

import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SupporterSearchFragment : BaseFragment<SupporterSearchViewModel>(){
    override val layoutSource: Int
        get() =  R.layout.fragment_supporter_search
    override val viewModel: SupporterSearchViewModel by viewModel()

    override fun viewInit() {
    }

    override fun dataInit() {
    }

    override fun finishInit() {
    }
}