package com.monthlycoding.dmc2.recommendation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class RecommendationPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 4 // 총 4개의 페이지가 있음
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TotalFragment() // 첫 번째 페이지
            1 -> PlayFragment() // 두 번째 페이지
            2 -> FoodAroundFragment() // 세 번째 페이지
            3 -> SchoolTipFragment() // 네 번째 페이지
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}