package com.example.wnews.views.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wnews.R
import com.example.wnews.databinding.ActivityHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity(R.layout.activity_home) {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        inicialiteTabAdapter()
    }

    private fun inicialiteTabAdapter() {
        val numberOfTabs = 2

        binding.tabLayoutHome.tabMode = TabLayout.MODE_FIXED

        val adapter = HomePagerAdapter(supportFragmentManager, lifecycle, numberOfTabs)
        binding.viewPagerHome.adapter = adapter

        TabLayoutMediator(binding.tabLayoutHome, binding.viewPagerHome) { tab, position ->
            when (position) {

                0 -> {
                    tab.text = getString(R.string.home_news)
                    tab.setIcon(R.drawable.ic_baseline_view_list_24)
                }

                1 -> {
                    tab.text = getString(R.string.home_profile)
                    tab.setIcon(R.drawable.ic_baseline_person_24)
                }

            }
        }.attach()


        setContentView(binding.root)

    }


}