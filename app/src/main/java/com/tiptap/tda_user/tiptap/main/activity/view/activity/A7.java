package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.A7_Module;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A7;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A7_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivityDetail;
import java.util.List;
import javax.inject.Inject;

public class A7 extends AppCompatActivity
                implements MVP_A7.RequiredViewOps {

    private static final String TAG = A7.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A7.class.getName());

    @Inject
    public MVP_A7.ProvidedPresenterOps mPresenter;

    public static int idlesson;
    public static int activitynumber;
    TbActivity tbActivity;
    int max;
    List<TbActivityDetail> tbActivityDetailList;
    ImageView img;
    TextView txt;
    Button play,next;
    SeekBar seekBar;
    MediaPlayer mp;
    Handler seekHandler = new Handler();
    int end = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a7);

        setupViews();
        setupMVP();

        tbActivity = mPresenter.getActivity(idlesson, activitynumber);
        max = mPresenter.max_Activitynumber(idlesson);
        int idactivity = tbActivity.get_id();
        tbActivityDetailList = mPresenter.getListActivityDetail(idactivity);

    }

    private void setupViews(){

        img = (ImageView)findViewById(R.id.img);
        txt = (TextView)findViewById(R.id.txt);

        mp = MediaPlayer.create(A7.this, R.raw.music);
        mp.setVolume(100,100);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                end = 1;
                next.setTextColor(Color.WHITE);
                next.setBackgroundResource(R.drawable.btn);
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

        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(end == 1) {

                    if (activitynumber == max) {
                        A7.this.finish();
                        startActivity(new Intent(A7.this, End.class));

                    } else {
                        mp.stop();

                        TbActivity tb_new = mPresenter.getActivity(idlesson, ++activitynumber);
                        int id_at_new = tb_new.getId_ActivityType();

                        switch (id_at_new){

                            case 1: break;
                            case 2: break;

                            case 3:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A3.class));
                                break;

                            case 4:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A4.class));
                                break;

                            case 5:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A5.class));
                                break;

                            case 6:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A6.class));
                                break;

                            case 7:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A7.class));
                                break;

                            case 8:
                                A8.idlesson = idlesson ;
                                A8.activitynumber = activitynumber;
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A8.class));
                                break;

                            case 9:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A9.class));
                                break;

                            case 10: break;
                            case 11: break;
                            case 12: break;
                            case 13: break;
                            case 14: break;

                            case 15:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A15.class));
                                break;

                            case 16: break;
                            case 17: break;

                            case 18:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A18.class));
                                break;

                            case 19:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A19.class));
                                break;

                            case 20:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A20.class));
                                break;

                            case 21: break;

                            case 22:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A22.class));
                                break;

                            case 23: break;

                            case 24:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A24.class));
                                break;

                            case 25:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A25.class));
                                break;

                            case 26:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A26.class));
                                break;

                            case 27:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A27.class));
                                break;

                            case 28:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A28.class));
                                break;

                            case 29:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A29.class));
                                break;

                            case 30:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A30.class));
                                break;

                            case 31:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A31.class));
                                break;

                            case 32:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A32.class));
                                break;

                            case 33:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A33.class));
                                break;

                            case 34:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A34.class));
                                break;

                            case 35:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A35.class));
                                break;

                            case 36: break;

                            case 37:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A37.class));
                                break;

                            case 38:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A38.class));
                                break;

                            case 39:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A39.class));
                                break;

                            case 40:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A40.class));
                                break;

                            case 41:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A41.class));
                                break;

                            case 42:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A42.class));
                                break;

                            case 43:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A43.class));
                                break;

                            case 44:
                                A7.this.finish();
                                startActivity(new Intent(A7.this,  A44.class));
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
        mStateMaintainer.put(A7_Presenter.class.getSimpleName(), mPresenter);
    }

    private void reinitialize() {
        Log.d(TAG, "reinitialize");
        mPresenter = mStateMaintainer.get(A7_Presenter.class.getSimpleName());
        mPresenter.setView(this);
        if ( mPresenter == null )
            setupComponent();
    }

    private void setupComponent(){
        Log.d(TAG, "setupComponent");
        SampleApp.get(this)
                .getAppComponent()
                .getA7Component(new A7_Module(this))
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

}