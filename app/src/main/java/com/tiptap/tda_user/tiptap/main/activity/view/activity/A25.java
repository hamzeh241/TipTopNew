package com.tiptap.tda_user.tiptap.main.activity.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tiptap.tda_user.tiptap.R;

public class A25 extends BaseActivity {

    Button next;
    public static String Act_Status;
    public static int idactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a25);

        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                A25.this.finish();
                startActivity(new Intent(A25.this, A40.class));
            }
        });
    }
}