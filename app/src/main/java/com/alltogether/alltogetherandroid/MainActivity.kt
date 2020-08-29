package com.alltogether.alltogetherandroid

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.alltogether.alltogetherandroid.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityViewModel>() {

    override val layoutSource: Int
        get() = R.layout.activity_main

    override val viewModel: ActivityViewModel by viewModels()

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun viewInit() {
        val navHost = main_fragment_container as NavHostFragment
        val navController = navHost.navController

        setSupportActionBar(activity_main_toolbar)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mainFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _: NavController, navDestination: NavDestination, _: Bundle? ->
            when (navDestination.id) {
                R.id.mainFragment -> {
                    activity_main_toolbar.title = ""
                    activity_main_toolbar.visibility = View.VISIBLE
                }
                R.id.supporterSearchResultFragment -> {
                    activity_main_toolbar.title = ""
                    activity_main_toolbar.visibility = View.VISIBLE
                }
                R.id.supporterSearchDescFragment -> {
                    activity_main_toolbar.title = ""
                    activity_main_toolbar.visibility = View.VISIBLE
                }
                else -> {
                    activity_main_toolbar.visibility = View.GONE
                }
            }
        }
    }

    override fun dataInit() {}

    override fun finishInit() {}

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.main_fragment_container).navigateUp(appBarConfiguration)
    }

    override fun onStop() {
        super.onStop()
        Log.e("LOG", "MainActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("LOG", "MainActivity onDestroy")
    }
}