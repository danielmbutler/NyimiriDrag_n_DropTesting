package com.example.nyimiridrag_n_droptesting;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.alphamovie.lib.AlphaMovieView;

public class Congrats extends AppCompatActivity {
    VideoView videoView;
    Button btn_play;

    AlphaMovieView myVideo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);

        myVideo1 = findViewById(R.id.myVideo);
        Uri myUri = Uri.parse("android.resource://"+getPackageName() + "/" + R.raw.congrats);
        myVideo1.setVideoFromUri(Congrats.this,myUri);
        myVideo1.setLooping(true);



        /*DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        VideoView videoView = findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.congrats));
        videoView.start();*/
    }

}