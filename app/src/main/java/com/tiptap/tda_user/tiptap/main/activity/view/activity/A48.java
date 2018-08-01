package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.A48_Module;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Main_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.view.BaseActivity;

import java.util.List;
import java.util.regex.Pattern;
import javax.inject.Inject;

public class A48 extends BaseActivity
        implements MVP_Main.RequiredViewOps,View.OnClickListener {

    private static final String TAG = A48.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A48.class.getName());

    @Inject
    public MVP_Main.ProvidedPresenterOps mPresenter;


    LinearLayout l;
    TextView t[];
    EditText e[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a48);

        setupViews();
        setupMVP();

        max = mPresenter.max_Activitynumber(idlesson);

        tbActivity = mPresenter.getActivity(idlesson, activitynumber);
        title1= tbActivity.getTitle1();
        title2 = tbActivity.getTitle2();

        after_setup();
    }

    private void setupViews() {

        next = (Button) findViewById(R.id.next);

        l = (LinearLayout)findViewById(R.id.l);
    }

    private void after_setup() {

        next.setOnClickListener(this);

        String w = "A:hello i am sara. B:... ... ... A: how are you? B: ... ... ... A:i am great.";

        String [] list_w = w.split(Pattern.quote("..."));

        int start = 0;
        int end = 0;
        String s_s = w.substring(0, 3);
        if(s_s.equals("...")){start = 1;}
        String s_e = w.substring(w.length()-3, w.length());
        if(s_e.equals("...")){end = 1;}

        int t_number = 0;
        int id_w = 0;
        if(list_w[0].equals("")){
            id_w = 1;
            t_number = list_w.length-1;
        }else{
            t_number = list_w.length;
        }

        int e_number = 0;
        if(t_number > 1){
            e_number = (t_number-1)+(start)+(end);
        }else{
            e_number = (start)+(end);
        }

        int total = t_number + e_number;

        t = new TextView[t_number];
        int id_t = 0;
        e = new EditText[e_number];
        int id_e = 0;

        String now = "txt";

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int added = 0;

        for(int i=0 ; i<total ; i++){

            final int finalId_e = id_e;

            // start
            if(i == 0 ){
                if(start == 1){
                    e[id_e] = new EditText(this);
                    e[id_e].setLayoutParams(params);
                    e[id_e].setEms(4);
                    e[id_e].setTextSize(18);
                    added = added + 5;
                    l.addView(e[id_e]);
                    id_e++;
                }

                if(start == 0){
                    t[id_t] = new TextView(this);
                    t[id_t].setLayoutParams(params);
                    t[id_t].setText(list_w[id_w]);
                    t[id_t].setTextSize(18);
                    added = added + list_w[id_w].length();
                    l.addView(t[id_t]);
                    now = "edt";
                    id_w++;
                    id_t++;
                }
            }

            if(i!=0 && i!=total-1){
                // textview
                switch (now) {
                    case "txt":
                        t[id_t] = new TextView(this);
                        t[id_t].setLayoutParams(params);
                        t[id_t].setText(list_w[id_w]);
                        t[id_t].setTextSize(18);
                        added = added + list_w[id_w].length();
                        l.addView(t[id_t]);
                        now = "edt";
                        id_w++;
                        id_t++;
                        break;

                    case "edt":
                        e[id_e] = new EditText(this);
                        e[id_e].setLayoutParams(params);
                        e[id_e].setEms(4);
                        e[id_e].setTextSize(18);
                        added = added + 5;
                        l.addView(e[id_e]);
                        now = "txt";
                        id_e++;
                        break;
                }
            }

            // end
            if(i == total-1){
                if(end == 1){
                    e[id_e] = new EditText(this);
                    e[id_e].setLayoutParams(params);
                    e[id_e].setEms(4);
                    e[id_e].setTextSize(18);
                    added = added + 5;
                    l.addView(e[id_e]);
                    id_e++;
                }

                if(end == 0){
                    t[id_t] = new TextView(this);
                    t[id_t].setLayoutParams(params);
                    t[id_t].setText(list_w[id_w]);
                    t[id_t].setTextSize(18);
                    added = added + list_w[id_w].length();
                    l.addView(t[id_t]);
                    now = "edt";
                    id_w++;
                    id_t++;
                }

            }
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.next) {

            switch (next.getText().toString()) {

                case "check":
                    if( e[0].getText().toString() != " ") {

                        // String a = nice_string( you_say );
                        //String b = nice_string( title1 );
                        //a.equals(b)
                        if (true) {
                            // Toast.makeText(getApplicationContext(), "CorrectTEST", Toast.LENGTH_LONG).show();
                        } else {
                            //Toast.makeText(getApplicationContext(), "False", Toast.LENGTH_LONG).show();
                        }

                        next.setTextColor(Color.WHITE);
                        next.setBackgroundResource(R.drawable.btn_green);
                        next.setText("countinue");
                    }
                    break;

                case "countinue":
                    if( e[0].getText().toString() != " " ) {

                        if(activitynumber == max){
                            now_less = mPresenter.now_IdLesson();

                            // post

                            // update
                            List<Integer> id_less = mPresenter.lesson(idfunction);
                            List<Integer> id_func =  mPresenter.function();

                            for(int i=0 ; i< id_less.size() ; i++){
                                if(id_less.get(i) == idlesson){
                                    if(i == id_less.size()-1){
                                        End.gofunction = 1;
                                        for(int j=0 ; j< id_func.size() ; j++) {
                                            if (id_func.get(j) == idfunction) {
                                                if (now_less == idlesson){
                                                    int next_func = j+1;
                                                    mPresenter.update_idfunction(id_func.get(next_func));
                                                    mPresenter.update_idlesson(0);
                                                }
                                                break;
                                            }
                                        }
                                    }
                                    else{
                                        End.gofunction = 0;
                                        if (now_less == idlesson){
                                            int next_less = i+1;
                                            mPresenter.update_idlesson(id_less.get(next_less));
                                        }
                                    }
                                    break;
                                }
                            }
                            A48.this.finish();
                            startActivity(new Intent(A48.this, End.class ));

                        } else {

                            TbActivity tb_new = mPresenter.getActivity(idlesson, ++activitynumber);
                            int id_at_new = tb_new.getId_ActivityType();

                            switch (id_at_new){

                                case 1: break;
                                case 2: break;

                                case 3:
                                    A3.idlesson = idlesson ;
                                    A3.idfunction = idfunction ;
                                    A3.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A3.class));
                                    break;

                                case 4:
                                    A4.idlesson = idlesson ;
                                    A4.idfunction = idfunction ;
                                    A4.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A4.class));
                                    break;

                                case 5:
                                    A5.idlesson = idlesson ;
                                    A5.idfunction = idfunction ;
                                    A5.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A5.class));
                                    break;

                                case 6:
                                    A6.idlesson = idlesson ;
                                    A6.idfunction = idfunction ;
                                    A6.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A6.class));
                                    break;

                                case 7:
                                    A7.idlesson = idlesson ;
                                    A7.idfunction = idfunction ;
                                    A7.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A7.class));
                                    break;

                                case 8:
                                    A8.idlesson = idlesson ;
                                    A8.idfunction = idfunction ;
                                    A8.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A8.class));
                                    break;

                                case 9:
                                    A9.idlesson = idlesson ;
                                    A9.idfunction = idfunction ;
                                    A9.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A9.class));
                                    break;

                                case 10: break;
                                case 11: break;
                                case 12: break;
                                case 13: break;
                                case 14: break;

                                case 15:
                                    //A15.idlesson = idlesson ;
                                    // A.idfunction = idfunction ;
                                    //A15.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A15.class));
                                    break;

                                case 16: break;
                                case 17: break;

                                case 18:
                                    A18.idlesson = idlesson ;
                                    A18.idfunction = idfunction ;
                                    A18.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A18.class));
                                    break;

                                case 19:
                                    A19.idlesson = idlesson ;
                                    A19.idfunction = idfunction ;
                                    A19.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A19.class));
                                    break;

                                case 20:
                                    //A20.idlesson = idlesson ;
                                    // A.idfunction = idfunction ;
                                    //A20.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A20.class));
                                    break;

                                case 21: break;

                                case 22:
                                    A22.idlesson = idlesson ;
                                    A22.idfunction = idfunction ;
                                    A22.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A22.class));
                                    break;

                                case 23: break;

                                case 24:
                                    A24.idlesson = idlesson ;
                                    A24.idfunction = idfunction ;
                                    A24.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A24.class));
                                    break;

                                case 25:
                                    //A25.idlesson = idlesson ;
                                    // A.idfunction = idfunction ;
                                    //A25.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A25.class));
                                    break;

                                case 26:
                                    A26.idlesson = idlesson ;
                                    A26.idfunction = idfunction ;
                                    A26.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A26.class));
                                    break;

                                case 27:
                                    A27.idlesson = idlesson ;
                                    A27.idfunction = idfunction ;
                                    A27.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A27.class));
                                    break;

                                case 28:
                                    A28.idlesson = idlesson ;
                                    A28.idfunction = idfunction ;
                                    A28.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A28.class));
                                    break;

                                case 29:
                                    A29.idlesson = idlesson ;
                                    A29.idfunction = idfunction ;
                                    A29.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A29.class));
                                    break;

                                case 30:
                                    A30.idlesson = idlesson ;
                                    A30.idfunction = idfunction ;
                                    A30.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A30.class));
                                    break;

                                case 31:
                                    //A31.idlesson = idlesson ;
                                    // A.idfunction = idfunction ;
                                    //A31.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A31.class));
                                    break;

                                case 32:
                                    //A32.idlesson = idlesson ;
                                    //A.idfunction = idfunction ;
                                    //A32.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A32.class));
                                    break;

                                case 33:
                                    A33.idlesson = idlesson ;
                                    A33.idfunction = idfunction ;
                                    A33.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A33.class));
                                    break;

                                case 34:
                                    A34.idlesson = idlesson ;
                                    A34.idfunction = idfunction ;
                                    A34.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A34.class));
                                    break;

                                case 35:
                                    A35.idlesson = idlesson ;
                                    A35.idfunction = idfunction ;
                                    A35.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A35.class));
                                    break;

                                case 36: break;

                                case 37:
                                    //A37.idlesson = idlesson ;
                                    //A.idfunction = idfunction ;
                                    //A37.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A37.class));
                                    break;

                                case 38:
                                    //A38.idlesson = idlesson ;
                                    // A.idfunction = idfunction ;
                                    //A38.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A38.class));
                                    break;

                                case 39:
                                    A39.idlesson = idlesson ;
                                    A39.idfunction = idfunction ;
                                    A39.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A39.class));

                                    break;

                                case 40:
                                    //A40.idlesson = idlesson ;
                                    // A.idfunction = idfunction ;
                                    //A40.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A40.class));
                                    break;

                                case 41:
                                    //A41.idlesson = idlesson ;
                                    // A.idfunction = idfunction ;
                                    //A41.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A41.class));
                                    break;

                                case 42:
                                    //A42.idlesson = idlesson ;
                                    // A.idfunction = idfunction ;
                                    //A42.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A42.class));
                                    break;

                                case 43:
                                    //A43.idlesson = idlesson ;
                                    // A.idfunction = idfunction ;
                                    //A43.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A43.class));
                                    break;

                                case 44:
                                    //A44.idlesson = idlesson ;
                                    // A.idfunction = idfunction ;
                                    //A44.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A44.class));
                                    break;

                                case 46:
                                    A46.idlesson = idlesson ;
                                    A46.idfunction = idfunction ;
                                    A46.activitynumber = activitynumber;
                                    A48.this.finish();
                                    startActivity(new Intent(A48.this,  A46.class));
                                    break;
                            }
                        }
                    }
                    break;
            }
        }
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
        mStateMaintainer.put(Main_Presenter.class.getSimpleName(), mPresenter);
    }

    private void reinitialize() {
        Log.d(TAG, "reinitialize");
        mPresenter = mStateMaintainer.get(Main_Presenter.class.getSimpleName());
        mPresenter.setView(this);
        if ( mPresenter == null )
            setupComponent();
    }

    private void setupComponent(){
        Log.d(TAG, "setupComponent");
        SampleApp.get(this)
                .getAppComponent()
                .getA48Component(new A48_Module(this))
                .inject(this);
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }
}
