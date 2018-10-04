package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;
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
import android.view.View.OnClickListener;
import android.media.MediaPlayer.OnPreparedListener;

import org.json.JSONException;

import java.util.List;
import java.util.Random;
import javax.inject.Inject;

public class A37 extends BaseActivity
                 implements MVP_Main.RequiredViewOps, OnClickListener, OnPreparedListener{

    VideoView videoView;
    private static final String TAG = A37.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A37.class.getName());

    @Inject
    public MVP_Main.ProvidedPresenterOps mPresenter;
    int back_pressed = 0;

    VideoView video_view;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.a37);

        setupViews();
        setupMVP();
        videoView =(VideoView)findViewById(R.id.videoView);
        //Set MediaController  to enable play, pause, forward, etc options.
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);

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
        tbActivity = mPresenter.getActivity(idlesson, activitynumber);
        path1 = tbActivity.getPath1();

        after_setup();
    }

    private void setupViews() {

        p = (ProgressBar)findViewById(R.id.p);
        p.setMax(100);
        next = (Button)findViewById(R.id.next);
        pd = new ProgressDialog(A37.this);
        pd.setMessage("در حال دریافت اطلاعات ...");
        pd.show();
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

        if(haveNetworkConnection()) {
            Uri uri = Uri.parse(url_download+path1);
            MediaController mediaController= new MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
            videoView.setOnPreparedListener(new OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    pd.dismiss();
                }
            });

        }else {
            pd.dismiss();
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }

        next.setOnClickListener(this);
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        pd.dismiss();
        video_view.start();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.next) {

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

                        // get now_less
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
                        A37.this.finish();
                        startActivity(new Intent(A37.this, End.class));
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

                    // get now_less
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
                    A37.this.finish();
                    startActivity(new Intent(A37.this, End.class));

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
                .getA37Component(new Main_Module(this))
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

    @Override
    public void onBackPressed() {
        back_pressed++;
        back();
    }

    public void back(){
        if(back_pressed == 1){
            Toast.makeText(getApplicationContext(), "برای خروج دوباره برگشت را بفشارید", Toast.LENGTH_LONG).show();
        }else{
            A37.this.finish();
            startActivity(new Intent(A37.this, Lesson.class));
        }
    }

    public void update_server(int idL) {
        try {
            String UserName = mPresenter.get_UserName();
            new Post_UpdateUser(getApplicationContext(), A37.this, haveNetworkConnection(), UserName, idL).post();
        } catch (JSONException e) {}
    }
}