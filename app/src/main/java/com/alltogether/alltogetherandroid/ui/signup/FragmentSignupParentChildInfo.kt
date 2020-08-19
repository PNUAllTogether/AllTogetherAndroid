package com.alltogether.alltogetherandroid.ui.signup

import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import com.alltogether.alltogetherandroid.ui.login.LoginViewModel
import com.alltogether.alltogetherandroid.utils.setErrorMessage
import com.alltogether.alltogetherandroid.utils.setKeyboardAction
import com.alltogether.alltogetherandroid.utils.setTextInputErrorAction
import com.alltogether.alltogetherandroid.utils.sharedGraphViewModel
import kotlinx.android.synthetic.main.fragment_signup_parent_child_info.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FragmentSignupParentChildInfo : BaseFragment<LoginViewModel>() {
    override val layoutSource: Int
        get() = R.layout.fragment_signup_parent_child_info
    override val viewModel: LoginViewModel by sharedGraphViewModel(R.id.nav_graph)

    override fun viewInit() {
        val childTypeAdapter: ArrayAdapter<*> = ArrayAdapter(requireContext(),
            R.layout.spinner_input_child_type,
            resources.getStringArray(R.array.child_type_list))
        childTypeAdapter.setDropDownViewResource(R.layout.spinner_layout_child_type)
        child_type_input_spinner.adapter = childTypeAdapter
        child_type_input_spinner.setSelection(1)

        setTextInputErrorAction(listOf(
            Pair(child_name_input_edit, child_name_input_selector),
            Pair(child_age_input_edit, child_age_input_selector),
            Pair(child_target_input_edit, child_target_input_selector),
            Pair(child_character_input_edit, child_character_input_selector)))
        setKeyboardAction(listOf(child_name_input_edit, child_age_input_edit, child_target_input_edit, child_character_input_edit))
    }

    override fun dataInit() {
        viewModel.onChildNameError.observe(viewLifecycleOwner, Observer {
            child_name_input_selector.setErrorMessage(it)
            child_name_input_edit.text?.clear()
        })
        viewModel.onChildAgeError.observe(viewLifecycleOwner, Observer {
            child_age_input_selector.setErrorMessage(it)
            child_age_input_edit.text?.clear()
        })
        viewModel.onChildGoalError.observe(viewLifecycleOwner, Observer {
            child_target_input_selector.setErrorMessage(it)
            child_target_input_edit.text?.clear()
        })
        viewModel.onChildCharacterError.observe(viewLifecycleOwner, Observer {
            child_character_input_selector.setErrorMessage(it)
            child_character_input_edit.text?.clear()
        })
        viewModel.isPostFinished.observe(viewLifecycleOwner, Observer {
            Log.e("LOG", "post parent info finished")
        })
    }

    override fun finishInit() {
        signup_parent_child_info_finish_button.setOnClickListener {
            viewModel.checkValidParentInfo(child_name_input_edit.text?.toString(), child_age_input_edit.text?.toString(),
                child_type_input_spinner.selectedItem.toString(),
                child_target_input_edit.text?.toString(), child_character_input_edit.text?.toString())
        }
    }
}