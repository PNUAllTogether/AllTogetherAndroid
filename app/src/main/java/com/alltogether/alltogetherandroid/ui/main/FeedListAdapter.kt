package com.alltogether.alltogetherandroid.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.dto.list
import com.alltogether.alltogetherandroid.utils.setErrorMessage
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.feed_item.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FeedListAdapter(private val list: ArrayList<list>, private val viewModel: FeedViewModel) : RecyclerView.Adapter<FeedListAdapter.ViewHolder>(){
    class ViewHolder(val view: View):RecyclerView.ViewHolder(view), LayoutContainer {
        override val containerView: View?
            get() = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.feed_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FeedListAdapter.ViewHolder, position: Int) {
        Log.e("LOG", "onBingViewHolder of position $position is called!!")
        list[position].let { item ->
            val checklist = ArrayList(item.check_list)
            holder.feed_item_check_list.adapter = FeedCheckListAdapter(checklist)
            holder.feed_date.text = SimpleDateFormat("MM월 dd일").format(SimpleDateFormat("yyyy-MM-dd").parse(item.date))
            holder.feed_item_add.setOnClickListener {
                holder.feed_item_add_selector.visibility = View.VISIBLE
                holder.feed_item_add_button.visibility = View.VISIBLE
            }
            holder.feed_item_add_button.setOnClickListener {
                val content = holder.feed_item_add_edit.text?.toString()
                if(content == null || content == "") holder.feed_item_add_selector.setErrorMessage("내용을 입력해주세요!")
                else viewModel.postCheckList(SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().time), content)
            }
        }
    }
}