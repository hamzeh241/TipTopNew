package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.A32_Module;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A32;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A32_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivityDetail;
import com.tiptap.tda_user.tiptap.main.activity.view.lesson.Lesson;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;
import javax.inject.Inject;

public class A32 extends AppCompatActivity
        implements MVP_A32.RequiredViewOps, OnClickListener{

    private static final String TAG = A32.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A32.class.getName());

    @Inject
    public MVP_A32.ProvidedPresenterOps mPresenter;

    public static int idlesson;
    public static int idfunction;
    public static int activitynumber;
    public static String Act_Status;
    public static int idactivity;
    TbActivity tbActivity;
    int max,now_less;
    List<TbActivityDetail> tbActivityDetailList;
    LinearLayout l[];
    int added = 0;
    ImageView b_mic[],b_play[];
    int id_bplay = 0;
    TextView t1,t2;
    TextView t[];
    TextView e[];
    String tohi1 = "----------------", tohi2 = "----------------------------------------";
    String ans[];
    int xali = 0;
    String path1[];
    int count=0;
    Button next;
    private MediaPlayer mp,mpt,mpf;
    int mpLength;
    boolean end = false;
    final int REQ_CODE_SPEECH_INPUT = 100;
    TextView now_say;
    ProgressBar p;
    String z1[];
    int all;
    int fill = 0;
    String url_download = "http://tiptop.tdaapp.ir/image/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a32);

        setupViews();
        setupMVP();

        // hide keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        max = mPresenter.max_Activitynumber(idlesson);

        // first
        if(Act_Status.equals("first")){
            tbActivity = mPresenter.getActivity(idlesson, activitynumber);
        }
        // second
        if(Act_Status.equals("second")) {
            tbActivity = mPresenter.getActivity2(idactivity);
        }

        idactivity = tbActivity.get_id();
        tbActivityDetailList = mPresenter.getListActivityDetail(idactivity);
        count = tbActivityDetailList.size();
        after_setup();
    }

    private void setupViews() {

        t1 = (TextView)findViewById(R.id.title1);
        t2 = (TextView)findViewById(R.id.title2);

        p = (ProgressBar)findViewById(R.id.p);
        p.setMax(100);

        LinearLayout l1= (LinearLayout)findViewById(R.id.l1);
        LinearLayout l2 = (LinearLayout)findViewById(R.id.l2);
        LinearLayout l3 = (LinearLayout)findViewById(R.id.l3);
        LinearLayout l4  = (LinearLayout)findViewById(R.id.l4);
        LinearLayout l5 = (LinearLayout)findViewById(R.id.l5);
        LinearLayout l6 = (LinearLayout)findViewById(R.id.l6);
        LinearLayout l7 = (LinearLayout)findViewById(R.id.l7);
        LinearLayout l8 = (LinearLayout)findViewById(R.id.l8);

        l = new LinearLayout[]{l1, l2, l3, l4, l5, l6, l7, l8};
        next = (Button) findViewById(R.id.next);
        mp = new MediaPlayer();
        mpt = MediaPlayer.create (this, R.raw.true_sound);
        mpf =  MediaPlayer.create (this, R.raw.false_sound);
    }

    private void after_setup(){

        all = mPresenter.countActivity(idlesson);

        // set all activity false in activitynumber = 1
        if(activitynumber == 1 && Act_Status.equals("first")){
            mPresenter.false_activitys(idlesson);
        }

        // show passed activity
        List<Integer> p1 = mPresenter.activity_true(idlesson);
        int p2 = p1.size();
        if(p2 == 0){
            p.setProgress(0);
        }else{
            double d_number = (double) p2/all;
            int i_number = (int) (d_number*100);
            p.setProgress(i_number);
        }

        t1.setText(R.string.A32_EN);
        t1.setTextColor(getResources().getColor(R.color.my_black));

        int lang_id = mPresenter.getlanguage();
        switch (lang_id){
            // فارسی
            case 1:
                t2.setText(R.string.A32_FA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // کردی
            case 2:
                t2.setText(R.string.A32_KU);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // ترکی آذری
            case 3:
                t2.setText(R.string.A32_TA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // چینی
            case 4:
                t2.setText(R.string.A32_CH);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
        }

        next.setOnClickListener(this);

        /* ------------------------------------------------------------------------------------------------------ */
        // each row - title2

        for(int i=0 ; i<count ; i++) {
            String temp = tbActivityDetailList.get(i).getTitle2();
            if (temp.equals("null")) {

            }else{
                int have = 0;
                for(int j=0 ; j<temp.length() ; j++){
                    if(temp.charAt(j) == '_'){
                        have = 1;
                    }
                }
                if(have == 1){
                    String z[] = temp.split("_");
                    xali = xali + (z.length);
                }
                else if(have == 0){
                    xali = xali + 1;
                }
            }
        }

        ans = new String[xali];
        int c = 0;
        for(int i=0 ; i<count ; i++) {
            String temp = tbActivityDetailList.get(i).getTitle2();
            if (temp.equals("null")) {

            }else{
                int have = 0;
                for(int j=0 ; j<temp.length() ; j++){
                    if(temp.charAt(j) == '_'){
                        have = 1;
                    }
                }
                if(have == 1){
                    String z[] = temp.split("_");
                    for(int j=0 ; j<z.length ; j++){
                        ans[c] = z[j];
                        c++;
                    }
                }
                else if(have == 0){
                    ans[c] = temp;
                    c++;
                }
            }
        }

        e = new TextView[xali];
        int id_e = 0;

        // all voice in path1[]
        int cpath = 0;
        for(int p=0 ; p<count ; p++){
            String temp = tbActivityDetailList.get(p).getPath1();
            if(temp.equals("null")){

            }else{
                cpath++;
            }
        }

        path1 = new String[cpath];
        int pcount = 0;
        for(int p=0 ; p<count ; p++){
            String temp = tbActivityDetailList.get(p).getPath1();
            if(temp.equals("null")){

            }else{
               path1[pcount] = temp;
               pcount++;
            }
        }

        b_mic = new ImageView[xali];
        b_play = new ImageView[path1.length];

        // ------------------------------------------------------------------------------------------------------
        // each row - title1
        for(int i=0 ; i<count ; i++) {

            // ------------------------------------------------------------------------------------------------------
            // splite textview & edittext

            String w = tbActivityDetailList.get(i).getTitle1();
            w = w.replace("…", "...");
            String[] list_w = w.split(Pattern.quote("..."));

            int start = 0;
            int end = 0;

            // only ...
            if(w.equals("...")){
                start = 1;
            }else{
                String s_s = w.substring(0, 3);
                if (s_s.equals("...")) {
                    start = 1;
                }
                String s_e = w.substring(w.length() - 3, w.length());
                if (s_e.equals("...")) {
                    end = 1;
                }
            }

            int t_number = 0;
            int id_w = 0;

            if(w.equals("...")){
                t_number = 0;
            }else{
                if (list_w[0].equals("")) {
                    id_w = 1;
                    t_number = list_w.length - 1;
                } else {
                    t_number = list_w.length;
                }
            }

            int e_number = 0;
            if (t_number > 1) {
                e_number = (t_number - 1) + (start) + (end);
            } else {
                e_number = (start) + (end);
            }

            int total = t_number + e_number;
            t = new TextView[t_number];
            int id_t = 0;

            // ------------------------------------------------------------------------------------------------------
            // add to view
            String now = "txt";
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(10,6,6,6);

            if(total == 1){
                // only ...
                if(w.equals("...")) {
                    b_mic[id_e] = new ImageView(this);
                    b_mic[id_e].setBackgroundResource(R.drawable.mic1);
                    b_mic[id_e].setPadding(12,12,12,12);
                    l[added].addView(b_mic[id_e]);

                    e[id_e] = new TextView(this);
                    e[id_e].setLayoutParams(params);
                    e[id_e].setInputType(InputType.TYPE_CLASS_TEXT);
                    e[id_e].setText(tohi2);
                    e[id_e].setTextSize(16);
                    e[id_e].setTextColor(getResources().getColor(R.color.blue));
                    e[id_e].addTextChangedListener(new CheckEdit());
                    l[added].addView(e[id_e]);

                    final int finalId_e1 = id_e;
                    b_mic[id_e].setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            now_say = e[finalId_e1];
                            promptSpeechInput();
                        }
                    });

                    id_e++;
                    added++;
                }
                // only text without ...
                else{
                    b_play[id_bplay] = new ImageView(this);
                    b_play[id_bplay].setBackgroundResource(R.drawable.play1);
                    b_play[id_bplay].setPadding(12,12,12,12);
                    l[added].addView(b_play[id_bplay]);

                    t[id_t] = new TextView(this);
                    t[id_t].setLayoutParams(params);
                    t[id_t].setText(list_w[0]);
                    t[id_t].setTextSize(16);
                    l[added].addView(t[id_t]);

                    final int final_id_bplay = id_bplay;
                    b_play[id_bplay].setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            play(final_id_bplay);
                        }
                    });

                    id_bplay++;
                    added++;
                }

            }else{
                for(int xi=0 ; xi<total ; xi++){

                    final int finalId_e = id_e;

                    // start
                    if(xi == 0){
                        if(start == 1){
                            b_mic[id_e] = new ImageView(this);
                            b_mic[id_e].setBackgroundResource(R.drawable.mic1);
                            b_mic[id_e].setPadding(12,12,12,12);
                            l[added].addView(b_mic[id_e]);

                            e[id_e] = new TextView(this);
                            e[id_e].setLayoutParams(params);
                            e[id_e].setInputType(InputType.TYPE_CLASS_TEXT);
                            e[id_e].setText(tohi1);
                            e[id_e].setTextSize(16);
                            e[id_e].setTextColor(getResources().getColor(R.color.blue));
                            e[id_e].addTextChangedListener(new CheckEdit());
                            l[added].addView(e[id_e]);

                            final int finalId_e1 = id_e;
                            b_mic[id_e].setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    now_say = e[finalId_e1];
                                    promptSpeechInput();
                                }
                            });

                            id_e++;
                        }

                        if(start == 0){
                            t[id_t] = new TextView(this);
                            t[id_t].setLayoutParams(params);
                            t[id_t].setText(list_w[id_w]);
                            t[id_t].setTextSize(16);
                            l[added].addView(t[id_t]);

                            now = "edt";
                            id_w++;
                            id_t++;
                        }
                    }

                    if(xi!=0 && xi!=total-1){
                        // textview
                        switch (now) {
                            case "txt":
                                t[id_t] = new TextView(this);
                                t[id_t].setLayoutParams(params);
                                t[id_t].setText(list_w[id_w]);
                                t[id_t].setTextSize(16);
                                l[added].addView(t[id_t]);

                                now = "edt";
                                id_w++;
                                id_t++;
                                break;

                            case "edt":
                                b_mic[id_e] = new ImageView(this);
                                b_mic[id_e].setBackgroundResource(R.drawable.mic1);
                                b_mic[id_e].setPadding(12,12,12,12);
                                l[added].addView(b_mic[id_e]);

                                e[id_e] = new TextView(this);
                                e[id_e].setLayoutParams(params);
                                e[id_e].setInputType(InputType.TYPE_CLASS_TEXT);
                                e[id_e].setText(tohi1);
                                e[id_e].setTextSize(16);
                                e[id_e].setTextColor(getResources().getColor(R.color.blue));
                                e[id_e].addTextChangedListener(new CheckEdit());
                                l[added].addView(e[id_e]);

                                final int finalId_e1 = id_e;
                                b_mic[id_e].setOnClickListener(new OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        now_say = e[finalId_e1];
                                        promptSpeechInput();
                                    }
                                });

                                now = "txt";
                                id_e++;
                                break;
                        }
                    }

                    // end
                    if(xi == total-1){
                        if(end == 1){
                            b_mic[id_e] = new ImageView(this);
                            b_mic[id_e].setBackgroundResource(R.drawable.mic1);
                            b_mic[id_e].setPadding(12,12,12,12);
                            l[added].addView(b_mic[id_e]);

                            e[id_e] = new TextView(this);
                            e[id_e].setLayoutParams(params);
                            e[id_e].setInputType(InputType.TYPE_CLASS_TEXT);
                            e[id_e].setText(tohi1);
                            e[id_e].setTextSize(16);
                            e[id_e].setTextColor(getResources().getColor(R.color.blue));
                            e[id_e].addTextChangedListener(new CheckEdit());
                            l[added].addView(e[id_e]);

                            final int finalId_e1 = id_e;
                            b_mic[id_e].setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    now_say = e[finalId_e1];
                                    promptSpeechInput();
                                }
                            });

                            id_e++;
                        }

                        if(end == 0){
                            t[id_t] = new TextView(this);
                            t[id_t].setLayoutParams(params);
                            t[id_t].setText(list_w[id_w]);
                            t[id_t].setTextSize(16);
                            l[added].addView(t[id_t]);

                            now = "edt";
                            id_w++;
                            id_t++;
                        }
                    }
                }
                added++;
            }
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.voice){
            promptSpeechInput();
        }

        if (v.getId() == R.id.next) {

            mp.stop();
            switch (next.getText().toString()) {

                case "check":

                    fill = 0;
                    for(int i=0 ; i<e.length ; i++){
                        if (e[i].getText().toString().equals(tohi1) || e[i].getText().toString().equals(tohi2)) {
                            // nothing
                        }else {
                            fill++;
                        }
                    }

                    if(fill == e.length){
                        if(end){

                            String answer = cheak();
                            if (answer.equals("")) {

                                // update - true
                                mPresenter.update_activity(idactivity);

                                // show passed activity
                                List<Integer> passed1 = mPresenter.activity_true(idlesson);
                                int passed2 = passed1.size();
                                if(passed2 == 0){
                                    p.setProgress(0);
                                }else{
                                    double d_number = (double) passed2/all;
                                    int i_number = (int) (d_number*100);
                                    p.setProgress(i_number);
                                }

                                // Clickable_false
                                t1.setClickable(false);
                                t2.setClickable(false);
                                for(int i=0 ; i<b_mic.length ; i++){
                                    b_mic[i].setClickable(false);
                                }
                                for(int i=0 ; i<b_play.length ; i++){
                                    b_play[i].setClickable(false);
                                }
                                p.setClickable(false);

                                // Fragment_true
                                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder1);
                                Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                                linearLayout.setAnimation(slide_down);
                                linearLayout.setVisibility(View.VISIBLE);

                                Fragment_True f1 = new Fragment_True();
                                FragmentManager fragMan = getSupportFragmentManager();
                                FragmentTransaction fragTransaction = fragMan.beginTransaction();
                                fragTransaction.add(R.id.fragment1, f1);
                                fragTransaction.commit();

                                // play sound
                                mpt.start();

                            } else {

                                // Clickable_false
                                t1.setClickable(false);
                                t2.setClickable(false);
                                for(int i=0 ; i<b_mic.length ; i++){
                                    b_mic[i].setClickable(false);
                                }
                                for(int i=0 ; i<b_play.length ; i++){
                                    b_play[i].setClickable(false);
                                }
                                p.setClickable(false);

                                // Fragment_false
                                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder2);
                                Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                                linearLayout.setAnimation(slide_down);
                                linearLayout.setVisibility(View.VISIBLE);

                                Fragment_False f2 = new Fragment_False();
                                f2.t.setText(answer);

                                FragmentManager fragMan = getSupportFragmentManager();
                                FragmentTransaction fragTransaction = fragMan.beginTransaction();
                                fragTransaction.add(R.id.fragment2, f2);
                                fragTransaction.commit();

                                // play sound
                                mpf.start();
                            }

                            next.setTextColor(Color.WHITE);
                            next.setBackgroundResource(R.drawable.btn_green);
                            next.setText("countinue");

                        }else{
                            Toast.makeText(getApplicationContext(), "به فایل های صوتی گوش دهید", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "جاهای خالی را پر کنید", Toast.LENGTH_LONG).show();
                    }

                    break;

                case "countinue":

                    if(fill == e.length && end){

                        // first
                        if(Act_Status.equals("first")){

                            // max - end of lesson
                            if(activitynumber == max) {

                                // list of false answer
                                List<Integer> id_act_false = mPresenter.activity_false(idlesson);
                                int number = id_act_false.size();

                                // number = 0 and update
                                if (number == 0) {

                                    // get now lesson
                                    now_less = mPresenter.now_IdLesson();

                                    // post

                                    // update
                                    List<Integer> id_less = mPresenter.lesson(idfunction);
                                    List<Integer> id_func = mPresenter.function();

                                    for (int i = 0; i < id_less.size(); i++) {
                                        if (id_less.get(i) == idlesson) {
                                            if (i == id_less.size() - 1) {
                                                End.gofunction = 1;
                                                for (int j = 0; j < id_func.size(); j++) {
                                                    if (id_func.get(j) == idfunction) {
                                                        if (now_less == idlesson) {
                                                            int next_func = j + 1;
                                                            mPresenter.update_idfunction(id_func.get(next_func));
                                                            mPresenter.update_idlesson(0);
                                                        }
                                                        break;
                                                    }
                                                }
                                            } else {
                                                End.gofunction = 0;
                                                if (now_less == idlesson) {
                                                    int next_less = i + 1;
                                                    mPresenter.update_idlesson(id_less.get(next_less));
                                                }
                                            }
                                            break;
                                        }
                                    }
                                    A32.this.finish();
                                    startActivity(new Intent(A32.this, End.class));
                                }

                                // number != 0 and go on to Next
                                else {

                                    // next is random
                                    int max_range = (id_act_false.size())-1;
                                    int min_range = 0;
                                    int rnd = new Random().nextInt(max_range - min_range + 1) + min_range;
                                    int id_act = id_act_false.get(rnd);

                                    TbActivity tb_new_f = mPresenter.getActivity2(id_act);
                                    int id_at_new_f = tb_new_f.getId_ActivityType();

                                    // second
                                    switch (id_at_new_f) {

                                        case 1: break;
                                        case 2: break;

                                        case 3:
                                            A3.idlesson = idlesson ;
                                            A3.idfunction = idfunction ;
                                            A3.idactivity = id_act;
                                            A3.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A3.class));
                                            break;

                                        case 4:
                                            A4.idlesson = idlesson ;
                                            A4.idfunction = idfunction ;
                                            A4.idactivity = id_act;
                                            A4.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A4.class));
                                            break;

                                        case 5:
                                            A5.idlesson = idlesson ;
                                            A5.idfunction = idfunction ;
                                            A5.idactivity = id_act;
                                            A5.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A5.class));
                                            break;

                                        case 6:
                                            A6.idlesson = idlesson ;
                                            A6.idfunction = idfunction ;
                                            A6.idactivity = id_act;
                                            A6.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A6.class));
                                            break;

                                        case 7:
                                            A7.idlesson = idlesson ;
                                            A7.idfunction = idfunction ;
                                            A7.idactivity = id_act;
                                            A7.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A7.class));
                                            break;

                                        case 8:
                                            A8.idlesson = idlesson ;
                                            A8.idfunction = idfunction ;
                                            A8.idactivity = id_act;
                                            A8.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A8.class));
                                            break;

                                        case 9:
                                            A9.idlesson = idlesson ;
                                            A9.idfunction = idfunction ;
                                            A9.idactivity = id_act;
                                            A9.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A9.class));
                                            break;

                                        case 10: break;
                                        case 11: break;
                                        case 12: break;
                                        case 13: break;
                                        case 14: break;

                                        case 15:
                                            A15.idlesson = idlesson ;
                                            A15.idfunction = idfunction ;
                                            A15.idactivity = id_act;
                                            A15.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A15.class));
                                            break;

                                        case 16: break;
                                        case 17: break;

                                        case 18:
                                            A18.idlesson = idlesson ;
                                            A18.idfunction = idfunction ;
                                            A18.idactivity = id_act;
                                            A18.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A18.class));
                                            break;

                                        case 19:
                                            A19.idlesson = idlesson ;
                                            A19.idfunction = idfunction ;
                                            A19.idactivity = id_act;
                                            A19.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A19.class));
                                            break;

                                        case 20:
                                            A20.idlesson = idlesson ;
                                            A20.idfunction = idfunction ;
                                            A20.idactivity = id_act;
                                            A20.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A20.class));
                                            break;

                                        case 21: break;

                                        case 22:
                                            A22.idlesson = idlesson ;
                                            A22.idfunction = idfunction ;
                                            A22.idactivity = id_act;
                                            A22.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A22.class));
                                            break;

                                        case 23: break;

                                        case 24:
                                            A24.idlesson = idlesson ;
                                            A24.idfunction = idfunction ;
                                            A24.idactivity = id_act;
                                            A24.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A24.class));
                                            break;

                                        case 25:
                                            //A25.idlesson = idlesson ;
                                            // A.idfunction = idfunction ;
                                            //A6.idactivity = id_act;
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A25.class));
                                            break;

                                        case 26:
                                            A26.idlesson = idlesson ;
                                            A26.idfunction = idfunction ;
                                            A26.idactivity = id_act;
                                            A26.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A26.class));
                                            break;

                                        case 27:
                                            A27.idlesson = idlesson ;
                                            A27.idfunction = idfunction ;
                                            A27.idactivity = id_act;
                                            A27.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A27.class));
                                            break;

                                        case 28:
                                            A28.idlesson = idlesson ;
                                            A28.idfunction = idfunction ;
                                            A28.idactivity = id_act;
                                            A28.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A28.class));
                                            break;

                                        case 29:
                                            A29.idlesson = idlesson ;
                                            A29.idfunction = idfunction ;
                                            A29.idactivity = id_act;
                                            A29.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A29.class));
                                            break;

                                        case 30:
                                            A30.idlesson = idlesson ;
                                            A30.idfunction = idfunction ;
                                            A30.idactivity = id_act;
                                            A30.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A30.class));
                                            break;

                                        case 31:
                                            //A31.idlesson = idlesson ;
                                            // A.idfunction = idfunction ;
                                            //A6.idactivity = id_act;
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A31.class));
                                            break;

                                        case 32:
                                            A32.idlesson = idlesson ;
                                            A32.idfunction = idfunction ;
                                            A32.idactivity = id_act;
                                            A32.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A32.class));
                                            break;

                                        case 33:
                                            A33.idlesson = idlesson ;
                                            A33.idfunction = idfunction ;
                                            A33.idactivity = id_act;
                                            A33.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A33.class));
                                            break;

                                        case 34:
                                            A34.idlesson = idlesson ;
                                            A34.idfunction = idfunction ;
                                            A34.idactivity = id_act;
                                            A34.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A34.class));
                                            break;

                                        case 35:
                                            A35.idlesson = idlesson ;
                                            A35.idfunction = idfunction ;
                                            A35.idactivity = id_act;
                                            A35.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A35.class));
                                            break;

                                        case 36: break;

                                        case 37:
                                            A37.idlesson = idlesson ;
                                            A37.idfunction = idfunction ;
                                            A37.idactivity = id_act;
                                            A37.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A37.class));
                                            break;

                                        case 38:
                                            //A38.idlesson = idlesson ;
                                            // A.idfunction = idfunction ;
                                            //A6.idactivity = id_act;
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A38.class));
                                            break;

                                        case 39:
                                            A39.idlesson = idlesson ;
                                            A39.idfunction = idfunction ;
                                            A39.idactivity = id_act;
                                            A39.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A39.class));

                                            break;

                                        case 40:
                                            //A40.idlesson = idlesson ;
                                            // A.idfunction = idfunction ;
                                            //A6.idactivity = id_act;
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A40.class));
                                            break;

                                        case 41:
                                            //A41.idlesson = idlesson ;
                                            // A.idfunction = idfunction ;
                                            //A6.idactivity = id_act;
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A41.class));
                                            break;

                                        case 42:
                                            //A42.idlesson = idlesson ;
                                            // A.idfunction = idfunction ;
                                            //A6.idactivity = id_act;
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A42.class));
                                            break;

                                        case 43:
                                            //A43.idlesson = idlesson ;
                                            // A.idfunction = idfunction ;
                                            //A6.idactivity = id_act;
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A43.class));
                                            break;

                                        case 44:
                                            //A44.idlesson = idlesson ;
                                            // A.idfunction = idfunction ;
                                            //A6.idactivity = id_act;
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A44.class));
                                            break;

                                        case 46:
                                            A46.idlesson = idlesson ;
                                            A46.idfunction = idfunction ;
                                            A46.idactivity = id_act;
                                            A46.Act_Status = "second";
                                            A32.this.finish();
                                            startActivity(new Intent(A32.this,  A46.class));
                                            break;
                                    }
                                }

                            } else {

                                TbActivity tb_new = mPresenter.getActivity(idlesson, ++activitynumber);
                                int id_at_new = tb_new.getId_ActivityType();

                                // first
                                switch (id_at_new){

                                    case 1: break;
                                    case 2: break;

                                    case 3:
                                        A3.idlesson = idlesson ;
                                        A3.idfunction = idfunction ;
                                        A3.activitynumber = activitynumber;
                                        A3.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A3.class));
                                        break;

                                    case 4:
                                        A4.idlesson = idlesson ;
                                        A4.idfunction = idfunction ;
                                        A4.activitynumber = activitynumber;
                                        A4.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A4.class));
                                        break;

                                    case 5:
                                        A5.idlesson = idlesson ;
                                        A5.idfunction = idfunction ;
                                        A5.activitynumber = activitynumber;
                                        A5.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A5.class));
                                        break;

                                    case 6:
                                        A6.idlesson = idlesson ;
                                        A6.idfunction = idfunction ;
                                        A6.activitynumber = activitynumber;
                                        A6.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A6.class));
                                        break;

                                    case 7:
                                        A7.idlesson = idlesson ;
                                        A7.idfunction = idfunction ;
                                        A7.activitynumber = activitynumber;
                                        A7.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A7.class));
                                        break;

                                    case 8:
                                        A8.idlesson = idlesson ;
                                        A8.idfunction = idfunction ;
                                        A8.activitynumber = activitynumber;
                                        A8.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A8.class));
                                        break;

                                    case 9:
                                        A9.idlesson = idlesson ;
                                        A9.idfunction = idfunction ;
                                        A9.activitynumber = activitynumber;
                                        A9.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A9.class));
                                        break;

                                    case 10: break;
                                    case 11: break;
                                    case 12: break;
                                    case 13: break;
                                    case 14: break;

                                    case 15:
                                        A15.idlesson = idlesson ;
                                        A15.idfunction = idfunction ;
                                        A15.activitynumber = activitynumber;
                                        A15.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A15.class));
                                        break;

                                    case 16: break;
                                    case 17: break;

                                    case 18:
                                        A18.idlesson = idlesson ;
                                        A18.idfunction = idfunction ;
                                        A18.activitynumber = activitynumber;
                                        A18.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A18.class));
                                        break;

                                    case 19:
                                        A19.idlesson = idlesson ;
                                        A19.idfunction = idfunction ;
                                        A19.activitynumber = activitynumber;
                                        A19.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A19.class));
                                        break;

                                    case 20:
                                        A20.idlesson = idlesson ;
                                        A20.idfunction = idfunction ;
                                        A20.activitynumber = activitynumber;
                                        A20.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A20.class));
                                        break;

                                    case 21: break;

                                    case 22:
                                        A22.idlesson = idlesson ;
                                        A22.idfunction = idfunction ;
                                        A22.activitynumber = activitynumber;
                                        A22.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A22.class));
                                        break;

                                    case 23: break;

                                    case 24:
                                        A24.idlesson = idlesson ;
                                        A24.idfunction = idfunction ;
                                        A24.activitynumber = activitynumber;
                                        A24.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A24.class));
                                        break;

                                    case 25:
                                        //A25.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A25.activitynumber = activitynumber;
                                        A25.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A25.class));
                                        break;

                                    case 26:
                                        A26.idlesson = idlesson ;
                                        A26.idfunction = idfunction ;
                                        A26.activitynumber = activitynumber;
                                        A26.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A26.class));
                                        break;

                                    case 27:
                                        A27.idlesson = idlesson ;
                                        A27.idfunction = idfunction ;
                                        A27.activitynumber = activitynumber;
                                        A27.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A27.class));
                                        break;

                                    case 28:
                                        A28.idlesson = idlesson ;
                                        A28.idfunction = idfunction ;
                                        A28.activitynumber = activitynumber;
                                        A28.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A28.class));
                                        break;

                                    case 29:
                                        A29.idlesson = idlesson ;
                                        A29.idfunction = idfunction ;
                                        A29.activitynumber = activitynumber;
                                        A29.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A29.class));
                                        break;

                                    case 30:
                                        A30.idlesson = idlesson ;
                                        A30.idfunction = idfunction ;
                                        A30.activitynumber = activitynumber;
                                        A30.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A30.class));
                                        break;

                                    case 31:
                                        //A31.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A31.activitynumber = activitynumber;
                                        //A31.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A31.class));
                                        break;

                                    case 32:
                                        A32.idlesson = idlesson ;
                                        A32.idfunction = idfunction ;
                                        A32.activitynumber = activitynumber;
                                        A32.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A32.class));
                                        break;

                                    case 33:
                                        A33.idlesson = idlesson ;
                                        A33.idfunction = idfunction ;
                                        A33.activitynumber = activitynumber;
                                        A33.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A33.class));
                                        break;

                                    case 34:
                                        A34.idlesson = idlesson ;
                                        A34.idfunction = idfunction ;
                                        A34.activitynumber = activitynumber;
                                        A34.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A34.class));
                                        break;

                                    case 35:
                                        A35.idlesson = idlesson ;
                                        A35.idfunction = idfunction ;
                                        A35.activitynumber = activitynumber;
                                        A35.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A35.class));
                                        break;

                                    case 36: break;

                                    case 37:
                                        A37.idlesson = idlesson ;
                                        A37.idfunction = idfunction ;
                                        A37.activitynumber = activitynumber;
                                        A37.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A37.class));
                                        break;

                                    case 38:
                                        //A38.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A38.activitynumber = activitynumber;
                                        //  A38.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A38.class));
                                        break;

                                    case 39:
                                        A39.idlesson = idlesson ;
                                        A39.idfunction = idfunction ;
                                        A39.activitynumber = activitynumber;
                                        A39.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A39.class));

                                        break;

                                    case 40:
                                        //A40.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A40.activitynumber = activitynumber;
                                        //  A40.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A40.class));
                                        break;

                                    case 41:
                                        //A41.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A41.activitynumber = activitynumber;
                                        //  A41.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A41.class));
                                        break;

                                    case 42:
                                        //A42.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A42.activitynumber = activitynumber;
                                        //  A42.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A42.class));
                                        break;

                                    case 43:
                                        //A43.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A43.activitynumber = activitynumber;
                                        //  A43.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A43.class));
                                        break;

                                    case 44:
                                        //A44.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A44.activitynumber = activitynumber;
                                        //  A44.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A44.class));
                                        break;

                                    case 46:
                                        A46.idlesson = idlesson ;
                                        A46.idfunction = idfunction ;
                                        A46.activitynumber = activitynumber;
                                        A46.Act_Status = "first";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A46.class));
                                        break;
                                }
                            }
                        }

                        // second
                        if(Act_Status.equals("second")){

                            // list of false answer
                            List<Integer> id_act_f = mPresenter.activity_false(idlesson);
                            int number = id_act_f.size();

                            // number = 0 and update
                            if(number == 0){

                                // get now lesson
                                now_less = mPresenter.now_IdLesson();

                                // post

                                // update
                                List<Integer> id_less = mPresenter.lesson(idfunction);
                                List<Integer> id_func = mPresenter.function();

                                for (int i = 0; i < id_less.size(); i++) {
                                    if (id_less.get(i) == idlesson) {
                                        if (i == id_less.size() - 1) {
                                            End.gofunction = 1;
                                            for (int j = 0; j < id_func.size(); j++) {
                                                if (id_func.get(j) == idfunction) {
                                                    if (now_less == idlesson) {
                                                        int next_func = j + 1;
                                                        mPresenter.update_idfunction(id_func.get(next_func));
                                                        mPresenter.update_idlesson(0);
                                                    }
                                                    break;
                                                }
                                            }
                                        } else {
                                            End.gofunction = 0;
                                            if (now_less == idlesson) {
                                                int next_less = i + 1;
                                                mPresenter.update_idlesson(id_less.get(next_less));
                                            }
                                        }
                                        break;
                                    }
                                }

                                A32.this.finish();
                                startActivity(new Intent(A32.this, End.class));

                            }

                            // number != 0 and go on to Next
                            else{

                                // next is random
                                int max_range = (id_act_f.size())-1;
                                int min_range = 0;
                                int rnd = new Random().nextInt(max_range - min_range + 1) + min_range;
                                int id_act = id_act_f.get(rnd);

                                TbActivity tb_new_f = mPresenter.getActivity2(id_act);
                                int id_at_new_f = tb_new_f.getId_ActivityType();

                                // second
                                switch (id_at_new_f){

                                    case 1: break;
                                    case 2: break;

                                    case 3:
                                        A3.idlesson = idlesson ;
                                        A3.idfunction = idfunction ;
                                        A3.idactivity = id_act;
                                        A3.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A3.class));
                                        break;

                                    case 4:
                                        A4.idlesson = idlesson ;
                                        A4.idfunction = idfunction ;
                                        A4.idactivity = id_act;
                                        A4.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A4.class));
                                        break;

                                    case 5:
                                        A5.idlesson = idlesson ;
                                        A5.idfunction = idfunction ;
                                        A5.idactivity = id_act;
                                        A5.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A5.class));
                                        break;

                                    case 6:
                                        A6.idlesson = idlesson ;
                                        A6.idfunction = idfunction ;
                                        A6.idactivity = id_act;
                                        A6.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A6.class));
                                        break;

                                    case 7:
                                        A7.idlesson = idlesson ;
                                        A7.idfunction = idfunction ;
                                        A7.idactivity = id_act;
                                        A7.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A7.class));
                                        break;

                                    case 8:
                                        A8.idlesson = idlesson ;
                                        A8.idfunction = idfunction ;
                                        A8.idactivity = id_act;
                                        A8.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A8.class));
                                        break;

                                    case 9:
                                        A9.idlesson = idlesson ;
                                        A9.idfunction = idfunction ;
                                        A9.idactivity = id_act;
                                        A9.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A9.class));
                                        break;

                                    case 10: break;
                                    case 11: break;
                                    case 12: break;
                                    case 13: break;
                                    case 14: break;

                                    case 15:
                                        A15.idlesson = idlesson ;
                                        A15.idfunction = idfunction ;
                                        A15.idactivity = id_act;
                                        A15.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A15.class));
                                        break;

                                    case 16: break;
                                    case 17: break;

                                    case 18:
                                        A18.idlesson = idlesson ;
                                        A18.idfunction = idfunction ;
                                        A18.idactivity = id_act;
                                        A18.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A18.class));
                                        break;

                                    case 19:
                                        A19.idlesson = idlesson ;
                                        A19.idfunction = idfunction ;
                                        A19.idactivity = id_act;
                                        A19.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A19.class));
                                        break;

                                    case 20:
                                        A20.idlesson = idlesson ;
                                        A20.idfunction = idfunction ;
                                        A20.idactivity = id_act;
                                        A20.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A20.class));
                                        break;

                                    case 21: break;

                                    case 22:
                                        A22.idlesson = idlesson ;
                                        A22.idfunction = idfunction ;
                                        A22.idactivity = id_act;
                                        A22.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A22.class));
                                        break;

                                    case 23: break;

                                    case 24:
                                        A24.idlesson = idlesson ;
                                        A24.idfunction = idfunction ;
                                        A24.idactivity = id_act;
                                        A24.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A24.class));
                                        break;

                                    case 25:
                                        //A25.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A6.idactivity = id_act;
                                        //A9.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A25.class));
                                        break;

                                    case 26:
                                        A26.idlesson = idlesson ;
                                        A26.idfunction = idfunction ;
                                        A26.idactivity = id_act;
                                        A26.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A26.class));
                                        break;

                                    case 27:
                                        A27.idlesson = idlesson ;
                                        A27.idfunction = idfunction ;
                                        A27.idactivity = id_act;
                                        A27.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A27.class));
                                        break;

                                    case 28:
                                        A28.idlesson = idlesson ;
                                        A28.idfunction = idfunction ;
                                        A28.idactivity = id_act;
                                        A28.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A28.class));
                                        break;

                                    case 29:
                                        A29.idlesson = idlesson ;
                                        A29.idfunction = idfunction ;
                                        A29.idactivity = id_act;
                                        A29.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A29.class));
                                        break;

                                    case 30:
                                        A30.idlesson = idlesson ;
                                        A30.idfunction = idfunction ;
                                        A30.idactivity = id_act;
                                        A30.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A30.class));
                                        break;

                                    case 31:
                                        //A31.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A29.idactivity = id_act;
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A31.class));
                                        break;

                                    case 32:
                                        A32.idlesson = idlesson ;
                                        A32.idfunction = idfunction ;
                                        A32.idactivity = id_act;
                                        A32.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A32.class));
                                        break;

                                    case 33:
                                        A33.idlesson = idlesson ;
                                        A33.idfunction = idfunction ;
                                        A33.idactivity = id_act;
                                        A33.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A33.class));
                                        break;

                                    case 34:
                                        A34.idlesson = idlesson ;
                                        A34.idfunction = idfunction ;
                                        A34.idactivity = id_act;
                                        A34.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A34.class));
                                        break;

                                    case 35:
                                        A35.idlesson = idlesson ;
                                        A35.idfunction = idfunction ;
                                        A35.idactivity = id_act;
                                        A35.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A35.class));
                                        break;

                                    case 36: break;

                                    case 37:
                                        A37.idlesson = idlesson ;
                                        A37.idfunction = idfunction ;
                                        A37.idactivity = id_act;
                                        A37.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A37.class));
                                        break;

                                    case 38:
                                        //A38.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A29.idactivity = id_act;
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A38.class));
                                        break;

                                    case 39:
                                        A39.idlesson = idlesson ;
                                        A39.idfunction = idfunction ;
                                        A39.idactivity = id_act;
                                        A39.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A39.class));

                                        break;

                                    case 40:
                                        //A40.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A29.idactivity = id_act;
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A40.class));
                                        break;

                                    case 41:
                                        //A41.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A29.idactivity = id_act;
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A41.class));
                                        break;

                                    case 42:
                                        //A42.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A29.idactivity = id_act;
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A42.class));
                                        break;

                                    case 43:
                                        //A43.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A29.idactivity = id_act;
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A43.class));
                                        break;

                                    case 44:
                                        //A44.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A29.idactivity = id_act;
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A44.class));
                                        break;

                                    case 46:
                                        A46.idlesson = idlesson ;
                                        A46.idfunction = idfunction ;
                                        A46.idactivity = id_act;
                                        A46.Act_Status = "second";
                                        A32.this.finish();
                                        startActivity(new Intent(A32.this,  A46.class));
                                        break;
                                }
                            }
                        }
                    }

                    break;
            }
        }
    }

    class CheckEdit implements TextWatcher {
        public void afterTextChanged(Editable s) {
            try {
                if(s.toString().equals("")){ // && fill == 0
                    next.setTextColor(Color.GRAY);
                    next.setBackgroundResource(R.drawable.btn_gray);
                }
                else{
                    //fill++;
                    next.setTextColor(Color.WHITE);
                    next.setBackgroundResource(R.drawable.btn_green);
                }
            }
            catch(NumberFormatException nfe){}
        }
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
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
        mStateMaintainer.put(A32_Presenter.class.getSimpleName(), mPresenter);
    }

    private void reinitialize() {
        Log.d(TAG, "reinitialize");
        mPresenter = mStateMaintainer.get(A32_Presenter.class.getSimpleName());
        mPresenter.setView(this);
        if ( mPresenter == null )
            setupComponent();
    }

    private void setupComponent(){
        Log.d(TAG, "setupComponent");
        SampleApp.get(this)
                .getAppComponent()
                .getA32Component(new A32_Module(this))
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

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    now_say.setTextSize(16);
                    now_say.setPadding(10,0,0,0);
                    now_say.setText(result.get(0));
                    now_say.setTextColor(getResources().getColor(R.color.blue));

                    int fill = 0;
                    for(int i=0 ; i<e.length ; i++) {
                        if (e[i].getText().toString().equals("")) {

                        } else {
                            fill++;
                        }
                    }
                    if(e.length == fill){
                        if(end){
                            next.setTextColor(Color.WHITE);
                            next.setBackgroundResource(R.drawable.btn_green);
                        }
                    }
                }
                break;
            }
        }
    }

    public void play(final int i){

        if(haveNetworkConnection()){
            try {
                String voice_url = null;
                mp = new MediaPlayer();
                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                voice_url = url_download + path1[i];
                mp.setDataSource(voice_url);
                mp.prepare();
                mpLength = mp.getDuration();
                mp.start();
                b_play[i].setBackgroundResource(R.drawable.pause1);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        end=true;
                        b_play[i].setBackgroundResource(R.drawable.play1);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else{
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }
    }

    public String cheak(){

        String result = "";
        boolean final_answer = true;
        boolean answer[] = new boolean[xali];
        int cc = 0;

        for(int i=0 ; i < e.length ; i++){

            // yek javab
            int baxsh = 0;
            for(int j=0 ; j<ans[i].length() ; j++){
                if(ans[i].charAt(j) == ','){
                    baxsh++;
                }
            }

            z1 = null;
            switch (baxsh){
                // 1 baxsh
                case 0:
                    z1 = ans[i].split("/");
                    break;

                // 2 baxsh
                case 1:
                    String[] s = ans[i].split(",");
                    String[] x = s[0].split("/");
                    String[] y = s[1].split("/");

                    z1 = new String[x.length * y.length];
                    int count2 = 0;
                    for (int ii = 0; ii < x.length; ii++) {
                        for (int jj = 0; jj < y.length; jj++) {
                            z1[count2] = x[ii] + " " + y[jj];
                            count2++;
                        }
                    }
                    break;

                // 3 baxsh
                case 2:
                    String[] a = ans[i].split(",");
                    String[] b = a[0].split("/");
                    String[] c = a[1].split("/");
                    String[] d = a[2].split("/");

                    z1 = new String[b.length * c.length* d.length];
                    int count3 = 0;
                    for (int ii = 0; ii < b.length ; ii++) {
                        for (int jj = 0; jj < c.length ; jj++) {
                            for(int k = 0 ; k < d.length ; k++){
                                z1[count3] = b[ii] + " " + c[jj] + " " + d[k];
                                count3++;
                            }
                        }
                    }
                    break;
            }

            // moqayese ba javab
            result = result + " / "+ z1[0] ;
            for(int j=0 ; j < z1.length ; j++){
                String a = nice_string( e[i].getText().toString() );
                String b = nice_string( z1[j].toString() );
                if(a.equals(b)){
                    answer[cc] = true;
                    cc++;
                }
            }
        }

        for(int x=0 ; x<answer.length ; x++){
            if(answer[x]){

            }else{
                final_answer = false;
            }
        }

        if(final_answer){
            return "";
        }else{
            return result;
        }
    }

    public String nice_string (String a){
        // space in first or end
        String b = a.trim();
        // ever name
        b = b.replace("&", "");
        // other space
        b = b.replace(" ", "");
        // other
        b = b.replace(".", "");
        b = b.replace("!", "");
        b = b.replace("?", "");
        b = b.replace("؟", "");
        b = b.replace(",", "");
        b = b.replace("'", "");
        b = b.replace("’", "");
        b = b.replace("\n", "");
        // lowerCase
        b = b.toLowerCase();
        return b;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    public void back(){
        mp.stop();
        A32.this.finish();
        startActivity(new Intent(A32.this, Lesson.class));
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}