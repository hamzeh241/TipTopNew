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
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.A28_Module;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A28;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A28_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivityDetail;
import java.util.List;
import javax.inject.Inject;

public class A28 extends AppCompatActivity
                 implements MVP_A28.RequiredViewOps {

    private static final String TAG = A7.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A7.class.getName());

    @Inject
    public MVP_A28.ProvidedPresenterOps mPresenter;

    public static int idlesson;
    public static int activitynumber;
    TbActivity tbActivity;
    int max;
    List<TbActivityDetail> tbActivityDetailList;
    EditText editText;
    String ans;
    Button play,next;
    SeekBar seekBar;
    MediaPlayer mp;
    Handler seekHandler = new Handler();
    int end = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a28);

        setupViews();
        setupMVP();

        tbActivity = mPresenter.getActivity(idlesson, activitynumber);
        max = mPresenter.max_Activitynumber(idlesson);
        int idactivity = tbActivity.get_id();
        tbActivityDetailList = mPresenter.getListActivityDetail(idactivity);
    }

    private void setupViews(){

        editText = (EditText) findViewById(R.id.txt);

        mp = MediaPlayer.create(A28.this, R.raw.music);
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

                switch (next.getText().toString()) {

                    case "check":

                        if(editText.getText().toString() != ""){

                            boolean answer = true;
                            if(editText.equals(ans)){
                                answer = true;
                            }else{
                                answer = false;
                            }

                            if (answer == true) {
                                Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
                            } else if (answer == false) {
                                Toast.makeText(getApplicationContext(), "False", Toast.LENGTH_LONG).show();
                            }

                            next.setTextColor(Color.WHITE);
                            next.setBackgroundResource(R.drawable.btn);
                            next.setText("countinue");
                        }

                        break;

                    case "countinue":

                        if(end == 1 && editText.getText().toString() != ""){

                            mp.stop();

                            if (activitynumber == max) {
                                A28.this.finish();
                                startActivity(new Intent(A28.this, End.class));

                            } else {

                                TbActivity tb_new = mPresenter.getActivity(idlesson, ++activitynumber);
                                int id_at_new = tb_new.getId_ActivityType();

                                Toast.makeText(getApplicationContext(), ""+id_at_new , Toast.LENGTH_LONG).show();
                                switch (id_at_new){

                                    case 1: break;
                                    case 2: break;

                                    case 3:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A3.class));
                                        break;

                                    case 4:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A4.class));
                                        break;

                                    case 5:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A5.class));
                                        break;

                                    case 6:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A6.class));
                                        break;

                                    case 7:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A7.class));
                                        break;

                                    case 8:
                                        A8.idlesson = idlesson ;
                                        A8.activitynumber = activitynumber;
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A8.class));
                                        break;

                                    case 9:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A9.class));
                                        break;

                                    case 10: break;
                                    case 11: break;
                                    case 12: break;
                                    case 13: break;
                                    case 14: break;

                                    case 15:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A15.class));
                                        break;

                                    case 16: break;
                                    case 17: break;

                                    case 18:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A18.class));
                                        break;

                                    case 19:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A19.class));
                                        break;

                                    case 20:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A20.class));
                                        break;

                                    case 21: break;

                                    case 22:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A22.class));
                                        break;

                                    case 23: break;

                                    case 24:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A24.class));
                                        break;

                                    case 25:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A25.class));
                                        break;

                                    case 26:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A26.class));
                                        break;

                                    case 27:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A27.class));
                                        break;

                                    case 28:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A28.class));
                                        break;

                                    case 29:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A29.class));
                                        break;

                                    case 30:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A30.class));
                                        break;

                                    case 31:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A31.class));
                                        break;

                                    case 32:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A32.class));
                                        break;

                                    case 33:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A33.class));
                                        break;

                                    case 34:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A34.class));
                                        break;

                                    case 35:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A35.class));
                                        break;

                                    case 36: break;

                                    case 37:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A37.class));
                                        break;

                                    case 38:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A38.class));
                                        break;

                                    case 39:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A39.class));
                                        break;

                                    case 40:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A40.class));
                                        break;

                                    case 41:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A41.class));
                                        break;

                                    case 42:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A42.class));
                                        break;

                                    case 43:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A43.class));
                                        break;

                                    case 44:
                                        A28.this.finish();
                                        startActivity(new Intent(A28.this,  A44.class));
                                        break;
                                }

                            }

                        }

                        break;
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
}