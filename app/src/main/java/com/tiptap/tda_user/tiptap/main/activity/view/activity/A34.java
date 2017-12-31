package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.A34_Module;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A34;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A34_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivityDetail;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;

public class A34 extends AppCompatActivity
                 implements MVP_A34.RequiredViewOps {

    private static final String TAG = A34.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A34.class.getName());

    @Inject
    public MVP_A34.ProvidedPresenterOps mPresenter;

    public static int idlesson;
    public static int activitynumber;
    TbActivity tbActivity;
    int max;
    List<TbActivityDetail> tbActivityDetailList;
    String you_say = "";
    Button next;
    TextView txt;
    private Button voice;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a34);

        setupViews();
        setupMVP();

        tbActivity = mPresenter.getActivity(idlesson, activitynumber);
        max = mPresenter.max_Activitynumber(idlesson);
        int idactivity = tbActivity.get_id();
        tbActivityDetailList = mPresenter.getListActivityDetail(idactivity);
    }

    private void setupViews() {
        txt = (TextView)findViewById(R.id.txt);
        next = (Button)findViewById(R.id.next);
        voice = (Button) findViewById(R.id.voice);

        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (next.getText().toString()){
                    case "check":
                        // you_say != ""
                        if(true){

                            boolean answer = true;
                            if(you_say.equals(txt)){
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

                        // you_say != ""
                        if(true){

                            if (activitynumber == max) {
                                A34.this.finish();
                                startActivity(new Intent(A34.this, End.class));

                                // post

                                // update
                                List<Integer> id_less = mPresenter.lesson();
                                for(int i=0 ; i< id_less.size() ; i++){
                                    if(id_less.get(i) == idlesson){
                                        int next = i+1;
                                        mPresenter.update_idlesson(id_less.get(next));
                                    }
                                }

                            } else {

                                TbActivity tb_new = mPresenter.getActivity(idlesson, ++activitynumber);
                                int id_at_new = tb_new.getId_ActivityType();

                                switch (id_at_new){

                                    case 1: break;
                                    case 2: break;

                                    case 3:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A3.class));
                                        break;

                                    case 4:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A4.class));
                                        break;

                                    case 5:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A5.class));
                                        break;

                                    case 6:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A6.class));
                                        break;

                                    case 7:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A7.class));
                                        break;

                                    case 8:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A8.class));
                                        break;

                                    case 9:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A9.class));
                                        break;

                                    case 10: break;
                                    case 11: break;
                                    case 12: break;
                                    case 13: break;
                                    case 14: break;

                                    case 15:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A15.class));
                                        break;

                                    case 16: break;
                                    case 17: break;

                                    case 18:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A18.class));
                                        break;

                                    case 19:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A19.class));
                                        break;

                                    case 20:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A20.class));
                                        break;

                                    case 21: break;

                                    case 22:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A22.class));
                                        break;

                                    case 23: break;

                                    case 24:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A24.class));
                                        break;

                                    case 25:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A25.class));
                                        break;

                                    case 26:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A26.class));
                                        break;

                                    case 27:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A27.class));
                                        break;

                                    case 28:
                                        A28.idlesson = idlesson ;
                                        A28.activitynumber = activitynumber;
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A28.class));
                                        break;

                                    case 29:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A29.class));
                                        break;

                                    case 30:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A30.class));
                                        break;

                                    case 31:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A31.class));
                                        break;

                                    case 32:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A32.class));
                                        break;

                                    case 33:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A33.class));
                                        break;

                                    case 34:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A34.class));
                                        break;

                                    case 35:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A35.class));
                                        break;

                                    case 36: break;

                                    case 37:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A37.class));
                                        break;

                                    case 38:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A38.class));
                                        break;

                                    case 39:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A39.class));
                                        break;

                                    case 40:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A40.class));
                                        break;

                                    case 41:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A41.class));
                                        break;

                                    case 42:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A42.class));
                                        break;

                                    case 43:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A43.class));
                                        break;

                                    case 44:
                                        A34.this.finish();
                                        startActivity(new Intent(A34.this,  A44.class));
                                        break;
                                }
                            }
                        }

                        break;
                }
            }
        });
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
        mStateMaintainer.put(A34_Presenter.class.getSimpleName(), mPresenter);
    }

    private void reinitialize() {
        Log.d(TAG, "reinitialize");
        mPresenter = mStateMaintainer.get(A34_Presenter.class.getSimpleName());
        mPresenter.setView(this);
        if ( mPresenter == null )
            setupComponent();
    }

    private void setupComponent(){
        Log.d(TAG, "setupComponent");
        SampleApp.get(this)
                .getAppComponent()
                .getA34Component(new A34_Module(this))
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
            //if (intent.resolveActivity(getPackageManager()) != null){
            //txt.setText("");
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
                    you_say = result.get(0);
                    Toast.makeText(getApplicationContext(), you_say , Toast.LENGTH_SHORT).show();
                    next.setTextColor(Color.WHITE);
                    next.setBackgroundResource(R.drawable.btn);
                }
                break;
            }
        }
    }
}