package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.main.activity.view.BaseActivity;

public class A43 extends BaseActivity {

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a43);

        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                A43.this.finish();
                startActivity(new Intent(A43.this, Act_32.class));
            }
        });
    }
}