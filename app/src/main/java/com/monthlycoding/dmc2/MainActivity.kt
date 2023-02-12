package com.monthlycoding.dmc2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.monthlycoding.dmc2.databinding.ActivityMainBinding
import nl.joery.animatedbottombar.AnimatedBottomBar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBinding()
        initNavigationBar()


    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view_tag, fragment)
            .commit()
    }

    private fun initNavigationBar() {
        binding.bottomBar.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                when (newIndex) {
                    0 -> changeFragment(HomeFragment())
                    1 -> changeFragment(CategoryFragment())
                    2 -> changeFragment(MapFragment())
                    else -> changeFragment(SearchFragment())
                }
                Log.d("bottom_bar", "Selected index: $newIndex, title: ${newTab.title}")
            }

            // An optional method that will be fired whenever an already selected tab has been selected again.
            override fun onTabReselected(index: Int, tab: AnimatedBottomBar.Tab) {
                when (index) {
                    0 -> changeFragment(HomeFragment())
                    1 -> changeFragment(CategoryFragment())
                    2 -> changeFragment(MapFragment())
                    else -> changeFragment(SearchFragment())
                }
                Log.d("bottom_bar", "Reselected index: $index, title: ${tab.title}")
            }
        })


    }
}