package com.alltogether.alltogetherandroid.ui.signup

import androidx.navigation.fragment.findNavController
import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import com.alltogether.alltogetherandroid.ui.login.LoginViewModel
import com.alltogether.alltogetherandroid.utils.sharedGraphViewModel
import kotlinx.android.synthetic.main.fragment_signup_parent_guide.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FragmentSignupParentGuide : BaseFragment<LoginViewModel>() {
    override val layoutSource: Int
        get() = R.layout.fragment_signup_parent_guide
    override val viewModel: LoginViewModel by sharedGraphViewModel(R.id.nav_graph)

    override fun viewInit() {
        signup_parent_guide_next_button.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentSignupParentGuide_to_fragmentSignupParentChildInfo)
        }
    }

    override fun dataInit() {

    }

    override fun finishInit() {

    }
}