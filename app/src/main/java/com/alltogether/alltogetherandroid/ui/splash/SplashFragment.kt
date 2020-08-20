package com.alltogether.alltogetherandroid.ui.splash

import android.os.Handler
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment: BaseFragment<SplashViewModel>() {
    override val layoutSource: Int
        get() = R.layout.fragment_splash

    override val viewModel: SplashViewModel by viewModel()

    override fun viewInit() {

        viewModel.getSplashNavigationMode()
    }

    override fun dataInit() {
        viewModel.splashNavigationMain.observe(viewLifecycleOwner, Observer {
            Handler().postDelayed( Runnable {
                findNavController().navigate(R.id.action_splashFragment_to_fragmentSelectUser)
            }, 2000L)
        })
    }

    override fun finishInit() {}
}