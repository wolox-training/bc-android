package com.example.wnews.login

import com.example.wnews.models.News
import com.example.wnews.models.UserAuth
import com.example.wnews.views.home.news.NewsView
import com.example.wnews.views.home.news.listnews.presenter.NewsPresenter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TestNewsPresenter {
    lateinit var userAuth: UserAuth
    lateinit var newsView: NewsView

    @Test
    fun testOnResponseNews() {
        NewsPresenter().onResponseNews(1)
        val mockedArrayListNews = mock(NewsPresenter().arrayListNews::class.java)

        println(mockedArrayListNews.size)
    }

    @Test
    fun testOnResponseUpdateNewsLike() {
        NewsPresenter().onResponseUpdateNewsLike(0, newsView)
    }

    @Test
    fun testCurrentUserLikeNews() {

        val mockedListLike = mock(News::class.java)
        mockedListLike.like.plus(3)
        println(NewsPresenter().currentUserLikesNews(mockedListLike.like))

    }

    @Before
    fun initPresenter() {
        this.newsView = mock(NewsView::class.java)
        userAuth = UserAuth(
            7,
            "8yxXivSvj-YD9Vhplv2L3g",
            "bdn9s2kK6ZV4Q7xcEtq8Rg",
            "clinton.harris59@example.com"
        )
        NewsPresenter().initProvider(userAuth, newsView)
    }
}