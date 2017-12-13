package com.tiptap.tda_user.tiptap.main.activity.view.language;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.main.activity.view.function_lesson.Function;

import javax.inject.Inject;

public class Language{
        //extends AppCompatActivity implements MVP_Language
       // .RequiredViewOps {

  /*  private CircleMenu circleMenu;
    String menu;

    @Inject
    public MVP_Language.ProvidedPresenterOps mPresenter;

    private static final String TAG = Language.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), Language.class.getName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language);

        setupViews();
        setupMVP();

        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);

        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.mipmap.icon_earth, R.mipmap.icon_earth)
                .addSubMenu(Color.parseColor("#c2185b"), R.mipmap.icon_kurdistanflag)
                .addSubMenu(Color.parseColor("#c2185b"), R.mipmap.icon_turkeyflag)
                .addSubMenu(Color.parseColor("#c2185b"), R.mipmap.icon_chineseflag)
                .addSubMenu(Color.parseColor("#c2185b"), R.mipmap.icon_iranflag)
                .setOnMenuSelectedListener(new com.hitomi.cmlibrary.OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        switch(index){
                            case 0:
                                menu = "Kurdi";
                                break;
                            case 1:
                                menu = "Turki";
                                break;
                            case 2:
                                menu = "Chini";
                                break;
                            case 3:
                                menu = "Farsi";
                                break;
                        }
                    }
                }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

            @Override
            public void onMenuOpened() {}

            @Override
            public void onMenuClosed() {
                Function.lan = menu;
                startActivity(new Intent(Language.this, Function.class));
            }
        });
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                circleMenu.openMenu();
            }
        },500);
    }

   private void setupViews(){
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
        mStateMaintainer.put(Language_Presenter.class.getSimpleName(), mPresenter);
    }

    private void reinitialize() {
        Log.d(TAG, "reinitialize");
        mPresenter = mStateMaintainer.get(Language_Presenter.class.getSimpleName());
        mPresenter.setView(this);
        if ( mPresenter == null )
            setupComponent();
    }

    private void setupComponent(){
        Log.d(TAG, "setupComponent");
        SampleApp.get(this)
                .getAppComponent()
                .getLanguageComponent(new Language_Module(this))
                .inject(this);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        circleMenu.openMenu();
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public void onBackPressed() {
        circleMenu.closeMenu();
        this.finish();
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public Context getActivityContext() {
            return this;
    }


    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }*/
}