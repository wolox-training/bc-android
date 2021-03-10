package com.example.wnews.views.home.news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wnews.R
import com.example.wnews.databinding.ItemNewsBinding
import com.example.wnews.models.News
import com.example.wnews.views.home.news.NewsView

class NewsAdapter(private val newsArrayList: ArrayList<News>, private val newsViews: NewsView) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(root)
    }

    override fun onBindViewHolder(holder: NewsAdapter.NewsViewHolder, position: Int) {
        holder.bind(newsArrayList[position])
    }

    override fun getItemCount(): Int {
        return newsArrayList.size
    }

    inner class NewsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(news: News) {

            val binding = ItemNewsBinding.bind(view)
            binding.titleNewsItem.text = news.title
            binding.detailNewsItem.text = news.detail
            binding.imageNewsItem.setImageURI(news.imageUrl.replace(" ", "%20"))

            news.like.forEach { like ->
                if (like == 0) {

                    return@forEach
                }
            }

            setListener(news)

        }

        private fun setListener(news: News) {
            view.setOnClickListener {
                newsViews.openDetail(news)
            }
        }
    }
}
