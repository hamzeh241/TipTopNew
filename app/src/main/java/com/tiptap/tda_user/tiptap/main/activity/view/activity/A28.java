package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.A28_Module;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A28;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A28_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.view.lesson.Lesson;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

public class A28 extends BaseActivity
                 implements MVP_A28.RequiredViewOps,
                 OnClickListener, OnTouchListener, OnCompletionListener, OnBufferingUpdateListener{

    private static final String TAG = A28.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A28.class.getName());

    @Inject
    public MVP_A28.ProvidedPresenterOps mPresenter;

    EditText editText;
    boolean end = false;
    int all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a28);

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
        title1 = tbActivity.getTitle1();
        path1 = tbActivity.getPath1();

        after_setup();
    }

    private void setupViews() {

        t1 = (TextView)findViewById(R.id.title1);
        t2 = (TextView)findViewById(R.id.title2);
        mp = new MediaPlayer();
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setMax(99);
        play = (Button) findViewById(R.id.play);
        next = (Button) findViewById(R.id.next);
        editText = (EditText) findViewById(R.id.txt);
        p = (ProgressBar)findViewById(R.id.p);
        p.setMax(100);
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

        t1.setText(R.string.A28_EN);
        t1.setTextColor(getResources().getColor(R.color.my_black));

        int lang_id = mPresenter.getlanguage();
        switch (lang_id){
            // فارسی
            case 1:
                t2.setText(R.string.A28_FA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // کردی
            case 2:
                t2.setText(R.string.A28_KU);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // ترکی آذری
            case 3:
                t2.setText(R.string.A28_TA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // چینی
            case 4:
                t2.setText(R.string.A28_CH);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
        }

        next.setOnClickListener(this);
        play.setOnClickListener(this);
        editText.addTextChangedListener(new CheckEdit());
        seekBar.setOnTouchListener(this);
        mp.setOnBufferingUpdateListener(this);
        mp.setOnCompletionListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.play) {
            try {
                String voice_url = url_download + path1;
                mp.setDataSource(voice_url);
                mp.prepare();

            } catch (Exception e) {
                e.printStackTrace();
            }

            mpLength = mp.getDuration();

            if (!mp.isPlaying()) {
                mp.start();
                play.setBackgroundResource(R.drawable.pause);
            } else {
                mp.pause();
                play.setBackgroundResource(R.drawable.play);
            }
            SeekBarProgressUpdater();
        }

        if (v.getId() == R.id.next) {

            switch (next.getText().toString()) {

                case "check":

                    if (editText.getText().toString().equals("")) {
                        // nothing
                    } else {
                        if (end) {

                            boolean answer = cheak();

                            if (answer == true) {

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
                                editText.setClickable(false);
                                editText.setFocusable(false);
                                play.setClickable(false);
                                seekBar.setClickable(false);
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

                            } else if (answer == false) {

                                // Clickable_false
                                t1.setClickable(false);
                                t2.setClickable(false);
                                editText.setClickable(false);
                                editText.setFocusable(false);
                                play.setClickable(false);
                                seekBar.setClickable(false);
                                p.setClickable(false);

                                // Fragment_false
                                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder2);
                                Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                                linearLayout.setAnimation(slide_down);
                                linearLayout.setVisibility(View.VISIBLE);

                                Fragment_False f2 = new Fragment_False();
                                f2.t.setText(title1);
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
                        }
                    }

                    break;

                case "countinue":

                    if (editText.getText().toString().equals("")) {
                        // nothing
                    } else {

                        if (end) {

                            mp.stop();

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

                                        A28.this.finish();
                                        startActivity(new Intent(A28.this, End.class));
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
                                    A28.this.finish();
                                    startActivity(new Intent(A28.this, End.class));

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
                }
                break;
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(v.getId() == R.id.seekbar){
            if(mp.isPlaying()){
                SeekBar sb = (SeekBar)v;
                int playPositionInMillisecconds = (mpLength / 100) * sb.getProgress();
                mp.seekTo(playPositionInMillisecconds);
            }
        }
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        end = true;
        play.setBackgroundResource(R.drawable.play);
        if(editText.getText().toString().equals("")){
        }else{
            next.setTextColor(Color.WHITE);
            next.setBackgroundResource(R.drawable.btn_green);
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        seekBar.setSecondaryProgress(percent);
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
        mStateMaintainer.put(A28_Presenter.class.getSimpleName(), mPresenter);
    }

    private void reinitialize() {
        Log.d(TAG, "reinitialize");
        mPresenter = mStateMaintainer.get(A28_Presenter.class.getSimpleName());
        mPresenter.setView(this);
        if ( mPresenter == null )
            setupComponent();
    }

    private void setupComponent(){
        Log.d(TAG, "setupComponent");
        SampleApp.get(this)
                .getAppComponent()
                .getA28Component(new A28_Module(this))
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

    public boolean cheak(){
        boolean answer;
        String a = nice_string1( editText.getText().toString() );
        String b = nice_string1( title1 );
        if (a.equals(b)) {
             answer = true;
        } else {
            answer = false;
        }
        return answer;
    }

    class CheckEdit implements TextWatcher {
        public void afterTextChanged(Editable s) {
            try {
                if(s.toString().equals("")){
                    next.setTextColor(Color.GRAY);
                    next.setBackgroundResource(R.drawable.btn_gray);
                }
                else{
                    if(end){
                        next.setTextColor(Color.WHITE);
                        next.setBackgroundResource(R.drawable.btn_green);
                    }
                }
            }
            catch(NumberFormatException nfe){}
        }
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    public void back(){
        A28.this.finish();
        startActivity(new Intent(A28.this, Lesson.class));
    }
}