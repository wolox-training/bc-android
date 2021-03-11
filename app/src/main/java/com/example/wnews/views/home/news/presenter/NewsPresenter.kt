package com.example.wnews.views.home.news.presenter

import com.example.wnews.models.LikeResponse
import com.example.wnews.models.ListNewsResponse
import com.example.wnews.models.News
import com.example.wnews.models.UserAuth
import com.example.wnews.providers.RetrofitProvider
import com.example.wnews.views.home.news.NewsView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsPresenter(val newsView: NewsView) {

    var arrayListNews = arrayListOf<News>()
        private set

    var loading = false
        private set

    fun onResponseLike(comment: Int, userAuth: UserAuth) {
        val call: Call<LikeResponse> = RetrofitProvider.newsService.putLike(
            comment,
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

                    val items = response.body()



                } else if (response.errorBody() != null) {

                }

                loading = false

                newsView.changeData()

                newsView.showProgressBar(loading)

            }

            override fun onFailure(call: Call<LikeResponse?>?, t: Throwable?) {

            }


        })
    }

    fun clearArrayList() {
        arrayListNews = arrayListOf()
    }

    fun onResponseNews(page: Int, userAuth: UserAuth) {
        loading = true
        newsView.showProgressBar(loading)


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

                }

                loading = false

                newsView.changeData()

                newsView.showProgressBar(loading)

            }

            override fun onFailure(call: Call<ListNewsResponse?>?, t: Throwable?) {
                loading = false

            }


        })
    }
}