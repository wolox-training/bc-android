package com.example.wnews.views.home.news.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.wnews.R
import com.example.wnews.databinding.FragmentDetailBinding
import com.example.wnews.models.News


class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var binding: FragmentDetailBinding? = null
    lateinit var newsObject: News

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

        val activity: DetailActivity? = activity as DetailActivity?
        newsObject = activity!!.newsObject

        setContent()
    }

    private fun setContent() {
        binding!!.textViewTitle.text = newsObject.title
        binding!!.textViewDetail.text = newsObject.detail
    }
}