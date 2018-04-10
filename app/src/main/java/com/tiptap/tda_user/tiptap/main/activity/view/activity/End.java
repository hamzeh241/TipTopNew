package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.main.activity.view.function.Function;
import com.tiptap.tda_user.tiptap.main.activity.view.lesson.Lesson;


public class End extends AppCompatActivity {

    Button next;
    public static int gofunction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder);
        Animation anim = AnimationUtils.loadAnimation(getBaseContext(), R.anim.end_anim);
        linearLayout.setAnimation(anim);

        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                End.this.finish();
                if(gofunction == 0){
                    startActivity(new Intent(End.this, Lesson.class));
                }
                if(gofunction == 1){
                    startActivity(new Intent(End.this, Function.class));
                }
            }
        });
    }
}