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
import android.widget.Toast;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.Language_Module;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Language;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Language_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.LR.Enter;
import com.tiptap.tda_user.tiptap.main.activity.view.LR.LR;
import com.tiptap.tda_user.tiptap.main.activity.view.login.Login;
import javax.inject.Inject;

public class Language extends AppCompatActivity implements MVP_Language.RequiredViewOps {

    int back_pressed = 0;
    private CircleMenu circleMenu;
    String menu="";

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
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.earth, R.drawable.earth)
                .addSubMenu(Color.parseColor("#c2185b"), R.drawable.iran)
                .addSubMenu(Color.parseColor("#c2185b"), R.drawable.kurd)
                .addSubMenu(Color.parseColor("#c2185b"), R.drawable.turkey)
                .addSubMenu(Color.parseColor("#c2185b"), R.drawable.china)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        switch(index){
                            case 0:
                                menu = "فارسی";
                                break;
                            case 1:
                                menu = "کردی";
                                break;
                            case 2:
                                menu = "ترکی";
                                break;
                            case 3:
                                menu = "چینی";
                                break;
                        }
                    }
                }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

            @Override
            public void onMenuOpened() {}

            @Override
            public void onMenuClosed() {
                if(menu.equals("") || menu.equals(null)){

                }else{
                    LR.selecte_lans_title = menu;
                    Enter.selecte_lans_title = menu;
                    Login.selecte_lans_title = menu;
                    Language.this.finish();
                    startActivity(new Intent(Language.this, LR.class));
                }
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

   private void setupViews(){}

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
        back_pressed++;
        back();
    }

    public void back(){
        if(back_pressed == 1){
            Toast.makeText(getApplicationContext(), "برای خروج دوباره برگشت را بفشارید", Toast.LENGTH_LONG).show();
        }else{
            Language.this.finish();
        }
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
    }
}