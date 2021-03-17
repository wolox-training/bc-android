package com.example.wnews.views.home.news

import com.example.wnews.models.LikeResponse
import com.example.wnews.models.ListNewsResponse
import com.example.wnews.models.News
import com.example.wnews.models.UserAuth
import com.example.wnews.providers.RetrofitProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NewsProvider {


    var arrayListNews = arrayListOf<News>()

    var isLoading = false

    lateinit var userAuthProvider: UserAuth

    lateinit var newsViewProvider: NewsView

    fun initNewsProvider(userAuth: UserAuth, newsView: NewsView) {
        userAuthProvider = userAuth
        newsViewProvider = newsView
    }

    fun onResponseUpdateNewsLike(newsId: Int, newsView: NewsView) {

        val call: Call<LikeResponse> = RetrofitProvider.newsService.putLike(
            newsId,
            userAuthProvider.token,
            userAuthProvider.client,
            userAuthProvider.uid
        )
        call.enqueue(object : Callback<LikeResponse?> {

            override fun onResponse(
                call: Call<LikeResponse?>?,
                response: Response<LikeResponse?>?
            ) {

                if (response!!.isSuccessful) {

                    newsView.onSuccessResponse()

                } else if (response.errorBody() != null) {
                    newsView.onFailureResponse(response.message())
                }


            }

            override fun onFailure(call: Call<LikeResponse?>?, t: Throwable?) {
                newsView.onFailureResponse("")
            }

        })
    }

    fun clearArrayList() {
        arrayListNews = arrayListOf()
    }

    fun onResponseNews(page: Int) {
        isLoading = true

        if (page != 1) {
            newsViewProvider.showProgressBar(isLoading)
        }

        val call: Call<ListNewsResponse> = RetrofitProvider.newsService.getNews(
            page,
            userAuthProvider.token,
            userAuthProvider.client,
            userAuthProvider.uid
        )
        call.enqueue(object : Callback<ListNewsResponse?> {

            override fun onResponse(
                call: Call<ListNewsResponse?>?,
                response: Response<ListNewsResponse?>?
            ) {

                if (response!!.isSuccessful) {

                    if (page == 1) {
                        clearArrayList()
                    }

                    val items = response.body()


                    if (!items!!.page.isNullOrEmpty()) {
                        items.page.forEach { item ->

                            arrayListNews.add(
                                News(
                                    item.newsId,
                                    item.title,
                                    item.detail,
                                    getRandomImage(),
                                    item.like
                                )
                            )
                        }
                    }

                    newsViewProvider.onSuccessResponse()

                } else if (response.errorBody() != null) {
                    newsViewProvider.onFailureResponse(response.message())
                }

                if (page == 1) {
                    newsViewProvider.initAdapter()
                }

                newsViewProvider.onNewPageReceived()
                isLoading = false
                newsViewProvider.showProgressBar(isLoading)

            }

            override fun onFailure(call: Call<ListNewsResponse?>?, t: Throwable?) {
                newsViewProvider.onFailureResponse("")
                isLoading = false
                newsViewProvider.showProgressBar(isLoading)

            }

        })
    }

    fun currentUserLikesNews(likes: List<Int>): Boolean {
        likes.forEach { like ->
            if (like == userAuthProvider.userAuthId) {
                return true
            }
        }
        return false
    }

    private fun getRandomImage(): String {
        val imageRandomArray = listOf(
            "https://media.npr.org/assets/img/2017/09/12/securedrop_wide-472d6f4eac0cacba984ecddd185af7ba4e07ba35-s700-c85.png",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTw8APMOp0Dgq-u625yVEI3ajShQO0zBjI2Dg&usqp=CAU",
            "https://images.pexels.com/photos/6335/man-coffee-cup-pen.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500",
            "https://dmn-dallas-news-prod.cdn.arcpublishing.com/resizer/7x0fbdr8RUPt1naXJLWmvcxp3JE=/740x555/smart/filters:no_upscale()/cloudfront-us-east-1.images.arcpublishing.com/dmn/BLWAWRUVVZHIRACQREWYSGZXME.jpg",
            "https://www.knoxnews.com/tangstatic/sites/pkns/og-image-q1a2z3a19173ca.png",
            "https://m.media-amazon.com/images/M/MV5BNTM4ZmJjOWQtZTI5OS00YzUxLTk0ODQtNGYxMWY5YzE2ZDEzXkEyXkFqcGdeQXZ3ZXNsZXk@._V1_UX477_CR0,0,477,268_AL_.jpg"
        )

        return imageRandomArray.shuffled().take(1)[0]
    }
}