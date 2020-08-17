package com.alltogether.alltogetherandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alltogether.alltogetherandroid.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityViewModel>() {
    override val layoutSource: Int
        get() = R.layout.activity_main
    override val viewModel: ActivityViewModel by viewModel()

    override fun viewInit() {

    }

    override fun dataInit() {
    }

    override fun finishInit() {

    }
}