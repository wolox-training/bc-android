package com.example.wnews.views.home.news.presenter

import androidx.recyclerview.widget.RecyclerView
import com.example.wnews.models.LikeResponse
import com.example.wnews.models.ListNewsResponse
import com.example.wnews.models.News
import com.example.wnews.models.UserAuth
import com.example.wnews.providers.RetrofitProvider
import com.example.wnews.views.home.news.NewsView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NewsPresenter {

    var arrayListNews = arrayListOf<News>()

    var isLoading = false
        private set

    fun onResponseUpdateNewsLike(newsId: Int, userAuth: UserAuth, newsView: NewsView) {
        val call: Call<LikeResponse> = RetrofitProvider.newsService.putLike(
            newsId,
            userAuth.token,
            userAuth.client,
            userAuth.uid
        )
        call.enqueue(object : Callback<LikeResponse?> {

            override fun onResponse(
                call: Call<LikeResponse?>?,
                response: Response<LikeResponse?>?
            ) {

                if (response!!.isSuccessful) {

                    newsView.onSuccessResponse(response.body()!!.message, newsId)

                } else if (response.errorBody() != null) {
                    newsView.onFailureResponse(response.message())
                }

                newsView.onNewPageReceived()

            }

            override fun onFailure(call: Call<LikeResponse?>?, t: Throwable?) {
                newsView.onFailureResponse("")
            }

        })
    }

    fun clearArrayList() {
        arrayListNews = arrayListOf()
    }

    fun onResponseNews(page: Int, userAuth: UserAuth, newsView: NewsView) {
        isLoading = true

        if(page!=1) {
            newsView.showProgressBar(isLoading)
        }

        val call: Call<ListNewsResponse> = RetrofitProvider.newsService.getNews(
            page,
            userAuth.token,
            userAuth.client,
            userAuth.uid
        )
        call.enqueue(object : Callback<ListNewsResponse?> {

            override fun onResponse(
                call: Call<ListNewsResponse?>?,
                response: Response<ListNewsResponse?>?
            ) {

                if (response!!.isSuccessful) {

                    val items = response.body()


                    if (!items!!.page.isNullOrEmpty()) {
                        items.page.forEach { item ->

                            arrayListNews.add(
                                News(
                                    item.newsId,
                                    item.title,
                                    item.detail,
                                    item.imageUrl,
                                    item.like
                                )
                            )
                        }
                    }

                } else if (response.errorBody() != null) {
                    newsView.onFailureResponse(response.message())
                }

                isLoading = false
                newsView.onNewPageReceived()
                newsView.showProgressBar(isLoading)

            }

            override fun onFailure(call: Call<ListNewsResponse?>?, t: Throwable?) {
                newsView.onFailureResponse("")
                isLoading = false

            }

        })
    }
}