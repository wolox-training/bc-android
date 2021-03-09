package com.example.wnews.views.home.news.presenter

import android.util.Log
import android.widget.Adapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.wnews.models.ListNews
import com.example.wnews.models.News
import com.example.wnews.models.UserAuth
import com.example.wnews.providers.RetrofitProvider
import com.example.wnews.views.home.news.NewsView
import com.example.wnews.views.home.news.adapter.NewsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsPresenter(val newsView: NewsView){

    var arrayListNews = arrayListOf<News>()
        private set

    var loading = false
        private set

    fun saveAsFavorite(){}

    fun clearArrayList(){
        arrayListNews = arrayListOf()
    }

    fun onResponseNews(page:Int,userAuth:UserAuth){
        loading = true
        newsView.showProgressBar(loading)


        val call: Call<ListNews> = RetrofitProvider.newsService.getNews(page,userAuth.token,userAuth.client,userAuth.uid)
        call.enqueue(object : Callback<ListNews?> {

            override fun onResponse(
                    call: Call<ListNews?>?,
                    response: Response<ListNews?>?
            ) {

                if (response!!.isSuccessful) {

                    val items = response.body()


                    if(!items!!.page.isNullOrEmpty()){
                        items.page.forEach{ item ->

                            arrayListNews.add(
                                    News(
                                            item.newsId,
                                            item.title,
                                            item.detail,
                                            item.imageUrl
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

            override fun onFailure(call: Call<ListNews?>?, t: Throwable?) {
                loading = false

            }


        })
    }
}