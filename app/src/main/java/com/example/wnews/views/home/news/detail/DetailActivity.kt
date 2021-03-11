package com.example.wnews.views.home.news.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*
import com.example.wnews.R
import com.example.wnews.models.News
import com.google.gson.Gson

class DetailActivity : AppCompatActivity(R.layout.activity_detail) {

    lateinit var newsObject: News
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsObject = Gson().fromJson(intent.getStringExtra("newsObject")!!, News::class.java)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add<DetailFragment>(R.id.fragment_container_detail_view)
            }
        }
    }

}