package com.example.wnews.views.home.news

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wnews.R
import com.example.wnews.databinding.FragmentNewsBinding
import com.example.wnews.views.auth.AuthPresenter
import com.example.wnews.views.home.news.adapter.NewsAdapter
import com.example.wnews.views.home.news.presenter.NewsPresenter


class NewsFragment : Fragment(R.layout.fragment_news),NewsView {

    private var binding: FragmentNewsBinding? = null
    private var viewManager = LinearLayoutManager(context)
    lateinit var newsPresenter:NewsPresenter
    var page = 1
    lateinit var sPref:SharedPreferences
    lateinit var authPresenter: AuthPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)

        initialisePresenter()
        initialiseAdapter()
        setListener()
    }

    private fun initialisePresenter(){
        sPref = PreferenceManager.getDefaultSharedPreferences(context)
        authPresenter = AuthPresenter(sPref, null)
        newsPresenter = NewsPresenter(this)
        newsPresenter.onResponseNews(page, authPresenter.getUserAuth())
    }

    private fun initialiseAdapter(){
        binding!!.recyclerViewNews.layoutManager = viewManager
        binding!!.recyclerViewNews.adapter = NewsAdapter(newsPresenter.arrayListNews)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setListener(){
        binding!!.butttonAddNews.setOnClickListener{ }

        binding!!.recyclerViewNews.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if (dy > 0) {
                    val visibleItemCount = viewManager.childCount
                    val totalItemCount = viewManager.itemCount
                    val pastVisiblesItems = viewManager.findFirstVisibleItemPosition()

                    if (!newsPresenter.loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount - 10) {
                            newsPresenter.onResponseNews(++page, authPresenter.getUserAuth())
                            binding!!.recyclerViewNews.adapter!!.notifyDataSetChanged()

                        }
                    }
                }

            }
        })

        binding!!.swipeNews.setOnRefreshListener {
            page = 1
            newsPresenter.clearArrayList()
            newsPresenter.onResponseNews(++page, authPresenter.getUserAuth())

        }
    }

    override fun changeData() {
        binding!!.recyclerViewNews.adapter!!.notifyDataSetChanged()
    }

    override fun showProgressBar(isLoading: Boolean) {
        if(isLoading) {
            binding!!.progressBarNews.visibility = View.VISIBLE
        }else{
            binding!!.progressBarNews.visibility = View.GONE
            binding!!.swipeNews.isRefreshing= false
        }
    }

}