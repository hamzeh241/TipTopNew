package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tiptap.tda_user.tiptap.R;

public class Act_11 extends AppCompatActivity {

    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8;
    TextView txt9,txt10,txt11,txt12,txt13,txt14,txt15,txt16;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_11);

        txt1 = (TextView)findViewById(R.id.txt1);
        txt2 = (TextView)findViewById(R.id.txt2);
        txt3 = (TextView)findViewById(R.id.txt3);
        txt4 = (TextView)findViewById(R.id.txt4);
        txt5 = (TextView)findViewById(R.id.txt5);
        txt6 = (TextView)findViewById(R.id.txt6);
        txt7 = (TextView)findViewById(R.id.txt7);
        txt8 = (TextView)findViewById(R.id.txt8);

        txt9 = (TextView)findViewById(R.id.txt9); txt9.setVisibility(View.GONE);
        txt10 = (TextView)findViewById(R.id.txt10); txt10.setVisibility(View.GONE);
        txt11 = (TextView)findViewById(R.id.txt11); txt11.setVisibility(View.GONE);
        txt12 = (TextView)findViewById(R.id.txt12); txt12.setVisibility(View.GONE);
        txt13 = (TextView)findViewById(R.id.txt13); txt13.setVisibility(View.GONE);
        txt14 = (TextView)findViewById(R.id.txt14); txt14.setVisibility(View.GONE);
        txt15 = (TextView)findViewById(R.id.txt15); txt15.setVisibility(View.GONE);
        txt16 = (TextView)findViewById(R.id.txt16); txt16.setVisibility(View.GONE);

        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Act_11.this.finish();
                startActivity(new Intent(Act_11.this, Act_12.class));
            }
        });
    }
}
