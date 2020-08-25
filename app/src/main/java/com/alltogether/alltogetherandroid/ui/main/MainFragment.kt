package com.alltogether.alltogetherandroid.ui.main

import androidx.viewpager2.widget.ViewPager2
import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment: BaseFragment<MainViewModel>() {
    override val layoutSource: Int
        get() = R.layout.fragment_main
    override val viewModel: MainViewModel by viewModel()

    private var viewPager: ViewPager2? = null
    private var viewPagerAdapter: MainAdapter? = null

    private val ic_list = listOf(R.drawable.ic_feed_disabled, R.drawable.ic_sup_disabled, R.drawable.ic_community_disabled, R.drawable.ic_alarm_disabled, R.drawable.ic_gift_disabled)
    private val ic_list_selected = listOf(R.drawable.ic_feed_enabled, R.drawable.ic_sup_enabled, R.drawable.ic_community_enabled, R.drawable.ic_alarm_enabled, R.drawable.ic_gift_enabled)

    override fun viewInit() {
        fragment_main_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab!!.setIcon(ic_list_selected[tab.position])
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab!!.setIcon(ic_list[tab.position])
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        viewPagerAdapter = MainAdapter(requireActivity())
        viewPager = fragment_main_view_pager.apply {
            offscreenPageLimit = 3
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = viewPagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int){
                    //DO NOTHING
                }
            })
        }
        TabLayoutMediator(fragment_main_tab_layout, viewPager!!, true) { tab, position ->
            tab.setIcon(ic_list[position])
        }.attach()
    }

    override fun dataInit() {}

    override fun finishInit() {}
}