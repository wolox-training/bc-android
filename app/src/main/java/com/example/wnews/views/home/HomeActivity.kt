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

        val numberOfTabs = 2

        // Show all Tabs in screen
        binding.tabLayoutHome.tabMode = TabLayout.MODE_FIXED

        // Set Tab icons next to the text, instead of above the text
        //binding.tabLayoutHome.isInlineLabel = true

        // Set the ViewPager Adapter
        val adapter = HomePagerAdapter(supportFragmentManager, lifecycle, numberOfTabs)
        binding.viewPagerHome.adapter = adapter

        // Enable Swipe
        //tabs_viewpager.isUserInputEnabled = true

        // Link the TabLayout and the ViewPager2 together and Set Text & Icons
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
            // Change color of the icons
            /*tab.icon?.colorFilter =
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    Color.WHITE,
                    BlendModeCompat.SRC_ATOP
                )*/
        }.attach()


        //setCustomTabTitles()
        setContentView(binding.root)

    }

    /*private fun setCustomTabTitles() {
        val vg = tab_layout.getChildAt(0) as ViewGroup
        val tabsCount = vg.childCount

        for (j in 0 until tabsCount) {
            val vgTab = vg.getChildAt(j) as ViewGroup

            val tabChildCount = vgTab.childCount

            for (i in 0 until tabChildCount) {
                val tabViewChild = vgTab.getChildAt(i)
                if (tabViewChild is TextView) {

                    // Change Font and Size
                    tabViewChild.typeface = Typeface.DEFAULT_BOLD
//                    val font = ResourcesCompat.getFont(this, R.font.myFont)
//                    tabViewChild.typeface = font
//                    tabViewChild.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25f)
                }
            }
        }
    }*/
}