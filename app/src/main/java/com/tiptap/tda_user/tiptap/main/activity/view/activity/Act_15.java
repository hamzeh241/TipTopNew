package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tiptap.tda_user.tiptap.R;

public class Act_15 extends AppCompatActivity {

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_15);

        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Act_15.this.finish();
                startActivity(new Intent(Act_15.this, Act_16.class));
            }
        });
    }
}