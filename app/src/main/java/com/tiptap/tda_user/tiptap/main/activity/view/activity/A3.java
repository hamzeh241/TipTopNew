package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.A3_Module;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A3;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A3_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;

public class A3 extends BaseActivity implements MVP_A3.RequiredViewOps,OnClickListener, OnTouchListener, OnCompletionListener, OnBufferingUpdateListener {

    private static final String TAG = A3.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A3.class.getName());

    @Inject
    public MVP_A3.ProvidedPresenterOps mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a3);

        setupViews();
        setupMVP();

        max = mPresenter.max_Activitynumber(idlesson);

        // addd
        // to

        // test

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
        txt = (TextView) findViewById(R.id.txt);
        mp = new MediaPlayer();
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        play = (Button) findViewById(R.id.play);
        next = (Button) findViewById(R.id.next);
        p = (ProgressBar)findViewById(R.id.p);
        p.setMax(100);
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

        t1.setText(R.string.A3_EN);
        t1.setTextColor(getResources().getColor(R.color.my_black));

        int lang_id = mPresenter.getlanguage();
        switch (lang_id){
            // فارسی
            case 1:
                t2.setText(R.string.A3_FA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // کردی
            case 2:
                t2.setText(R.string.A3_KU);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // ترکی آذری
            case 3:
                t2.setText(R.string.A3_TA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // چینی
            case 4:
                t2.setText(R.string.A3_CH);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
        }

        txt.setText(title1);

        next.setOnClickListener(this);

        play.setOnClickListener(this);

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

            if(end) {

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

                            A3.this.finish();
                            startActivity(new Intent(A3.this, End.class));
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
                        A3.this.finish();
                        startActivity(new Intent(A3.this, End.class));

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
        next.setTextColor(Color.WHITE);
        next.setBackgroundResource(R.drawable.btn_green);
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
        mStateMaintainer.put(A3_Presenter.class.getSimpleName(), mPresenter);
    }

    private void reinitialize() {
        Log.d(TAG, "reinitialize");
        mPresenter = mStateMaintainer.get(A3_Presenter.class.getSimpleName());
        mPresenter.setView(this);
        if ( mPresenter == null )
            setupComponent();
    }

    private void setupComponent(){
        Log.d(TAG, "setupComponent");
        SampleApp.get(this)
                .getAppComponent()
                .getA3Component(new A3_Module(this))
                .inject(this);
    }
}