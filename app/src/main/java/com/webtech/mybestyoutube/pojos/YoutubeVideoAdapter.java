package com.webtech.mybestyoutube.pojos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.webtech.mybestyoutube.R;

import java.util.List;

public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.YoutubeVideoViewHolder> {
    private final List<YoutubeVideo> videos;
    public class YoutubeVideoViewHolder extends RecyclerView.ViewHolder {
        public TextView Title;
        public TextView Description;

        public YoutubeVideoViewHolder(View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.tvTitle);
            Description = itemView.findViewById(R.id.tvDescription);
        }
    }

    public YoutubeVideoAdapter(List<YoutubeVideo> videos) {
        this.videos = videos;
    }

    @NonNull
    @Override
    public YoutubeVideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item, parent, false);
        return new YoutubeVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(YoutubeVideoViewHolder holder, int position) {
        YoutubeVideo youtubeVideo = videos.get(position);
        holder.Title.setText(youtubeVideo.getTitle());
        holder.Description.setText(youtubeVideo.getDescription());
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }
}