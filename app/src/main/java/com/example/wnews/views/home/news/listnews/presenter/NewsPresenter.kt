package com.example.wnews.views.home.news.listnews.presenter

import com.example.wnews.models.UserAuth
import com.example.wnews.views.home.news.NewsProvider
import com.example.wnews.views.home.news.NewsView

class NewsPresenter {

    val arrayListNews = NewsProvider.arrayListNews

    val isLoading = NewsProvider.isLoading

    fun onResponseNews(page: Int) {
        NewsProvider.onResponseNews(page)
    }

    fun onResponseUpdateNewsLike(newsId: Int, newsView: NewsView) {
        NewsProvider.onResponseUpdateNewsLike(newsId, newsView)
    }

    fun initProvider(userAuth: UserAuth, newsView: NewsView) {
        NewsProvider.initNewsProvider(userAuth, newsView)
    }

    fun currentUserLikesNews(likes: List<Int>) =
        NewsProvider.currentUserLikesNews(likes)
}