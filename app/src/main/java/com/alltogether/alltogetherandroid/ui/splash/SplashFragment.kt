package com.alltogether.alltogetherandroid.ui.splash

import android.os.Handler
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.alltogether.alltogetherandroid.ActivityViewModel
import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import com.alltogether.alltogetherandroid.ui.login.LoginViewModel
import com.alltogether.alltogetherandroid.utils.sharedGraphViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment: BaseFragment<LoginViewModel>() {
    override val layoutSource: Int
        get() = R.layout.fragment_splash

    override val viewModel: LoginViewModel by sharedGraphViewModel(R.id.nav_graph)
    val activityViewModel: ActivityViewModel by sharedViewModel()

    override fun viewInit() {
        Handler().postDelayed( Runnable {
            viewModel.getToken(requireActivity())
            //findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
        }, 1000L)
    }

    override fun dataInit() {
        viewModel.onLoginProceed.observe(viewLifecycleOwner, Observer {
            activityViewModel.userID = viewModel.id
            activityViewModel.userType = viewModel.usertype

            findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
        })
        viewModel.onSignupProceed.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_splashFragment_to_fragmentSelectUser)
        })
    }

    override fun finishInit() {}
}