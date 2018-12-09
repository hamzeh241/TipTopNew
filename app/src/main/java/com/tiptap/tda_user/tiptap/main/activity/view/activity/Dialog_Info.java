package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import com.tiptap.tda_user.tiptap.R;

public class Dialog_Info extends Dialog{

    Activity c;
    String fname, lname, age, city, country, birthday;
    TextView txt_fname, txt_lname, txt_age, txt_city, txt_country, txt_birthday;

    public Dialog_Info(Activity a, String a1, String a2, String a3, String a4, String a5, String a6) {
        super(a);
        this.c = a;
        fname = a1;
        lname = a2;
        age = a3;
        city = a4;
        country = a5;
        birthday = a6;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog);

        txt_fname = (TextView)findViewById(R.id.fname);
        txt_lname = (TextView)findViewById(R.id.lname);
        txt_age = (TextView)findViewById(R.id.age);
        txt_city = (TextView)findViewById(R.id.city);
        txt_country = (TextView)findViewById(R.id.country);
        txt_birthday = (TextView)findViewById(R.id.birthday);

        txt_fname.setText(fname);
        txt_lname.setText(lname);
        txt_age.setText(age);
        txt_city.setText(city);
        txt_country.setText(country);
        txt_birthday.setText(birthday);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}