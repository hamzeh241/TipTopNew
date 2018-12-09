package com.tiptap.tda_user.tiptap.main.activity.view.login;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.tiptap.tda_user.tiptap.main.activity.view.LR.Enter;
import com.tiptap.tda_user.tiptap.main.activity.view.LR.LR;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;

public class Login extends BaseActivity implements MVP_Login.RequiredViewOps {

    Calendar myCalendar;
    List<TbLanguage> lans;
    List<String> title_lans;
    public static String selecte_lans_title;
    int Id_Language = 1;
    EditText username, email, password, name, lastname, age, city, country, birthday;
    Spinner spinner;
    CheckBox woman, man;
    Button login;
    String select_jen;
    TextView goenter, z, fname, lname, t_age, t_city, t_country,t_birthday;
    private static final String TAG = Login.class.getSimpleName();

    @Inject
    public MVP_Login.ProvidedPresenterOps mPresenter;
    private final StateMaintainer mStateMaintainer = new StateMaintainer(getFragmentManager(), Login.class.getName());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.register);

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
        myCalendar = Calendar.getInstance();
        username = (EditText) findViewById(R.id.edt_username);
        email = (EditText) findViewById(R.id.edt_email);
        password = (EditText) findViewById(R.id.edt_password);
        z = (TextView) findViewById(R.id.z);
        man = (CheckBox) findViewById(R.id.man);
        woman = (CheckBox) findViewById(R.id.woman);
        fname = (TextView) findViewById(R.id.fname);
        name = (EditText) findViewById(R.id.edt_name);
        lname = (TextView) findViewById(R.id.lname);
        lastname = (EditText) findViewById(R.id.edt_lastname);
        t_age = (TextView) findViewById(R.id.t_age);
        age = (EditText) findViewById(R.id.edt_age);
        t_city = (TextView) findViewById(R.id.t_city);
        city = (EditText) findViewById(R.id.edt_city);
        t_country = (TextView) findViewById(R.id.t_country);
        country = (EditText) findViewById(R.id.edt_country);
        t_birthday = (TextView) findViewById(R.id.t_birthday);
        birthday = (EditText) findViewById(R.id.edt_birthday);
        spinner = (Spinner) findViewById(R.id.spinner);
        login = (Button) findViewById(R.id.login);
        goenter = (TextView) findViewById(R.id.goenter);

        switch (selecte_lans_title){
            // فارسی
            case "فارسی":
                username.setHint(R.string.enter_1_FA);
                email.setHint(R.string.enter_2_FA);
                password.setHint(R.string.enter_3_FA);
                z.setText(R.string.enter_4_FA);
                man.setText(R.string.enter_5_FA);
                woman.setText(R.string.enter_6_FA);
                fname.setText(R.string.enter_7_FA); name.setText(R.string.enter_7_FA);
                lname.setText(R.string.enter_8_FA); lastname.setText(R.string.enter_8_FA);
                t_age.setText(R.string.enter_9_FA); age.setText(R.string.enter_9_FA);
                t_city.setText(R.string.enter_10_FA); city.setText(R.string.enter_10_FA);
                t_country.setText(R.string.enter_11_FA); country.setText(R.string.enter_11_FA);
                t_birthday.setText(R.string.enter_12_FA); birthday.setText(R.string.enter_12_FA);
                login.setText(R.string.enter_13_FA);
                goenter.setText(R.string.enter_14_FA);
                break;
            // کردی
            case "کردی":
                username.setHint(R.string.enter_1_KU);
                email.setHint(R.string.enter_2_KU);
                password.setHint(R.string.enter_3_KU);
                z.setText(R.string.enter_4_KU);
                man.setText(R.string.enter_5_KU);
                woman.setText(R.string.enter_6_KU);
                fname.setText(R.string.enter_7_KU); name.setText(R.string.enter_7_KU);
                lname.setText(R.string.enter_8_KU); lastname.setText(R.string.enter_8_KU);
                t_age.setText(R.string.enter_9_KU); age.setText(R.string.enter_9_KU);
                t_city.setText(R.string.enter_10_KU); city.setText(R.string.enter_10_KU);
                t_country.setText(R.string.enter_11_KU); country.setText(R.string.enter_11_KU);
                t_birthday.setText(R.string.enter_12_KU); birthday.setText(R.string.enter_12_KU);
                login.setText(R.string.enter_13_KU);
                goenter.setText(R.string.enter_14_KU);
                break;
            // ترکی آذری
            case "ترکی":
                username.setHint(R.string.enter_1_TA);
                email.setHint(R.string.enter_2_TA);
                password.setHint(R.string.enter_3_TA);
                z.setText(R.string.enter_4_TA);
                man.setText(R.string.enter_5_TA);
                woman.setText(R.string.enter_6_TA);
                fname.setText(R.string.enter_7_TA); name.setText(R.string.enter_7_TA);
                lname.setText(R.string.enter_8_TA); lastname.setText(R.string.enter_8_TA);
                t_age.setText(R.string.enter_9_TA); age.setText(R.string.enter_9_TA);
                t_city.setText(R.string.enter_10_TA); city.setText(R.string.enter_10_TA);
                t_country.setText(R.string.enter_11_TA); country.setText(R.string.enter_11_TA);
                t_birthday.setText(R.string.enter_12_TA); birthday.setText(R.string.enter_12_TA);
                login.setText(R.string.enter_13_TA);
                goenter.setText(R.string.enter_14_TA);
                break;
            // چینی
            case "چینی":
                username.setHint(R.string.enter_1_CH);
                email.setHint(R.string.enter_2_CH);
                password.setHint(R.string.enter_3_CH);
                z.setText(R.string.enter_4_CH);
                man.setText(R.string.enter_5_CH);
                woman.setText(R.string.enter_6_CH);
                fname.setText(R.string.enter_7_CH); name.setText(R.string.enter_7_CH);
                lname.setText(R.string.enter_8_CH); lastname.setText(R.string.enter_8_CH);
                t_age.setText(R.string.enter_9_CH); age.setText(R.string.enter_9_CH);
                t_city.setText(R.string.enter_10_CH); city.setText(R.string.enter_10_CH);
                t_country.setText(R.string.enter_11_CH); country.setText(R.string.enter_11_CH);
                t_birthday.setText(R.string.enter_12_CH); birthday.setText(R.string.enter_12_CH);
                login.setText(R.string.enter_13_CH);
                goenter.setText(R.string.enter_14_CH);
                break;
        }
    }

    private void after_setup(){
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //update
                String myFormat = "yyyy/MM/dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                birthday.setText(sdf.format(myCalendar.getTime()));
            }
        };

        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Login.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

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
                    age.setText("23");
                    city.setText("Berlin");
                    country.setText("Germany");
                    birthday.setText("1995/07/22");
                }else{
                    select_jen = "مرد";
                    man.setChecked(true);
                    name.setText("Max");
                    lastname.setText("Anderson");
                    age.setText("20");
                    city.setText("Paris");
                    country.setText("France");
                    birthday.setText("1998/05/08");
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
                    age.setText("20");
                    city.setText("Paris");
                    country.setText("France");
                    birthday.setText("1998/05/08");

                }else{
                    select_jen = "زن";
                    woman.setChecked(true);
                    name.setText("Lily");
                    lastname.setText("Anderson");
                    age.setText("23");
                    city.setText("Berlin");
                    country.setText("Germany");
                    birthday.setText("1995/07/22");
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
        for(int i=0 ; i<title_lans.size() ; i++){
            if (title_lans.get(i).equals(selecte_lans_title)){
                spinner.setSelection(i);
            }
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TbLanguage language = lans.get(position);
                Id_Language = language.get_id();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        goenter.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Login.this.finish();
                 startActivity(new Intent(Login.this, Enter.class));
             }
         });

        // login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String idlesson = "0";
                String a1 = username.getText().toString();
                String a2 = email.getText().toString();
                String a3 = password.getText().toString();
                String a4 = Id_Language+"";
                String a5 = name.getText().toString();
                String a6 = lastname.getText().toString();
                String a7 = age.getText().toString();
                String a8 = city.getText().toString();
                String a9 = country.getText().toString();
                String a10 = birthday.getText().toString();

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
        // name
        if (a5.equals("")) {
            name.setError("نام وارد شود");
            valid = false;
        } else {
            name.setError(null);
        }
        // lastname
        if (a6.equals("")) {
            lastname.setError("نام خانوادگی وارد شود");
            valid = false;
        } else {
            lastname.setError(null);
        }
        // age
        if (a7.equals("")) {
            age.setError("سن وارد شود");
            valid = false;
        } else {
            age.setError(null);
        }
        // city
        if (a8.equals("")) {
            city.setError("شهر وارد شود");
            valid = false;
        } else {
            city.setError(null);
        }
        // country
        if (a9.equals("")) {
            country.setError("کشور وارد شود");
            valid = false;
        } else {
            country.setError(null);
        }
        // birthday
        if (a10.equals("")) {
            birthday.setError("تاریخ تولد وارد شود");
            valid = false;
        } else {
            birthday.setError(null);
        }

        return valid;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    public void back(){
        Login.this.finish();
        startActivity(new Intent(Login.this, LR.class));
    }
}