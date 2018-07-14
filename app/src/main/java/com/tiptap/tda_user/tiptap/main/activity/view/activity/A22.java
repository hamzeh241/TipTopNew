package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.A22_Module;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Main_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;

import java.util.List;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import java.util.regex.Pattern;
import javax.inject.Inject;

public class A22 extends BaseActivity
        implements MVP_Main.RequiredViewOps {

    private static final String TAG = A22.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A22.class.getName());

    @Inject
    public MVP_Main.ProvidedPresenterOps mPresenter;



    TextView t[];
    EditText e[];


    boolean end = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a22);

        setupViews();
        setupMVP();

        tbActivity = mPresenter.getActivity(idlesson, activitynumber);
        max = mPresenter.max_Activitynumber(idlesson);
        int idactivity = tbActivity.get_id();
        tbActivityDetailList = mPresenter.getListActivityDetail(idactivity);
        //count = mPresenter.count_ActivityDetail(idactivity);

    }
    private void setupViews() {

        //mp = MediaPlayer.create(A22.this, R.raw.music);
        mp.setVolume(100,100);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                end = true;
                next.setTextColor(Color.WHITE);
                next.setBackgroundResource(R.drawable.btn_green);
            }
        });

        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setMax(mp.getDuration());
        seekUpdation();

        play = (Button)findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
            }
        });


        img = (NetworkImageView) findViewById(R.id.text);
       // img.setImageURI("");

        //String w = "... hello hi... how are you";
        //String w = "... hello hi how are you nice";
        //String w = "... hello how are you my ...";
        //String w = "... hello how are you my ... nice to meet you";
        //String w = "... hello hi ... how are you ...";
        //String w = "... hello hi... how are you ... i am busy call you later";
        //String w = "hello, how are you ...";
        //String w = "hello, how are you ... me to ...";
        //String w = "hello hi... how are you ... me to";
        //String w = "hello hi i am ... how are you";

        String w = "A:good ... . My ... is Alex. B:hello ... Robert. A:how is ... ? B: i am ... thanks. And ... ? A:not bad.";

        String [] list_w = w.split(Pattern.quote("..."));

        int start = 0;
        int end = 0;
        String s_s = w.substring(0, 3);
        if(s_s.equals("...")){start = 1;}
        String s_e = w.substring(w.length()-3, w.length());
        if(s_e.equals("...")){end = 1;}

        int t_number = 0;
        int id_w = 0;
        if(list_w[0].equals("")){
            id_w = 1;
            t_number = list_w.length-1;
        }else{
            t_number = list_w.length;
        }

        int e_number = 0;
        if(t_number > 1){
            e_number = (t_number-1)+(start)+(end);
        }else{
            e_number = (start)+(end);
        }

        int total = t_number + e_number;

        t = new TextView[t_number];
        int id_t = 0;
        e = new EditText[e_number];
        int id_e = 0;

        String now = "txt";

        LinearLayout l1= (LinearLayout)findViewById(R.id.l1);
        LinearLayout l2 = (LinearLayout)findViewById(R.id.l2);
        LinearLayout l3 = (LinearLayout)findViewById(R.id.l3);
        LinearLayout l4 = (LinearLayout)findViewById(R.id.l4);
        LinearLayout l5 = (LinearLayout)findViewById(R.id.l5);
        LinearLayout l6 = (LinearLayout)findViewById(R.id.l6);
        LinearLayout l7 = (LinearLayout)findViewById(R.id.l7);
        LinearLayout l8 = (LinearLayout)findViewById(R.id.l8);

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int added = 0;

        for(int i=0 ; i<total ; i++){

            final int finalId_e = id_e;

            // start
            if(i == 0 ){
                if(start == 1){
                    e[id_e] = new EditText(this);
                    e[id_e].setLayoutParams(params);
                    e[id_e].setEms(4);
                    e[id_e].setTextSize(16);
                    added = added + 5;
                    if( 0 <= added && added <= 33 ){
                        l1.addView(e[id_e]);
                    }
                    if( 34 <= added && added <= 66 ){
                        l2.addView(e[id_e]);
                    }
                    if( 67 <= added && added <= 99 ){
                        l3.addView(e[id_e]);
                    }
                    if( 100 <= added && added <= 132 ){
                        l4.addView(e[id_e]);
                    }
                    if( 133 <= added && added <= 165 ){
                        l5.addView(e[id_e]);
                    }
                    id_e++;
                }

                if(start == 0){
                    t[id_t] = new TextView(this);
                    t[id_t].setLayoutParams(params);
                    t[id_t].setText(list_w[id_w]);
                    t[id_t].setTextSize(16);
                    added = added + list_w[id_w].length();
                    if( 0 <= added && added <= 33 ){
                        l1.addView(t[id_t]);
                    }
                    if( 34 <= added && added <= 66 ){
                        l2.addView(t[id_t]);
                    }
                    if( 67 <= added && added <= 99 ){
                        l3.addView(t[id_t]);
                    }
                    if( 100 <= added && added <= 132 ){
                        l4.addView(t[id_t]);
                    }
                    if( 133 <= added && added <= 165 ){
                        l5.addView(t[id_t]);
                    }
                    now = "edt";
                    id_w++;
                    id_t++;
                }
            }

            if(i!=0 && i!=total-1){
                // textview
                switch (now) {
                    case "txt":
                        t[id_t] = new TextView(this);
                        t[id_t].setLayoutParams(params);
                        t[id_t].setText(list_w[id_w]);
                        t[id_t].setTextSize(16);
                        added = added + list_w[id_w].length();
                        Toast.makeText(getActivityContext(), added+"" , Toast.LENGTH_LONG).show();
                        if( 0 <= added && added <= 33 ){
                            l1.addView(t[id_t]);
                        }
                        if( 34 <= added && added <= 66 ){
                            l2.addView(t[id_t]);
                        }
                        if( 67 <= added && added <= 99 ){
                            l3.addView(t[id_t]);
                        }
                        if( 100 <= added && added <= 132 ){
                            l4.addView(t[id_t]);
                        }
                        if( 133 <= added && added <= 165 ){
                            l5.addView(t[id_t]);
                        }
                        now = "edt";
                        id_w++;
                        id_t++;
                        break;

                    case "edt":
                        e[id_e] = new EditText(this);
                        e[id_e].setLayoutParams(params);
                        e[id_e].setEms(4);
                        e[id_e].setTextSize(16);
                        added = added + 5;
                        if( 0 <= added && added <= 33 ){
                            l1.addView(e[id_e]);
                        }
                        if( 34 <= added && added <= 66 ){
                            l2.addView(e[id_e]);
                        }
                        if( 67 <= added && added <= 99 ){
                            l3.addView(e[id_e]);
                        }
                        if( 100 <= added && added <= 132 ){
                            l4.addView(e[id_e]);
                        }
                        if( 133 <= added && added <= 165 ){
                            l5.addView(e[id_e]);
                        }
                        now = "txt";
                        id_e++;
                        break;
                }
            }

            // end
            if(i == total-1){
                if(end == 1){
                    e[id_e] = new EditText(this);
                    e[id_e].setLayoutParams(params);
                    e[id_e].setEms(4);
                    e[id_e].setTextSize(16);
                    added = added + 5;
                    if( 0 <= added && added <= 33 ){
                        l1.addView(e[id_e]);
                    }
                    if( 34 <= added && added <= 66 ){
                        l2.addView(e[id_e]);
                    }
                    if( 67 <= added && added <= 99 ){
                        l3.addView(e[id_e]);
                    }
                    if( 100 <= added && added <= 132 ){
                        l4.addView(e[id_e]);
                    }
                    if( 133 <= added && added <= 165 ){
                        l5.addView(e[id_e]);
                    }
                    id_e++;
                }

                if(end == 0){
                    t[id_t] = new TextView(this);
                    t[id_t].setLayoutParams(params);
                    t[id_t].setText(list_w[id_w]);
                    t[id_t].setTextSize(16);
                    added = added + list_w[id_w].length();
                    if( 0 <= added && added <= 33 ){
                        l1.addView(t[id_t]);
                    }
                    if( 34 <= added && added <= 66 ){
                        l2.addView(t[id_t]);
                    }
                    if( 67 <= added && added <= 99 ){
                        l3.addView(t[id_t]);
                    }
                    if( 100 <= added && added <= 132 ){
                        l4.addView(t[id_t]);
                    }
                    if( 133 <= added && added <= 165 ){
                        l5.addView(t[id_t]);
                    }
                    now = "edt";
                    id_w++;
                    id_t++;
                }

            }
        }

        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //boolean answer = cheak_answer();
                boolean answer = true;
                if(answer) {

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
                        A22.this.finish();
                        startActivity(new Intent(A22.this, End.class ));

                    } else {

                        TbActivity tb_new = mPresenter.getActivity(idlesson, ++activitynumber);
                        int id_at_new = tb_new.getId_ActivityType();

                        switch (id_at_new){

                            case 1: break;
                            case 2: break;

                            case 3:
                                //A3.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A3.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A3.class));
                                break;

                            case 4:
                                A4.idlesson = idlesson ;
                                A4.idfunction = idfunction ;
                                A4.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A4.class));
                                break;

                            case 5:
                                A5.idlesson = idlesson ;
                                A5.idfunction = idfunction ;
                                A5.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A5.class));
                                break;

                            case 6:
                                A6.idlesson = idlesson ;
                                A6.idfunction = idfunction ;
                                A6.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A6.class));
                                break;

                            case 7:
                                A7.idlesson = idlesson ;
                                A7.idfunction = idfunction ;
                                A7.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A7.class));
                                break;

                            case 8:
                                A8.idlesson = idlesson ;
                                A8.idfunction = idfunction ;
                                A8.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A8.class));
                                break;

                            case 9:
                                A9.idlesson = idlesson ;
                                A9.idfunction = idfunction ;
                                A9.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A9.class));
                                break;

                            case 10: break;
                            case 11: break;
                            case 12: break;
                            case 13: break;
                            case 14: break;

                            case 15:
                                //A15.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A15.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A15.class));
                                break;

                            case 16: break;
                            case 17: break;

                            case 18:
                                A18.idlesson = idlesson ;
                                A18.idfunction = idfunction ;
                                A18.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A18.class));
                                break;

                            case 19:
                                A19.idlesson = idlesson ;
                                A19.idfunction = idfunction ;
                                A19.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A19.class));
                                break;

                            case 20:
                                //A20.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A20.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A20.class));
                                break;

                            case 21: break;

                            case 22:
                                A22.idlesson = idlesson ;
                                A22.idfunction = idfunction ;
                                A22.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A22.class));
                                break;

                            case 23: break;

                            case 24:
                                A24.idlesson = idlesson ;
                                A24.idfunction = idfunction ;
                                A24.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A24.class));
                                break;

                            case 25:
                                //A25.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A25.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A25.class));
                                break;

                            case 26:
                                A26.idlesson = idlesson ;
                                A26.idfunction = idfunction ;
                                A26.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A26.class));
                                break;

                            case 27:
                                //A27.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A27.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A27.class));
                                break;

                            case 28:
                                A28.idlesson = idlesson ;
                                A28.idfunction = idfunction ;
                                A28.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A28.class));
                                break;

                            case 29:
                                A29.idlesson = idlesson ;
                                A29.idfunction = idfunction ;
                                A29.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A29.class));
                                break;

                            case 30:
                                A30.idlesson = idlesson ;
                                A30.idfunction = idfunction ;
                                A30.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A30.class));
                                break;

                            case 31:
                                //A31.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A31.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A31.class));
                                break;

                            case 32:
                                //A32.idlesson = idlesson ;
                                //A.idfunction = idfunction ;
                                //A32.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A32.class));
                                break;

                            case 33:
                                A33.idlesson = idlesson ;
                                A33.idfunction = idfunction ;
                                A33.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A33.class));
                                break;

                            case 34:
                                A34.idlesson = idlesson ;
                                A34.idfunction = idfunction ;
                                A34.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A34.class));
                                break;

                            case 35:
                                A35.idlesson = idlesson ;
                                A35.idfunction = idfunction ;
                                A35.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A35.class));
                                break;

                            case 36: break;

                            case 37:
                                //A37.idlesson = idlesson ;
                                //A.idfunction = idfunction ;
                                //A37.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A37.class));
                                break;

                            case 38:
                                //A38.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A38.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A38.class));
                                break;

                            case 39:
                                A39.idlesson = idlesson ;
                                A39.idfunction = idfunction ;
                                A39.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A39.class));

                                break;

                            case 40:
                                //A40.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A40.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A40.class));
                                break;

                            case 41:
                                //A41.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A41.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A41.class));
                                break;

                            case 42:
                                //A42.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A42.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A42.class));
                                break;

                            case 43:
                                //A43.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A43.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A43.class));
                                break;

                            case 44:
                                //A44.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A44.activitynumber = activitynumber;
                                A22.this.finish();
                                startActivity(new Intent(A22.this,  A44.class));
                                break;
                        }
                    }
                }
            }
        });
    }

    Runnable run = new Runnable() {
        @Override public void run() {
            seekUpdation();
        }
    };

    public void seekUpdation() {
        seekBar.setProgress(mp.getCurrentPosition());
        seekHandler.postDelayed(run, 500);
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
                .getA22Component(new A22_Module(this))
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

    public boolean cheak_answer(){
        boolean result = false;
        for(int i=0 ; i<e.length ; i++){
            if( tbActivityDetailList.get(i).getTitle1().equals( e[i].toString() ) ){
                result = true;
            }else{
                result = false;
            }
        }
        return result;
    }
}