package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.tiptap.tda_user.tiptap.R;


public class A37 extends AppCompatActivity {

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a37);

        VideoView video_view = (VideoView) findViewById(R.id.video_player_view);
        MediaController media_Controller = new MediaController(this);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;
        int width = dm.widthPixels;
        video_view.setMinimumWidth(width);
        video_view.setMinimumHeight(height);
        video_view.setMediaController(media_Controller);
        video_view.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video1);

        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                A37.this.finish();
                startActivity(new Intent(A37.this, A8.class));
            }
        });
    }
}