package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tiptap.tda_user.tiptap.R;

public class Act_8 extends AppCompatActivity {

    Button next;
    TextView txt1,txt2,txt3,txt4;
    TextView ans1,ans2,ans3,ans4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_8);

        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView) findViewById(R.id.txt3);
        txt4 = (TextView) findViewById(R.id.txt4);

        ans1 = (TextView) findViewById(R.id.ans1);
        ans2 = (TextView) findViewById(R.id.ans2);
        ans3 = (TextView) findViewById(R.id.ans3);
        ans4 = (TextView) findViewById(R.id.ans4);

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt1.getDrawingCacheBackgroundColor() == getResources().getColor(R.color.green)){
                    //txt1.setBackgroundColor(getResources().getColor(R.color.withe));
                }else{
                    if(ans1.getText().equals("-------")){
                        ans1.setText(txt1.getText());
                        txt1.setBackgroundColor(getResources().getColor(R.color.green));
                    }else{
                        if(ans2.getText().equals("-------")){
                            ans2.setText(txt1.getText());
                            txt1.setBackgroundColor(getResources().getColor(R.color.green));
                        }else{
                            if(ans3.getText().equals("-------")){
                                ans3.setText(txt1.getText());
                                txt1.setBackgroundColor(getResources().getColor(R.color.green));
                            }else{
                                if(ans4.getText().equals("-------")){
                                    ans4.setText(txt1.getText());
                                    txt1.setBackgroundColor(getResources().getColor(R.color.green));
                                }
                            }
                        }
                    }
                }
            }
        });

        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt2.getDrawingCacheBackgroundColor() == getResources().getColor(R.color.green)){
                    //txt1.setBackgroundColor(getResources().getColor(R.color.withe));
                }else{
                    if(ans1.getText().equals("-------")){
                        ans1.setText(txt2.getText());
                        txt2.setBackgroundColor(getResources().getColor(R.color.green));
                    }else{
                        if(ans2.getText().equals("-------")){
                            ans2.setText(txt2.getText());
                            txt2.setBackgroundColor(getResources().getColor(R.color.green));
                        }else{
                            if(ans3.getText().equals("-------")){
                                ans3.setText(txt2.getText());
                                txt2.setBackgroundColor(getResources().getColor(R.color.green));
                            }else{
                                if(ans4.getText().equals("-------")){
                                    ans4.setText(txt2.getText());
                                    txt2.setBackgroundColor(getResources().getColor(R.color.green));
                                }
                            }
                        }
                    }
                }
            }
        });

        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt3.getDrawingCacheBackgroundColor() == getResources().getColor(R.color.green)){
                    //txt1.setBackgroundColor(getResources().getColor(R.color.withe));
                }else{
                    if(ans1.getText().equals("-------")){
                        ans1.setText(txt3.getText());
                        txt3.setBackgroundColor(getResources().getColor(R.color.green));
                    }else{
                        if(ans2.getText().equals("-------")){
                            ans2.setText(txt3.getText());
                            txt3.setBackgroundColor(getResources().getColor(R.color.green));
                        }else{
                            if(ans3.getText().equals("-------")){
                                ans3.setText(txt3.getText());
                                txt3.setBackgroundColor(getResources().getColor(R.color.green));
                            }else{
                                if(ans4.getText().equals("-------")){
                                    ans4.setText(txt3.getText());
                                    txt3.setBackgroundColor(getResources().getColor(R.color.green));
                                }
                            }
                        }
                    }
                }
            }
        });

        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt4.getDrawingCacheBackgroundColor() == getResources().getColor(R.color.green)){
                    //txt1.setBackgroundColor(getResources().getColor(R.color.withe));
                }else{
                    if(ans1.getText().equals("-------")){
                        ans1.setText(txt4.getText());
                        txt4.setBackgroundColor(getResources().getColor(R.color.green));
                    }else{
                        if(ans2.getText().equals("-------")){
                            ans2.setText(txt4.getText());
                            txt4.setBackgroundColor(getResources().getColor(R.color.green));
                        }else{
                            if(ans3.getText().equals("-------")){
                                ans3.setText(txt4.getText());
                                txt4.setBackgroundColor(getResources().getColor(R.color.green));
                            }else{
                                if(ans4.getText().equals("-------")){
                                    ans4.setText(txt4.getText());
                                    txt4.setBackgroundColor(getResources().getColor(R.color.green));
                                }
                            }
                        }
                    }
                }
            }
        });

        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Act_8.this.finish();
                startActivity(new Intent(Act_8.this, Act_9.class));
            }
        });


    }
}
