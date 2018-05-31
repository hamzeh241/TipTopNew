package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A19;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A19_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivityDetail;
import com.tiptap.tda_user.tiptap.main.activity.view.lesson.Lesson;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;
import android.view.View.OnClickListener;

public class A19 extends BaseActivity
                 implements MVP_A19.RequiredViewOps,
                 OnClickListener{

    private static final String TAG = A19.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A19.class.getName());

    @Inject
    public MVP_A19.ProvidedPresenterOps mPresenter;

    public static int idlesson;
    public static int idfunction;
    public static int activitynumber;
    public static String Act_Status;
    public static int idactivity;
    TbActivity tbActivity;
    int max,now_less;
    List<TbActivityDetail> tbActivityDetailList;
    int count;
    String w1[],w2[];
    String s1[][],s2[][];
    TextView t1[],t2[],ti1,ti2;
    static String aval_type = null;
    static int aval_int;
    ProgressBar p;
    static String aval_original;
    int correct=0;
    Button next;
    LinearLayout l [][];
    int all;

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
                            switch (id_at_new_f) {

                                case 1: break;
                                case 2: break;

                                case 3:
                                    A3.idlesson = idlesson ;
                                    A3.idfunction = idfunction ;
                                    A3.idactivity = id_act;
                                    A3.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A3.class));
                                    break;

                                case 4:
                                    A4.idlesson = idlesson ;
                                    A4.idfunction = idfunction ;
                                    A4.idactivity = id_act;
                                    A4.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A4.class));
                                    break;

                                case 5:
                                    A5.idlesson = idlesson ;
                                    A5.idfunction = idfunction ;
                                    A5.idactivity = id_act;
                                    A5.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A5.class));
                                    break;

                                case 6:
                                    A6.idlesson = idlesson ;
                                    A6.idfunction = idfunction ;
                                    A6.idactivity = id_act;
                                    A6.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A6.class));
                                    break;

                                case 7:
                                    A7.idlesson = idlesson ;
                                    A7.idfunction = idfunction ;
                                    A7.idactivity = id_act;
                                    A7.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A7.class));
                                    break;

                                case 8:
                                    A8.idlesson = idlesson ;
                                    A8.idfunction = idfunction ;
                                    A8.idactivity = id_act;
                                    A8.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A8.class));
                                    break;

                                case 9:
                                    A9.idlesson = idlesson ;
                                    A9.idfunction = idfunction ;
                                    A9.idactivity = id_act;
                                    A9.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A9.class));
                                    break;

                                case 10: break;
                                case 11: break;
                                case 12: break;
                                case 13: break;
                                case 14: break;

                                case 15:
                                    A15.idlesson = idlesson ;
                                    A15.idfunction = idfunction ;
                                    A15.idactivity = id_act;
                                    A15.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A15.class));
                                    break;

                                case 16: break;
                                case 17: break;

                                case 18:
                                    A18.idlesson = idlesson ;
                                    A18.idfunction = idfunction ;
                                    A18.idactivity = id_act;
                                    A18.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A18.class));
                                    break;

                                case 19:
                                    A19.idlesson = idlesson ;
                                    A19.idfunction = idfunction ;
                                    A19.idactivity = id_act;
                                    A19.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A19.class));
                                    break;

                                case 20:
                                    A20.idlesson = idlesson ;
                                    A20.idfunction = idfunction ;
                                    A20.idactivity = id_act;
                                    A20.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A20.class));
                                    break;

                                case 21: break;

                                case 22:
                                    A22.idlesson = idlesson ;
                                    A22.idfunction = idfunction ;
                                    A22.idactivity = id_act;
                                    A22.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A22.class));
                                    break;

                                case 23: break;

                                case 24:
                                    A24.idlesson = idlesson ;
                                    A24.idfunction = idfunction ;
                                    A24.idactivity = id_act;
                                    A24.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A24.class));
                                    break;

                                case 25:
                                    //A25.idlesson = idlesson ;
                                    // A.idfunction = idfunction ;
                                    // A34.idactivity = id_act;
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A25.class));
                                    break;

                                case 26:
                                    A26.idlesson = idlesson ;
                                    A26.idfunction = idfunction ;
                                    A26.idactivity = id_act;
                                    A26.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A26.class));
                                    break;

                                case 27:
                                    A27.idlesson = idlesson ;
                                    A27.idfunction = idfunction ;
                                    A27.idactivity = id_act;
                                    A27.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A27.class));
                                    break;

                                case 28:
                                    A28.idlesson = idlesson ;
                                    A28.idfunction = idfunction ;
                                    A28.idactivity = id_act;
                                    A28.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A28.class));
                                    break;

                                case 29:
                                    A29.idlesson = idlesson ;
                                    A29.idfunction = idfunction ;
                                    A29.idactivity = id_act;
                                    A29.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A29.class));
                                    break;

                                case 30:
                                    A30.idlesson = idlesson ;
                                    A30.idfunction = idfunction ;
                                    A30.idactivity = id_act;
                                    A30.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A30.class));
                                    break;

                                case 31:
                                    //A31.idlesson = idlesson ;
                                    // A.idfunction = idfunction ;
                                    // A26.idactivity = id_act;
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A31.class));
                                    break;

                                case 32:
                                    A32.idlesson = idlesson ;
                                    A32.idfunction = idfunction ;
                                    A32.idactivity = id_act;
                                    A32.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A32.class));
                                    break;

                                case 33:
                                    A33.idlesson = idlesson ;
                                    A33.idfunction = idfunction ;
                                    A33.idactivity = id_act;
                                    A33.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A33.class));
                                    break;

                                case 34:
                                    A34.idlesson = idlesson ;
                                    A34.idfunction = idfunction ;
                                    A34.idactivity = id_act;
                                    A34.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A34.class));
                                    break;

                                case 35:
                                    A35.idlesson = idlesson ;
                                    A35.idfunction = idfunction ;
                                    A35.idactivity = id_act;
                                    A35.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A35.class));
                                    break;

                                case 36: break;

                                case 37:
                                    A37.idlesson = idlesson ;
                                    A37.idfunction = idfunction ;
                                    A37.idactivity = id_act;
                                    A37.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A37.class));
                                    break;

                                case 38:
                                    //A38.idlesson = idlesson ;
                                    // A.idfunction = idfunction ;
                                    // A30.idactivity = id_act;
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A38.class));
                                    break;

                                case 39:
                                    A39.idlesson = idlesson ;
                                    A39.idfunction = idfunction ;
                                    A39.idactivity = id_act;
                                    A39.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A39.class));

                                    break;

                                case 40:
                                    //A40.idlesson = idlesson ;
                                    // A.idfunction = idfunction ;
                                    // A30.idactivity = id_act;
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A40.class));
                                    break;

                                case 41:
                                    //A41.idlesson = idlesson ;
                                    // A.idfunction = idfunction ;
                                    // A30.idactivity = id_act;
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A41.class));
                                    break;

                                case 42:
                                    //A42.idlesson = idlesson ;
                                    // A.idfunction = idfunction ;
                                    // A30.idactivity = id_act;
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A42.class));
                                    break;

                                case 43:
                                    //A43.idlesson = idlesson ;
                                    // A.idfunction = idfunction ;
                                    // A30.idactivity = id_act;
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A43.class));
                                    break;

                                case 44:
                                    //A44.idlesson = idlesson ;
                                    // A.idfunction = idfunction ;
                                    // A30.idactivity = id_act;
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A44.class));
                                    break;

                                case 46:
                                    A46.idlesson = idlesson ;
                                    A46.idfunction = idfunction ;
                                    A46.idactivity = id_act;
                                    A46.Act_Status = "second";
                                    A19.this.finish();
                                    startActivity(new Intent(A19.this,  A46.class));
                                    break;
                            }
                        }

                    } else {

                        TbActivity tb_new = mPresenter.getActivity(idlesson, ++activitynumber);
                        int id_at_new = tb_new.getId_ActivityType();

                        // first
                        switch (id_at_new){

                            case 1: break;
                            case 2: break;

                            case 3:
                                A3.idlesson = idlesson ;
                                A3.idfunction = idfunction ;
                                A3.activitynumber = activitynumber;
                                A3.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A3.class));
                                break;

                            case 4:
                                A4.idlesson = idlesson ;
                                A4.idfunction = idfunction ;
                                A4.activitynumber = activitynumber;
                                A4.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A4.class));
                                break;

                            case 5:
                                A5.idlesson = idlesson ;
                                A5.idfunction = idfunction ;
                                A5.activitynumber = activitynumber;
                                A5.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A5.class));
                                break;

                            case 6:
                                A6.idlesson = idlesson ;
                                A6.idfunction = idfunction ;
                                A6.activitynumber = activitynumber;
                                A6.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A6.class));
                                break;

                            case 7:
                                A7.idlesson = idlesson ;
                                A7.idfunction = idfunction ;
                                A7.activitynumber = activitynumber;
                                A7.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A7.class));
                                break;

                            case 8:
                                A8.idlesson = idlesson ;
                                A8.idfunction = idfunction ;
                                A8.activitynumber = activitynumber;
                                A8.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A8.class));
                                break;

                            case 9:
                                A9.idlesson = idlesson ;
                                A9.idfunction = idfunction ;
                                A9.activitynumber = activitynumber;
                                A9.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A9.class));
                                break;

                            case 10: break;
                            case 11: break;
                            case 12: break;
                            case 13: break;
                            case 14: break;

                            case 15:
                                A15.idlesson = idlesson ;
                                A15.idfunction = idfunction ;
                                A15.activitynumber = activitynumber;
                                A15.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A15.class));
                                break;

                            case 16: break;
                            case 17: break;

                            case 18:
                                A18.idlesson = idlesson ;
                                A18.idfunction = idfunction ;
                                A18.activitynumber = activitynumber;
                                A18.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A18.class));
                                break;

                            case 19:
                                A19.idlesson = idlesson ;
                                A19.idfunction = idfunction ;
                                A19.activitynumber = activitynumber;
                                A19.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A19.class));
                                break;

                            case 20:
                                A20.idlesson = idlesson ;
                                A20.idfunction = idfunction ;
                                A20.activitynumber = activitynumber;
                                A20.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A20.class));
                                break;

                            case 21: break;

                            case 22:
                                A22.idlesson = idlesson ;
                                A22.idfunction = idfunction ;
                                A22.activitynumber = activitynumber;
                                A22.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A22.class));
                                break;

                            case 23: break;

                            case 24:
                                A24.idlesson = idlesson ;
                                A24.idfunction = idfunction ;
                                A24.activitynumber = activitynumber;
                                A24.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A24.class));
                                break;

                            case 25:
                                //A25.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A25.activitynumber = activitynumber;
                                A25.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A25.class));
                                break;

                            case 26:
                                A26.idlesson = idlesson ;
                                A26.idfunction = idfunction ;
                                A26.activitynumber = activitynumber;
                                A26.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A26.class));
                                break;

                            case 27:
                                A27.idlesson = idlesson ;
                                A27.idfunction = idfunction ;
                                A27.activitynumber = activitynumber;
                                A27.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A27.class));
                                break;

                            case 28:
                                A28.idlesson = idlesson ;
                                A28.idfunction = idfunction ;
                                A28.activitynumber = activitynumber;
                                A28.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A28.class));
                                break;

                            case 29:
                                A29.idlesson = idlesson ;
                                A29.idfunction = idfunction ;
                                A29.activitynumber = activitynumber;
                                A29.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A29.class));
                                break;

                            case 30:
                                A30.idlesson = idlesson ;
                                A30.idfunction = idfunction ;
                                A30.activitynumber = activitynumber;
                                A30.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A30.class));
                                break;

                            case 31:
                                //A31.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A31.activitynumber = activitynumber;
                                //A31.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A31.class));
                                break;

                            case 32:
                                A32.idlesson = idlesson ;
                                A32.idfunction = idfunction ;
                                A32.activitynumber = activitynumber;
                                A32.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A32.class));
                                break;

                            case 33:
                                A33.idlesson = idlesson ;
                                A33.idfunction = idfunction ;
                                A33.activitynumber = activitynumber;
                                A33.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A33.class));
                                break;

                            case 34:
                                A34.idlesson = idlesson ;
                                A34.idfunction = idfunction ;
                                A34.activitynumber = activitynumber;
                                A34.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A34.class));
                                break;

                            case 35:
                                A35.idlesson = idlesson ;
                                A35.idfunction = idfunction ;
                                A35.activitynumber = activitynumber;
                                A35.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A35.class));
                                break;

                            case 36: break;

                            case 37:
                                A37.idlesson = idlesson ;
                                A37.idfunction = idfunction ;
                                A37.activitynumber = activitynumber;
                                A37.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A37.class));
                                break;

                            case 38:
                                //A38.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A38.activitynumber = activitynumber;
                                //  A38.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A38.class));
                                break;

                            case 39:
                                A39.idlesson = idlesson ;
                                A39.idfunction = idfunction ;
                                A39.activitynumber = activitynumber;
                                A39.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A39.class));

                                break;

                            case 40:
                                //A40.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A40.activitynumber = activitynumber;
                                //  A40.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A40.class));
                                break;

                            case 41:
                                //A41.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A41.activitynumber = activitynumber;
                                //  A41.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A41.class));
                                break;

                            case 42:
                                //A42.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A42.activitynumber = activitynumber;
                                //  A42.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A42.class));
                                break;

                            case 43:
                                //A43.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A43.activitynumber = activitynumber;
                                //  A43.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A43.class));
                                break;

                            case 44:
                                //A44.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A44.activitynumber = activitynumber;
                                //  A44.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A44.class));
                                break;

                            case 46:
                                A46.idlesson = idlesson ;
                                A46.idfunction = idfunction ;
                                A46.activitynumber = activitynumber;
                                A46.Act_Status = "first";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A46.class));
                                break;
                        }
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
                        switch (id_at_new_f){

                            case 1: break;
                            case 2: break;

                            case 3:
                                A3.idlesson = idlesson ;
                                A3.idfunction = idfunction ;
                                A3.idactivity = id_act;
                                A3.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A3.class));
                                break;

                            case 4:
                                A4.idlesson = idlesson ;
                                A4.idfunction = idfunction ;
                                A4.idactivity = id_act;
                                A4.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A4.class));
                                break;

                            case 5:
                                A5.idlesson = idlesson ;
                                A5.idfunction = idfunction ;
                                A5.idactivity = id_act;
                                A5.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A5.class));
                                break;

                            case 6:
                                A6.idlesson = idlesson ;
                                A6.idfunction = idfunction ;
                                A6.idactivity = id_act;
                                A6.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A6.class));
                                break;

                            case 7:
                                A7.idlesson = idlesson ;
                                A7.idfunction = idfunction ;
                                A7.idactivity = id_act;
                                A7.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A7.class));
                                break;

                            case 8:
                                A8.idlesson = idlesson ;
                                A8.idfunction = idfunction ;
                                A8.idactivity = id_act;
                                A8.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A8.class));
                                break;

                            case 9:
                                A9.idlesson = idlesson ;
                                A9.idfunction = idfunction ;
                                A9.idactivity = id_act;
                                A9.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A9.class));
                                break;

                            case 10: break;
                            case 11: break;
                            case 12: break;
                            case 13: break;
                            case 14: break;

                            case 15:
                                A15.idlesson = idlesson ;
                                A15.idfunction = idfunction ;
                                A15.idactivity = id_act;
                                A15.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A15.class));
                                break;

                            case 16: break;
                            case 17: break;

                            case 18:
                                A18.idlesson = idlesson ;
                                A18.idfunction = idfunction ;
                                A18.idactivity = id_act;
                                A18.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A18.class));
                                break;

                            case 19:
                                A19.idlesson = idlesson ;
                                A19.idfunction = idfunction ;
                                A19.idactivity = id_act;
                                A19.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A19.class));
                                break;

                            case 20:
                                A20.idlesson = idlesson ;
                                A20.idfunction = idfunction ;
                                A20.idactivity = id_act;
                                A20.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A20.class));
                                break;

                            case 21: break;

                            case 22:
                                A22.idlesson = idlesson ;
                                A22.idfunction = idfunction ;
                                A22.idactivity = id_act;
                                A22.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A22.class));
                                break;

                            case 23: break;

                            case 24:
                                A24.idlesson = idlesson ;
                                A24.idfunction = idfunction ;
                                A24.idactivity = id_act;
                                A24.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A24.class));
                                break;

                            case 25:
                                //A25.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A6.idactivity = id_act;
                                //A9.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A25.class));
                                break;

                            case 26:
                                A26.idlesson = idlesson ;
                                A26.idfunction = idfunction ;
                                A26.idactivity = id_act;
                                A26.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A26.class));
                                break;

                            case 27:
                                A27.idlesson = idlesson ;
                                A27.idfunction = idfunction ;
                                A27.idactivity = id_act;
                                A27.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A27.class));
                                break;

                            case 28:
                                A28.idlesson = idlesson ;
                                A28.idfunction = idfunction ;
                                A28.idactivity = id_act;
                                A28.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A28.class));
                                break;

                            case 29:
                                A29.idlesson = idlesson ;
                                A29.idfunction = idfunction ;
                                A29.idactivity = id_act;
                                A29.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A29.class));
                                break;

                            case 30:
                                A30.idlesson = idlesson ;
                                A30.idfunction = idfunction ;
                                A30.idactivity = id_act;
                                A30.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A30.class));
                                break;

                            case 31:
                                //A31.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A29.idactivity = id_act;
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A31.class));
                                break;

                            case 32:
                                A32.idlesson = idlesson ;
                                A32.idfunction = idfunction ;
                                A32.idactivity = id_act;
                                A32.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A32.class));
                                break;

                            case 33:
                                A33.idlesson = idlesson ;
                                A33.idfunction = idfunction ;
                                A33.idactivity = id_act;
                                A33.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A33.class));
                                break;

                            case 34:
                                A34.idlesson = idlesson ;
                                A34.idfunction = idfunction ;
                                A34.idactivity = id_act;
                                A34.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A34.class));
                                break;

                            case 35:
                                A35.idlesson = idlesson ;
                                A35.idfunction = idfunction ;
                                A35.idactivity = id_act;
                                A35.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A35.class));
                                break;

                            case 36: break;

                            case 37:
                                A37.idlesson = idlesson ;
                                A37.idfunction = idfunction ;
                                A37.idactivity = id_act;
                                A37.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A37.class));
                                break;

                            case 38:
                                //A38.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A29.idactivity = id_act;
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A38.class));
                                break;

                            case 39:
                                A39.idlesson = idlesson ;
                                A39.idfunction = idfunction ;
                                A39.idactivity = id_act;
                                A39.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A39.class));

                                break;

                            case 40:
                                //A40.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A29.idactivity = id_act;
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A40.class));
                                break;

                            case 41:
                                //A41.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A29.idactivity = id_act;
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A41.class));
                                break;

                            case 42:
                                //A42.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A29.idactivity = id_act;
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A42.class));
                                break;

                            case 43:
                                //A43.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A29.idactivity = id_act;
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A43.class));
                                break;

                            case 44:
                                //A44.idlesson = idlesson ;
                                // A.idfunction = idfunction ;
                                //A29.idactivity = id_act;
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A44.class));
                                break;

                            case 46:
                                A46.idlesson = idlesson ;
                                A46.idfunction = idfunction ;
                                A46.idactivity = id_act;
                                A46.Act_Status = "second";
                                A19.this.finish();
                                startActivity(new Intent(A19.this,  A46.class));
                                break;
                        }
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
        mStateMaintainer.put(A19_Presenter.class.getSimpleName(), mPresenter);
    }

    private void reinitialize() {
        Log.d(TAG, "reinitialize");
        mPresenter = mStateMaintainer.get(A19_Presenter.class.getSimpleName());
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