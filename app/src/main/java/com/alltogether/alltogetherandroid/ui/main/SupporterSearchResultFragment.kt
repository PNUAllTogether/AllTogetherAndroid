package com.alltogether.alltogetherandroid.ui.main

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_supporter_search_result.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SupporterSearchResultFragment : BaseFragment<SupporterSearchViewModel>(){
    override val layoutSource: Int
        get() = R.layout.fragment_supporter_search_result
    override val viewModel: SupporterSearchViewModel by sharedViewModel()

    private lateinit var adapter: SupporterSearchResultAdapter

    override fun viewInit() {
        adapter = SupporterSearchResultAdapter(viewModel.supporterList!!, itemClick)
        supporter_search_result_list.adapter = adapter
        supporter_search_result_list.layoutManager = GridLayoutManager(requireContext(), 2)
        supporter_search_desc_num.text = viewModel.supporterList!!.size.toString()
    }

    override fun dataInit() {

    }

    override fun finishInit() {

    }

    private val itemClick = object : SupporterSearchResultAdapter.OnItemClickListener {
        override fun onItemClick(v: View, position: Int) {

        }
    }
}