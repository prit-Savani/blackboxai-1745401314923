package com.example.task5_1c;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.task5_1c.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsAdapter.OnItemClickListener {

    private ActivityMainBinding binding;
    private NewsAdapter topStoriesAdapter;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Setup top stories recycler view
        topStoriesAdapter = new NewsAdapter(getTopStories(), this);
        binding.recyclerTopStories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerTopStories.setAdapter(topStoriesAdapter);

        // Setup news recycler view
        newsAdapter = new NewsAdapter(getNews(), this);
        binding.recyclerNews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerNews.setAdapter(newsAdapter);
    }

    private List<NewsItem> getTopStories() {
        List<NewsItem> list = new ArrayList<>();
        list.add(new NewsItem("Top Story 1", "Description 1", "https://via.placeholder.com/150"));
        list.add(new NewsItem("Top Story 2", "Description 2", "https://via.placeholder.com/150"));
        list.add(new NewsItem("Top Story 3", "Description 3", "https://via.placeholder.com/150"));
        return list;
    }

    private List<NewsItem> getNews() {
        List<NewsItem> list = new ArrayList<>();
        list.add(new NewsItem("News 1", "Description 1", "https://via.placeholder.com/150"));
        list.add(new NewsItem("News 2", "Description 2", "https://via.placeholder.com/150"));
        list.add(new NewsItem("News 3", "Description 3", "https://via.placeholder.com/150"));
        return list;
    }

    @Override
    public void onItemClick(NewsItem newsItem) {
        NewsDetailFragment fragment = NewsDetailFragment.newInstance(newsItem);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(binding.fragmentContainer.getId(), fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
