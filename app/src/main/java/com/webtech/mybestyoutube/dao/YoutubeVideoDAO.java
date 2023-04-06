package com.webtech.mybestyoutube.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import androidx.room.Update;

import com.webtech.mybestyoutube.pojos.YoutubeVideo;

import java.util.List;

@Dao
public interface YoutubeVideoDAO{
    @Query("SELECT * FROM youtubevideo WHERE id = :id")
    public YoutubeVideo find(Long id);

    @Query("SELECT * FROM youtubevideo")
    public List<YoutubeVideo> list();

    @Insert
    public void add(YoutubeVideo youtubevideo);

    @Update
    public void update(YoutubeVideo youtubevideo);

    @Delete
    public void delete(YoutubeVideo youtubevideo);
}