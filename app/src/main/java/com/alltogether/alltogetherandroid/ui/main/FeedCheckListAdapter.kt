package com.alltogether.alltogetherandroid.ui.main

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.dto.Check
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.feed_check_item.*

class FeedCheckListAdapter(
    private val checkList: ArrayList<Check>,
    private val viewModel: FeedViewModel
) : RecyclerView.Adapter<FeedCheckListAdapter.ViewHolder>() {
    class ViewHolder(val view: View):RecyclerView.ViewHolder(view), LayoutContainer {
        override val containerView: View?
            get() = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.feed_check_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return checkList.size
    }

    override fun onBindViewHolder(holder: FeedCheckListAdapter.ViewHolder, position: Int) {
        Log.e("LOG", "onBingViewHolder of position $position is called!!")
        checkList[position].let { item ->
            holder.check_item_text.text = item.content
            holder.check_item_radio.let {
                it.isChecked = item.isDone
                if(it.isChecked) {
                    holder.check_item_text.paintFlags =
                        holder.check_item_text.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                }
                it.setOnCheckedChangeListener { compoundButton, b ->
                    if(b) {
                        Log.e("position $position", "true")
                        holder.check_item_text.paintFlags =
                            holder.check_item_text.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                        viewModel.addDone(item.itemId, true)
                    }
                    else {
                        Log.e("position $position", "false")
                        holder.check_item_text.paintFlags = 0
                        viewModel.addDone(item.itemId, false)
                    }
                }
            }
            holder.check_item_delete.setOnClickListener {
                viewModel.delete(item.itemId)
            }
        }
    }
}