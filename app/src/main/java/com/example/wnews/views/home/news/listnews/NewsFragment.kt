package com.example.wnews.views.home.news.listnews

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
import com.example.wnews.views.home.news.NewsProvider
import com.example.wnews.views.home.news.detail.DetailActivity
import com.example.wnews.views.home.news.listnews.adapter.NewsAdapter
import com.example.wnews.views.home.news.listnews.presenter.NewsPresenter
import com.google.android.material.snackbar.Snackbar

class NewsFragment : Fragment(R.layout.fragment_news), NewsListView {

    private var binding: FragmentNewsBinding? = null
    private var viewManager = LinearLayoutManager(context)
    private var page = 1
    lateinit var userAuth: UserAuth
    lateinit var newView: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)

        newView = view
        initPresenter()
        initializeSkeleton()
        initAdapter()
        setDesign()
        setListener()

    }

    private fun initializeSkeleton() {
        binding!!.shimmerFrameLayout.showShimmer(true)
    }

    private fun initPresenter() {
        userAuth = UserProvider.userAuth
        NewsPresenter().initProvider(userAuth, this)
        NewsPresenter().onResponseNews(page)
    }

    private fun setDesign() {
        binding!!.recyclerViewNews.apply {
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }

    }

    override fun initAdapter() {

        binding!!.recyclerViewNews.layoutManager = viewManager
        binding!!.recyclerViewNews.adapter =
            NewsAdapter(NewsProvider.arrayListNews, this)

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

                    if (!NewsPresenter().isLoading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount - 10) {
                            NewsPresenter().onResponseNews(++page)
                        }
                    }
                }
            }
        })

        binding!!.swipeNews.setOnRefreshListener {
            page = 1
            NewsPresenter().onResponseNews(page)
        }

    }

    override fun onNewPageReceived() {
        binding!!.recyclerViewNews.adapter!!.notifyDataSetChanged()
    }

    override fun showProgressBar(isLoading: Boolean) {

        if (isLoading) {
            binding!!.progressBarNews.visibility = View.VISIBLE
        } else {
            binding!!.shimmerFrameLayout.visibility = View.GONE
            binding!!.progressBarNews.visibility = View.GONE
            binding!!.swipeNews.isRefreshing = false
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        binding!!.recyclerViewNews.adapter!!.notifyDataSetChanged()
    }

    override fun openDetail(news: News) {

        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.INTENT_EXTRA_ID_NEWS, news.newsId)
        startActivityForResult(intent, 1)

    }

    override fun onClickLike(newsId: Int) {

        if (!NewsPresenter().isLoading) {

            val newsToUpdateLike = NewsPresenter().arrayListNews.find { it.newsId == newsId }


            newsToUpdateLike!!.like =
                if (NewsPresenter().currentUserLikesNews(newsToUpdateLike.like)) {
                    newsToUpdateLike.like.filter { it != userAuth.userAuthId }
                } else {
                    newsToUpdateLike.like.plusElement(userAuth.userAuthId)
                }

            binding!!.recyclerViewNews.adapter!!.notifyDataSetChanged()

            NewsPresenter().onResponseUpdateNewsLike(newsId, this)

        }
    }

    override fun onResponseSuccess() {
        binding!!.containerNoConnection.visibility = View.GONE
    }

    override fun onResponseFailure(message: String) {

        val newMessage = if (message.isEmpty()) {
            getString(R.string.server_error)
        } else {
            message
        }

        Snackbar.make(newView, newMessage, Snackbar.LENGTH_LONG).show()

        if (binding!!.recyclerViewNews.adapter!!.itemCount == 0) {
            binding!!.containerNoConnection.visibility = View.VISIBLE
        }
    }

    override fun onRequestFailure() {
        TODO("Not yet implemented")
    }

}