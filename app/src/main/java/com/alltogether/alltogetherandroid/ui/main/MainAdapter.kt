package com.alltogether.alltogetherandroid.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alltogether.alltogetherandroid.R

class MainAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> FeedFragment()
            1-> SupporterSearchFragment()
            2-> CommunityFragment()
            3-> AlarmFragment()
            4-> GiftFragment()
            else-> FeedFragment()
        }
    }
}