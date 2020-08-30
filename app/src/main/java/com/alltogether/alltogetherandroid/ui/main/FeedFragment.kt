package com.alltogether.alltogetherandroid.ui.main

import android.util.Log
import android.view.View
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

    override fun viewInit() {

    }

    override fun dataInit() {
        viewModel.onSearchFinished.observe(viewLifecycleOwner, Observer {
            adapter = FeedSupporterAdapter(viewModel.supporterList!!, itemClick)
            feed_supporter_list.adapter = adapter
            Log.e("LOG", viewModel.supporterList!!.toString())
        })
        mainViewModel.getChildFinished.observe(viewLifecycleOwner, Observer {
            viewModel.currentSupporter(mainViewModel.childInfo!!.childId)
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