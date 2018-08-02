package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.Main_Module;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Main_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.view.BaseActivity;
import java.util.List;
import javax.inject.Inject;

public class A15 extends BaseActivity
        implements MVP_Main.RequiredViewOps,
        OnClickListener{

    private static final String TAG = A15.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A15.class.getName());

    @Inject
    public MVP_Main.ProvidedPresenterOps mPresenter;



    String answer;
    CheckBox a,b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a15);

        setupViews();
        setupMVP();

        max = mPresenter.max_Activitynumber(idlesson);

        tbActivity = mPresenter.getActivity(idlesson, activitynumber);
        int idactivity = tbActivity.get_id();
        tbActivityDetailList = mPresenter.getListActivityDetail(idactivity);
        title1 = tbActivityDetailList.get(0).getTitle1().toString();
        title2 = tbActivityDetailList.get(1).getTitle1().toString();
        if(tbActivityDetailList.get(0).getIsAnswer().equals("true")){
            answer = title1;
        }
        if(tbActivityDetailList.get(1).getIsAnswer().equals("true")){
            answer = title2;
        }

        after_setup();
    }

    private void setupViews() {

        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        t1 = (TextView)findViewById(R.id.title1);
        t2 = (TextView)findViewById(R.id.title2);
        a = (CheckBox)findViewById(R.id.a);
        b = (CheckBox)findViewById(R.id.b);
        next = (Button) findViewById(R.id.next);
        p = (ProgressBar)findViewById(R.id.p);
        p.setMax(100);
    }

    private void after_setup(){

        all = mPresenter.countActivity(idlesson);

        // set all activity false in activitynumber = 1
        if(activitynumber == 1 && Act_Status.equals("first")){
            mPresenter.false_activitys(idlesson);
        }

        // show passed activity
        List<Integer> p1 = mPresenter.activity_true(idlesson);
        int p2 = p1.size();
        if(p2 == 0){
            p.setProgress(0);
        }else{
            double d_number = (double) p2/all;
            int i_number = (int) (d_number*100);
            p.setProgress(i_number);
        }

        t1.setText(R.string.A3_EN);
        t1.setTextColor(getResources().getColor(R.color.my_black));

        int lang_id = mPresenter.getlanguage();
        switch (lang_id){
            // فارسی
            case 1:
                t2.setText(R.string.A3_FA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // کردی
            case 2:
                t2.setText(R.string.A3_KU);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // ترکی آذری
            case 3:
                t2.setText(R.string.A3_TA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // چینی
            case 4:
                t2.setText(R.string.A3_CH);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
        }

        txt1.setText(title1);
        txt2.setText(title2);

        next.setOnClickListener(this);
        a.setOnClickListener(this);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.a) {
            a.setChecked(true);

            next.setTextColor(Color.WHITE);
            next.setBackgroundResource(R.drawable.btn_green);

            if( b.isChecked() ){
                b.setChecked(false);
            }
        }

        if (v.getId() == R.id.b) {
            b.setChecked(true);

            next.setTextColor(Color.WHITE);
            next.setBackgroundResource(R.drawable.btn_green);

            if( a.isChecked() ){
                a.setChecked(false);
            }
        }

        if (v.getId() == R.id.next) {

            switch (next.getText().toString()) {

                case "check":

                    if( a.isChecked() || b.isChecked() ) {

                        boolean ans = false;
                        if(a.isChecked()){
                            if(txt1.getText().equals(answer)){
                                ans = true;
                            }
                        }
                        if(b.isChecked()){
                            if(txt2.getText().equals(answer)){
                                ans = true;
                            }
                        }

                        if (ans) {
                            Toast.makeText(getApplicationContext(), "CorrectTEST", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "False", Toast.LENGTH_LONG).show();
                        }

                        next.setTextColor(Color.WHITE);
                        next.setBackgroundResource(R.drawable.btn_green);
                        next.setText("countinue");
                    }
                    break;

                case "countinue":
                    break;
            }

            if(a.isChecked() || b.isChecked()) {

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
                    A15.this.finish();
                    startActivity(new Intent(A15.this, End.class ));

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
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A3.class));
                            break;

                        case 4:
                            A4.idlesson = idlesson ;
                            A4.idfunction = idfunction ;
                            A4.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A4.class));
                            break;

                        case 5:
                            A5.idlesson = idlesson ;
                            A5.idfunction = idfunction ;
                            A5.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A5.class));
                            break;

                        case 6:
                            A6.idlesson = idlesson ;
                            A6.idfunction = idfunction ;
                            A6.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A6.class));
                            break;

                        case 7:
                            A7.idlesson = idlesson ;
                            A7.idfunction = idfunction ;
                            A7.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A7.class));
                            break;

                        case 8:
                            A8.idlesson = idlesson ;
                            A8.idfunction = idfunction ;
                            A8.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A8.class));
                            break;

                        case 9:
                            A9.idlesson = idlesson ;
                            A9.idfunction = idfunction ;
                            A9.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A9.class));
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
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A15.class));
                            break;

                        case 16: break;
                        case 17: break;

                        case 18:
                            A18.idlesson = idlesson ;
                            A18.idfunction = idfunction ;
                            A18.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A18.class));
                            break;

                        case 19:
                            A19.idlesson = idlesson ;
                            A19.idfunction = idfunction ;
                            A19.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A19.class));
                            break;

                        case 20:
                            //A20.idlesson = idlesson ;
                            //  A.idfunction = idfunction ;
                            //A20.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A20.class));
                            break;

                        case 21: break;

                        case 22:
                            A22.idlesson = idlesson ;
                            A22.idfunction = idfunction ;
                            A22.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A22.class));
                            break;

                        case 23: break;

                        case 24:
                            A24.idlesson = idlesson ;
                            A24.idfunction = idfunction ;
                            A24.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A24.class));
                            break;

                        case 25:
                            //A25.idlesson = idlesson ;
                            //  A.idfunction = idfunction ;
                            //A25.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A25.class));
                            break;

                        case 26:
                            A26.idlesson = idlesson ;
                            A26.idfunction = idfunction ;
                            A26.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A26.class));
                            break;

                        case 27:
                            //A27.idlesson = idlesson ;
                            //  A.idfunction = idfunction ;
                            //A27.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A27.class));
                            break;

                        case 28:
                            A28.idlesson = idlesson ;
                            A28.idfunction = idfunction ;
                            A28.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A28.class));
                            break;

                        case 29:
                            A29.idlesson = idlesson ;
                            A29.idfunction = idfunction ;
                            A29.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A29.class));
                            break;

                        case 30:
                            A30.idlesson = idlesson ;
                            A30.idfunction = idfunction ;
                            A30.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A30.class));
                            break;

                        case 31:
                            //A31.idlesson = idlesson ;
                            //  A.idfunction = idfunction ;
                            //A31.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A31.class));
                            break;

                        case 32:
                            //A32.idlesson = idlesson ;
                            //  A.idfunction = idfunction ;
                            //A32.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A32.class));
                            break;

                        case 33:
                            A33.idlesson = idlesson ;
                            A33.idfunction = idfunction ;
                            A33.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A33.class));
                            break;

                        case 34:
                            A34.idlesson = idlesson ;
                            A34.idfunction = idfunction ;
                            A34.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A34.class));
                            break;

                        case 35:
                            A35.idlesson = idlesson ;
                            A35.idfunction = idfunction ;
                            A35.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A35.class));
                            break;

                        case 36: break;

                        case 37:
                            A37.idlesson = idlesson ;
                            A37.idfunction = idfunction ;
                            A37.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A37.class));
                            break;

                        case 38:
                            //A38.idlesson = idlesson ;
                            //  A.idfunction = idfunction ;
                            //A38.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A38.class));
                            break;

                        case 39:
                            A39.idlesson = idlesson ;
                            A39.idfunction = idfunction ;
                            A39.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A39.class));
                            break;

                        case 40:
                            //A40.idlesson = idlesson ;
                            //  A.idfunction = idfunction ;
                            //A40.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A40.class));
                            break;

                        case 41:
                            //A41.idlesson = idlesson ;
                            //  A.idfunction = idfunction ;
                            //A41.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A41.class));
                            break;

                        case 42:
                            //A42.idlesson = idlesson ;
                            //  A.idfunction = idfunction ;
                            //A42.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A42.class));
                            break;

                        case 43:
                            //A43.idlesson = idlesson ;
                            //  A.idfunction = idfunction ;
                            //A43.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A43.class));
                            break;

                        case 44:
                            //A44.idlesson = idlesson ;
                            //  A.idfunction = idfunction ;
                            //A44.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A44.class));
                            break;

                        case 46:
                            A46.idlesson = idlesson ;
                            A46.idfunction = idfunction ;
                            A46.activitynumber = activitynumber;
                            A15.this.finish();
                            startActivity(new Intent(A15.this,  A46.class));
                            break;
                    }
                }
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
                .getA15Component(new Main_Module(this))
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