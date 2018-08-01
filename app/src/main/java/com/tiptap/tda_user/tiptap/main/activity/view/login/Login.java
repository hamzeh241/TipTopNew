package com.tiptap.tda_user.tiptap.main.activity.view.login;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.Login_Module;
import com.tiptap.tda_user.tiptap.main.activity.Api.Post_User;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Login;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Login_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbLanguage;
import com.tiptap.tda_user.tiptap.main.activity.view.BaseActivity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class Login extends BaseActivity implements MVP_Login.RequiredViewOps {

    List<TbLanguage> lans;
    List<String> title_lans;
    int Id_Language = 0;
    EditText username, email, password, name, lastname, age, city, country;
    Spinner spinner;
    CheckBox woman, man;
    Button login;
    String select_language, select_jen;
    private static final String TAG = Login.class.getSimpleName();

    @Inject
    public MVP_Login.ProvidedPresenterOps mPresenter;
    private final StateMaintainer mStateMaintainer = new StateMaintainer(getFragmentManager(), Login.class.getName());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // hide keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        setupViews();
        setupMVP();
        after_setup();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy(isChangingConfigurations());
    }

    private void setupViews() {

        username = (EditText) findViewById(R.id.edt_username);
        email = (EditText) findViewById(R.id.edt_email);
        password = (EditText) findViewById(R.id.edt_password);
        name = (EditText) findViewById(R.id.edt_name);
        lastname = (EditText) findViewById(R.id.edt_lastname);
        age = (EditText) findViewById(R.id.edt_age);
        city = (EditText) findViewById(R.id.edt_city);
        country = (EditText) findViewById(R.id.edt_country);
        spinner = (Spinner) findViewById(R.id.spinner);
        woman = (CheckBox) findViewById(R.id.woman);
        man = (CheckBox) findViewById(R.id.man);
        login = (Button) findViewById(R.id.login);
    }

    private void after_setup(){
        select_jen = "مرد";
        // checkBox
        woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(woman.isChecked()){
                    select_jen = "زن";
                    man.setChecked(false);
                    name.setText("Lily");
                    lastname.setText("Anderson");
                    age.setText("21");
                    city.setText("Bon");
                    country.setText("Germany");
                }else{
                    select_jen = "مرد";
                    man.setChecked(true);
                    name.setText("Max");
                    lastname.setText("Anderson");
                    age.setText("27");
                    city.setText("Abc");
                    country.setText("France");
                }
            }
        });
        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(man.isChecked()){
                    select_jen = "مرد";
                    woman.setChecked(false);
                    name.setText("Max");
                    lastname.setText("Anderson");
                    age.setText("27");
                    city.setText("Abc");
                    country.setText("France");

                }else{
                    select_jen = "زن";
                    woman.setChecked(true);
                    name.setText("Lily");
                    lastname.setText("Anderson");
                    age.setText("21");
                    city.setText("Bon");
                    country.setText("Germany");
                }
            }
        });

        // spinner
        lans = mPresenter.getLanguages();
        title_lans = new ArrayList<String>();
        for (int i = 0; i < lans.size(); i++) {
            title_lans.add(lans.get(i).getLanguage());
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, title_lans);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TbLanguage language = lans.get(position);
                Id_Language = language.get_id();
                select_language = language.getLanguage();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        // login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String idlesson = "0";
                String a1 = username.getText().toString();
                String a2 = email.getText().toString();
                String a3 = password.getText().toString();
                String a4 = select_language;
                String a5 = select_jen;
                String a6 = name.getText().toString();
                String a7 = lastname.getText().toString();
                String a8 = age.getText().toString();
                String a9 = city.getText().toString();
                String a10 = country.getText().toString();

                if (validate(a1,a2,a3,a4,a5,a6,a7,a8,a9,a10)) {
                    if (haveNetworkConnection()) {
                        try {
                             new Post_User(mPresenter,getAppContext(),Login.this,haveNetworkConnection(),idlesson,a1,a2,a3,a4,a5,a6,a7,a8,a9,a10).post();
                        } catch (Exception e) {}

                    } else {
                        Toast.makeText(getApplicationContext(), "خطا در اتصال به اینترنت", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "تمام اطلاعات را به درستی وارد کنید", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setupMVP() {
        if (mStateMaintainer.firstTimeIn()) {
            initialize();
        } else {
            reinitialize();
        }
    }

    private void initialize() {
        setupComponent();
        mStateMaintainer.put(Login_Presenter.class.getSimpleName(), mPresenter);
    }

    private void reinitialize() {
        mPresenter = mStateMaintainer.get(Login_Presenter.class.getSimpleName());
        mPresenter.setView(this);
        if (mPresenter == null)
            setupComponent();
    }

    private void setupComponent() {
        SampleApp.get(this).getAppComponent().getLoginComponent(new Login_Module(this)).inject(this);
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    public boolean validate(String a1,String a2,String a3,String a4,String a5,String a6,String a7,String a8,String a9,String a10) {
        boolean valid = true;
        // username
        if (a1.equals("")) {
            username.setError("نام کاربری وارد شود");
            valid = false;
        } else {
            username.setError(null);
        }
        // email
        if (a2.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(a2).matches()) {
            email.setError("ایمیل معتبر وارد شود ");
            valid = false;
        } else {
            email.setError(null);
        }
        // password
        if (a3.isEmpty() || password.length() < 6) {
            password.setError("رمز عبور حداقل 6 کاراکتر باشد");
            valid = false;
        } else {
            password.setError(null);
        }
        // language
        if (a4.equals("")) {
            valid = false;
        }
        // jensiat
        if (a5.equals("")) {
            valid = false;
        }
        // name
        if (a6.equals("")) {
            name.setError("نام وارد شود");
            valid = false;
        } else {
            name.setError(null);
        }
        // lastname
        if (a7.equals("")) {
            lastname.setError("نام خانوادگی وارد شود");
            valid = false;
        } else {
            lastname.setError(null);
        }
        // age
        if (a8.equals("")) {
            age.setError("سن وارد شود");
            valid = false;
        } else {
            age.setError(null);
        }
        // city
        if (a9.equals("")) {
            city.setError("شهر وارد شود");
            valid = false;
        } else {
            city.setError(null);
        }
        // country
        if (a10.equals("")) {
            country.setError("کشور وارد شود");
            valid = false;
        } else {
            country.setError(null);
        }

        return valid;
    }
}