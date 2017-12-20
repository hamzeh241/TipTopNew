package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tiptap.tda_user.tiptap.R;

public class A39 extends AppCompatActivity {

    Button next;
    EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a39);

        et1 = (EditText) findViewById(R.id.et1); et1.setVisibility(View.GONE);
        et2 = (EditText) findViewById(R.id.et2); et2.setVisibility(View.GONE);

        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                A39.this.finish();
                startActivity(new Intent(A39.this, A28.class));
            }
        });

    }
}