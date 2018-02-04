package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.tiptap.tda_user.tiptap.R;

public class Correct extends AppCompatActivity {

    Button next;
    public static int gofunction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correct);

        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Correct.this.finish();
                if(gofunction == 0){
                    //startActivity(new Intent(Correct.this, Lesson.class));
                }
                if(gofunction == 1){
                    //startActivity(new Intent(Correct.this, Function.class));
                }
            }
        });
    }
}