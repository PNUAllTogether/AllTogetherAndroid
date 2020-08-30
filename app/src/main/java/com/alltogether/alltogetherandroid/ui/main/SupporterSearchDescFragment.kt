package com.alltogether.alltogetherandroid.ui.main

import android.widget.Toast
import androidx.lifecycle.Observer
import coil.api.load
import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import com.google.android.material.transition.MaterialContainerTransform
import kotlinx.android.synthetic.main.fragment_supporter_desc.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SupporterSearchDescFragment : BaseFragment<SupporterSearchViewModel>() {
    override val layoutSource: Int
        get() = R.layout.fragment_supporter_desc
    override val viewModel: SupporterSearchViewModel by sharedViewModel()
    private val mainViewModel: MainViewModel by sharedViewModel()

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

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 500L
            isElevationShadowEnabled = true
            setAllContainerColors(requireContext().getColor(R.color.white))
        }
    }

    override fun dataInit() {
        supporter_container.transitionName = viewModel.selectedSupporter?.id.toString()
        viewModel.onAddSuccess.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), "전문가 추가 성공!", Toast.LENGTH_SHORT).show()
        })
        viewModel.onAddFailed.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), "전문가 추가 실패\n네트워크 환경을 확인하세요.", Toast.LENGTH_SHORT).show()
        })
    }

    override fun finishInit() {
        supporter_add_button.setOnClickListener {
            viewModel.addSupporter(mainViewModel.childInfo!!.childId, viewModel.selectedSupporter?.id!!)
        }
    }
}