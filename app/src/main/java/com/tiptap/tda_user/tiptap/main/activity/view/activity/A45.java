package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.Main_Module;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Main_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.view.lesson.Lesson;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

public class A45 extends BaseActivity
        implements MVP_Main.RequiredViewOps,
        OnClickListener {

    private static final String TAG = A45.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A45.class.getName());

    @Inject
    public MVP_Main.ProvidedPresenterOps mPresenter;

    String answer;
    String title1detailactivity, title2detailactivity, title1activity;
    CheckBox a,b;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a45);

        setupViews();
        setupMVP();

        max = mPresenter.max_Activitynumber(idlesson);
        // first
        if(Act_Status.equals("first")){
            tbActivity = mPresenter.getActivity(idlesson, activitynumber);
        }
        // second
        if(Act_Status.equals("second")) {
            tbActivity = mPresenter.getActivity2(idactivity);
        }

        // get tbactivity
        tbActivity = mPresenter.getActivity(idlesson, activitynumber);
        idactivity = tbActivity.get_id();
        title1activity = tbActivity.getTitle1();
        //path1 = tbActivity.getPath1();

        // get tbactvity detail
        tbActivityDetailList = mPresenter.getListActivityDetail(idactivity);
        title1detailactivity = tbActivityDetailList.get(0).getTitle1().toString();
        title2detailactivity = tbActivityDetailList.get(1).getTitle1().toString();

        // find answer
        if(tbActivityDetailList.get(0).getIsAnswer().equals("1")){
            answer = tbActivityDetailList.get(0).getTitle1().toString();
        }

        //////////////
        else if (tbActivityDetailList.get(1).getIsAnswer().equals("1")) {
            // answer = title2;
            answer=tbActivityDetailList.get(1).getTitle1().toString();
        }
//answer=title2detailactivity;
//String aa=answer;

        after_setup();
    }
      //set layout
    private void setupViews() {

        img = (NetworkImageView) findViewById(R.id.text);
        t1 = (TextView)findViewById(R.id.title1);
        t2 = (TextView)findViewById(R.id.title2);
        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        a = (CheckBox)findViewById(R.id.a);
        b = (CheckBox)findViewById(R.id.b);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        p = (ProgressBar)findViewById(R.id.p);
        next = (Button) findViewById(R.id.next);
        text=(TextView)findViewById(R.id.title);

    }

    private void after_setup(){

        if (title1activity==null){

        }else{

            text.setText(title1activity);
        }

        // set all activity false in activitynumber = 1
        if(activitynumber == 1 && Act_Status.equals("first")){
            mPresenter.false_activitys(idlesson);
        }

        // show passed activity
        List<Integer> p1 = mPresenter.activity_true(idlesson);
        int p2= p1.size();
        if(p2 == 0){
            p.setProgress(0);
        }else{
            double d_number = (double) p2/all;
            int i_number = (int) (d_number*100);
            p.setProgress(i_number);
        }

        next.setOnClickListener(this);

        t1.setText(R.string.A45_EN);
        t1.setTextColor(getResources().getColor(R.color.my_black));

        int lang_id = mPresenter.getlanguage();
        switch (lang_id){
            // فارسی
            case 1:
                t2.setText(R.string.A45_FA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // کردی
            case 2:
                t2.setText(R.string.A45_KU);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // ترکی آذری
            case 3:
                t2.setText(R.string.A45_TA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // چینی
            case 4:
                t2.setText(R.string.A45_CH);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
        }

        //get image

       path1 = tbActivity.getPath1();
        getImage(path1);



        // set text for checkbox

        txt1.setText(title1detailactivity);
        txt2.setText(title2detailactivity);
         //
        next.setOnClickListener(this);
        a.setOnClickListener(this);
        b.setOnClickListener(this);
    }

    @Override

     // check answer for checkbox and change text color for true answer
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


        //
        if (v.getId() == R.id.next) {

            switch (next.getText().toString()) {

                case "check":

                    if (a.isChecked() || b.isChecked()) {

                        boolean ans = false;
                        if (a.isChecked()) {
                            if (txt1.getText().equals(answer)) {
                                ans = true;
                            }
                        }
                        if (b.isChecked()) {
                            if (txt2.getText().equals(answer)) {
                                ans = true;
                            }
                        }
                        if (ans) {


                            // update - true
                            mPresenter.update_activity(idactivity);

                            // show passed activity
                            List<Integer> passed1 = mPresenter.activity_true(idlesson);
                            int passed2 = passed1.size();
                            if (passed2 == 0) {
                                p.setProgress(0);
                            } else {
                                double d_number = (double) passed2 / all;
                                int i_number = (int) (d_number * 100);
                                p.setProgress(i_number);
                            }

                            // Clickable_false
                            t1.setClickable(false);
                            t2.setClickable(false);
                            txt1.setClickable(false);
                            txt2.setClickable(false);
                            img.setClickable(false);
                            a.setClickable(false);
                            b.setClickable(false);
                            p.setClickable(false);

                            // Fragment_true
                            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder1);
                            Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                            linearLayout.setAnimation(slide_down);
                            linearLayout.setVisibility(View.VISIBLE);

                            Fragment_True f1 = new Fragment_True();
                            FragmentManager fragMan = getSupportFragmentManager();
                            FragmentTransaction fragTransaction = fragMan.beginTransaction();
                            fragTransaction.add(R.id.fragment1, f1);
                            fragTransaction.commit();


                        } else {

                            // Clickable_false
                            t1.setClickable(false);
                            t2.setClickable(false);
                            txt1.setClickable(false);
                            txt2.setClickable(false);
                            img.setClickable(false);
                            a.setClickable(false);
                            b.setClickable(false);
                            p.setClickable(false);

                            // Fragment_false
                            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder2);
                            Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                            linearLayout.setAnimation(slide_down);
                            linearLayout.setVisibility(View.VISIBLE);

                            Fragment_False f2 = new Fragment_False();
                            f2.t.setText(title1);
                            FragmentManager fragMan = getSupportFragmentManager();
                            FragmentTransaction fragTransaction = fragMan.beginTransaction();
                            fragTransaction.add(R.id.fragment2, f2);
                            fragTransaction.commit();


                        }

                        // change text color for button next when answer is true or false

                        next.setTextColor(Color.WHITE);
                        next.setBackgroundResource(R.drawable.btn_green);
                        next.setText("countinue");
                    }
                    break;

                case "countinue":


                    if (a.isChecked() || b.isChecked()) {

                        // first
                        if (Act_Status.equals("first")) {

                            // max - end of lesson
                            if (activitynumber == max) {

                                // list of false answer
                                List<Integer> id_act_false = mPresenter.activity_false(idlesson);
                                int number = id_act_false.size();

                                // number = 0 and update
                                if (number == 0) {

                                    // get now lesson
                                    now_less = mPresenter.now_IdLesson();

                                    // post

                                    // update
                                    List<Integer> id_less = mPresenter.lesson(idfunction);
                                    List<Integer> id_func = mPresenter.function();

                                    for (int i = 0; i < id_less.size(); i++) {
                                        if (id_less.get(i) == idlesson) {
                                            if (i == id_less.size() - 1) {
                                                End.gofunction = 1;
                                                for (int j = 0; j < id_func.size(); j++) {
                                                    if (id_func.get(j) == idfunction) {
                                                        if (now_less == idlesson) {
                                                            int next_func = j + 1;
                                                            mPresenter.update_idfunction(id_func.get(next_func));
                                                            mPresenter.update_idlesson(0);
                                                        }
                                                        break;
                                                    }
                                                }
                                            } else {
                                                End.gofunction = 0;
                                                if (now_less == idlesson) {
                                                    int next_less = i + 1;
                                                    mPresenter.update_idlesson(id_less.get(next_less));
                                                }
                                            }
                                            break;
                                        }
                                    }
                                    A45.this.finish();
                                    startActivity(new Intent(A45.this, End.class));
                                }

                                // number != 0 and go on to Next
                                else {
                                    int max_range = (id_act_false.size()) - 1;
                                    int min_range = 0;
                                    int rnd = new Random().nextInt(max_range - min_range + 1) + min_range;
                                    int id_act = id_act_false.get(rnd);
                                    TbActivity tb_new_f = mPresenter.getActivity2(id_act);
                                    int id_at_new_f = tb_new_f.getId_ActivityType();

                                    // second
                                    go_activity1(id_at_new_f, "second", id_act);
                                }

                            } else {

                                TbActivity tb_new = mPresenter.getActivity(idlesson, ++activitynumber);
                                int id_at_new = tb_new.getId_ActivityType();

                                // first
                                go_activity2(id_at_new, "first", activitynumber);

                            }
                        }

                        // second
                        if (Act_Status.equals("second")) {

                            // list of false answer
                            List<Integer> id_act_f = mPresenter.activity_false(idlesson);
                            int number = id_act_f.size();

                            // number = 0 and update
                            if (number == 0) {

                                // get now lesson
                                now_less = mPresenter.now_IdLesson();

                                // post

                                // update
                                List<Integer> id_less = mPresenter.lesson(idfunction);
                                List<Integer> id_func = mPresenter.function();

                                for (int i = 0; i < id_less.size(); i++) {
                                    if (id_less.get(i) == idlesson) {
                                        if (i == id_less.size() - 1) {
                                            End.gofunction = 1;
                                            for (int j = 0; j < id_func.size(); j++) {
                                                if (id_func.get(j) == idfunction) {
                                                    if (now_less == idlesson) {
                                                        int next_func = j + 1;
                                                        mPresenter.update_idfunction(id_func.get(next_func));
                                                        mPresenter.update_idlesson(0);
                                                    }
                                                    break;
                                                }
                                            }
                                        } else {
                                            End.gofunction = 0;
                                            if (now_less == idlesson) {
                                                int next_less = i + 1;
                                                mPresenter.update_idlesson(id_less.get(next_less));
                                            }
                                        }
                                        break;
                                    }
                                }
                                A45.this.finish();
                                startActivity(new Intent(A45.this, End.class));

                            }

                            // number != 0 and go on to Next
                            else {

                                // next is random
                                int max_range = (id_act_f.size()) - 1;
                                int min_range = 0;
                                int rnd = new Random().nextInt(max_range - min_range + 1) + min_range;
                                int id_act = id_act_f.get(rnd);

                                TbActivity tb_new_f = mPresenter.getActivity2(id_act);
                                int id_at_new_f = tb_new_f.getId_ActivityType();

                                // second
                                go_activity1(id_at_new_f, "second", id_act);
                            }
                        }

                    }

                    break;
            }
        }}


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
                .getA45Component(new Main_Module(this))
                .inject(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    public void back(){
        A45.this.finish();
        startActivity(new Intent(A45.this, Lesson.class));
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