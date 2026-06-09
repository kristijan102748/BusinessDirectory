package com.example.businessdirectory.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.businessdirectory.CompanyListFragment   // Важно!

class ViewPagerAdapter(
    activity: FragmentActivity,
    private val categories: List<String>
) : FragmentStateAdapter(activity) {

    private val fragments = mutableListOf<CompanyListFragment>()

    override fun getItemCount(): Int = categories.size

    override fun createFragment(position: Int): Fragment {
        val fragment = CompanyListFragment.newInstance(categories[position])
        fragments.add(fragment)
        return fragment
    }

    fun getFragment(position: Int): CompanyListFragment? {
        return if (position < fragments.size) fragments[position] else null
    }
}