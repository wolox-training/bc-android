package com.example.wnews.views.home.news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wnews.R
import com.example.wnews.databinding.ItemNewsBinding
import com.example.wnews.models.News
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.imagepipeline.request.ImageRequest

class NewsAdapter(private val newsArrayList: ArrayList<News>): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
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
            binding.imageNewsItem.setImageURI(news.imageUrl.replace(" ","%20"))


        }
    }

    private fun setupImageView(binding: ItemNewsBinding,url:String) {
        /*val controller: DraweeController = Fresco.newDraweeControllerBuilder()
            .setLowResImageRequest(ImageRequest.fromUri(url.getThumbUrl()))
            .setImageRequest(ImageRequest.fromUri(media.getImageUrl()))
            .setOldController(binding.imageNewsItem.controller)
            .build()
        binding.imageNewsItem.controller = controller*/
    }
}
