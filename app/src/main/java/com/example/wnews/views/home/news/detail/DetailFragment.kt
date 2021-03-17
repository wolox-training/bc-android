package com.example.wnews.views.home.news.detail

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.wnews.R
import com.example.wnews.UserProvider
import com.example.wnews.databinding.FragmentDetailBinding
import com.example.wnews.models.News
import com.example.wnews.models.UserAuth
import com.example.wnews.views.home.news.detail.presenter.DetailPresenter
import com.google.android.material.snackbar.Snackbar


class DetailFragment : Fragment(R.layout.fragment_detail), NewsDetailView {

    private var binding: FragmentDetailBinding? = null
    private lateinit var newView: View
    private lateinit var userAuth: UserAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

        newView = view

        val activity: DetailActivity? = activity as DetailActivity?

        val intentNewsId = activity!!.newsId

        val news =
            DetailPresenter().arrayListNews.find { it.newsId == intentNewsId }

        initializeProvider()
        setContent(news)
        setListener(news)

    }

    private fun initializeProvider() {
        userAuth = UserProvider.userAuth
    }

    private fun setContent(news: News?) {
        binding!!.textViewTitle.text = news!!.title
        binding!!.textViewDetail.text = news.detail
        binding!!.bgImageUrl.setImageURI(news.imageUrl)

        if (DetailPresenter().currentUserLikesNews(news.like)) {
            binding!!.imageButtonLikeDetail.setImageResource(R.drawable.ic_like_on)
        }
    }

    private fun setListener(news: News?) {

        binding!!.imageButtonBackDetail.setOnClickListener {
            val intent = Intent()
            requireActivity().setResult(RESULT_OK, intent)
            requireActivity().finish()
        }

        binding!!.imageButtonLikeDetail.setOnClickListener {

            val newsToUpdateLike =
                DetailPresenter().arrayListNews.find { it.newsId == news!!.newsId }


            if (DetailPresenter().currentUserLikesNews(newsToUpdateLike!!.like)) {

                newsToUpdateLike.like = newsToUpdateLike.like.filter { it != userAuth.userAuthId }
                binding!!.imageButtonLikeDetail.setImageResource(R.drawable.ic_like_off)

            } else {
                newsToUpdateLike.like = newsToUpdateLike.like.plusElement(userAuth.userAuthId)
                binding!!.imageButtonLikeDetail.setImageResource(R.drawable.ic_like_on)
            }

            DetailPresenter().onResponseUpdateNewsLike(news!!.newsId, this)
        }
    }

    override fun onResponseFailure(message: String) {
        val newMessage = if (message.isEmpty()) {
            getString(R.string.server_error)
        } else {
            message
        }

        Snackbar.make(newView, newMessage, Snackbar.LENGTH_LONG).show()
    }

}