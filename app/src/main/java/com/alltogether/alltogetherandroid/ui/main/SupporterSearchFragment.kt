package com.alltogether.alltogetherandroid.ui.main

import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_supporter_search.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SupporterSearchFragment : BaseFragment<SupporterSearchViewModel>(){
    override val layoutSource: Int
        get() =  R.layout.fragment_supporter_search
    override val viewModel: SupporterSearchViewModel by sharedViewModel()

    override fun viewInit() {
        val locationAdapter: ArrayAdapter<*> = ArrayAdapter(requireContext(),
            R.layout.spinner_input_layout_location,
            resources.getStringArray(R.array.location_list))
        locationAdapter.setDropDownViewResource(R.layout.spinner_layout_child_type)
        supporter_search_region_spinner.adapter = locationAdapter
        supporter_search_region_spinner.setSelection(0)
    }

    override fun dataInit() {
        viewModel.onSearchFinished.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_mainFragment_to_supporterSearchResultFragment)
        })
    }

    override fun finishInit() {
        supporter_search_button.setOnClickListener {
            viewModel.searchSupporter(supporter_search_region_spinner.selectedItem.toString(), supporter_search_major_edit.text!!.toString())
        }
    }
}