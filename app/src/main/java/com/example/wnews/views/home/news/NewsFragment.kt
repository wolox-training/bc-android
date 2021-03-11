package com.example.wnews.views.home.news

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wnews.R
import com.example.wnews.UserProvider
import com.example.wnews.databinding.FragmentNewsBinding
import com.example.wnews.models.News
import com.example.wnews.models.UserAuth
import com.example.wnews.views.auth.AuthPresenter
import com.example.wnews.views.home.news.adapter.NewsAdapter
import com.example.wnews.views.home.news.detail.DetailActivity
import com.example.wnews.views.home.news.presenter.NewsPresenter
import com.google.gson.Gson

class NewsFragment : Fragment(R.layout.fragment_news), NewsView {

    private var binding: FragmentNewsBinding? = null
    private var viewManager = LinearLayoutManager(context)
    lateinit var newsPresenter: NewsPresenter
    private var page = 1
    lateinit var userAuth: UserAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)

        initialisePresenter()
        initialiseAdapter()
        setListener()
    }

    private fun initialisePresenter() {
        userAuth = UserProvider.userAuth
        newsPresenter = NewsPresenter(this)
        newsPresenter.onResponseNews(page, userAuth)
    }

    private fun initialiseAdapter() {
        binding!!.recyclerViewNews.layoutManager = viewManager
        binding!!.recyclerViewNews.adapter =
            NewsAdapter(newsPresenter.arrayListNews, this, userAuth)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setListener() {

        binding!!.butttonAddNews.setOnClickListener { }

        binding!!.recyclerViewNews.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if (dy > 0) {
                    val visibleItemCount = viewManager.childCount
                    val totalItemCount = viewManager.itemCount
                    val pastVisiblesItems = viewManager.findFirstVisibleItemPosition()

                    if (!newsPresenter.loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount - 10) {
                            newsPresenter.onResponseNews(++page, userAuth)
                            binding!!.recyclerViewNews.adapter!!.notifyDataSetChanged()

                        }
                    }
                }

            }
        })

        binding!!.swipeNews.setOnRefreshListener {
            page = 1
            newsPresenter.clearArrayList()
            newsPresenter.onResponseNews(++page, userAuth)

        }

    }

    override fun changeData() {
        binding!!.recyclerViewNews.adapter!!.notifyDataSetChanged()
    }

    override fun showProgressBar(isLoading: Boolean) {

        if (isLoading) {
            binding!!.progressBarNews.visibility = View.VISIBLE
        } else {
            binding!!.progressBarNews.visibility = View.GONE
            binding!!.swipeNews.isRefreshing = false
        }

    }

    override fun openDetail(news: News) {

        val intent = Intent(context, DetailActivity::class.java)
        val gson = Gson()
        val jsonNews = gson.toJson(news)
        intent.putExtra("newsObject", jsonNews)
        startActivity(intent)

    }

}