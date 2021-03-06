package com.alltogether.alltogetherandroid.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.dto.filterBody
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.feed_supporter_item.*

class FeedSupporterAdapter(private val supporterList: ArrayList<filterBody>,
                           private val listener: OnItemClickListener
) : RecyclerView.Adapter<FeedSupporterAdapter.ViewHolder>(){
    interface OnItemClickListener {
        fun onItemClick(v: View, position: Int)
    }

    class ViewHolder(val view: View):RecyclerView.ViewHolder(view), LayoutContainer {
        override val containerView: View?
            get() = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedSupporterAdapter.ViewHolder {
        return FeedSupporterAdapter.ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.feed_supporter_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return supporterList.size
    }

    override fun onBindViewHolder(holder: FeedSupporterAdapter.ViewHolder, position: Int) {
        Log.e("LOG", "onBingViewHolder of position $position is called!!")
        supporterList[position].let { item ->
            if(item.image != null) {
                holder.feed_supporter_item_image.load(item.image)
            }
            holder.view.setOnClickListener {
                listener.onItemClick(holder.itemView, position)
            }
        }
    }
}