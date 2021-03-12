package com.example.wnews.views.home.news.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.wnews.R
import com.example.wnews.UserProvider
import com.example.wnews.databinding.FragmentDetailBinding
import com.example.wnews.models.News
import com.example.wnews.models.UserAuth
import com.example.wnews.views.home.news.NewsView
import com.example.wnews.views.home.news.presenter.NewsPresenter
import com.google.android.material.snackbar.Snackbar


class DetailFragment : Fragment(R.layout.fragment_detail), NewsView {

    private var binding: FragmentDetailBinding? = null
    lateinit var newsObject: News
    lateinit var userAuth: UserAuth
    lateinit var newView: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

        newView = view
        val activity: DetailActivity? = activity as DetailActivity?
        newsObject = activity!!.newsObject

        initializeProvider()
        setContent()
        setListener()
    }

    private fun initializeProvider() {
        userAuth = UserProvider.userAuth
    }

    private fun setContent() {
        binding!!.textViewTitle.text = newsObject.title
        binding!!.textViewDetail.text = newsObject.detail

        newsObject.like.forEach { like ->
            if (like == userAuth.userAuthId) {
                binding!!.imageButtonLikeDetail.setImageResource(R.drawable.ic_like_on)
                return@forEach
            }
        }
    }

    private fun setListener() {

        binding!!.imageButtonBackDetail.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding!!.imageButtonLikeDetail.setOnClickListener {
            NewsPresenter.onResponseLike(newsObject.newsId, userAuth, this)
        }
    }

    override fun onNewPageReceived() {}

    override fun showProgressBar(isLoading: Boolean) {}

    override fun openDetail(news: News) {}

    override fun onClickLike(newsId: Int) {}

    override fun onSuccessResponse(message: String, newsId: Int) {
        Snackbar.make(newView, message, Snackbar.LENGTH_LONG).show()

        val newsLike = NewsPresenter.arrayListNews.find { it.newsId == newsId }

        if (newsLike!!.like.isEmpty()) {
            newsLike.like = listOf(userAuth.userAuthId)
            binding!!.imageButtonLikeDetail.setImageResource(R.drawable.ic_like_on)
        } else {
            newsLike.like = listOf()
            binding!!.imageButtonLikeDetail.setImageResource(R.drawable.ic_like_off)
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