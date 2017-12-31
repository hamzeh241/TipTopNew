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
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivityDetail;
import java.util.List;
import javax.inject.Inject;
import android.widget.LinearLayout.LayoutParams;

public class A8 extends AppCompatActivity
        implements MVP_A8.RequiredViewOps {

    private static final String TAG = A8.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A8.class.getName());

    @Inject
    public MVP_A8.ProvidedPresenterOps mPresenter;

    public static int idlesson;
    public static int activitynumber;
    TbActivity tbActivity;
    int max;
    List<TbActivityDetail> tbActivityDetailList;
    TextView t1[],t2[];
    Button next;
    String tohi = "-------";
    static int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a8);

        setupViews();
        setupMVP();

        tbActivity = mPresenter.getActivity(idlesson, activitynumber);
        max = mPresenter.max_Activitynumber(idlesson);
        int idactivity = tbActivity.get_id();
        tbActivityDetailList = mPresenter.getListActivityDetail(idactivity);
    }

    private void setupViews() {

        position = 0;
        String w1 = "my name is logo";
        final String w2 [] = w1.split(" ");
        String s1 = "logo name is my";
        String s2 [] = s1.split(" ");

        final LinearLayout t = (LinearLayout)findViewById(R.id.t);
        LinearLayout a = (LinearLayout)findViewById(R.id.a);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(30,10,30,10);

        t1 = new TextView[s2.length];
        t2 = new TextView[s2.length];

        for(int i = 0; i < s2.length ; i++){

            t1[i] = new TextView(this);
            t1[i].setLayoutParams(params);
            t1[i].setText(s2[i]);
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
            t1[i].setTextSize(22);
            a.addView(t2[i]);
            t2[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    unset_data(t2[finalI],finalI);
                }
            });
        }

        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (next.getText().toString()){
                    case "check":

                        if(position > 0) {

                            boolean answer = true;
                            for (int i = 0; i < t2.length; i++) {
                                if (t2[i].getText().toString().equals(w2[i])) {
                                    answer = true;
                                } else {
                                    answer = false;
                                }
                            }

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

                            if (activitynumber == max) {
                                A8.this.finish();
                                startActivity(new Intent(A8.this, End.class));

                            } else {

                                TbActivity tb_new = mPresenter.getActivity(idlesson, ++activitynumber);
                                int id_at_new = tb_new.getId_ActivityType();

                                switch (id_at_new){

                                    case 1: break;
                                    case 2: break;

                                    case 3:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A3.class));
                                        break;

                                    case 4:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A4.class));
                                        break;

                                    case 5:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A5.class));
                                        break;

                                    case 6:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A6.class));
                                        break;

                                    case 7:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A7.class));
                                        break;

                                    case 8:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A8.class));
                                        break;

                                    case 9:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A9.class));
                                        break;

                                    case 10: break;
                                    case 11: break;
                                    case 12: break;
                                    case 13: break;
                                    case 14: break;

                                    case 15:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A15.class));
                                        break;

                                    case 16: break;
                                    case 17: break;

                                    case 18:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A18.class));
                                        break;

                                    case 19:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A19.class));
                                        break;

                                    case 20:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A20.class));
                                        break;

                                    case 21: break;

                                    case 22:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A22.class));
                                        break;

                                    case 23: break;

                                    case 24:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A24.class));
                                        break;

                                    case 25:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A25.class));
                                        break;

                                    case 26:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A26.class));
                                        break;

                                    case 27:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A27.class));
                                        break;

                                    case 28:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A28.class));
                                        break;

                                    case 29:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A29.class));
                                        break;

                                    case 30:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A30.class));
                                        break;

                                    case 31:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A31.class));
                                        break;

                                    case 32:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A32.class));
                                        break;

                                    case 33:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A33.class));
                                        break;

                                    case 34:
                                        A34.idlesson = idlesson ;
                                        A34.activitynumber = activitynumber;
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A34.class));
                                        break;

                                    case 35:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A35.class));
                                        break;

                                    case 36: break;

                                    case 37:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A37.class));
                                        break;

                                    case 38:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A38.class));
                                        break;

                                    case 39:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A39.class));
                                        break;

                                    case 40:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A40.class));
                                        break;

                                    case 41:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A41.class));
                                        break;

                                    case 42:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A42.class));
                                        break;

                                    case 43:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A43.class));
                                        break;

                                    case 44:
                                        A8.this.finish();
                                        startActivity(new Intent(A8.this,  A44.class));
                                        break;
                                }
                            }
                        }

                        break;
                }
            }
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
}