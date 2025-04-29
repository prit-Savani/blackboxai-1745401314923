package com.example.task5_1c;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bumptech.glide.Glide;
import com.example.task5_1c.databinding.FragmentNewsDetailBinding;
import java.util.ArrayList;
import java.util.List;

public class NewsDetailFragment extends Fragment implements NewsAdapter.OnItemClickListener {

    private FragmentNewsDetailBinding binding;
    private NewsAdapter relatedNewsAdapter;
    private NewsItem currentNewsItem;

    private static final String ARG_NEWS_ITEM = "news_item";

    public static NewsDetailFragment newInstance(NewsItem newsItem) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NEWS_ITEM, newsItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentNewsItem = getArguments().getParcelable(ARG_NEWS_ITEM);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentNewsDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.textDescription.setText(currentNewsItem.getDescription());
        Glide.with(this)
                .load(currentNewsItem.getImageUrl())
                .centerCrop()
                .into(binding.imageNews);

        relatedNewsAdapter = new NewsAdapter(getRelatedNews(), this);
        binding.recyclerRelatedNews.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerRelatedNews.setAdapter(relatedNewsAdapter);
    }

    private List<NewsItem> getRelatedNews() {
        List<NewsItem> list = new ArrayList<>();
        list.add(new NewsItem("Related News 1", "Description 1", "https://via.placeholder.com/150"));
        list.add(new NewsItem("Related News 2", "Description 2", "https://via.placeholder.com/150"));
        list.add(new NewsItem("Related News 3", "Description 3", "https://via.placeholder.com/150"));
        return list;
    }

    @Override
    public void onItemClick(NewsItem newsItem) {
        NewsDetailFragment fragment = NewsDetailFragment.newInstance(newsItem);
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(binding.getRoot().getId(), fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
