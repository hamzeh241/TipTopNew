package com.tiptap.tda_user.tiptap.main.activity.view.LR;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.Enter_Module;
import com.tiptap.tda_user.tiptap.main.activity.Api.Post_CheckLogin;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Enter;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Enter_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.BaseActivity;
import com.tiptap.tda_user.tiptap.main.activity.view.function.Function;
import com.tiptap.tda_user.tiptap.main.activity.view.language.Language;
import com.tiptap.tda_user.tiptap.main.activity.view.lesson.Lesson;
import com.tiptap.tda_user.tiptap.main.activity.view.login.Login;

import org.json.JSONException;

import javax.inject.Inject;

public class Enter extends BaseActivity implements MVP_Enter.RequiredViewOps {

    TextView gologin;
    EditText edt_username, edt_password;
    Button btn_login;
    public static String selecte_lans_title;

    @Inject
    public MVP_Enter.ProvidedPresenterOps mPresenter;
    private final StateMaintainer mStateMaintainer = new StateMaintainer(getFragmentManager(), Enter.class.getName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

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
        gologin = (TextView) findViewById(R.id.gologin);
        edt_username = (EditText) findViewById(R.id.edt_username);
        edt_password = (EditText) findViewById(R.id.edt_password);
        btn_login = (Button) findViewById(R.id.login);

        switch (selecte_lans_title){
            // فارسی
            case "فارسی":
                edt_username.setHint(R.string.login_1_FA);
                edt_password.setHint(R.string.login_2_FA);
                btn_login.setText(R.string.login_3_FA);
                gologin.setText(R.string.login_4_FA);
                break;
            // کردی
            case "کردی":
                edt_username.setHint(R.string.login_1_KU);
                edt_password.setHint(R.string.login_2_KU);
                btn_login.setText(R.string.login_3_KU);
                gologin.setText(R.string.login_4_KU);
                break;
            // ترکی آذری
            case "ترکی":
                edt_username.setHint(R.string.login_1_KU);
                edt_password.setHint(R.string.login_2_KU);
                btn_login.setText(R.string.login_3_KU);
                gologin.setText(R.string.login_4_KU);
                break;
            // چینی
            case "چینی":
                edt_username.setHint(R.string.login_1_CH);
                edt_password.setHint(R.string.login_2_CH);
                btn_login.setText(R.string.login_3_CH);
                gologin.setText(R.string.login_4_CH);
                break;
        }
    }

    private void after_setup(){
        gologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Enter.this.finish();
                startActivity(new Intent(Enter.this, Login.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u = edt_username.getText().toString();
                String p = edt_password.getText().toString();
                try {
                    new Post_CheckLogin(mPresenter, getApplicationContext(), Enter.this, haveNetworkConnection(), u, p).post();
                } catch (JSONException e) {}
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
        mStateMaintainer.put(Enter_Presenter.class.getSimpleName(), mPresenter);
    }

    private void reinitialize() {
        mPresenter = mStateMaintainer.get(Enter_Presenter.class.getSimpleName());
        mPresenter.setView(this);
        if (mPresenter == null)
            setupComponent();
    }

    private void setupComponent() {
        SampleApp.get(this).getAppComponent().getEnterComponent(new Enter_Module(this)).inject(this);
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    public void back(){
        Enter.this.finish();
        startActivity(new Intent(Enter.this, LR.class));
    }
}