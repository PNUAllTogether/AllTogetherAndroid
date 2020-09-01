package com.alltogether.alltogetherandroid.ui.main

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import com.alltogether.alltogetherandroid.utils.setErrorMessage
import kotlinx.android.synthetic.main.fragment_child_info.*
import kotlinx.android.synthetic.main.fragment_feed.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class FeedFragment : BaseFragment<FeedViewModel>(){
    override val layoutSource: Int
        get() = R.layout.fragment_feed
    override val viewModel: FeedViewModel by viewModel()
    private val mainViewModel: MainViewModel by sharedViewModel()

    private lateinit var adapter: FeedSupporterAdapter
    private lateinit var check_adapter: FeedListAdapter

    private lateinit var childInfoDialogFragment : childInfoDialog

    override fun viewInit() {
    }

    override fun dataInit() {
        viewModel.onSearchFinished.observe(viewLifecycleOwner, Observer {
            adapter = FeedSupporterAdapter(viewModel.supporterList!!, itemClick)
            feed_supporter_list.adapter = adapter
            Log.e("LOG", viewModel.supporterList!!.toString())
        })
        viewModel.onGetCheckListFinished.observe(viewLifecycleOwner, Observer {
            if(viewModel.checkList!!.size == 0) {
                feed_empty_title.visibility = View.VISIBLE
                feed_empty_selector.visibility = View.VISIBLE
                feed_empty_add_button.visibility = View.VISIBLE
            }
            else {
                feed_empty_title.visibility = View.GONE
                feed_empty_selector.visibility = View.GONE
                feed_empty_add_button.visibility = View.GONE
            }
            childInfoDialogFragment = childInfoDialog(mainViewModel)
            feed_content_swipe.isRefreshing = false
            check_adapter = FeedListAdapter(viewModel.checkList!!, viewModel)
            feed_content.adapter = check_adapter
        })
        mainViewModel.getChildFinished.observe(viewLifecycleOwner, Observer {
            viewModel.currentSupporter(mainViewModel.childInfo!!.childId)
            viewModel.getAllCheckList(mainViewModel.childInfo!!.childId)
        })
        viewModel.onDoneFinished.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), "체크", Toast.LENGTH_SHORT).show()
        })
    }

    override fun finishInit() {
        feed_content_swipe.setOnRefreshListener {
            viewModel.getAllCheckList(mainViewModel.childInfo!!.childId)
        }
        add_supporter_button.setOnClickListener {
            mainViewModel.moveToSpecificPosition(1)
        }
        feed_empty_add_button.setOnClickListener {
            val content = feed_empty_edit.text?.toString()
            if(content == null || content == "") feed_empty_selector.setErrorMessage("내용을 입력해주세요!")
            else viewModel.postCheckList(SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().time), content)
        }
        feed_fab.setOnClickListener {
            childInfoDialogFragment.show(childFragmentManager, "childInfo")
        }
    }

    private val itemClick = object : FeedSupporterAdapter.OnItemClickListener {
        override fun onItemClick(v: View, position: Int) {
        }
    }

    class childInfoDialog(val mainViewModel: MainViewModel) : DialogFragment() {

        override fun onResume() {
            super.onResume()
            val windowManager = requireActivity().getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = windowManager.defaultDisplay
            val size = Point()
            display.getRealSize(size)
            val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
            val deviceWidth = size.x
            params?.width = (deviceWidth * 0.9).toInt()
            dialog?.window?.attributes = params as WindowManager.LayoutParams
        }
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val view = inflater.inflate(R.layout.fragment_child_info, container,false)
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
            return view
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            child_name_input_edit.setText(mainViewModel.childInfo?.childName)
            child_age_input_edit.setText(mainViewModel.childInfo?.childAge.toString())
            child_type_input_edit.setText(mainViewModel.childInfo?.childType)
            child_target_input_edit.setText(mainViewModel.childInfo?.childGoal)
            child_character_input_edit.setText(mainViewModel.childInfo?.childCharacter)
        }
    }
}