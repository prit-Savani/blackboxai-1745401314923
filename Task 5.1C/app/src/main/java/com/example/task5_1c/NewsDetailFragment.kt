package com.example.task5_1c

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.task5_1c.databinding.FragmentNewsDetailBinding

class NewsDetailFragment : Fragment(), NewsAdapter.OnItemClickListener {

    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var relatedNewsAdapter: NewsAdapter
    private lateinit var currentNewsItem: NewsItem

    companion object {
        private const val ARG_NEWS_ITEM = "news_item"

        fun newInstance(newsItem: NewsItem): NewsDetailFragment {
            val fragment = NewsDetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_NEWS_ITEM, newsItem)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currentNewsItem = it.getParcelable(ARG_NEWS_ITEM)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textDescription.text = currentNewsItem.description
        Glide.with(this)
            .load(currentNewsItem.imageUrl)
            .centerCrop()
            .into(binding.imageNews)

        // Setup related news recycler view (vertical)
        relatedNewsAdapter = NewsAdapter(getRelatedNews(), this)
        binding.recyclerRelatedNews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = relatedNewsAdapter
        }
    }

    private fun getRelatedNews(): List<NewsItem> {
        // Dummy related news data
        return listOf(
            NewsItem("Related News 1", "Description 1", "https://via.placeholder.com/150"),
            NewsItem("Related News 2", "Description 2", "https://via.placeholder.com/150"),
            NewsItem("Related News 3", "Description 3", "https://via.placeholder.com/150")
        )
    }

    override fun onItemClick(newsItem: NewsItem) {
        // Replace current fragment with new news detail
        parentFragmentManager.beginTransaction()
            .replace(id, newInstance(newsItem))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
