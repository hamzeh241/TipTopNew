package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.Main_Module;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Main_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.view.BaseActivity;
import java.util.List;
import java.util.regex.Pattern;
import javax.inject.Inject;

public class A35 extends BaseActivity
        implements MVP_Main.RequiredViewOps {

    private static final String TAG = A35.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A35.class.getName());

    @Inject
    public MVP_Main.ProvidedPresenterOps mPresenter;

    String w1;
    String w2 [];
    String tohi = "-------";
    TextView txt1[];
    TextView txt2[];
    int count;
    TextView text_answer [];
    String answer;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a35);

        setupViews();
        setupMVP();

        tbActivity = mPresenter.getActivity(idlesson, activitynumber);
        max = mPresenter.max_Activitynumber(idlesson);
        int idactivity = tbActivity.get_id();
        tbActivityDetailList = mPresenter.getListActivityDetail(idactivity);
        count = 3;
        //count = tbActivityDetailList.size();
        //ans = tbActivityDetailList.get(0).getTitle2();
    }

    private void setupViews(){

        position = 0;
        count = 3;
        text_answer = new TextView[count];

        w1 = "my name ... logo";
        //w1 = "... are you ?";
        //w1 = "nice too meet ...";
        w2 = w1.split(Pattern.quote("..."));

        int start = 0;
        int end = 0;
        String s_s = w1.substring(0, 3);
        if(s_s.equals("...")){start = 1;}
        String s_e = w1.substring(w1.length()-3, w1.length());
        if(s_e.equals("...")){end = 1;}

        int t = 0;
        int id_w = 0;
        if(w2[0].equals("")){
            id_w = 1;
            t = w2.length-1;
        }else{
            t = w2.length;
        }
        int e = 1;
        int total = t + e;

        txt1 = new TextView[t];
        txt2 = new TextView[e];
        int id_t = 0;
        final int id_e = 0;

        String now = "txt";

        LinearLayout tlinear = (LinearLayout)findViewById(R.id.t);
        LinearLayout alinear = (LinearLayout)findViewById(R.id.a);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(30,10,30,10);

        for(int i=0 ; i<count ; i++){
            final int finalI = i;
            text_answer[i] = new TextView(this);
            text_answer[i].setLayoutParams(params);
            //text_answer[i].setText(tbActivityDetailList.get(i).getTitle1());
            //answer = tbActivityDetailList.get(2).getTitle1()
            text_answer[i].setText("is"+i);
            text_answer[i].setTextSize(20);
            text_answer[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
            tlinear.addView(text_answer[i]);
            text_answer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    set_data(text_answer[finalI]);
                }
            });
        }

        for(int i=0 ; i<total ; i++){
            // start
            if(i == 0){
                if(start == 1){
                    txt2[id_e] = new TextView(this);
                    txt2[id_e].setLayoutParams(params);
                    txt2[id_e].setText(tohi);
                    txt2[id_e].setTextSize(20);
                    alinear.addView(txt2[id_e]);
                    txt2[id_e].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            unset_data(txt2[id_e]);
                        }
                    });
                }
                if(start == 0){
                    txt1[id_t] = new TextView(this);
                    txt1[id_t].setLayoutParams(params);
                    txt1[id_t].setText(w2[id_w]);
                    txt1[id_t].setTextSize(20);
                    alinear.addView(txt1[id_t]);
                    now = "edt";
                    id_w++;
                    id_t++;
                }
            }

            if(i!=0 && i!=total-1){
                // textview
                switch (now) {
                    case "txt":
                        txt1[id_t] = new TextView(this);
                        txt1[id_t].setLayoutParams(params);
                        txt1[id_t].setText(w2[id_w]);
                        txt1[id_t].setTextSize(20);
                        alinear.addView(txt1[id_t]);
                        now = "edt";
                        id_w++;
                        id_t++;
                        break;

                    case "edt":
                        txt2[id_e] = new TextView(this);
                        txt2[id_e].setLayoutParams(params);
                        txt2[id_e].setText(tohi);
                        txt2[id_e].setTextSize(20);
                        alinear.addView(txt2[id_e]);
                        txt2[id_e].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                unset_data(txt2[id_e]);
                            }
                        });
                        now = "txt";
                        break;
                }
            }

            // end
            if(i == total-1){
                if(end == 1) {
                    txt2[id_e] = new TextView(this);
                    txt2[id_e].setLayoutParams(params);
                    txt2[id_e].setText(tohi);
                    txt2[id_e].setTextSize(20);
                    alinear.addView(txt2[id_e]);
                    txt2[id_e].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            unset_data(txt2[id_e]);
                        }
                    });
                }
                if(end == 0){
                    txt1[id_t] = new TextView(this);
                    txt1[id_t].setLayoutParams(params);
                    txt1[id_t].setText(w2[id_w]);
                    txt1[id_t].setTextSize(20);
                    alinear.addView(txt1[id_t]);
                    now = "edt";
                    id_w++;
                    id_t++;
                }
            }
        }

        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (next.getText().toString()) {

                    case "check":
                        if(position == 1){

                            int answer = cheak();
                            if (answer == 1) {
                                Toast.makeText(getApplicationContext(), "CorrectTEST", Toast.LENGTH_LONG).show();
                            } else if (answer == 2) {
                                Toast.makeText(getApplicationContext(), "False", Toast.LENGTH_LONG).show();
                            }

                            next.setTextColor(Color.WHITE);
                            next.setBackgroundResource(R.drawable.btn_green);
                            next.setText("countinue");
                        }

                        break;

                    case "countinue":
                        if(position == 1){
                            if(activitynumber == max){
                                now_less = mPresenter.now_IdLesson();

                                // post

                                // update
                                List<Integer> id_less = mPresenter.lesson(idfunction);
                                List<Integer> id_func =  mPresenter.function();

                                for(int i=0 ; i< id_less.size() ; i++){
                                    if(id_less.get(i) == idlesson){
                                        if(i == id_less.size()-1){
                                            End.gofunction = 1;
                                            for(int j=0 ; j< id_func.size() ; j++) {
                                                if (id_func.get(j) == idfunction) {
                                                    if (now_less == idlesson){
                                                        int next_func = j+1;
                                                        mPresenter.update_idfunction(id_func.get(next_func));
                                                        mPresenter.update_idlesson(0);
                                                    }
                                                    break;
                                                }
                                            }
                                        }
                                        else{
                                            End.gofunction = 0;
                                            if (now_less == idlesson){
                                                int next_less = i+1;
                                                mPresenter.update_idlesson(id_less.get(next_less));
                                            }
                                        }
                                        break;
                                    }
                                }
                                A35.this.finish();
                                startActivity(new Intent(A35.this, End.class ));

                            } else {

                                TbActivity tb_new = mPresenter.getActivity(idlesson, ++activitynumber);
                                int id_at_new = tb_new.getId_ActivityType();

                                switch (id_at_new){

                                    case 1: break;
                                    case 2: break;

                                    case 3:
                                        //A3.idlesson = idlesson ;
                                        //A.idfunction = idfunction ;
                                        //A3.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A3.class));
                                        break;

                                    case 4:
                                        A4.idlesson = idlesson ;
                                        A4.idfunction = idfunction ;
                                        A4.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A4.class));
                                        break;

                                    case 5:
                                        A5.idlesson = idlesson ;
                                        A5.idfunction = idfunction ;
                                        A5.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A5.class));
                                        break;

                                    case 6:
                                        A6.idlesson = idlesson ;
                                        A6.idfunction = idfunction ;
                                        A6.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A6.class));
                                        break;

                                    case 7:
                                        A7.idlesson = idlesson ;
                                        A7.idfunction = idfunction ;
                                        A7.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A7.class));
                                        break;

                                    case 8:
                                        A8.idlesson = idlesson ;
                                        A8.idfunction = idfunction ;
                                        A8.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A8.class));
                                        break;

                                    case 9:
                                        A9.idlesson = idlesson ;
                                        A9.idfunction = idfunction ;
                                        A9.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A9.class));
                                        break;

                                    case 10: break;
                                    case 11: break;
                                    case 12: break;
                                    case 13: break;
                                    case 14: break;

                                    case 15:
                                        //A15.idlesson = idlesson ;
                                        //A.idfunction = idfunction ;
                                        //A15.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A15.class));
                                        break;

                                    case 16: break;
                                    case 17: break;

                                    case 18:
                                        A18.idlesson = idlesson ;
                                        A18.idfunction = idfunction ;
                                        A18.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A18.class));
                                        break;

                                    case 19:
                                        A19.idlesson = idlesson ;
                                        A19.idfunction = idfunction ;
                                        A19.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A19.class));
                                        break;

                                    case 20:
                                        //A20.idlesson = idlesson ;
                                        //A.idfunction = idfunction ;
                                        //A20.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A20.class));
                                        break;

                                    case 21: break;

                                    case 22:
                                        A22.idlesson = idlesson ;
                                        A22.idfunction = idfunction ;
                                        A22.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A22.class));
                                        break;

                                    case 23: break;

                                    case 24:
                                        A24.idlesson = idlesson ;
                                        A24.idfunction = idfunction ;
                                        A24.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A24.class));
                                        break;

                                    case 25:
                                        //A25.idlesson = idlesson ;
                                        //A.idfunction = idfunction ;
                                        //A25.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A25.class));
                                        break;

                                    case 26:
                                        A26.idlesson = idlesson ;
                                        A26.idfunction = idfunction ;
                                        A26.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A26.class));
                                        break;

                                    case 27:
                                        //A27.idlesson = idlesson ;
                                        //A.idfunction = idfunction ;
                                        //A27.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A27.class));
                                        break;

                                    case 28:
                                        A28.idlesson = idlesson ;
                                        A28.idfunction = idfunction ;
                                        A28.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A28.class));
                                        break;

                                    case 29:
                                        A29.idlesson = idlesson ;
                                        A29.idfunction = idfunction ;
                                        A29.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A29.class));
                                        break;

                                    case 30:
                                        A30.idlesson = idlesson ;
                                        A30.idfunction = idfunction ;
                                        A30.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A30.class));
                                        break;

                                    case 31:
                                        //A31.idlesson = idlesson ;
                                        //A.idfunction = idfunction ;
                                        //A31.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A31.class));
                                        break;

                                    case 32:
                                        //A32.idlesson = idlesson ;
                                        //A.idfunction = idfunction ;
                                        //A32.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A32.class));
                                        break;

                                    case 33:
                                        A33.idlesson = idlesson ;
                                        A33.idfunction = idfunction ;
                                        A33.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A33.class));
                                        break;

                                    case 34:
                                        A34.idlesson = idlesson ;
                                        A34.idfunction = idfunction ;
                                        A34.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A34.class));
                                        break;

                                    case 35:
                                        A35.idlesson = idlesson ;
                                        A35.idfunction = idfunction ;
                                        A35.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A35.class));
                                        break;

                                    case 36: break;

                                    case 37:
                                        //A37.idlesson = idlesson ;
                                        //A.idfunction = idfunction ;
                                        //A37.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A37.class));
                                        break;

                                    case 38:
                                        //A38.idlesson = idlesson ;
                                        //A.idfunction = idfunction ;
                                        //A38.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A38.class));
                                        break;

                                    case 39:
                                        A39.idlesson = idlesson ;
                                        A39.idfunction = idfunction ;
                                        A39.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A39.class));
                                        break;

                                    case 40:
                                        //A40.idlesson = idlesson ;
                                        //A.idfunction = idfunction ;
                                        //A40.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A40.class));
                                        break;

                                    case 41:
                                        //A41.idlesson = idlesson ;
                                        //A.idfunction = idfunction ;
                                        //A41.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A41.class));
                                        break;

                                    case 42:
                                        //A42.idlesson = idlesson ;
                                        //A.idfunction = idfunction ;
                                        //A42.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A42.class));
                                        break;

                                    case 43:
                                        //A43.idlesson = idlesson ;
                                        //A.idfunction = idfunction ;
                                        //A43.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A43.class));
                                        break;

                                    case 44:
                                        //A44.idlesson = idlesson ;
                                        //A.idfunction = idfunction ;
                                        //A44.activitynumber = activitynumber;
                                        A35.this.finish();
                                        startActivity(new Intent(A35.this,  A44.class));
                                        break;
                                }

                            }

                        }

                        break;
                }
            }
        });
    }

    private void setupMVP(){
        if ( mStateMaintainer.firstTimeIn() ) {
            initialize();
        } else {
            reinitialize();
        }
    }

    private void initialize(){
        Log.d(TAG, "initialize");
        setupComponent();
        mStateMaintainer.put(Main_Presenter.class.getSimpleName(), mPresenter);
    }

    private void reinitialize() {
        Log.d(TAG, "reinitialize");
        mPresenter = mStateMaintainer.get(Main_Presenter.class.getSimpleName());
        mPresenter.setView(this);
        if ( mPresenter == null )
            setupComponent();
    }

    private void setupComponent(){
        Log.d(TAG, "setupComponent");
        SampleApp.get(this)
                .getAppComponent()
                .getA35Component(new Main_Module(this))
                .inject(this);
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    public void set_data(TextView t){
        txt2[0].setText(t.getText());
        txt2[0].setTextSize(22);
        // disable
        t.setTextColor(getResources().getColor(R.color.gray));
        t.setBackgroundColor(getResources().getColor(R.color.gray));
        for(int i=0 ; i<text_answer.length ; i++){
            text_answer[i].setClickable(false);
        }

        // next
        position++;
        if(position > 0) {
            next.setTextColor(Color.WHITE);
            next.setBackgroundResource(R.drawable.btn_green);
        }
        if(position == 0){
            next.setTextColor(getResources().getColor(R.color.gray));
            next.setBackgroundResource(R.drawable.btn_gray);
        }
    }

    public void unset_data(TextView a){
        for(int j = 0 ; j < text_answer.length ; j++){
            if(a.getText().toString().equals( text_answer[j].getText().toString())){
                a.setText(tohi);
                // enable
                text_answer[j].setTextColor(getResources().getColor(R.color.colorPrimary));
                text_answer[j].setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
                for(int i=0 ; i<text_answer.length ; i++){
                    text_answer[i].setClickable(true);
                }
            }
        }
        // next
        position--;
        if(position > 0) {
            next.setTextColor(Color.WHITE);
            next.setBackgroundResource(R.drawable.btn_green);
        }
        if(position == 0){
            next.setTextColor(getResources().getColor(R.color.gray));
            next.setBackgroundResource(R.drawable.btn_gray);
        }
    }

    public int cheak(){
        int result = 0;
        if(txt2[0].getText().toString().equals(answer)){
            result = 1;
        }else{
            result = 2;
        }
        return result;
    }
}