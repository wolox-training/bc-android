package com.example.wnews.views.home.news.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.wnews.R
import com.example.wnews.databinding.FragmentDetailBinding
import com.example.wnews.views.home.news.NewsView

class DetailFragment(news:NewsView) : Fragment(R.layout.fragment_detail) {

    private var binding:FragmentDetailBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)


    }
}