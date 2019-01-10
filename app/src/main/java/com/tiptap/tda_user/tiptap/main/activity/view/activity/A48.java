package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.Main_Module;
import com.tiptap.tda_user.tiptap.main.activity.Api.Post_UpdateUser;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Main_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.view.BaseActivity;
import com.tiptap.tda_user.tiptap.main.activity.view.lesson.Lesson;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;
import javax.inject.Inject;

public class A48 extends BaseActivity
        implements MVP_Main.RequiredViewOps,View.OnClickListener{

    private static final String TAG = A48.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A48.class.getName());

    @Inject
    public MVP_Main.ProvidedPresenterOps mPresenter;
    ArrayList<String> you_say = new ArrayList<>();
    ArrayList<String> you_say1 = new ArrayList<>();
    ImageView voice,voice1;
    TextView text;
    Button play1, isplay1;
    public MediaPlayer mp1;
    String answer, title1detailactivity, title2detailactivity,a, path3,answer1,answer2,userAnswer1,userAnswer2;
    int count;
    int back_pressed = 0;
    boolean mic_status1 = false, mic_status2 = false;
    boolean can_play = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
        int id = tbActivityDetailList.get(0).get_id();
        count = mPresenter.count_ActivityDetail(idactivity);
        title2detailactivity = tbActivityDetailList.get(1).getTitle1().toString();
        int id1 = tbActivityDetailList.get(1).get_id();
        answer1 = tbActivityDetailList.get(0).getTitle1().toString();
        answer2 = tbActivityDetailList.get(1).getTitle1().toString();
        path2 = tbActivityDetailList.get(0).getPath1();
        path3 = tbActivityDetailList.get(1).getPath1();

        setupViews();
        after_setup();
    }

    private void setupViews() {
        p = (ProgressBar)findViewById(R.id.p);
        p.setMax(100);
        t1 = (TextView)findViewById(R.id.title1);
        t2 = (TextView)findViewById(R.id.title2);
        img = (ImageView) findViewById(R.id.img);
        play=(Button)findViewById(R.id.play);
        isplay = (Button) findViewById(R.id.isplay);
        txt1 = (TextView)findViewById(R.id.txt1);
        voice = (ImageView) findViewById(R.id.voice);
        play1=(Button)findViewById(R.id.play1);
        isplay1 = (Button) findViewById(R.id.isplay1);
        txt2 = (TextView)findViewById(R.id.txt2);
        voice1 = (ImageView) findViewById(R.id.voice1);
        next = (Button)findViewById(R.id.next);
        mp = new MediaPlayer();
        mp1 = new MediaPlayer();
        mpt = MediaPlayer.create (this, R.raw.true_sound);
        mpf =  MediaPlayer.create (this, R.raw.false_sound);
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

        String img_url = url_download+path1;
        Glide.with(this).load(img_url).placeholder(R.drawable.ph).error(R.drawable.e).into(img);

        // set text for textbox
        txt1.setText(title1detailactivity);
        txt2.setText(title2detailactivity);

        //set OnClickListener
        play.setOnClickListener(this);
        play1.setOnClickListener(this);
        isplay.setOnClickListener(this);
        isplay1.setOnClickListener(this);
        voice.setOnClickListener(this);
        voice1.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.play){
            if(haveNetworkConnection()){
                if(can_play){
                    // change
                    play.setClickable(false);
                    // mic
                    // mic_status = false;
                    // play1
                    can_play = false;

                    MediaPlayer mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(url_download+path2);
                        // fix 1 player
                        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                                    .setUsage(AudioAttributes.USAGE_MEDIA)
                                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                    .build());
                        } else {
                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        }
                        mediaPlayer.prepare();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error_Media", Toast.LENGTH_LONG).show();
                        play.setClickable(true);
                        can_play = true;
                        //mic_status = true;
                    }
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        public void onPrepared(MediaPlayer mp) {
                            try{
                                // change
                                play.setVisibility(View.GONE);
                                isplay.setVisibility(View.VISIBLE);
                                isplay.setClickable(true);

                                if(!(mp.isPlaying())){
                                    mp.start();
                                }
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(), "Error_Play", Toast.LENGTH_LONG).show();
                                // change
                                play.setVisibility(View.VISIBLE);
                                play.setClickable(true);
                                isplay.setVisibility(View.GONE);
                                isplay.setClickable(false);
                                // mic
                                //mic_status = true;
                                // play1
                                can_play = true;
                            }
                        }
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            end = true;
                            // change
                            play.setVisibility(View.VISIBLE);
                            play.setClickable(true);
                            isplay.setVisibility(View.GONE);
                            isplay.setClickable(false);
                            // mic
                            mic_status1 = true;
                            // play1
                            can_play = true;
                            // countinue
                            next.setTextColor(Color.WHITE);
                            next.setBackgroundResource(R.drawable.btn_green);
                            // fix 2 player
                            mediaPlayer.release();
                        }
                    });
                }
            }else{
                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }
        }

        if (view.getId() == R.id.isplay) {
            // Toast.makeText(getActivityContext(), "Listen", Toast.LENGTH_LONG).show();
        }

        if(view.getId() == R.id.play1){
            //play sound 2
            if(haveNetworkConnection()){
                if(can_play){
                    // change
                    play1.setClickable(false);
                    // mic
                    //mic_status = false;
                    // play1
                    can_play = false;

                    MediaPlayer mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(url_download+path3);
                        // fix 1 player
                        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                                    .setUsage(AudioAttributes.USAGE_MEDIA)
                                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                    .build());
                        } else {
                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        }
                        mediaPlayer.prepare();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error_Media", Toast.LENGTH_LONG).show();
                        play1.setClickable(true);
                        can_play = true;
                        //mic_status = true;
                    }
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        public void onPrepared(MediaPlayer mp) {
                            try {
                                // change
                                play1.setVisibility(View.GONE);
                                isplay1.setVisibility(View.VISIBLE);
                                isplay1.setClickable(true);

                                if (!(mp.isPlaying())) {
                                    mp.start();
                                }
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(), "Error_Play", Toast.LENGTH_LONG).show();
                                // change
                                play1.setVisibility(View.VISIBLE);
                                play1.setClickable(true);
                                isplay1.setVisibility(View.GONE);
                                isplay1.setClickable(false);
                                // mic
                               // mic_status = true;
                                // play1
                                can_play = true;
                            }
                        }
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            end = true;
                            // change
                            play1.setVisibility(View.VISIBLE);
                            play1.setClickable(true);
                            isplay1.setVisibility(View.GONE);
                            isplay1.setClickable(false);
                            // mic
                            mic_status2 = true;
                            // play1
                            can_play = true;
                            // countinue
                            next.setTextColor(Color.WHITE);
                            next.setBackgroundResource(R.drawable.btn_green);
                            // fix 2 player
                            mediaPlayer.release();
                        }
                    });
                }
            }else{
                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }
        }

        if (view.getId() == R.id.isplay1) {
            // Toast.makeText(getActivityContext(), "Listen", Toast.LENGTH_LONG).show();
        }

        if (view.getId() == R.id.voice) {
            if(mic_status1){
                if (haveNetworkConnection()) {
                    promptSpeechInput(101);

                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                }
            }
        } if (view.getId() == R.id.voice1) {
            if(mic_status2){
                if (haveNetworkConnection()) {
                    promptSpeechInput(102);

                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                }
            }
        }
        if(view.getId() == R.id.next){
            switch (next.getText().toString()) {
                case "check":

                    if( (you_say.size()>=1)&&(you_say1.size()>=1) ) {

                        // answer1 with array of you_say
                        boolean result1 = false;
                        answer1=nice_string1(answer1);
                        for(int i=0 ; i<you_say.size() ; i++){
                            userAnswer1 = nice_string1( you_say.get(i) );
                            if(cheak(answer1,userAnswer1)){result1 = true;}
                        }

                        // answer2 with array of you_say1
                        boolean result2 = false;
                        answer2=nice_string1(answer2);
                        for(int i=0 ; i<you_say1.size() ; i++){
                            userAnswer2 = nice_string1( you_say1.get(i) );
                            if(cheak(answer2,userAnswer2)){result2 = true;}
                        }

                        if ( result1 && result2) {

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
                            f1.txt_true.setText(tbActivityDetailList.get(0).getTitle1().toString()+"\n"+tbActivityDetailList.get(1).getTitle1().toString());
                            FragmentManager fragMan = getSupportFragmentManager();
                            FragmentTransaction fragTransaction = fragMan.beginTransaction();
                            fragTransaction.add(R.id.fragment1, f1);
                            fragTransaction.commit();

                            // play sound
                            mpt.start();

                        } else {

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

                            String show = "";
                            if(!result1){
                                show = show + tbActivityDetailList.get(0).getTitle1().toString();
                            }
                            if(!result2){
                                show = show + "\n" + tbActivityDetailList.get(1).getTitle1().toString();
                            }
                            f2.txt_false.setText(show);
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

                    }else {
                        Toast.makeText(getApplicationContext(),"جملات را تکرار کنید",Toast.LENGTH_LONG).show();
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

                                                        // update server - next function
                                                        List<Integer> id_less_new = mPresenter.lesson(id_func.get(next_func));
                                                        update_server(id_less_new.get(0));
                                                    }
                                                    break;
                                                }
                                            }
                                        } else {
                                            End.gofunction = 0;
                                            if (now_less == idlesson) {
                                                int next_less = i + 1;
                                                mPresenter.update_idlesson(id_less.get(next_less));

                                                // update server - next lesson
                                                update_server(id_less.get(next_less));
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

                                                    // update server - next function
                                                    List<Integer> id_less_new = mPresenter.lesson(id_func.get(next_func));
                                                    update_server(id_less_new.get(0));
                                                }
                                                break;
                                            }
                                        }
                                    } else {
                                        End.gofunction = 0;
                                        if (now_less == idlesson) {
                                            int next_less = i + 1;
                                            mPresenter.update_idlesson(id_less.get(next_less));

                                            // update server - next lesson
                                            update_server(id_less.get(next_less));
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
       // intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
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
            case 101: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    you_say = result;
                    next.setTextColor(Color.WHITE);
                    next.setBackgroundResource(R.drawable.btn_green);
                }
                break;
            }

            case 102: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    you_say1 = result;
                    next.setTextColor(Color.WHITE);
                    next.setBackgroundResource(R.drawable.btn_green);
                }
                break;
            }
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
            A48.this.finish();
            startActivity(new Intent(A48.this, Lesson.class));
        }
    }

    public void update_server(int idL) {
        try {
            String UserName = mPresenter.get_UserName();
            new Post_UpdateUser(getApplicationContext(), A48.this, haveNetworkConnection(), UserName, idL).post();
        } catch (JSONException e) {}
    }
}