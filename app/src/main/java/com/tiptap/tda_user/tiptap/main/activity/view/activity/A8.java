package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.A8_Module;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A8;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A8_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import android.view.View.OnClickListener;
import java.util.List;
import javax.inject.Inject;
import android.widget.LinearLayout.LayoutParams;

public class A8 extends AppCompatActivity
                implements MVP_A8.RequiredViewOps, OnClickListener {

    private static final String TAG = A8.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A8.class.getName());

    @Inject
    public MVP_A8.ProvidedPresenterOps mPresenter;

    public static int idlesson;
    public static int idfunction;
    public static int activitynumber;
    TbActivity tbActivity;
    String title1,title2;
    int max,now_less;
    TextView t1[],t2[];
    String w [];
    Button next;
    String tohi = "-------";
    static int position = 0;
    LinearLayout t, a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a8);

        setupViews();
        setupMVP();

        max = mPresenter.max_Activitynumber(idlesson);

        tbActivity = mPresenter.getActivity(idlesson, activitynumber);
        title1 = tbActivity.getTitle1();
        title1 = title1.trim();
        title2 = tbActivity.getTitle2();
        title2 = title2.trim();

        after_setup();
    }

    private void setupViews() {

        position = 0;
        t = (LinearLayout) findViewById(R.id.t);
        a = (LinearLayout) findViewById(R.id.a);
        next = (Button) findViewById(R.id.next);
    }

    private void after_setup(){

        next.setOnClickListener(this);

        w = title1.split(" ");
        String s [] = title2.split(" ");
        /*int max_range = s2.length-1;
        int min_range = 0;
        for(int i=0 ; i<s2.length ;  i++) {
            int rnd = new Random().nextInt(max_range - min_range + 1) + min_range;
            String temp = s2[rnd];
            s2[rnd] = s2[i];
            s2[i] = temp;
        }*/

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(10,10,10,10);

        t1 = new TextView[s.length];
        t2 = new TextView[s.length];

        for(int i = 0; i < s.length ; i++){

            t1[i] = new TextView(this);
            t1[i].setLayoutParams(params);
            t1[i].setText(s[i]);
            t1[i].setTextSize(22);
            t1[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
            t.addView(t1[i]);
            final int finalI = i;
            t1[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    set_data(t1[finalI]);
                }
            });

            t2[i] = new TextView(this);
            t2[i].setLayoutParams(params);
            t2[i].setText(tohi);
            t2[i].setTextSize(22);
            a.addView(t2[i]);
            t2[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    unset_data(t2[finalI],finalI);
                }
            });
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.next) {

            switch (next.getText().toString()){
                case "check":

                    if(position > 0) {

                        boolean answer = check();

                        if (answer == true) {
                            Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
                        } else if (answer == false) {
                            Toast.makeText(getApplicationContext(), "False", Toast.LENGTH_LONG).show();
                        }

                        next.setTextColor(Color.WHITE);
                        next.setBackgroundResource(R.drawable.btn);
                        next.setText("countinue");
                    }

                    break;

                case "countinue":

                    if(position > 0) {

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
                            A8.this.finish();
                            startActivity(new Intent(A8.this, End.class ));

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
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A3.class));
                                    break;

                                case 4:
                                    A4.idlesson = idlesson ;
                                    A4.idfunction = idfunction ;
                                    A4.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A4.class));
                                    break;

                                case 5:
                                    A5.idlesson = idlesson ;
                                    A5.idfunction = idfunction ;
                                    A5.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A5.class));
                                    break;

                                case 6:
                                    A6.idlesson = idlesson ;
                                    A6.idfunction = idfunction ;
                                    A6.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A6.class));
                                    break;

                                case 7:
                                    A7.idlesson = idlesson ;
                                    A7.idfunction = idfunction ;
                                    A7.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A7.class));
                                    break;

                                case 8:
                                    A8.idlesson = idlesson ;
                                    A8.idfunction = idfunction ;
                                    A8.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A8.class));
                                    break;

                                case 9:
                                    A9.idlesson = idlesson ;
                                    A9.idfunction = idfunction ;
                                    A9.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A9.class));
                                    break;

                                case 10: break;
                                case 11: break;
                                case 12: break;
                                case 13: break;
                                case 14: break;

                                case 15:
                                    //A15.idlesson = idlesson ;
                                    //  A.idfunction = idfunction ;
                                    //A15.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A15.class));
                                    break;

                                case 16: break;
                                case 17: break;

                                case 18:
                                    A18.idlesson = idlesson ;
                                    A18.idfunction = idfunction ;
                                    A18.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A18.class));
                                    break;

                                case 19:
                                    A19.idlesson = idlesson ;
                                    A19.idfunction = idfunction ;
                                    A19.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A19.class));
                                    break;

                                case 20:
                                    //A20.idlesson = idlesson ;
                                    //  A.idfunction = idfunction ;
                                    //A20.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A20.class));
                                    break;

                                case 21: break;

                                case 22:
                                    A22.idlesson = idlesson ;
                                    A22.idfunction = idfunction ;
                                    A22.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A22.class));
                                    break;

                                case 23: break;

                                case 24:
                                    A24.idlesson = idlesson ;
                                    A24.idfunction = idfunction ;
                                    A24.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A24.class));
                                    break;

                                case 25:
                                    //A25.idlesson = idlesson ;
                                    //  A.idfunction = idfunction ;
                                    //A25.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A25.class));
                                    break;

                                case 26:
                                    A26.idlesson = idlesson ;
                                    A26.idfunction = idfunction ;
                                    A26.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A26.class));
                                    break;

                                case 27:
                                    //A27.idlesson = idlesson ;
                                    //  A.idfunction = idfunction ;
                                    //A27.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A27.class));
                                    break;

                                case 28:
                                    A28.idlesson = idlesson ;
                                    A28.idfunction = idfunction ;
                                    A28.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A28.class));
                                    break;

                                case 29:
                                    A29.idlesson = idlesson ;
                                    A29.idfunction = idfunction ;
                                    A29.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A29.class));
                                    break;

                                case 30:
                                    A30.idlesson = idlesson ;
                                    A30.idfunction = idfunction ;
                                    A30.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A30.class));
                                    break;

                                case 31:
                                    //A31.idlesson = idlesson ;
                                    //  A.idfunction = idfunction ;
                                    //A31.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A31.class));
                                    break;

                                case 32:
                                    A32.idlesson = idlesson ;
                                    A32.idfunction = idfunction ;
                                    A32.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A32.class));
                                    break;

                                case 33:
                                    A33.idlesson = idlesson ;
                                    A33.idfunction = idfunction ;
                                    A33.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A33.class));
                                    break;

                                case 34:
                                    A34.idlesson = idlesson ;
                                    A34.idfunction = idfunction ;
                                    A34.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A34.class));
                                    break;

                                case 35:
                                    A35.idlesson = idlesson ;
                                    A35.idfunction = idfunction ;
                                    A35.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A35.class));
                                    break;

                                case 36: break;

                                case 37:
                                    //A37.idlesson = idlesson ;
                                    //  A.idfunction = idfunction ;
                                    //A37.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A37.class));
                                    break;

                                case 38:
                                    //A38.idlesson = idlesson ;
                                    //  A.idfunction = idfunction ;
                                    //A38.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A38.class));
                                    break;

                                case 39:
                                    A39.idlesson = idlesson ;
                                    A39.idfunction = idfunction ;
                                    A39.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A39.class));
                                    break;

                                case 40:
                                    //A40.idlesson = idlesson ;
                                    //  A.idfunction = idfunction ;
                                    //A40.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A40.class));
                                    break;

                                case 41:
                                    //A41.idlesson = idlesson ;
                                    //  A.idfunction = idfunction ;
                                    //A41.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A41.class));
                                    break;

                                case 42:
                                    //A42.idlesson = idlesson ;
                                    //  A.idfunction = idfunction ;
                                    //A42.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A42.class));
                                    break;

                                case 43:
                                    //A43.idlesson = idlesson ;
                                    //  A.idfunction = idfunction ;
                                    //A43.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A43.class));
                                    break;

                                case 44:
                                    //A44.idlesson = idlesson ;
                                    //  A.idfunction = idfunction ;
                                    //A44.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A44.class));
                                    break;

                                case 46:
                                    A46.idlesson = idlesson ;
                                    A46.idfunction = idfunction ;
                                    A46.activitynumber = activitynumber;
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this,  A46.class));
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
        mStateMaintainer.put(A8_Presenter.class.getSimpleName(), mPresenter);
    }

    private void reinitialize() {
        Log.d(TAG, "reinitialize");
        mPresenter = mStateMaintainer.get(A8_Presenter.class.getSimpleName());
        mPresenter.setView(this);
        if ( mPresenter == null )
            setupComponent();
    }

    private void setupComponent(){
        Log.d(TAG, "setupComponent");
        SampleApp.get(this)
                .getAppComponent()
                .getA8Component(new A8_Module(this))
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

    public void set_data(TextView t){
        for(int j = 0; j < t2.length ; j++){
            if(position == j){
                // set
                t2[j].setText(t.getText());
                t2[j].setTextSize(22);
                // disable
                t.setTextColor(getResources().getColor(R.color.gray));
                t.setBackgroundColor(getResources().getColor(R.color.gray));
                t.setClickable(false);
            }
        }
        // position
        position++;
        if(position > 0) {
            next.setTextColor(Color.WHITE);
            next.setBackgroundResource(R.drawable.btn);
        }
        if(position == 0){
            next.setTextColor(getResources().getColor(R.color.gray));
            next.setBackgroundResource(R.drawable.btn_def);
        }
    }

    public void unset_data(TextView a, int ia){
        for(int j = 0 ; j < t1.length ; j++){
            if(a.getText().toString().equals( t1[j].getText().toString() )){
                a.setText(tohi);
                // enable
                t1[j].setTextColor(getResources().getColor(R.color.colorPrimary));
                t1[j].setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
                t1[j].setClickable(true);
            }
        }

        // fix - order
        sort(ia);

        // position
        position--;
        if(position > 0) {
            next.setTextColor(Color.WHITE);
            next.setBackgroundResource(R.drawable.btn);
        }
        if(position == 0){
            next.setTextColor(getResources().getColor(R.color.gray));
            next.setBackgroundResource(R.drawable.btn_def);
        }
    }

    public void sort(int x) {
        if (x != (t2.length-1)) {
            if (t2[x + 1].getText().toString().equals(tohi)) {

            } else {
                t2[x].setText(t2[x + 1].getText().toString());
                t2[x + 1].setText(tohi);
                sort(x + 1);
            }
        }
    }

    public boolean check(){
        boolean answer = true;
        for (int i = 0; i < t2.length; i++) {
            String a = nice_string( t2[i].getText().toString() );
            String b = nice_string( w[i] );
            if (a.equals(b)) {
               // answer = true;
            } else {
                answer = false;
            }
        }
        return answer;
    }


    public String nice_string (String a){
        // space in first or end
        String b = a.trim();
        // other space
        b = b.replace(" ", "");
        // other
        b = b.replace(".", "");
        b = b.replace("!", "");
        b = b.replace("?", "");
        b = b.replace("؟", "");
        b = b.replace(",", "");
        b = b.replace("’", "");
        b = b.replace("\n", "");
        // lowerCase
        b = b.toLowerCase();
        return b;
    }
}