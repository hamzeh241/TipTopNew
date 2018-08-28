package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.tiptap.tda_user.tiptap.di.module.Main_Module;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Main_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.view.BaseActivity;
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

public class A32 extends BaseActivity
        implements MVP_Main.RequiredViewOps, OnClickListener{

    private static final String TAG = A32.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A32.class.getName());

    @Inject
    public MVP_Main.ProvidedPresenterOps mPresenter;
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
    boolean end = false;
    TextView now_say;
    String z1[];
    int fill = 0;
    int back_pressed = 0;

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
                                f1.txt_true.setText(answer);
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
                                f2.txt_false.setText(answer);
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
                                    go_activity1(id_at_new_f, "second", id_act);
                                }

                            } else {

                                TbActivity tb_new = mPresenter.getActivity(idlesson, ++activitynumber);
                                int id_at_new = tb_new.getId_ActivityType();

                                // first
                                go_activity2(id_at_new, "first", activitynumber);
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
                                go_activity1(id_at_new_f, "second", id_act);
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
                .getA32Component(new Main_Module(this))
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
                if(ans[i].charAt(j) == '^'){ // Replace , TO ^
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
                    String[] s = ans[i].split("\\^"); // Replace , TO ^
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
                    String[] a = ans[i].split("\\^"); // Replace , TO ^
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
                String a = nice_string2( e[i].getText().toString() );
                String b = nice_string2( z1[j].toString() );
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

    @Override
    public void onBackPressed() {
        back_pressed++;
        back();
    }

    public void back(){
        if(back_pressed == 1){
            Toast.makeText(getApplicationContext(), "برای خروج دوباره برگشت را بفشارید", Toast.LENGTH_LONG).show();
        }else{
            mp.stop();
            A32.this.finish();
            startActivity(new Intent(A32.this, Lesson.class));
        }
    }
}