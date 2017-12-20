package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.tiptap.tda_user.tiptap.R;

public class A19 extends AppCompatActivity {

    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8;
    TextView txt9,txt10,txt11,txt12,txt13,txt14,txt15,txt16;
    public static int aval=0;
    public static int correct=0;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a19);

        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(correct == 4){
                    A19.this.finish();
                    startActivity(new Intent(A19.this, End.class));
                //}
            }
        });

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

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(aval == 0){
                    aval = 1;
                    txt1.setBackgroundColor(getResources().getColor(R.color.green));
                }else{
                    if(aval == 4 || aval == 8) {
                        if(aval == 4){
                            txt1.setVisibility(View.INVISIBLE);
                            txt4.setVisibility(View.INVISIBLE);
                            aval=0;
                            correct++;
                            if(correct == 4){
                                showCustomAlert();
                                //startActivity(new Intent(A19.this, End.class));
                            }
                        }
                        if(aval == 8){
                            txt1.setVisibility(View.INVISIBLE);
                            txt8.setVisibility(View.INVISIBLE);
                            aval=0;
                            correct++;
                            if(correct == 4){
                                showCustomAlert();
                                //startActivity(new Intent(A19.this, End.class));
                            }
                        }
                    }else{
                        switch(aval){
                            case 1:
                                txt1.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 2:
                                txt2.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 3:
                                txt3.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 4:
                                txt4.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 5:
                                txt5.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 6:
                                txt6.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 7:
                                txt7.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 8:
                                txt8.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                        }
                        aval=0;
                    }
                }
            }
        });

        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(aval == 0){
                    aval = 2;
                    txt2.setBackgroundColor(getResources().getColor(R.color.green));
                }else{
                    if(aval == 7){
                        txt2.setVisibility(View.INVISIBLE);
                        txt7.setVisibility(View.INVISIBLE);
                        aval=0;
                        correct++;
                        if(correct == 4){
                            showCustomAlert();
                            //startActivity(new Intent(A19.this, End.class));
                        }
                    }else{
                        switch(aval){
                            case 1:
                                txt1.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 2:
                                txt2.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 3:
                                txt3.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 4:
                                txt4.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 5:
                                txt5.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 6:
                                txt6.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 7:
                                txt7.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 8:
                                txt8.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                        }
                        aval=0;
                    }
                }
            }
        });

        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(aval == 0){
                    aval = 3;
                    txt3.setBackgroundColor(getResources().getColor(R.color.green));
                }else{
                    if(aval == 6){
                        txt3.setVisibility(View.INVISIBLE);
                        txt6.setVisibility(View.INVISIBLE);
                        aval=0;
                        correct++;
                        if(correct == 4){
                            showCustomAlert();
                            //startActivity(new Intent(A19.this, End.class));
                        }
                    }else{
                        switch(aval){
                            case 1:
                                txt1.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 2:
                                txt2.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 3:
                                txt3.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 4:
                                txt4.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 5:
                                txt5.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 6:
                                txt6.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 7:
                                txt7.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 8:
                                txt8.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                        }
                        aval=0;
                    }
                }
            }
        });

        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(aval == 0){
                    aval = 4;
                    txt4.setBackgroundColor(getResources().getColor(R.color.green));
                }else {
                    if(aval == 1 || aval == 5){
                        if (aval == 1) {
                            txt4.setVisibility(View.INVISIBLE);
                            txt1.setVisibility(View.INVISIBLE);
                            aval=0;
                            correct++;
                            if(correct == 4){
                                showCustomAlert();
                                //startActivity(new Intent(A19.this, End.class));
                            }
                        }
                        if (aval == 5) {
                            txt4.setVisibility(View.INVISIBLE);
                            txt5.setVisibility(View.INVISIBLE);
                            aval=0;
                            correct++;
                            if(correct == 4){
                                showCustomAlert();
                                //startActivity(new Intent(A19.this, End.class));
                            }
                        }
                    }else{
                        switch(aval){
                            case 1:
                                txt1.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 2:
                                txt2.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 3:
                                txt3.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 4:
                                txt4.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 5:
                                txt5.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 6:
                                txt6.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 7:
                                txt7.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 8:
                                txt8.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                        }
                        aval=0;
                    }
                }
            }
        });

        txt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(aval == 0){
                    aval = 5;
                    txt5.setBackgroundColor(getResources().getColor(R.color.green));
                }else{
                    if(aval == 4 || aval == 8){
                        if(aval == 4){
                            txt5.setVisibility(View.INVISIBLE);
                            txt4.setVisibility(View.INVISIBLE);
                            aval=0;
                            correct++;
                            if(correct == 4){
                                showCustomAlert();
                                //startActivity(new Intent(A19.this, End.class));
                            }
                        }
                        if(aval == 8){
                            txt5.setVisibility(View.INVISIBLE);
                            txt8.setVisibility(View.INVISIBLE);
                            aval=0;
                            correct++;
                            if(correct == 4){
                                showCustomAlert();
                                //startActivity(new Intent(A19.this, End.class));
                            }
                        }
                    }else{
                        switch(aval){
                            case 1:
                                txt1.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 2:
                                txt2.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 3:
                                txt3.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 4:
                                txt4.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 5:
                                txt5.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 6:
                                txt6.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 7:
                                txt7.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 8:
                                txt8.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                        }
                        aval=0;
                    }
                }
            }
        });

        txt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(aval == 0){
                    aval = 6;
                    txt6.setBackgroundColor(getResources().getColor(R.color.green));
                }else{
                    if(aval == 3){
                        txt6.setVisibility(View.INVISIBLE);
                        txt3.setVisibility(View.INVISIBLE);
                        aval=0;
                        correct++;
                        if(correct == 4){
                            showCustomAlert();
                            //startActivity(new Intent(A19.this, End.class));
                        }
                    }else{
                        switch(aval){
                            case 1:
                                txt1.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 2:
                                txt2.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 3:
                                txt3.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 4:
                                txt4.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 5:
                                txt5.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 6:
                                txt6.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 7:
                                txt7.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 8:
                                txt8.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                        }
                        aval=0;
                    }
                }
            }
        });

        txt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(aval == 0){
                    aval = 7;
                    txt7.setBackgroundColor(getResources().getColor(R.color.green));
                }else{
                    if(aval == 2){
                        txt7.setVisibility(View.INVISIBLE);
                        txt2.setVisibility(View.INVISIBLE);
                        aval=0;
                        correct++;
                        if(correct == 4){
                            showCustomAlert();
                            //startActivity(new Intent(A19.this, End.class));
                        }
                    }else{
                        switch(aval){
                            case 1:
                                txt1.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 2:
                                txt2.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 3:
                                txt3.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 4:
                                txt4.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 5:
                                txt5.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 6:
                                txt6.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 7:
                                txt7.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 8:
                                txt8.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                        }
                        aval=0;
                    }
                }
            }
        });


        txt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(aval == 0){
                    aval = 8;
                    txt8.setBackgroundColor(getResources().getColor(R.color.green));
                }else{
                    if(aval == 1 || aval == 5){
                        if(aval == 1){
                            txt8.setVisibility(View.INVISIBLE);
                            txt1.setVisibility(View.INVISIBLE);
                            aval=0;
                            correct++;
                            if(correct == 4){
                                showCustomAlert();
                               // startActivity(new Intent(A19.this, End.class));
                            }
                        }
                        if(aval == 5){
                            txt8.setVisibility(View.INVISIBLE);
                            txt5.setVisibility(View.INVISIBLE);
                            aval=0;
                            correct++;
                            if(correct == 4){
                                showCustomAlert();
                                //startActivity(new Intent(A19.this, End.class));
                            }
                        }
                    }else{
                        switch(aval){
                            case 1:
                                txt1.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 2:
                                txt2.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 3:
                                txt3.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 4:
                                txt4.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 5:
                                txt5.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 6:
                                txt6.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 7:
                                txt7.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                            case 8:
                                txt8.setBackgroundColor(getResources().getColor(R.color.withe));
                                break;
                        }
                        aval=0;
                    }
                }
            }
        });
    }

     public void showCustomAlert(){
         LayoutInflater inflater = getLayoutInflater();
         View toastRoot = inflater.inflate(R.layout.toast, null);
         Toast toast = new Toast(getApplicationContext());
         toast.setView(toastRoot);
         toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
         toast.setDuration(Toast.LENGTH_LONG);
         toast.show();
    }
}