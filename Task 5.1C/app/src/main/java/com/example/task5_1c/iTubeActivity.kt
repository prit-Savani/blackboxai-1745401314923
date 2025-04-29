package com.example.task5_1c

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task5_1c.databinding.ActivityItubeBinding

class iTubeActivity : AppCompatActivity(), PlaylistAdapter.OnItemClickListener {

    private lateinit var binding: ActivityItubeBinding
    private lateinit var database: Database
    private lateinit var playlistAdapter: PlaylistAdapter
    private var playlistItems = mutableListOf<PlaylistItem>()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItubeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Database(this)

        // Setup playlist recycler view
        playlistAdapter = PlaylistAdapter(playlistItems, this)
        binding.recyclerPlaylist.apply {
            layoutManager = LinearLayoutManager(this@iTubeActivity)
            adapter = playlistAdapter
        }

        // Setup WebView for YouTube iframe embed
        binding.youtubeWebView.webViewClient = WebViewClient()
        val webSettings: WebSettings = binding.youtubeWebView.settings
        webSettings.javaScriptEnabled = true

        // Load default video or first video in playlist
        loadPlaylistFromDb()
        if (playlistItems.isNotEmpty()) {
            loadVideo(playlistItems[0].videoUrl)
        }
    }

    private fun loadVideo(videoUrl: String) {
        val html = \"\"\"
            <html>
            <body style="margin:0;padding:0;">
            <iframe width="100%" height="100%" src="$videoUrl" frameborder="0" allowfullscreen></iframe>
            </body>
            </html>
        \"\"\"
        binding.youtubeWebView.loadData(html, "text/html", "utf-8")
    }

    private fun loadPlaylistFromDb() {
        playlistItems.clear()
        val db = database.readableDatabase
        val cursor: Cursor = db.query(Database.TABLE_PLAYLIST, null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(Database.COLUMN_ID))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(Database.COLUMN_TITLE))
                val videoUrl = cursor.getString(cursor.getColumnIndexOrThrow(Database.COLUMN_VIDEO_URL))
                playlistItems.add(PlaylistItem(id, title, videoUrl))
            } while (cursor.moveToNext())
        }
        cursor.close()
        playlistAdapter.notifyDataSetChanged()
    }

    override fun onItemClick(playlistItem: PlaylistItem) {
        loadVideo(playlistItem.videoUrl)
    }
}
