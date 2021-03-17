package com.example.wnews.views.home.news.detail

import com.example.wnews.models.News
import com.example.wnews.views.home.news.NewsView

interface NewsDetailView : NewsView {

    override fun onNewPageReceived() {}

    override fun showProgressBar(isLoading: Boolean) {}

    override fun openDetail(news: News) {}

    override fun onClickLike(newsId: Int) {}

    override fun initAdapter() {}

    override fun onSuccessResponse() {}

    override fun onFailureResponse(message: String)

}