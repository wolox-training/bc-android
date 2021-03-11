package com.example.wnews.views.home.news

import com.example.wnews.models.News

interface NewsView {

    fun onNewPageReceived()

    fun showProgressBar(isLoading: Boolean)

    fun openDetail(news: News)
}