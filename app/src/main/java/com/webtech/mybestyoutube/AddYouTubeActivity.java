package com.webtech.mybestyoutube;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.webtech.mybestyoutube.database.YoutubeVideoDatabase;
import com.webtech.mybestyoutube.pojos.YoutubeVideo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddYouTubeActivity extends AppCompatActivity {

private EditText titleInput;
private EditText linkInput;
private EditText descriptionInput;
private Spinner spinner;
private Button btnAdd;
private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_you_tube);
        Context context = getApplicationContext();

        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayHomeAsUpEnabled(true);

        titleInput = findViewById(R.id.titleInput);
        linkInput = findViewById(R.id.linkInput);
        descriptionInput = findViewById(R.id.descInput);
        spinner = findViewById(R.id.categorySpinner);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);

        // Initializing a String Array
        String[] categoryType = new String[]{
                "Sport",
                "Music",
                "Comedy",
                "Education",
                "News",
                "Gaming",
                "Film & Animation",
                "Pets & Animals",
                "Science & Technology"
        };
        List<String> catList = new ArrayList<>
                (Arrays.asList(categoryType));
        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter
                = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                catList
        );
        // Set the drop down view resource
        spinnerArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_dropdown_item_1line
        );
        spinner.setAdapter(spinnerArrayAdapter);


        btnAdd.setOnClickListener(v -> {
            String videoTitle = titleInput.getText().toString();
            String videoDescription = descriptionInput.getText().toString();
            String videoLink = linkInput.getText().toString();
            String videoCategory = spinner.getSelectedItem().toString();

            if(videoTitle.length()==0){
                CharSequence text = "A title is required";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return;
            }
            if(videoDescription.length()==0){
                CharSequence text = "A description is required";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return;
            }
            if(videoLink.length()==0){
                CharSequence text = "A link is required";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return;
            }

            YoutubeVideo youtubeVideo = new YoutubeVideo();

            youtubeVideo.setTitle(videoTitle);
            youtubeVideo.setDescription(videoDescription);
            youtubeVideo.setUrl(videoLink);
            youtubeVideo.setCategory(videoCategory);
            YoutubeVideoDatabase.getDb(context).youtubeVideoDAO().add(youtubeVideo);
            finish();
        });

        btnCancel.setOnClickListener(v -> {
            finish();
        });


    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}