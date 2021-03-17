package com.example.wnews.views.home.news.listnews

import com.example.wnews.models.News
import com.example.wnews.views.home.news.NewsView

interface NewsListView : NewsView {

    override fun onNewPageReceived()

    override fun showProgressBar(isLoading: Boolean)

    override fun openDetail(news: News)

    override fun onClickLike(newsId: Int)

    override fun onResponseSuccess()

    override fun onResponseFailure(message: String)

    override fun onRequestFailure()

    override fun initAdapter()
}