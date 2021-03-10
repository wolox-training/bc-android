package com.example.wnews.views.home.news

import com.example.wnews.models.News

interface NewsView {

    fun changeData()

    fun showProgressBar(isLoading: Boolean)

    fun openDetail(news: News)
}