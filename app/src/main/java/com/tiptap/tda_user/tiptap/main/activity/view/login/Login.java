package com.tiptap.tda_user.tiptap.main.activity.view.login;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.Login_Module;
import com.tiptap.tda_user.tiptap.main.activity.Api.Get_Glossary;
import com.tiptap.tda_user.tiptap.main.activity.Api.Post_User;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Login;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Login_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbLanguage;
import com.tiptap.tda_user.tiptap.main.activity.view.function_lesson.Function;
import org.json.JSONException;
import java.util.List;
import javax.inject.Inject;

public class Login
        extends AppCompatActivity
        implements MVP_Login.RequiredViewOps {

    EditText username, email, password;
    int Id_Language;
    Button b;
    List<TbLanguage> lans;
    private static final String TAG = Login.class.getSimpleName();

    @Inject
    public MVP_Login.ProvidedPresenterOps mPresenter;
    private final StateMaintainer mStateMaintainer = new StateMaintainer(getFragmentManager(), Login.class.getName());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        setupViews();
        setupMVP();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Username = username.getText().toString();
                String Email = email.getText().toString();
                String Password = password.getText().toString();

                if(Username!="" && Email!="" && Password!="" && Id_Language+""!=""){
                    try {
                        String Q = "insert into TbUser (UserName,Password,Email,Id_Language) values ('" + Username + "','" + password + "','" + Email + "','" + Id_Language + "')" ;
                        mPresenter.Insert_User(Q);
                        new Post_User(getAppContext(), Login.this, Username, Email, Password, Id_Language).post();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if (mPresenter.getCount_User() == 1){
                        //new Get_Glossary(haveNetworkConnection(), mPresenter, getAppContext(), Login.this, Id_Language);

                        Login.this.finish();
                        startActivity(new Intent(Login.this, Function.class));
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy(isChangingConfigurations());
    }

    private void setupViews(){

        username = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        b = (Button)findViewById(R.id.login);

        lans = mPresenter.getLanguages();
        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lans);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TbLanguage language = lans.get(position);
                Id_Language = language.get_id();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
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
        mStateMaintainer.put(Login_Presenter.class.getSimpleName(), mPresenter);
    }

    private void reinitialize() {
        Log.d(TAG, "reinitialize");
        mPresenter = mStateMaintainer.get(Login_Presenter.class.getSimpleName());
        mPresenter.setView(this);
        if ( mPresenter == null )
            setupComponent();
    }

    private void setupComponent(){
        Log.d(TAG, "setupComponent");
        SampleApp.get(this)
                .getAppComponent()
                .getLoginComponent(new Login_Module(this))
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