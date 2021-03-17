package com.example.wnews.views.home.news

import com.example.wnews.models.News

interface NewsView {

    fun initAdapter()

    fun onNewPageReceived()

    fun showProgressBar(isLoading: Boolean)

    fun openDetail(news: News)

    fun onClickLike(newsId: Int)

    fun onResponseSuccess()

    fun onResponseFailure(message: String)

    fun onRequestFailure()

}