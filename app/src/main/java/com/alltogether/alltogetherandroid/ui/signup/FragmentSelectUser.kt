package com.alltogether.alltogetherandroid.ui.signup

import androidx.navigation.fragment.findNavController
import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import com.alltogether.alltogetherandroid.ui.login.LoginViewModel
import com.alltogether.alltogetherandroid.utils.sharedGraphViewModel
import kotlinx.android.synthetic.main.fragment_select_user.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FragmentSelectUser : BaseFragment<LoginViewModel>() {
    override val layoutSource: Int
        get() = R.layout.fragment_select_user
    override val viewModel: LoginViewModel by sharedGraphViewModel(R.id.nav_graph)

    override fun viewInit() {
        button_select_parent.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentSelectUser_to_fragmentSignupParentGuide)
        }
        button_select_supporter.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentSelectUser_to_fragmentSignupSupporterGuide)
        }
    }

    override fun dataInit() {

    }

    override fun finishInit() {

    }

}