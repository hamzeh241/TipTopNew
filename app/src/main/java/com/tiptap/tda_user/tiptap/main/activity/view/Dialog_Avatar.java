package com.tiptap.tda_user.tiptap.main.activity.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tiptap.tda_user.tiptap.R;

public class Dialog_Avatar extends Dialog implements View.OnClickListener {

    Activity c;
    Dialog d;
    EditText mobile;
    TextView other_login;
    LinearLayout btn_login;

    public Dialog_Avatar(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //setContentView(R.layout.avatar);

        //mobile = (EditText) findViewById(R.id.mobile);
        //other_login = (TextView) findViewById(R.id.other_login);
        //btn_login = (LinearLayout) findViewById(R.id.btn_login);

        other_login.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        /*switch (v.getId()) {

            case R.id.btn_login:
                String m = faToEn(mobile.getText().toString());
                if(m.equals("")){
                    Toast.makeText(c, "شماره موبایل خود را وارد کنید", Toast.LENGTH_LONG).show();
                }else{
                    dismiss();
                   // new Get_Verification(true,c,c,m,true);
                }
                break;

            case R.id.other_login:
                dismiss();
                //Dialog_Login2 cdd=new Dialog_Login2(c);
                //cdd.show();
                break;
        }*/
        dismiss();
    }

    /*public String faToEn(String num) {
        return num
                .replace("۰", "0")
                .replace("۱", "1")
                .replace("۲", "2")
                .replace("۳", "3")
                .replace("۴", "4")
                .replace("۵", "5")
                .replace("۶", "6")
                .replace("۷", "7")
                .replace("۸", "8")
                .replace("۹", "9");
    }*/
}