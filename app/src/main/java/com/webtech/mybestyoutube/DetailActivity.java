package com.webtech.mybestyoutube;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.webtech.mybestyoutube.database.YoutubeVideoDatabase;
import com.webtech.mybestyoutube.pojos.YoutubeVideo;


public class DetailActivity extends AppCompatActivity {
    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvCategory;
    private TextView tvLink;
    private Button btnLink;

    public Context context;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        context = getApplicationContext();
        tvTitle = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);
        tvLink = findViewById(R.id.tvLink);
        tvCategory = findViewById(R.id.tvCategory);
        btnLink = findViewById(R.id.btnLink);

        Intent intent = getIntent();
        Long id = (Long)intent.getSerializableExtra(MainActivity.KEY_ID);
        YoutubeVideo youtubeVideo = YoutubeVideoDatabase.getDb(context).youtubeVideoDAO().find(id);
        tvTitle.setText(youtubeVideo.getTitle());
        tvCategory.setText(youtubeVideo.getCategory());
        tvLink.setText(youtubeVideo.getUrl());
        tvDescription.setText(youtubeVideo.getDescription());

        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String youtubeUrl = youtubeVideo.getUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
                intent.setPackage("com.google.android.youtube");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
                    startActivity(browserIntent);
                }
            }
        });
    }
}
