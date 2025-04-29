package com.example.task5_1c

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task5_1c.databinding.ItemPlaylistBinding

class PlaylistAdapter(
    private val playlist: List<PlaylistItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(playlistItem: PlaylistItem)
    }

    inner class PlaylistViewHolder(val binding: ItemPlaylistBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlaylistItem) {
            binding.textTitle.text = item.title
            binding.root.setOnClickListener {
                listener.onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val binding = ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaylistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind(playlist[position])
    }

    override fun getItemCount(): Int = playlist.size
}
