package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import com.bumptech.glide.Glide;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.Main_Module;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Main_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.view.BaseActivity;
import com.tiptap.tda_user.tiptap.main.activity.view.lesson.Lesson;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import javax.inject.Inject;

public class A27 extends BaseActivity implements MVP_Main.RequiredViewOps,OnClickListener, OnCompletionListener, OnBufferingUpdateListener {
    private static final String TAG = A27.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A27.class.getName());

    @Inject
    public MVP_Main.ProvidedPresenterOps mPresenter;

    ArrayList<String> you_say = new ArrayList<>();

    /*
    private Uri mVideoUri;
    private android.widget.VideoView mAndroidVideoView;
    private VideoView mMpxVideoView;
    private MediaPlayerMultiControl mMediaPlayerControl;
    private MediaController mMediaController;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a27);


        /*
        Utils.setActionBarSubtitleEllipsizeMiddle(this);

        mAndroidVideoView = (android.widget.VideoView) findViewById(R.id.androidvv);
        mMpxVideoView = (VideoView) findViewById(R.id.mpxvv);

        mMediaPlayerControl = new MediaPlayerMultiControl(mAndroidVideoView, mMpxVideoView);
        mMediaController = new MediaController(this);
        mMediaController.setAnchorView(findViewById(R.id.container));
        mMediaController.setMediaPlayer(mMediaPlayerControl);
        mMediaController.setEnabled(false);

        mVideoUri = getIntent().getData();
        getActionBar().setSubtitle(mVideoUri+"");

        // HACK: this needs to be deferred, else it fails when setting video on both players (it works when doing it just on one)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Runnable enableMediaController = new Runnable() {
                    int preparedCount = 0;
                    @Override
                    public void run() {
                        if(++preparedCount == mMediaPlayerControl.getControlsCount()) {
                            // Enable controller when all players are initialized
                            mMediaController.setEnabled(true);
                        }
                    }
                };

                mAndroidVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mAndroidVideoView.seekTo(0); // display first frame
                        enableMediaController.run();

                    }
                });
                mMpxVideoView.setOnPreparedListener(new net.protyposis.android.mediaplayer.MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(net.protyposis.android.mediaplayer.MediaPlayer mp) {
                        mMpxVideoView.seekTo(0); // display first frame
                        enableMediaController.run();
                    }
                });

                mAndroidVideoView.setVideoURI(mVideoUri);
                mMpxVideoView.setVideoURI(mVideoUri);
            }
        }, 1000);
        */


        setupViews();
        setupMVP();

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
        path2 = tbActivity.getPath2();

        after_setup();
    }

    private void setupViews() {

        t1 = (TextView)findViewById(R.id.title1);
        t2 = (TextView)findViewById(R.id.title2);
        txt = (TextView)findViewById(R.id.txt);
        img = (ImageView) findViewById(R.id.img);
        voice = (ImageView)findViewById(R.id.voice);
        next = (Button) findViewById(R.id.next);
        play = (Button)findViewById(R.id.play);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        p = (ProgressBar)findViewById(R.id.p);
        p.setMax(100);
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

        next.setOnClickListener(this);

        t1.setText(R.string.A27_EN);
        t1.setTextColor(getResources().getColor(R.color.my_black));

        int lang_id = mPresenter.getlanguage();
        switch (lang_id){
            // فارسی
            case 1:
                t2.setText(R.string.A27_FA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // کردی
            case 2:
                t2.setText(R.string.A27_KU);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // ترکی آذری
            case 3:
                t2.setText(R.string.A27_TA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // چینی
            case 4:
                t2.setText(R.string.A27_CH);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
        }

        txt.setText(title1);
        txt.setTextColor(getResources().getColor(R.color.my_black));
        txt.setTextSize(18);

        String img_url = url_download+path1;
        Glide.with(this).load(img_url).placeholder(R.drawable.ph).error(R.drawable.e).into(img);

        voice.setOnClickListener(this);
        next.setOnClickListener(this);
        play.setOnClickListener(this);
        mp.setOnBufferingUpdateListener(this);
        mp.setOnCompletionListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.voice){
            if(haveNetworkConnection()){
                promptSpeechInput();
            } else{
                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }
        }

        if(v.getId() == R.id.play){

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
                SeekBarProgressUpdater();

            }else{
                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }
        }

        if(v.getId() == R.id.next){

            mp.stop();

            switch (next.getText().toString()) {

                case "check":

                    if( end && you_say.size()>=1) {

                        boolean result = false;
                        for(int z=0 ; z < you_say.size() ; z++){
                            String a = nice_string1( you_say.get(z) );
                            String b = nice_string1( title1 );
                            if (a.equals(b)) { result = true;}
                        }

                        if (result) {

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
                            txt.setClickable(false);
                            voice.setClickable(false);
                            play.setClickable(false);
                            img.setClickable(false);
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

                        } else {

                            // Clickable_false
                            t1.setClickable(false);
                            t2.setClickable(false);
                            txt.setClickable(false);
                            voice.setClickable(false);
                            play.setClickable(false);
                            img.setClickable(false);
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
                    break;

                case "countinue":

                    if( end && you_say.size()>=1) {

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
                                    A27.this.finish();
                                    startActivity(new Intent(A27.this, End.class));
                                }

                                // number != 0 and go on to Next
                                else {
                                    int max_range = (id_act_false.size())-1;
                                    int min_range = 0;
                                    int rnd = new Random().nextInt(max_range - min_range + 1) + min_range;
                                    int id_act = id_act_false.get(rnd);
                                    TbActivity tb_new_f = mPresenter.getActivity2(id_act);
                                    int id_at_new_f = tb_new_f.getId_ActivityType();

                                    // second
                                    go_activity1(id_at_new_f,"second",id_act);
                                }

                            } else {

                                TbActivity tb_new = mPresenter.getActivity(idlesson, ++activitynumber);
                                int id_at_new = tb_new.getId_ActivityType();

                                // first
                                go_activity2(id_at_new,"first",activitynumber);

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
                                A27.this.finish();
                                startActivity(new Intent(A27.this, End.class));

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
                                go_activity1(id_at_new_f,"second",id_act);
                            }
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        end = true;
        play.setBackgroundResource(R.drawable.play);
        if(you_say.size()>=1){
            if(end){
                next.setTextColor(Color.WHITE);
                next.setBackgroundResource(R.drawable.btn_green);
            }
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
                .getA27Component(new Main_Module(this))
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
                    you_say = result;
                    if(you_say.size()>=1){
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    public void back(){
        mp.stop();
        mp.release();
        mpt.stop();
        mpt.release();
        mpf.stop();
        mpf.release();
        A27.this.finish();
        startActivity(new Intent(A27.this, Lesson.class));
    }

    /*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mMediaController.show();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onStop() {
        mMediaController.hide();
        super.onStop();
    }

    private class MediaPlayerMultiControl implements MediaController.MediaPlayerControl {

        private List<MediaController.MediaPlayerControl> mControls;

        public MediaPlayerMultiControl(MediaController.MediaPlayerControl... controls) {
            mControls = new ArrayList<>();
            for (MediaController.MediaPlayerControl mpc : controls) {
                mControls.add(mpc);
            }
        }

        public int getControlsCount() {
            return mControls.size();
        }

        @Override
        public void start() {
            for (MediaController.MediaPlayerControl mpc : mControls) {
                mpc.start();
            }
        }

        @Override
        public void pause() {
            for (MediaController.MediaPlayerControl mpc : mControls) {
                mpc.pause();
            }
        }

        @Override
        public int getDuration() {
            if (!mControls.isEmpty()) {
                return mControls.get(0).getDuration();
            }
            return 0;
        }

        @Override
        public int getCurrentPosition() {
            if (!mControls.isEmpty()) {
                return mControls.get(0).getCurrentPosition();
            }
            return 0;
        }

        @Override
        public void seekTo(int pos) {
            for (MediaController.MediaPlayerControl mpc : mControls) {
                mpc.seekTo(pos);
            }
        }

        @Override
        public boolean isPlaying() {
            if (!mControls.isEmpty()) {
                return mControls.get(0).isPlaying();
            }
            return false;
        }

        @Override
        public int getBufferPercentage() {
            if (!mControls.isEmpty()) {
                return mControls.get(0).getBufferPercentage();
            }
            return 0;
        }

        @Override
        public boolean canPause() {
            if (!mControls.isEmpty()) {
                return mControls.get(0).canPause();
            }
            return false;
        }

        @Override
        public boolean canSeekBackward() {
            if (!mControls.isEmpty()) {
                return mControls.get(0).canSeekBackward();
            }
            return false;
        }

        @Override
        public boolean canSeekForward() {
            if (!mControls.isEmpty()) {
                return mControls.get(0).canSeekForward();
            }
            return false;
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public int getAudioSessionId() {
            if (!mControls.isEmpty()) {
                return mControls.get(0).getAudioSessionId();
            }
            return 0;
        }
        */

}