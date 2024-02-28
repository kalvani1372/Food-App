package com.devamirali.foodapp.data.adapter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(private val list: List<Fragment>, private val aA: FragmentActivity) : FragmentStateAdapter(aA) {
    private val pagerList =list
    override fun getItemCount(): Int {
        return pagerList.size
    }

    override fun createFragment(position: Int): Fragment {
        return pagerList[position]
    }
}