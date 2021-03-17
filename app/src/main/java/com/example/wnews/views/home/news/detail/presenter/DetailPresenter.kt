package com.example.wnews.views.home.news.detail.presenter

import com.example.wnews.views.home.news.NewsProvider
import com.example.wnews.views.home.news.NewsView

class DetailPresenter {

    var arrayListNews = NewsProvider.arrayListNews

    fun onResponseUpdateNewsLike(newsId: Int, newsView: NewsView) {
        NewsProvider.onResponseUpdateNewsLike(newsId, newsView)
    }

    fun currentUserLikesNews(likes: List<Int>) =
        NewsProvider.currentUserLikesNews(likes)
}