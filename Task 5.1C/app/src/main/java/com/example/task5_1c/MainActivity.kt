package com.example.task5_1c

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task5_1c.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NewsAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var topStoriesAdapter: NewsAdapter
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup top stories recycler view
        topStoriesAdapter = NewsAdapter(getTopStories(), this)
        binding.recyclerTopStories.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = topStoriesAdapter
        }

        // Setup news recycler view
        newsAdapter = NewsAdapter(getNews(), this)
        binding.recyclerNews.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = newsAdapter
        }
    }

    private fun getTopStories(): List<NewsItem> {
        // Dummy data for top stories
        return listOf(
            NewsItem("Top Story 1", "Description 1", "https://via.placeholder.com/150"),
            NewsItem("Top Story 2", "Description 2", "https://via.placeholder.com/150"),
            NewsItem("Top Story 3", "Description 3", "https://via.placeholder.com/150")
        )
    }

    private fun getNews(): List<NewsItem> {
        // Dummy data for news
        return listOf(
            NewsItem("News 1", "Description 1", "https://via.placeholder.com/150"),
            NewsItem("News 2", "Description 2", "https://via.placeholder.com/150"),
            NewsItem("News 3", "Description 3", "https://via.placeholder.com/150")
        )
    }

    override fun onItemClick(newsItem: NewsItem) {
        // Show news detail fragment
        val fragment = NewsDetailFragment.newInstance(newsItem)
        supportFragmentManager.commit {
            replace(binding.fragmentContainer.id, fragment)
            addToBackStack(null)
        }
    }
}
