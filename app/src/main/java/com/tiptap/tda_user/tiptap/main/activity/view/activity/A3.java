package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.tiptap.tda_user.tiptap.R;

public class A3 extends AppCompatActivity {

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a3);

        // have activity & activity detail
        // get data
        // set to view


        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // answer is crrect or false
                // show message
                // who is the next
                // go next
                A3.this.finish();
                startActivity(new Intent(A3.this, A9.class));
            }
        });
    }
}