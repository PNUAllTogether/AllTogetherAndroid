package com.alltogether.alltogetherandroid.ui.main

import coil.api.load
import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_supporter_desc.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SupporterSearchDescFragment : BaseFragment<SupporterSearchViewModel>() {
    override val layoutSource: Int
        get() = R.layout.fragment_supporter_desc
    override val viewModel: SupporterSearchViewModel by sharedViewModel()

    override fun viewInit() {
        supporter_profile_image.load(viewModel.selectedSupporter?.image)
        supporter_name.text = viewModel.selectedSupporter?.name
        supporter_major.text = viewModel.selectedSupporter?.major
        supporter_school_desc.text = viewModel.selectedSupporter?.department
        supporter_age_desc.text = viewModel.selectedSupporter?.age
        supporter_gender_desc.text = viewModel.selectedSupporter?.gender
        supporter_child_desc.text = viewModel.selectedSupporter?.experience
        supporter_time_desc.text = viewModel.selectedSupporter?.officeTime
        supporter_intro_edit.setText(viewModel.selectedSupporter?.introduce)
    }

    override fun dataInit() {

    }

    override fun finishInit() {

    }
}