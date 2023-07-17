package com.monthlycoding.dmc2.recommendation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class RecommendationPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PlayFragment()
            1 -> FoodAroundFragment()
            2 -> SchoolTipFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}