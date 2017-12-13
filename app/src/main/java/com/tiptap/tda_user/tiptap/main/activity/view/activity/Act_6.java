package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tiptap.tda_user.tiptap.R;

public class Act_6 extends AppCompatActivity {

    Button play,next;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_6);

        mp = MediaPlayer.create(Act_6.this, R.raw.music1);
        mp.setVolume(100,100);

        play = (Button)findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
            }
        });

        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Act_6.this.finish();
                startActivity(new Intent(Act_6.this, Act_7.class));
            }
        });
    }
}