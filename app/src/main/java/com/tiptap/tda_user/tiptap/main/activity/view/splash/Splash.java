package com.tiptap.tda_user.tiptap.main.activity.view.splash;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Splash;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.Splash_Module;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Splash_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A12;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A1_;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A2;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A20;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A45;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A47;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A49;
import com.tiptap.tda_user.tiptap.main.activity.view.function.Function;
import com.tiptap.tda_user.tiptap.main.activity.view.login.Login;
import javax.inject.Inject;

public class Splash extends AppCompatActivity implements MVP_Splash.RequiredViewOps {
    TextView text;
    private static final String TAG = Splash.class.getSimpleName();

    @Inject
    public MVP_Splash.ProvidedPresenterOps mPresenter;

    private final StateMaintainer mStateMaintainer = new StateMaintainer(getFragmentManager(), Splash.class.getName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        setupViews();
        setupMVP();

        final int count = mPresenter.getCount_User();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (count == 0){
                    Splash.this.finish();
                    startActivity(new Intent(Splash.this, Login.class));

                }else if (count == 1){
                    int fid = mPresenter.Id_Function();
                    Function.id_function = fid;

                   // Splash.this.finish();
                    //__________________________________



                    A12.idlesson = 227 ;
                    A12.idfunction =10;
                    A12.idactivity = 900;
                    A12.Act_Status = "first";
                    A12.activitynumber=19;
                    Splash.this.finish();
                    startActivity(new Intent(Splash.this,  A12.class));

                    //_____________________________________
                   // startActivity(new Intent(Splash.this, Function.class));

                }
            }
        }, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy(isChangingConfigurations());
    }

    private void setupViews(){
        text = (TextView)findViewById(R.id.txt);
//        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/GreatVibes.ttf");
//        text.setTypeface(type);
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
        mStateMaintainer.put(Splash_Presenter.class.getSimpleName(), mPresenter);
    }

    private void reinitialize() {
        Log.d(TAG, "reinitialize");
        mPresenter = mStateMaintainer.get(Splash_Presenter.class.getSimpleName());
        mPresenter.setView(this);
        if ( mPresenter == null )
            setupComponent();
    }

    private void setupComponent(){
        Log.d(TAG, "setupComponent");
        SampleApp.get(this)
                .getAppComponent()
                .getSplashComponent(new Splash_Module(this))
                .inject(this);
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public Context getActivityContext() {
        return this;
    }
}