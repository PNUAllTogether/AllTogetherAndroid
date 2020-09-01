package com.alltogether.alltogetherandroid.ui.signup

import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import com.alltogether.alltogetherandroid.ui.login.LoginViewModel
import com.alltogether.alltogetherandroid.utils.setKeyboardAction
import com.alltogether.alltogetherandroid.utils.setTextInputErrorAction
import com.alltogether.alltogetherandroid.utils.sharedGraphViewModel
import kotlinx.android.synthetic.main.fragment_signup_parent_child_info.*
import kotlinx.android.synthetic.main.fragment_signup_supporter_info.*

class FragmentSignupSupporterInfo: BaseFragment<LoginViewModel>() {
    override val layoutSource: Int
        get() = R.layout.fragment_signup_supporter_info

    override val viewModel: LoginViewModel by sharedGraphViewModel(R.id.nav_graph)

    private var fromTime: String? = null
    private var toTime: String? = null
    private var possibleTime: String = ""

    override fun viewInit() {
        val genderTypeAdapter: ArrayAdapter<*> = ArrayAdapter(
            requireContext(), R.layout.spinner_input_child_type, resources.getStringArray(R.array.supporter_gender)
        )
        genderTypeAdapter.setDropDownViewResource(R.layout.spinner_layout_child_type)
        supporter_gender_input_spinner.adapter = genderTypeAdapter
        supporter_gender_input_spinner.setSelection(1)

        val regionTypeAdapter: ArrayAdapter<*> = ArrayAdapter(
            requireContext(), R.layout.spinner_input_child_type, resources.getStringArray(R.array.location_list)
        )
        regionTypeAdapter.setDropDownViewResource(R.layout.spinner_input_child_type)
        supporter_region_input_spinner.adapter = regionTypeAdapter
        supporter_region_input_spinner.setSelection(1)

        setTextInputErrorAction(
            listOf(
                Pair(supporter_age_input_edit, supporter_age_input_selector),
                Pair(supporter_major_input_edit, supporter_major_input_selector),
                Pair(supporter_department_input_edit, supporter_department_input_selector),
                Pair(supporter_experience_input_edit, supporter_experience_input_selector),
                Pair(supporter_speciality_input_edit, supporter_speciality_input_selector),
                Pair(supporter_introduce_input_edit, supporter_introduce_input_selector),
                Pair(supporter_cost_input_edit, supporter_cost_input_selector)
            )
        )
        setKeyboardAction(
            listOf(
                supporter_age_input_edit,
                supporter_major_input_edit,
                supporter_department_input_edit,
                supporter_experience_input_edit,
                supporter_speciality_input_edit,
                supporter_introduce_input_edit,
                supporter_cost_input_edit
            )
        )

        fromTime = "${supporter_office_hour_from_picker.hour} : ${supporter_office_hour_from_picker.minute}"
        toTime = "${supporter_office_hour_to_picker.hour} : ${supporter_office_hour_to_picker.minute}"
        possibleTime = "$fromTime ~ $toTime"
    }

    override fun dataInit() {
        viewModel.onSupporterAgeError.observe(viewLifecycleOwner, Observer {
            supporter_age_input_selector.error = it
            supporter_age_input_edit.text?.clear()
        })
        viewModel.onSupporterCostError.observe(viewLifecycleOwner, Observer {
            supporter_cost_input_selector.error = it
            supporter_cost_input_edit.text?.clear()
        })
        viewModel.onSupporterDepartmentError.observe(viewLifecycleOwner, Observer {
            supporter_department_input_selector.error = it
            supporter_department_input_edit.text?.clear()
        })
        viewModel.onSupporterExperienceError.observe(viewLifecycleOwner, Observer {
            supporter_experience_input_selector.error = it
            supporter_experience_input_edit.text?.clear()
        })
        viewModel.onSupporterIntroduceError.observe(viewLifecycleOwner, Observer {
            supporter_introduce_input_selector.error = it
            supporter_introduce_input_edit.text?.clear()
        })
        viewModel.onSupporterMajorError.observe(viewLifecycleOwner, Observer {
            supporter_major_input_selector.error = it
            supporter_major_input_edit.text?.clear()
        })
        viewModel.onSupporterSpecialityError.observe(viewLifecycleOwner, Observer {
            supporter_speciality_input_selector.error = it
            supporter_speciality_input_edit.text?.clear()
        })
        viewModel.isPostFinished.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_fragmentSignupSupporterInfo_to_mainFragment)
        })
    }

    override fun finishInit() {
        signup_supporter_info_finish_button.setOnClickListener {
            viewModel.checkValidSupporterInfo(
                age = supporter_age_input_edit.text?.toString(),
                gender = supporter_gender_input_spinner.selectedItem.toString(),
                region = supporter_region_input_spinner.selectedItem.toString(),
                major = supporter_major_input_edit.text?.toString(),
                department = supporter_department_input_edit.text?.toString(),
                experience = supporter_experience_input_edit.text?.toString(),
                time = possibleTime,
                speciality = supporter_speciality_input_edit.text?.toString(),
                introduce = supporter_introduce_input_edit.text?.toString(),
                cost = supporter_cost_input_edit.text?.toString()
            )
        }
    }
}