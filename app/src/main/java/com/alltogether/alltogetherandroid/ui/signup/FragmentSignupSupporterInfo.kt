package com.alltogether.alltogetherandroid.ui.signup

import android.widget.ArrayAdapter
import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import com.alltogether.alltogetherandroid.ui.login.LoginViewModel
import com.alltogether.alltogetherandroid.utils.setTextInputErrorAction
import com.alltogether.alltogetherandroid.utils.sharedGraphViewModel
import kotlinx.android.synthetic.main.fragment_signup_supporter_info.*

class FragmentSignupSupporterInfo: BaseFragment<LoginViewModel>() {
    override val layoutSource: Int
        get() = R.layout.fragment_signup_supporter_info

    override val viewModel: LoginViewModel by sharedGraphViewModel(R.id.nav_graph)

    override fun viewInit() {
        val supporterGenderAdapter: ArrayAdapter<*> = ArrayAdapter(requireContext(),
            R.layout.spinner_input_child_type,
            resources.getStringArray(R.array.supporter_gender)
        )
        val supporterChildAdapter: ArrayAdapter<*> = ArrayAdapter(requireContext(),
            R.layout.spinner_input_child_type,
            resources.getStringArray(R.array.child_type_list)
        )
        supporterGenderAdapter.setDropDownViewResource(R.layout.spinner_input_child_type)
        supporterChildAdapter.setDropDownViewResource(R.layout.spinner_input_child_type)
        supporter_gender_input_spinner.adapter = supporterGenderAdapter
        supporter_gender_input_spinner.setSelection(1)
        supporter_wish_input_spinner.adapter = supporterChildAdapter
        supporter_wish_input_spinner.setSelection(1)

        setTextInputErrorAction(listOf(
            Pair(supporter_name_input_edit, supporter_name_input_selector),
            Pair(supporter_age_input_edit, supporter_age_input_selector),
            Pair(supporter_major_input_edit, supporter_major_input_selector),
            Pair(supporter_character_input_edit, supporter_character_input_selector),
            Pair(supporter_cost_input_edit, supporter_cost_input_selector)
        ))
    }

    override fun dataInit() {}

    override fun finishInit() {}
}