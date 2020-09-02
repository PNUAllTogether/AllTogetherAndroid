package com.alltogether.alltogetherandroid.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.alltogether.alltogetherandroid.ActivityViewModel
import com.alltogether.alltogetherandroid.R
import com.alltogether.alltogetherandroid.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment: BaseFragment<MainViewModel>() {
    override val layoutSource: Int
        get() = R.layout.fragment_main
    override val viewModel: MainViewModel by sharedViewModel()
    private val activityViewModel : ActivityViewModel by sharedViewModel()

    private var viewPager: ViewPager2? = null
    private var viewPagerAdapter: MainAdapter? = null

    private val ic_list = listOf(R.drawable.ic_feed_disabled, R.drawable.ic_sup_disabled, R.drawable.ic_community_disabled, R.drawable.ic_alarm_disabled, R.drawable.ic_gift_disabled)
    private val ic_list_selected = listOf(R.drawable.ic_feed_enabled, R.drawable.ic_sup_enabled, R.drawable.ic_community_enabled, R.drawable.ic_alarm_enabled, R.drawable.ic_gift_enabled)

    override fun viewInit() {
        viewModel.getChildInfo(activityViewModel.userID)
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
            isUserInputEnabled = false
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

    override fun dataInit() {
        viewModel.moveTo.observe(viewLifecycleOwner, Observer {
            viewPager!!.setCurrentItem(it, true)
        })
        viewModel.noChildWait.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_mainFragment_to_waitFragment)
        })
    }

    override fun finishInit() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("LOG", "onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e("LOG", "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        Log.e("LOG", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("LOG", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("LOG", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("LOG", "onDestroyVIew")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("LOG", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("LOG", "onDetach")
    }
}