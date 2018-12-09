package com.tiptap.tda_user.tiptap.main.activity.view.LR;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.main.activity.view.BaseActivity;
import com.tiptap.tda_user.tiptap.main.activity.view.language.Language;
import com.tiptap.tda_user.tiptap.main.activity.view.login.Login;

public class LR extends BaseActivity {

    Button enter, login;
    public static String selecte_lans_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lr);

        enter = (Button)findViewById(R.id.enter);
        login = (Button)findViewById(R.id.login);

        switch (selecte_lans_title){
            // فارسی
            case "فارسی":
                enter.setText(R.string.lr_1_FA);
                login.setText(R.string.lr_2_FA);
                break;
            // کردی
            case "کردی":
                enter.setText(R.string.lr_1_KU);
                login.setText(R.string.lr_2_KU);
                break;
            // ترکی آذری
            case "ترکی":
                enter.setText(R.string.lr_1_TA);
                login.setText(R.string.lr_2_TA);
                break;
            // چینی
            case "چینی":
                enter.setText(R.string.lr_1_CH);
                login.setText(R.string.lr_2_CH);
                break;
        }

        Button login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LR.this.finish();
                startActivity(new Intent(LR.this, Login.class));
            }
        });
        Button enter = (Button)findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LR.this.finish();
                startActivity(new Intent(LR.this, Enter.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        LR.this.finish();
        startActivity(new Intent(LR.this, Language.class));
    }
}
