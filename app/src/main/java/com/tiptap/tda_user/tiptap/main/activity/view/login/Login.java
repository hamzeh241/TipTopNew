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
import android.widget.Toast;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.Login_Module;
import com.tiptap.tda_user.tiptap.main.activity.Api.Get_Glossary;
import com.tiptap.tda_user.tiptap.main.activity.Api.Post_User;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Login;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Login_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbLanguage;
import com.tiptap.tda_user.tiptap.main.activity.view.function.Function;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class Login
        extends AppCompatActivity
        implements MVP_Login.RequiredViewOps {

    List<TbLanguage> lans;
    List<String> title_lans;
    int Id_Language = 0;
    EditText username, email, password;
    Spinner s;
    Button b;
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

        lans = mPresenter.getLanguages();
        title_lans = new ArrayList<String>();

        for(int i=0 ; i<lans.size() ; i++){
            title_lans.add(lans.get(i).getLanguage());
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, title_lans);
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

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Username = username.getText().toString();
                String Email = email.getText().toString();
                String Password = password.getText().toString();

                if(validate()){

                    if(haveNetworkConnection()){

                        try{

                            new Post_User(mPresenter, getAppContext(), Login.this, Username, Email, Password, Id_Language).post();

                            new Get_Glossary(haveNetworkConnection(), mPresenter, getAppContext(), Login.this, Id_Language);

                            Login.this.finish();
                            startActivity(new Intent(Login.this, Function.class));

                        }catch (Exception e){}

                    }else {
                        Toast.makeText(getApplicationContext(), "خطا در اتصال به اینترنت" , Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "تمام اطلاعات را به درستی وارد کنید" , Toast.LENGTH_LONG).show();
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
        s = (Spinner) findViewById(R.id.spinner);
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

    public boolean validate() {
        boolean valid = true;

        String Username = username.getText().toString();
        String Email = email.getText().toString();
        String Password = password.getText().toString();

        if (Username.equals("")) {
            username.setError("enter a valid username");
            valid = false;
        } else {
            username.setError(null);
        }

        if (Email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }

        if (Password.isEmpty() || password.length() < 4 || password.length() > 10) {
            password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
        }

        if(Id_Language == 0){
            valid = false;
        }

        return valid;
    }
}