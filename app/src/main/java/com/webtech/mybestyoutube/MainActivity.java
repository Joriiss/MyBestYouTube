package com.webtech.mybestyoutube;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.webtech.mybestyoutube.database.YoutubeVideoDatabase;
import com.webtech.mybestyoutube.pojos.YoutubeVideo;
import com.webtech.mybestyoutube.pojos.YoutubeVideoAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public Context context;
    private RecyclerView rvVideos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        rvVideos = findViewById(R.id.rvVideos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvVideos.setHasFixedSize(true);
        rvVideos.setLayoutManager(layoutManager);
    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull android.view.MenuItem item) {
        switch (item.getItemId()){
            case R.id.addYoutubeVideo:
                Context context = getApplicationContext();
                Intent intent = new Intent(context, AddYouTubeActivity.class);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class YoutubeVideoAsyncTask extends AsyncTask<Nullable, Nullable, List<YoutubeVideo>> {
        @Override
        protected List<YoutubeVideo> doInBackground(Nullable... nullables) {
            List<YoutubeVideo> videos = YoutubeVideoDatabase.getDb(context).youtubeVideoDAO().list();

            return videos;
        }

        @Override
        protected void onPostExecute(List<YoutubeVideo> videos) {
            YoutubeVideoAdapter videoAdapter = new YoutubeVideoAdapter(videos);
            rvVideos.setAdapter(videoAdapter);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        YoutubeVideoAsyncTask youtubeVideoAsyncTask = new YoutubeVideoAsyncTask();
        youtubeVideoAsyncTask.execute();
    }
}