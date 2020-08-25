package com.alltogether.alltogetherandroid.ui.signup

import androidx.navigation.fragment.findNavController
import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import com.alltogether.alltogetherandroid.ui.login.LoginViewModel
import com.alltogether.alltogetherandroid.utils.sharedGraphViewModel
import kotlinx.android.synthetic.main.fragment_signup_supporter_guide.*

class FragmentSignupSupporterGuide: BaseFragment<LoginViewModel>() {
    override val layoutSource: Int
        get() = R.layout.fragment_signup_supporter_guide

    override val viewModel: LoginViewModel by sharedGraphViewModel(R.id.nav_graph)

    override fun viewInit() {
        signup_supporter_guide_next_button.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentSignupSupporterGuide_to_fragmentSignupSupporterInfo)
        }
    }

    override fun dataInit() {}

    override fun finishInit() {}
}