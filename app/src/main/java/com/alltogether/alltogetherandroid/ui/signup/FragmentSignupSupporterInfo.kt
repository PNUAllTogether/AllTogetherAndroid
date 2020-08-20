package com.alltogether.alltogetherandroid.ui.signup

import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import com.alltogether.alltogetherandroid.ui.login.LoginViewModel
import com.alltogether.alltogetherandroid.utils.sharedGraphViewModel

class FragmentSignupSupporterInfo: BaseFragment<LoginViewModel>() {
    override val layoutSource: Int
        get() = R.layout.fragment_signup_supporter_info

    override val viewModel: LoginViewModel by sharedGraphViewModel(R.id.nav_graph)

    override fun viewInit() {}

    override fun dataInit() {}

    override fun finishInit() {}
}