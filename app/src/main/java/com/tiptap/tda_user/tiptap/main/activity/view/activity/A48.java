package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.opengl.Visibility;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.toolbox.NetworkImageView;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.Main_Module;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Main_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;

import javax.inject.Inject;

public class A48 extends BaseActivity
        implements MVP_Main.RequiredViewOps,View.OnClickListener, View.OnTouchListener {

    private static final String TAG = A48.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A48.class.getName());

    @Inject
    public MVP_Main.ProvidedPresenterOps mPresenter;
    String you_say = "",you_say1 = "";
    String true_txt="";
    ImageView voice,voice1;
    TextView text;
    Button play1;
    public MediaPlayer mp1;
    String answer, title1detailactivity, title2detailactivity,a, path3,answer1,answer2,userAnswer1,userAnswer2;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a48);
        setupMVP();
        // first
        if (Act_Status.equals("first")) {
            tbActivity = mPresenter.getActivity(idlesson, activitynumber);
        }
        // second
        if (Act_Status.equals("second")) {
            tbActivity = mPresenter.getActivity2(idactivity);
        }

        // get tbactivity
        idactivity = tbActivity.get_id();
        title1 = tbActivity.getTitle1();
        path1 = tbActivity.getPath1();

        max = mPresenter.max_Activitynumber(idlesson);

        // get tbactvity detail
        tbActivityDetailList = mPresenter.getListActivityDetail(idactivity);
        title1detailactivity = tbActivityDetailList.get(0).getTitle1().toString();
        title2detailactivity = tbActivityDetailList.get(1).getTitle1().toString();
        answer1 = tbActivityDetailList.get(0).getTitle1().toString();
        answer2 = tbActivityDetailList.get(1).getTitle1().toString();
        path2 = tbActivityDetailList.get(0).getPath1();
        path3 = tbActivityDetailList.get(1).getPath1();
        count =mPresenter.count_ActivityDetail(idactivity);


        setupViews();
        after_setup();
    }

    private void setupViews() {
        p = (ProgressBar)findViewById(R.id.p);
        p.setMax(100);
        t1 = (TextView)findViewById(R.id.title1);
        t2 = (TextView)findViewById(R.id.title2);
        img = (NetworkImageView) findViewById(R.id.img);
        play=(Button)findViewById(R.id.play);
        txt1 = (TextView)findViewById(R.id.txt1);
        voice = (ImageView) findViewById(R.id.voice);
        play1=(Button)findViewById(R.id.play1);
        txt2 = (TextView)findViewById(R.id.txt2);
        voice1 = (ImageView) findViewById(R.id.voice1);
        next = (Button)findViewById(R.id.next);
        mp = new MediaPlayer();
        mp1 = new MediaPlayer();
    }
    private void after_setup() {

        all = mPresenter.countActivity(idlesson);

        // set all activity false in activitynumber = 1
        if (activitynumber == 1 && Act_Status.equals("first")) {
            mPresenter.false_activitys(idlesson);
        }

        // show passed activity
        List<Integer> p1 = mPresenter.activity_true(idlesson);
        int p2 = p1.size();
        if (p2 == 0) {
            p.setProgress(0);
        } else {
            double d_number = (double) p2 / all;
            int i_number = (int) (d_number * 100);
            p.setProgress(i_number);
        }

        t1.setText(R.string.A48_EN);
        t1.setTextColor(getResources().getColor(R.color.my_black));

        //Choising Language
        int lang_id = mPresenter.getlanguage();
        switch (lang_id) {
            // فارسی
            case 1:
                t2.setText(R.string.A48_FA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // کردی
            case 2:
                t2.setText(R.string.A48_KU);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // ترکی آذری
            case 3:
                t2.setText(R.string.A48_TA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // چینی
            case 4:
                t2.setText(R.string.A48_CH);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
        }

        //get image
        getImage(path1);

        // set text for textbox
        txt1.setText(title1detailactivity);
        txt2.setText(title2detailactivity);

        //set OnClickListener
        play.setOnClickListener(this);
        play1.setOnClickListener(this);
        voice.setOnClickListener(this);
        voice1.setOnClickListener(this);
        next.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.play){
            //play sound 1
            if(haveNetworkConnection()){
                try {
                    String voice_url = url_download+path2;
                    mp.setDataSource(voice_url);
                    mp.prepare();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage()+"", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

                mpLength = mp.getDuration();

                if(!mp.isPlaying()){
                    mp.start();
                    play.setBackgroundResource(R.drawable.pause);
                }else {
                    mp.pause();
                    play.setBackgroundResource(R.drawable.play);
                }

            }else{
                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }
        }
        if(view.getId() == R.id.play1){
            //play sound 1
            if(haveNetworkConnection()){
                try {
                    String voice_url = url_download+path3;
                    mp1.setDataSource(voice_url);
                    mp1.prepare();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage()+"", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

                mpLength = mp1.getDuration();

                if(!mp1.isPlaying()){
                    mp1.start();
                    play1.setBackgroundResource(R.drawable.pause);
                }else {
                    mp1.pause();
                    play1.setBackgroundResource(R.drawable.play);
                }

            }else{
                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }
        }
        if (view.getId() == R.id.voice) {
            if (haveNetworkConnection()) {
                promptSpeechInput(101);

            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }

        } if (view.getId() == R.id.voice1) {
            if (haveNetworkConnection()) {
                promptSpeechInput(102);

            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }

        }
        if(view.getId() == R.id.next){
            switch (next.getText().toString()) {
                case "check":
                    //you_say != ""

                    if( (you_say != "")&&(you_say1 != "") ) {

                        userAnswer1 = nice_string1( you_say );
                        userAnswer2 = nice_string1( you_say1 );
                        //       true_txt =  title1 ;
                        answer1=nice_string1(answer1);
                        answer2=nice_string1(answer2);
                        if (cheak(answer1,userAnswer1)&&cheak(answer2,userAnswer2)) {

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
                            p.setClickable(false);
                            t1.setClickable(false);
                            t2.setClickable(false);
                            img.setClickable(false);
                            play.setClickable(false);
                            txt1.setClickable(false);
                            voice.setClickable(false);
                            play1.setClickable(false);
                            txt2.setClickable(false);
                            voice1.setClickable(false);


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


                        }else if (!cheak(answer1,userAnswer1)){

                            // Clickable_false
                            p.setClickable(false);
                            t1.setClickable(false);
                            t2.setClickable(false);
                            img.setClickable(false);
                            play.setClickable(false);
                            txt1.setClickable(false);
                            voice.setClickable(false);
                            play1.setClickable(false);
                            txt2.setClickable(false);
                            voice1.setClickable(false);


                            // Fragment_false
                            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder2);
                            Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                            linearLayout.setAnimation(slide_down);
                            linearLayout.setVisibility(View.VISIBLE);

                            Fragment_False f2 = new Fragment_False();
                            f2.t.setText(tbActivityDetailList.get(0).getTitle2().toString());
                            FragmentManager fragMan = getSupportFragmentManager();
                            FragmentTransaction fragTransaction = fragMan.beginTransaction();
                            fragTransaction.add(R.id.fragment2, f2);
                            fragTransaction.commit();
                        }
                        else {

                            // Clickable_false
                            p.setClickable(false);
                            t1.setClickable(false);
                            t2.setClickable(false);
                            img.setClickable(false);
                            play.setClickable(false);
                            txt1.setClickable(false);
                            voice.setClickable(false);
                            play1.setClickable(false);
                            txt2.setClickable(false);
                            voice1.setClickable(false);

                            // Fragment_false
                            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder2);
                            Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                            linearLayout.setAnimation(slide_down);
                            linearLayout.setVisibility(View.VISIBLE);

                            Fragment_False f2 = new Fragment_False();
                            f2.t.setText(tbActivityDetailList.get(1).getTitle2().toString());
                            FragmentManager fragMan = getSupportFragmentManager();
                            FragmentTransaction fragTransaction = fragMan.beginTransaction();
                            fragTransaction.add(R.id.fragment2, f2);
                            fragTransaction.commit();
                        }

                        next.setTextColor(Color.WHITE);
                        next.setBackgroundResource(R.drawable.btn_green);
                        next.setText("countinue");
                    }else {

                        // Clickable_false
                        p.setClickable(false);
                        t1.setClickable(false);
                        t2.setClickable(false);
                        img.setClickable(false);
                        play.setClickable(false);
                        txt1.setClickable(false);
                        voice.setClickable(false);
                        play1.setClickable(false);
                        txt2.setClickable(false);
                        voice1.setClickable(false);

                        // Fragment_false
                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder2);
                        Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                        linearLayout.setAnimation(slide_down);
                        linearLayout.setVisibility(View.VISIBLE);

                        Fragment_False f2 = new Fragment_False();
                        f2.t.setText("جاهای خالی را پر کنید");
                        FragmentManager fragMan = getSupportFragmentManager();
                        FragmentTransaction fragTransaction = fragMan.beginTransaction();
                        fragTransaction.add(R.id.fragment2, f2);
                        fragTransaction.commit();

                        next.setTextColor(Color.WHITE);
                        next.setBackgroundResource(R.drawable.btn_green);
                        next.setText("countinue");
                    }

                    break;

                case "countinue":

                    // first
                    if (Act_Status.equals("first")) {

                        // max - end of lesson
                        if (activitynumber == max) {

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
                                A48.this.finish();
                                startActivity(new Intent(A48.this, End.class));
                            }

                            // number != 0 and go on to Next
                            else {
                                int max_range = (id_act_false.size()) - 1;
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
                    if (Act_Status.equals("second")) {

                        // list of false answer
                        List<Integer> id_act_f = mPresenter.activity_false(idlesson);
                        int number = id_act_f.size();

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
                            A48.this.finish();
                            startActivity(new Intent(A48.this, End.class));

                        }

                        // number != 0 and go on to Next
                        else {

                            // next is random
                            int max_range = (id_act_f.size()) - 1;
                            int min_range = 0;
                            int rnd = new Random().nextInt(max_range - min_range + 1) + min_range;
                            int id_act = id_act_f.get(rnd);

                            TbActivity tb_new_f = mPresenter.getActivity2(id_act);
                            int id_at_new_f = tb_new_f.getId_ActivityType();

                            // second
                            go_activity1(id_at_new_f, "second", id_act);
                        }
                    }

                    break;
            }
        }
    }

    public boolean cheak(String correctAnswer, String userAnswer){
        boolean flag=false;
        if (correctAnswer.equals("null")) {

        }else {
            int have = 0;
            for (int j = 0; j < correctAnswer.length(); j++) {
                if (correctAnswer.charAt(j) == '/') {
                    have = 1;
                }
            }
            if (have == 1) {
                String part[] = correctAnswer.split(Pattern.quote("/"));
                for (int i = 0; i < part.length; i++) {
                    if (userAnswer.equals(part[i]))
                        flag = true;
                }
            } else {
                if (userAnswer.equals(correctAnswer))
                    flag = true;
            }
        }
        return  flag;
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
                .getA48Component(new Main_Module(this))
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

    private void promptSpeechInput(int code) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, code);
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
                    you_say = result.get(0);
                    Toast.makeText(getApplicationContext(), you_say , Toast.LENGTH_SHORT).show();
                    next.setTextColor(Color.WHITE);
                    next.setBackgroundResource(R.drawable.btn_green);
                }
                break;
            }
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}