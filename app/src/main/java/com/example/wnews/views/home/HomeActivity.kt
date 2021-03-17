package com.example.wnews.views.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wnews.R
import com.example.wnews.databinding.ActivityHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity(R.layout.activity_home) {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        initializeTabAdapter()
    }

    private fun initializeTabAdapter() {
        val numberOfTabs = 2

        binding.tabLayoutHome.tabMode = TabLayout.MODE_FIXED

        val adapter = HomePagerAdapter(supportFragmentManager, lifecycle, numberOfTabs)
        binding.viewPagerHome.adapter = adapter

        TabLayoutMediator(binding.tabLayoutHome, binding.viewPagerHome) { tab, position ->
            when (position) {

                0 -> {
                    tab.text = getString(R.string.home_news)
                    tab.setIcon(R.drawable.ic_news_list_off)
                }

                1 -> {
                    tab.text = getString(R.string.home_profile)
                    tab.setIcon(R.drawable.ic_profile_off)
                }

            }
        }.attach()


        setContentView(binding.root)

    }


}