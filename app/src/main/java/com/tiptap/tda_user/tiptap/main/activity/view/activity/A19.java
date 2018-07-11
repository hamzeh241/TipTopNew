package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.A19_Module;
import com.tiptap.tda_user.tiptap.di.module.Main_Module;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Main_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.view.lesson.Lesson;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;
import android.view.View.OnClickListener;

public class A19 extends BaseActivity
                 implements MVP_Main.RequiredViewOps,
                 OnClickListener{

    private static final String TAG = A19.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A19.class.getName());

    @Inject
    public MVP_Main.ProvidedPresenterOps mPresenter;

    int count;
    String w1[],w2[];
    String s1[][],s2[][];
    TextView t1[],t2[],ti1,ti2;
    static String aval_type = null;
    static int aval_int;

    static String aval_original;
    int correct=0;

    LinearLayout l [][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a19);

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

        idactivity = tbActivity.get_id();
        tbActivityDetailList = mPresenter.getListActivityDetail(idactivity);
        count = mPresenter.count_ActivityDetail(idactivity);

        after_setup();
    }

    private void setupViews() {

        correct=0;

        ti1 = (TextView)findViewById(R.id.title1);
        ti2 = (TextView)findViewById(R.id.title2);

        p = (ProgressBar)findViewById(R.id.p);
        p.setMax(100);
        
        LinearLayout l1_l = (LinearLayout)findViewById(R.id.l1_l);
        LinearLayout l1_r = (LinearLayout)findViewById(R.id.l1_r);
        LinearLayout l2_l = (LinearLayout)findViewById(R.id.l2_l);
        LinearLayout l2_r = (LinearLayout)findViewById(R.id.l2_r);
        LinearLayout l3_l = (LinearLayout)findViewById(R.id.l3_l);
        LinearLayout l3_r = (LinearLayout)findViewById(R.id.l3_r);
        LinearLayout l4_l = (LinearLayout)findViewById(R.id.l4_l);
        LinearLayout l4_r = (LinearLayout)findViewById(R.id.l4_r);
        LinearLayout l5_l = (LinearLayout)findViewById(R.id.l5_l);
        LinearLayout l5_r = (LinearLayout)findViewById(R.id.l5_r);
        l  = new LinearLayout[][]{ {l1_l,l1_r}, {l2_l,l2_r}, {l3_l,l3_r}, {l4_l,l4_r}, {l5_l,l5_r}};

        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(this);
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

        ti1.setText(R.string.A19_EN);
        ti1.setTextColor(getResources().getColor(R.color.my_black));

        int lang_id = mPresenter.getlanguage();
        switch (lang_id){
            // فارسی
            case 1:
                ti2.setText(R.string.A19_FA);
                ti2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // کردی
            case 2:
                ti2.setText(R.string.A19_KU);
                ti2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // ترکی آذری
            case 3:
                ti2.setText(R.string.A19_TA);
                ti2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // چینی
            case 4:
                ti2.setText(R.string.A19_CH);
                ti2.setTextColor(getResources().getColor(R.color.my_black));
                break;
        }

        w1 = new String[count];
        w2 = new String[count];
        s1 = new String[count][2];
        s2 = new String[count][2];

        for(int i=0 ; i<count ; i++){
            w1[i] = tbActivityDetailList.get(i).getTitle1();
            w2[i] = tbActivityDetailList.get(i).getTitle2();

            s1[i][0] = tbActivityDetailList.get(i).getTitle1();
            s1[i][1] = i+"";

            s2[i][0] = tbActivityDetailList.get(i).getTitle2();
            s2[i][1] = i+"";
        }

        int max_range = s1.length-1;
        int min_range = 0;
        for(int i=0 ; i<s1.length ;  i++) {
            int rnd = new Random().nextInt(max_range - min_range + 1) + min_range;
            String in_s1 = s1[rnd][0];
            String i_s1 = s1[rnd][1];
            s1[rnd][0] = s1[i][0];
            s1[rnd][1] = s1[i][1];
            s1[i][0] = in_s1;
            s1[i][1] = i_s1;
        }

        for(int i=0 ; i<s2.length ;  i++) {
            int rnd = new Random().nextInt(max_range - min_range + 1) + min_range;
            String in_s2 = s2[rnd][0];
            String i_s2 = s2[rnd][1];
            s2[rnd][0] = s2[i][0];
            s2[rnd][1] = s2[i][1];
            s2[i][0] = in_s2;
            s2[i][1] = i_s2;
        }

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        t1 = new TextView[count];
        t2 = new TextView[count];

        for(int i = 0; i < count ; i++){

            t1[i] = new TextView(this);
            params.addRule(RelativeLayout.ALIGN_LEFT, RelativeLayout.TRUE);
            t1[i].setLayoutParams(params);
            t1[i].setText(s1[i][0]);
            t1[i].setTextSize(16);
            t1[i].setTextColor(getResources().getColor(R.color.my_black));
            t1[i].setGravity(Gravity.LEFT);
            l[i][0].addView(t1[i]);
            final int finalI = i;
            t1[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    check("t1", finalI, t1[finalI], s1[finalI][1]);
                }
            });

            t2[i] = new TextView(this);
            params.addRule(RelativeLayout.ALIGN_RIGHT, RelativeLayout.TRUE);
            t2[i].setLayoutParams(params);
            t2[i].setText(s2[i][0]);
            t2[i].setTextSize(16);
            t2[i].setTextColor(getResources().getColor(R.color.my_black));
            t2[i].setGravity(Gravity.RIGHT);
            l[i][1].addView(t2[i]);
            t2[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    check("t2", finalI, t2[finalI], s2[finalI][1]);
                }
            });
        }
    }

    @Override
    public void onClick(View v){

        if (v.getId() == R.id.next) {

            if(correct == count) {

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
                            A19.this.finish();
                            startActivity(new Intent(A19.this, End.class));
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

                        A19.this.finish();
                        startActivity(new Intent(A19.this, End.class));
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
                .getA19Component(new A19_Module(this))
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

    public void check(String type, int i, TextView t, String original_id){

        if(aval_type != null){
            // xod
            if(aval_type == type &&  aval_int == i){
                aval_type = null;
                t.setBackgroundColor(getResources().getColor(R.color.my_white));

            }
            else{
                // ham groh (t1)
                if(type.equals("t1")) {
                    if(aval_type.equals("t1")) {
                        t1[aval_int].setBackgroundColor(getResources().getColor(R.color.my_white));
                        aval_type = null;
                    }
                    // groh moqabel (t1)
                    else if(aval_type.equals("t2")){
                        boolean answer = false;
                        if(aval_original.equals(original_id)){
                            answer = true;
                        }
                        // true
                        if (answer) {
                            t1[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.border_dis));
                            t1[i].setClickable(false);

                            t2[aval_int].setBackgroundDrawable(getResources().getDrawable(R.drawable.border_dis));
                            t2[aval_int].setClickable(false);

                            aval_type = null;
                            correct++;
                            if(correct == count){
                                next.setTextColor(Color.WHITE);
                                next.setBackgroundResource(R.drawable.btn_green);
                                next.setText("countinue");
                            }
                        }
                        // false
                        else{
                            t2[aval_int].setBackgroundColor(getResources().getColor(R.color.my_white));
                            t.setBackgroundColor(getResources().getColor(R.color.my_white));

                            aval_type = null;
                        }
                    }
                }
                // ham groh (t2)
                if(type.equals("t2")) {
                    if(aval_type.equals("t2")) {
                        t2[aval_int].setBackgroundColor(getResources().getColor(R.color.my_white));
                        aval_type = null;
                    }
                    // groh moqabel (t2)
                    else if(aval_type.equals("t1")) {
                        boolean answer = false;
                        if(aval_original.equals(original_id)){
                            answer = true;
                        }
                        // true
                        if (answer) {
                            t2[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.border_dis));
                            t2[i].setClickable(false);

                            t1[aval_int].setBackgroundDrawable(getResources().getDrawable(R.drawable.border_dis));
                            t1[aval_int].setClickable(false);

                            aval_type = null;
                            correct++;
                            if(correct == count){
                                next.setTextColor(Color.WHITE);
                                next.setBackgroundResource(R.drawable.btn_green);
                                next.setText("countinue");
                            }
                        }
                        // false
                        else{
                            t1[aval_int].setBackgroundColor(getResources().getColor(R.color.my_white));
                            t.setBackgroundColor(getResources().getColor(R.color.my_white));

                            aval_type = null;
                        }
                    }
                }
            }

        }else{
            // entexab
            aval_type = type;
            aval_int = i;
            aval_original = original_id;
            t.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    public void back(){
        A19.this.finish();
        startActivity(new Intent(A19.this, Lesson.class));
    }
}