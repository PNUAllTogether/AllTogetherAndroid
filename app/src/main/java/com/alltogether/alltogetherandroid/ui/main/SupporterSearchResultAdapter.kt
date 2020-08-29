package com.alltogether.alltogetherandroid.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.dto.filterBody
import com.alltogether.alltogetherandroid.dto.supporterSearchResult
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.supporter_search_result_item.*

class SupporterSearchResultAdapter(private val supporterList: ArrayList<filterBody>,
                                   private val listener: OnItemClickListener): RecyclerView.Adapter<SupporterSearchResultAdapter.ViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(v: View, position: Int)
    }

    class ViewHolder(val view: View):RecyclerView.ViewHolder(view), LayoutContainer {
        override val containerView: View?
            get() = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.supporter_search_result_item, parent, false))
    }

    override fun getItemCount(): Int {
        return supporterList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.e("LOG", "onBingViewHolder of position $position is called!!")
        supporterList[position].let { item ->
            if(item.image != null) {
                holder.supporter_profile_image.load(item.image)
            }
            holder.supporter_name.text = item.name
            holder.supporter_major.text = item.major
            holder.view.setOnClickListener {
                listener.onItemClick(holder.itemView, position)
            }
        }

    }


}