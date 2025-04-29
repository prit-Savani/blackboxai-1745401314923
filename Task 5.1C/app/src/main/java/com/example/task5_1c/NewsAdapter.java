package com.example.task5_1c;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.task5_1c.databinding.ItemNewsBinding;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(NewsItem newsItem);
    }

    private List<NewsItem> newsList;
    private OnItemClickListener listener;

    public NewsAdapter(List<NewsItem> newsList, OnItemClickListener listener) {
        this.newsList = newsList;
        this.listener = listener;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private ItemNewsBinding binding;

        public NewsViewHolder(ItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final NewsItem newsItem) {
            binding.textTitle.setText(newsItem.getTitle());
            Glide.with(binding.imageThumbnail.getContext())
                    .load(newsItem.getImageUrl())
                    .centerCrop()
                    .into(binding.imageThumbnail);

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(newsItem);
                }
            });
        }
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemNewsBinding binding = ItemNewsBinding.inflate(inflater, parent, false);
        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(newsList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
