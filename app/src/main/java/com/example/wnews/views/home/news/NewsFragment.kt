package com.example.wnews.views.home.news

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wnews.R
import com.example.wnews.UserProvider
import com.example.wnews.databinding.FragmentNewsBinding
import com.example.wnews.models.News
import com.example.wnews.models.UserAuth
import com.example.wnews.views.home.news.adapter.NewsAdapter
import com.example.wnews.views.home.news.detail.DetailActivity
import com.example.wnews.views.home.news.presenter.NewsPresenter
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

class NewsFragment : Fragment(R.layout.fragment_news), NewsView {

    private var binding: FragmentNewsBinding? = null
    private var viewManager = LinearLayoutManager(context)
    private var page = 1
    lateinit var userAuth: UserAuth
    lateinit var newView: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)

        newView = view
        initializePresenter()
        initializeAdapter()
        setListener()
    }

    private fun initializePresenter() {
        userAuth = UserProvider.userAuth
        NewsPresenter.onResponseNews(page, userAuth, this)
    }

    private fun initializeAdapter() {

        binding!!.recyclerViewNews.apply {
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }

        binding!!.recyclerViewNews.layoutManager = viewManager
        binding!!.recyclerViewNews.adapter =
            NewsAdapter(NewsPresenter.arrayListNews, this, userAuth)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setListener() {

        binding!!.recyclerViewNews.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if (dy > 0) {
                    val visibleItemCount = viewManager.childCount
                    val totalItemCount = viewManager.itemCount
                    val pastVisiblesItems = viewManager.findFirstVisibleItemPosition()

                    if (!NewsPresenter.isLoading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount - 10) {
                            NewsPresenter.onResponseNews(++page, userAuth, this@NewsFragment)
                            binding!!.recyclerViewNews.adapter!!.notifyDataSetChanged()

                        }
                    }
                }
            }
        })

        binding!!.swipeNews.setOnRefreshListener {
            page = 1
            NewsPresenter.clearArrayList()
            NewsPresenter.onResponseNews(++page, userAuth, this)

        }

    }

    override fun onNewPageReceived() {
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

    override fun onClickLike(newsId: Int) {
        NewsPresenter.onResponseLike(newsId, userAuth, this)
    }

    override fun onSuccessResponse(message: String, newsId: Int) {
        Snackbar.make(newView, message, Snackbar.LENGTH_LONG).show()

        val newsLike = NewsPresenter.arrayListNews.find { it.newsId == newsId }

        newsLike!!.like = if (newsLike.like.isEmpty()) {
            listOf(userAuth.userAuthId)
        } else {
            listOf()
        }

    }

    override fun onFailureResponse(message: String) {
        val newMessage = if (message.isEmpty()) {
            getString(R.string.server_error)
        } else {
            message
        }

        Snackbar.make(newView, newMessage, Snackbar.LENGTH_LONG).show()
    }

}