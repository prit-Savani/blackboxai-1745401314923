package com.example.task5_1c

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task5_1c.databinding.ItemNewsBinding
import com.bumptech.glide.Glide

class NewsAdapter(
    private val newsList: List<NewsItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(newsItem: NewsItem)
    }

    inner class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsItem: NewsItem) {
            binding.textTitle.text = newsItem.title
            Glide.with(binding.imageThumbnail.context)
                .load(newsItem.imageUrl)
                .centerCrop()
                .into(binding.imageThumbnail)

            binding.root.setOnClickListener {
                listener.onItemClick(newsItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int = newsList.size
}
