package com.webtech.mybestyoutube.pojos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.webtech.mybestyoutube.R;

import java.util.List;

public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.YoutubeVideoViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(YoutubeVideo videos);
    }
    private final List<YoutubeVideo> videos;
    private final OnItemClickListener listener;

    public YoutubeVideoAdapter(List<YoutubeVideo> videos, OnItemClickListener listener) {
        this.videos = videos;
        this.listener = listener;
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
        holder.bind(youtubeVideo, listener);
    }

    static class YoutubeVideoViewHolder extends RecyclerView.ViewHolder {
        public TextView Title;
        public TextView Description;



        public YoutubeVideoViewHolder(View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.tvTitle);
            Description = itemView.findViewById(R.id.tvDescription);
        }

        public void bind(final YoutubeVideo youtubeVideo, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(youtubeVideo);
                }
            });
        }
    }
        @Override
    public int getItemCount() {
        return videos.size();
    }
}