package com.example.task5_1c;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.task5_1c.databinding.ItemPlaylistBinding;
import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(PlaylistItem playlistItem);
    }

    private List<PlaylistItem> playlist;
    private OnItemClickListener listener;

    public PlaylistAdapter(List<PlaylistItem> playlist, OnItemClickListener listener) {
        this.playlist = playlist;
        this.listener = listener;
    }

    public class PlaylistViewHolder extends RecyclerView.ViewHolder {
        private ItemPlaylistBinding binding;

        public PlaylistViewHolder(ItemPlaylistBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final PlaylistItem item) {
            binding.textTitle.setText(item.getTitle());
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    @NonNull
    @Override
    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemPlaylistBinding binding = ItemPlaylistBinding.inflate(inflater, parent, false);
        return new PlaylistViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistViewHolder holder, int position) {
        holder.bind(playlist.get(position));
    }

    @Override
    public int getItemCount() {
        return playlist.size();
    }
}
