package com.tiptap.tda_user.tiptap.main.activity.view.splash;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.tiptap.tda_user.tiptap.main.activity.Api.Get_Function;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A7;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Splash;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.Splash_Module;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Splash_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A1;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A7;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.BaseActivity;
import com.tiptap.tda_user.tiptap.main.activity.view.function.Function;
import com.tiptap.tda_user.tiptap.main.activity.view.login.Login;
import javax.inject.Inject;

import static com.tiptap.tda_user.tiptap.main.activity.view.activity.BaseActivity.idlesson;
import static com.tiptap.tda_user.tiptap.main.activity.view.activity.BaseActivity.Act_Status;
import static com.tiptap.tda_user.tiptap.main.activity.view.activity.BaseActivity.activitynumber;
import static com.tiptap.tda_user.tiptap.main.activity.view.activity.BaseActivity.idfunction;
import static com.tiptap.tda_user.tiptap.main.activity.view.activity.BaseActivity.idactivity;

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


                    A1.idlesson = 4 ;
                    A1.idfunction = 2 ;
                    A1.idactivity = 184;
                    A1.Act_Status = "first";
                    A1.activitynumber=13;
                    Splash.this.finish();
                    startActivity(new Intent(Splash.this,  A1.class));
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
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/GreatVibes.ttf");
        text.setTypeface(type);
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