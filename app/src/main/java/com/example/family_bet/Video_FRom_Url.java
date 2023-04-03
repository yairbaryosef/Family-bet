package com.example.family_bet;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.family_bet.Classes.Constants.constants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class Video_FRom_Url extends AppCompatActivity {
    String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_from_url);


        json=getIntent().getStringExtra(constants.tournament);
        YouTubePlayerView youTubePlayerView = findViewById(R.id.video);


    }



}