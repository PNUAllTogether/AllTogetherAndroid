package com.alltogether.alltogetherandroid.ui.login

import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import com.nhn.android.naverlogin.OAuthLogin
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<LoginViewModel>() {
    override val layoutSource: Int
        get() = R.layout.fragment_login
    override val viewModel: LoginViewModel by viewModel()

    override fun viewInit() {
        login_naver_button.setOnClickListener {
            viewModel.getToken(requireActivity())
        }
    }

    override fun dataInit() {

    }

    override fun finishInit() {

    }
}