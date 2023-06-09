package com.webtech.mybestyoutube.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.webtech.mybestyoutube.dao.YoutubeVideoDAO;
import com.webtech.mybestyoutube.pojos.YoutubeVideo;

@Database(entities = {YoutubeVideo.class}, version = 1)
public abstract class YoutubeVideoDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "todo";

    public static YoutubeVideoDatabase getDb(Context context){
        return Room.databaseBuilder(context, YoutubeVideoDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
    }

    public abstract YoutubeVideoDAO youtubeVideoDAO();
}