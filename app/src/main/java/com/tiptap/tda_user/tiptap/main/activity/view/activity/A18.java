package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.Main_Module;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Main_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.view.BaseActivity;
import com.tiptap.tda_user.tiptap.main.activity.view.lesson.Lesson;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;

public class A18 extends BaseActivity
                 implements MVP_Main.RequiredViewOps, View.OnClickListener{

    private static final String TAG = A18.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A18.class.getName());

    @Inject
    public MVP_Main.ProvidedPresenterOps mPresenter;

    LinearLayout l1,l2;
    Button b[];
    String true_ans, my_ans="";
    int count=0;
    int back_pressed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a18);

        setupViews();
        setupMVP();

        // hide keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        max = mPresenter.max_Activitynumber(idlesson);

        // first
        if(Act_Status.equals("first")){
            tbActivity = mPresenter.getActivity(idlesson, activitynumber);
        }
        // second
        if(Act_Status.equals("second")) {
            tbActivity = mPresenter.getActivity2(idactivity);
        }

        idactivity = tbActivity.get_id();

        tbActivityDetailList = mPresenter.getListActivityDetail(idactivity);
        count = tbActivityDetailList.size();

        after_setup();
    }

    private void setupViews() {

        t1 = (TextView)findViewById(R.id.title1);
        t2 = (TextView)findViewById(R.id.title2);

        next = (Button) findViewById(R.id.next);
        p = (ProgressBar)findViewById(R.id.p);
        p.setMax(100);

        l1 = (LinearLayout)findViewById(R.id.l1);
        l2 = (LinearLayout)findViewById(R.id.l2);

        mpt = MediaPlayer.create (this, R.raw.true_sound);
        mpf =  MediaPlayer.create (this, R.raw.false_sound);
    }

    private void after_setup() {

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

        // set title of activity
        t1.setText(R.string.A18_EN);
        t1.setTextColor(getResources().getColor(R.color.my_black));

        int lang_id = mPresenter.getlanguage();
        switch (lang_id){
            // فارسی
            case 1:
                t2.setText(R.string.A18_FA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // کردی
            case 2:
                t2.setText(R.string.A18_KU);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // ترکی آذری
            case 3:
                t2.setText(R.string.A18_TA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // چینی
            case 4:
                t2.setText(R.string.A18_CH);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
        }

        next.setOnClickListener(this);

        // A - title1 - sorate soal
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        String w = tbActivity.getTitle1();
        TextView t = new TextView(this);
        t.setLayoutParams(params2);
        t.setText(w);
        t.setTextSize(20);
        t.setTextColor(Color.BLACK);
        l1.addView(t);

        // AD - title1 - gozineha sahih
        for(int i=0 ; i<count ; i++) {
            String temp = tbActivityDetailList.get(i).getTitle1();
            String r = tbActivityDetailList.get(i).getIsAnswer(); //============= 1 ???
            if(r.equals("true")){
                true_ans = temp;
            }
        }

        // AD - title1 - gozineha
        int c = 0;
        b = new Button[count];
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(300, LinearLayout.LayoutParams.WRAP_CONTENT);
        params1.setMargins(0,2,0,8);
        for(int i=0 ; i<count ; i++) {
            final String temp = tbActivityDetailList.get(i).getTitle1();
            b[c] = new Button(this);
            b[c].setId(c);
            b[c].setLayoutParams(params1);
            b[c].setText(temp);
            b[c].setTextSize(14);
            b[c].setBackgroundResource(R.drawable.border_btn_normal);
            final int finalC = c;
            b[c].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    next.setTextColor(Color.WHITE);
                    next.setBackgroundResource(R.drawable.btn_green);

                    my_ans = temp;

                    for(int x=0 ; x<count ; x++){
                        if(finalC == x){
                            b[finalC].setBackgroundResource(R.drawable.border_btn_select);
                        }else{
                            b[x].setBackgroundResource(R.drawable.border_btn_normal);
                        }
                    }
                }
            });
            l2.addView(b[c]);
            c++;
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.next) {

            switch (next.getText().toString()) {

                case "check":

                    if( my_ans.equals("") || my_ans.equals("null")) {
                        Toast.makeText(getApplicationContext(), "یکی از گزینه ها را انتخاب کنید", Toast.LENGTH_SHORT).show();
                    }else{
                        // true answer
                        if(my_ans.equals(true_ans)){
                            // update - true
                            mPresenter.update_activity(idactivity);

                            // show passed activity
                            List<Integer> passed1 = mPresenter.activity_true(idlesson);
                            int passed2 = passed1.size();
                            if(passed2 == 0){
                                p.setProgress(0);
                            }else{
                                double d_number = (double) passed2/all;
                                int i_number = (int) (d_number*100);
                                p.setProgress(i_number);
                            }

                            // Clickable_false
                            t1.setClickable(false);
                            t2.setClickable(false);
                            p.setClickable(false);

                            // Fragment_true
                            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder1);
                            Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                            linearLayout.setAnimation(slide_down);
                            linearLayout.setVisibility(View.VISIBLE);

                            Fragment_True f1 = new Fragment_True();
                            f1.txt_true.setText(true_ans);
                            FragmentManager fragMan = getSupportFragmentManager();
                            FragmentTransaction fragTransaction = fragMan.beginTransaction();
                            fragTransaction.add(R.id.fragment1, f1);
                            fragTransaction.commit();

                            // play sound
                            mpt.start();

                        }else {
                            // false answer
                            // Clickable_false
                            t1.setClickable(false);
                            t2.setClickable(false);
                            p.setClickable(false);

                            // Fragment_false
                            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder2);
                            Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                            linearLayout.setAnimation(slide_down);
                            linearLayout.setVisibility(View.VISIBLE);

                            Fragment_False f2 = new Fragment_False();
                            f2.txt_false.setText(true_ans);
                            FragmentManager fragMan = getSupportFragmentManager();
                            FragmentTransaction fragTransaction = fragMan.beginTransaction();
                            fragTransaction.add(R.id.fragment2, f2);
                            fragTransaction.commit();

                            // play sound
                            mpf.start();

                        }

                        next.setTextColor(Color.WHITE);
                        next.setBackgroundResource(R.drawable.btn_green);
                        next.setText("countinue");
                    }
                    break;

                case "countinue":

                    // first
                    if(Act_Status.equals("first")){

                        // max - end of lesson
                        if(activitynumber == max) {

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

                                A18.this.finish();
                                startActivity(new Intent(A18.this, End.class));
                            }

                            // number != 0 and go on to Next
                            else {

                                // next is random
                                int max_range = (id_act_false.size())-1;
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
                    if(Act_Status.equals("second")){

                        // list of false answer
                        List<Integer> id_act_f = mPresenter.activity_false(idlesson);
                        int number = id_act_f.size();

                        // number = 0 and update
                        if(number == 0){

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
                            A18.this.finish();
                            startActivity(new Intent(A18.this, End.class));
                        }

                        // number != 0 and go on to Next
                        else{

                            // next is random
                            int max_range = (id_act_f.size())-1;
                            int min_range = 0;
                            int rnd = new Random().nextInt(max_range - min_range + 1) + min_range;
                            int id_act = id_act_f.get(rnd);

                            TbActivity tb_new_f = mPresenter.getActivity2(id_act);
                            int id_at_new_f = tb_new_f.getId_ActivityType();

                            // second
                            go_activity1(id_at_new_f, "second", id_act);
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
                .getA18Component(new Main_Module(this))
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

    @Override
    public void onBackPressed() {
        back_pressed++;
        back();
    }

    public void back(){
        if(back_pressed == 1){
            Toast.makeText(getApplicationContext(), "برای خروج دوباره برگشت را بفشارید", Toast.LENGTH_LONG).show();
        }else{
            A18.this.finish();
            startActivity(new Intent(A18.this, Lesson.class));
        }
    }
}