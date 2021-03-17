package com.example.wnews.views.home.news.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.wnews.R

class DetailActivity : AppCompatActivity(R.layout.activity_detail) {

    var newsId: Int = 0
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsId = intent.getIntExtra(INTENT_EXTRA_ID_NEWS, 0)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add<DetailFragment>(R.id.fragment_container_detail_view)
            }
        }
    }

    companion object {
        const val INTENT_EXTRA_ID_NEWS = "INTENT_EXTRA_NEWS"
    }

}