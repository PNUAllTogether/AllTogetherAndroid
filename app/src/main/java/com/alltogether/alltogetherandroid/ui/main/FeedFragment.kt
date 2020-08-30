package com.alltogether.alltogetherandroid.ui.main

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_feed.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedFragment : BaseFragment<FeedViewModel>(){
    override val layoutSource: Int
        get() = R.layout.fragment_feed
    override val viewModel: FeedViewModel by viewModel()
    private val mainViewModel: MainViewModel by sharedViewModel()

    private lateinit var adapter: FeedSupporterAdapter
    private lateinit var check_adapter: FeedListAdapter

    override fun viewInit() {
    }

    override fun dataInit() {
        viewModel.onSearchFinished.observe(viewLifecycleOwner, Observer {
            adapter = FeedSupporterAdapter(viewModel.supporterList!!, itemClick)
            feed_supporter_list.adapter = adapter
            Log.e("LOG", viewModel.supporterList!!.toString())
        })
        viewModel.onGetCheckListFinished.observe(viewLifecycleOwner, Observer {
            check_adapter = FeedListAdapter(viewModel.checkList!!, viewModel)
            feed_content.adapter = check_adapter
        })
        mainViewModel.getChildFinished.observe(viewLifecycleOwner, Observer {
            viewModel.currentSupporter(mainViewModel.childInfo!!.childId)
            viewModel.getAllCheckList(mainViewModel.childInfo!!.childId)
        })
        viewModel.onDoneFinished.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), "체크리스트", Toast.LENGTH_SHORT).show()
        })
    }

    override fun finishInit() {
        add_supporter_button.setOnClickListener {
            mainViewModel.moveToSpecificPosition(1)
        }
    }

    private val itemClick = object : FeedSupporterAdapter.OnItemClickListener {
        override fun onItemClick(v: View, position: Int) {
        }
    }
}