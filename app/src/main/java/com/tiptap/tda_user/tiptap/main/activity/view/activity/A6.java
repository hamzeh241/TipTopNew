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
import com.tiptap.tda_user.tiptap.main.activity.Api.Post_UpdateUser;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Main_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.view.BaseActivity;
import com.tiptap.tda_user.tiptap.main.activity.view.lesson.Lesson;

import org.json.JSONException;

import java.util.List;
import java.util.Random;
import javax.inject.Inject;

public class A6 extends BaseActivity
        implements MVP_Main.RequiredViewOps, View.OnClickListener{

    private static final String TAG = A6.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A6.class.getName());

    @Inject
    public MVP_Main.ProvidedPresenterOps mPresenter;

    //defination  public variable
    LinearLayout l1;
    Button b,c,d,e,f,h;
    String true_ans1, true_ans2, true_ans3, my_ans1="",my_ans2="",my_ans3="",title3,title4,title5,title6,title7,title8,title9;
    TextView txt3;
    int count=0;
    int back_pressed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.a6);

        setupViews();
        setupMVP();

        // hide keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        max = mPresenter.max_Activitynumber(idlesson);

        // first time you are do activity
        if(Act_Status.equals("first")){
            tbActivity = mPresenter.getActivity(idlesson, activitynumber);
        }
        // second time you are do activity
        if(Act_Status.equals("second")) {
            tbActivity = mPresenter.getActivity2(idactivity);
        }

        // get tbactivity for get idlesson and activitynumber
        idactivity = tbActivity.get_id();

        // get tbactvity detail for find answer and size of count for find structure that is 1 or 2 or 3 question
        tbActivityDetailList = mPresenter.getListActivityDetail(idactivity);
        count = mPresenter.count_ActivityDetail(idactivity);
        //count=tbActivityDetailList.size();


        //for 1 question count is 3 and check 2 state ( the statment is complete or no ....
        if(count==3){
            //check txt and button
            //set titile 1 for show 1 question
            title1=tbActivityDetailList.get(0).getTitle1().toString();

            //check title 2 have answer or null ( if null dont show button)
            if(tbActivityDetailList.get(1).getTitle1() == null || tbActivityDetailList.get(1).getTitle1().equals("null")){

                b.setVisibility(View.GONE);
                LinearLayout l2 = (LinearLayout)findViewById(R.id.l2);
                l2.setVisibility(View.GONE);

                //if title 2 is not null then title 2 get answer
            }else{

                title2=tbActivityDetailList.get(1).getTitle1().toString();
            }

            if(tbActivityDetailList.get(2).getTitle1() == null || tbActivityDetailList.get(2).getTitle1().equals("null")){
                c.setVisibility(View.GONE);
            }else{
                title3=tbActivityDetailList.get(2).getTitle1().toString();
            }

            //invisible txt2 and txt3 and buttun d , e , f , h
            d.setVisibility(View.INVISIBLE);
            e.setVisibility(View.INVISIBLE);
            f.setVisibility(View.INVISIBLE);
            h.setVisibility(View.INVISIBLE);
            txt2.setVisibility(View.INVISIBLE);
            txt3.setVisibility(View.INVISIBLE);

            //find true answer for question 1
            if(tbActivityDetailList.get(1).getIsAnswer().equals("true")) {
                true_ans1=title2;
            }
            if(tbActivityDetailList.get(2).getIsAnswer().equals("true")){
                true_ans1=title3;
            }
        }

        //for 2 question count is 6 and check 2 state ( the question 1 is complete or no ....
        if(count==6){

            //check txt and button
            title1=tbActivityDetailList.get(0).getTitle1().toString();

            if(tbActivityDetailList.get(1).getTitle1() == null || tbActivityDetailList.get(1).getTitle1().equals("null")){
                b.setVisibility(View.GONE);
                LinearLayout l2 = (LinearLayout)findViewById(R.id.l2);
                l2.setVisibility(View.GONE);
            }else{
                title2=tbActivityDetailList.get(1).getTitle1().toString();
            }

            if(tbActivityDetailList.get(2).getTitle1() == null || tbActivityDetailList.get(2).getTitle1().equals("null")){
                c.setVisibility(View.GONE);
            }else{
                title3=tbActivityDetailList.get(2).getTitle1().toString();
            }

            title4=tbActivityDetailList.get(3).getTitle1().toString();

            if(tbActivityDetailList.get(4).getTitle1() == null || tbActivityDetailList.get(4).getTitle1().equals("null")){
                d.setVisibility(View.GONE);
            }else{
                title5=tbActivityDetailList.get(4).getTitle1().toString();
            }

            if(tbActivityDetailList.get(5).getTitle1() == null || tbActivityDetailList.get(5).getTitle1().equals("null")){
                e.setVisibility(View.GONE);
            }else{
                title6=tbActivityDetailList.get(5).getTitle1().toString();
            }

            //invisible txt3 and buttun f , h
            f.setVisibility(View.INVISIBLE);
            h.setVisibility(View.INVISIBLE);
            txt3.setVisibility(View.INVISIBLE);

            //find true answer for question 1
            if(tbActivityDetailList.get(1).getIsAnswer().equals("true")) {
                true_ans1=title2;
            }
            if(tbActivityDetailList.get(2).getIsAnswer().equals("true")){
                true_ans1=title3;
            }

            //find true answer for question 2
            if(tbActivityDetailList.get(4).getIsAnswer().equals("true")) {
                true_ans2=title5;
            }
            if(tbActivityDetailList.get(5).getIsAnswer().equals("true")){
                true_ans2=title6;
            }

        }
        //for 2 question count is 6 and check 2 state ( the question 1 is complete or no ....
        if(count==9){

            //check txt and button
            title1=tbActivityDetailList.get(0).getTitle1().toString();

            if(tbActivityDetailList.get(1).getTitle1() == null || tbActivityDetailList.get(1).getTitle1().equals("null")){
                b.setVisibility(View.GONE);
                LinearLayout l2 = (LinearLayout)findViewById(R.id.l2);
                l2.setVisibility(View.GONE);
            }else{
                title2=tbActivityDetailList.get(1).getTitle1().toString();
            }

            if(tbActivityDetailList.get(2).getTitle1() == null || tbActivityDetailList.get(2).getTitle1().equals("null")){
                c.setVisibility(View.GONE);
            }else{
                title3=tbActivityDetailList.get(2).getTitle1().toString();
            }

            title4=tbActivityDetailList.get(3).getTitle1().toString();

            if(tbActivityDetailList.get(4).getTitle1() == null || tbActivityDetailList.get(4).getTitle1().equals("null")){
                d.setVisibility(View.GONE);
            }else{
                title5=tbActivityDetailList.get(4).getTitle1().toString();
            }

            if(tbActivityDetailList.get(5).getTitle1() == null || tbActivityDetailList.get(5).getTitle1().equals("null")){
                e.setVisibility(View.GONE);
            }else{
                title6=tbActivityDetailList.get(5).getTitle1().toString();
            }

            title7=tbActivityDetailList.get(6).getTitle1().toString();

            if(tbActivityDetailList.get(7).getTitle1() == null || tbActivityDetailList.get(7).getTitle1().equals("null")){
                f.setVisibility(View.GONE);
            }else{
                title8=tbActivityDetailList.get(7).getTitle1().toString();
            }

            if(tbActivityDetailList.get(8).getTitle1() == null || tbActivityDetailList.get(8).getTitle1().equals("null")){
                h.setVisibility(View.GONE);
            }else{
                title9=tbActivityDetailList.get(8).getTitle1().toString();
            }


            //find true answer for question 1
            if(tbActivityDetailList.get(1).getIsAnswer().equals("true")) {
                true_ans1=title2;
            }
            if(tbActivityDetailList.get(2).getIsAnswer().equals("true")){
                true_ans1=title3;
            }

            //find true answer for question 2
            if(tbActivityDetailList.get(4).getIsAnswer().equals("true")) {
                true_ans2=title5;
            }
            if(tbActivityDetailList.get(5).getIsAnswer().equals("true")){
                true_ans2=title6;
            }

            // find true answer for question 3
            if(tbActivityDetailList.get(7).getIsAnswer().equals("true")) {
                true_ans3=title8;
            }
            if(tbActivityDetailList.get(8).getIsAnswer().equals("true")){
                true_ans3=title9;
            }
        }

        after_setup();
    }

    //show and set the view
    private void setupViews() {

        t1 = (TextView)findViewById(R.id.title1);
        t2 = (TextView)findViewById(R.id.title2);
        txt1=(TextView)findViewById(R.id.txt1);
        txt2=(TextView)findViewById(R.id.txt2);
        txt3=(TextView)findViewById(R.id.txt3);
        b=(Button)findViewById(R.id.btn1);
        c=(Button)findViewById(R.id.btn2);
        d=(Button)findViewById(R.id.btn3);
        e=(Button)findViewById(R.id.btn4);
        f=(Button)findViewById(R.id.btn5);
        h=(Button)findViewById(R.id.btn6);
        next = (Button) findViewById(R.id.next);
        p = (ProgressBar)findViewById(R.id.p);
        p.setMax(100);
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
        t1.setText(R.string.A6_EN);
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
                t2.setText(R.string.A6_KU);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // ترکی آذری
            case 3:
                t2.setText(R.string.A6_TA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // چینی
            case 4:
                t2.setText(R.string.A6_CH);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
        }

        //set txt for question 1 and 2
        txt1.setText(title1);
        txt2.setText(title4);
        b.setText(title2);
        c.setText(title3);
        d.setText(title5);
        e.setText(title6);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        d.setOnClickListener(this);
        e.setOnClickListener(this);

        if(count==9) {
            //set txt for question 1 and 2 , 3
            txt1.setText(title1);
            txt2.setText(title4);
            b.setText(title2);
            c.setText(title3);
            d.setText(title5);
            e.setText(title6);
            b.setOnClickListener(this);
            c.setOnClickListener(this);
            d.setOnClickListener(this);
            e.setOnClickListener(this);
            //set text for question 3
            txt3.setText(title7);
            f.setText(title8);
            h.setText(title9);
            f.setOnClickListener(this);
            h.setOnClickListener(this);
        }

        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        // click on button b or c or d,e,f,h and change another button in every question
        if(v.getId()==R.id.btn1){

            my_ans1=b.getText().toString();
            b.setBackgroundResource(R.drawable.border_btn_select);
            c.setBackgroundResource(R.drawable.border_btn_normal);

            next.setTextColor(Color.WHITE);
            next.setBackgroundResource(R.drawable.btn_green);
        }

        if(v.getId()==R.id.btn2){

            my_ans1=c.getText().toString();
            c.setBackgroundResource(R.drawable.border_btn_select);
            b.setBackgroundResource(R.drawable.border_btn_normal);

            next.setTextColor(Color.WHITE);
            next.setBackgroundResource(R.drawable.btn_green);
        }

        if(v.getId()==R.id.btn3){
            my_ans2=d.getText().toString();

            d.setBackgroundResource(R.drawable.border_btn_select);
            e.setBackgroundResource(R.drawable.border_btn_normal);

            next.setTextColor(Color.WHITE);
            next.setBackgroundResource(R.drawable.btn_green);
        }

        if(v.getId()==R.id.btn4){

            my_ans2=e.getText().toString();
            e.setBackgroundResource(R.drawable.border_btn_select);
            d.setBackgroundResource(R.drawable.border_btn_normal);

            next.setTextColor(Color.WHITE);
            next.setBackgroundResource(R.drawable.btn_green);
        }

        if(v.getId()==R.id.btn5){

            my_ans3=f.getText().toString();
            f.setBackgroundResource(R.drawable.border_btn_select);
            h.setBackgroundResource(R.drawable.border_btn_normal);

            next.setTextColor(Color.WHITE);
            next.setBackgroundResource(R.drawable.btn_green);
        }

        if(v.getId()==R.id.btn6){

            my_ans3=h.getText().toString();
            h.setBackgroundResource(R.drawable.border_btn_select);
            f.setBackgroundResource(R.drawable.border_btn_normal);

            next.setTextColor(Color.WHITE);
            next.setBackgroundResource(R.drawable.btn_green);
        }


        if (v.getId() == R.id.next) {

            switch (next.getText().toString()) {

                case "check":

                    //use have_answer for show notifacation for answer question 1 && 2 && 3
                    int have_answer = 0;

                    //show notifacation for answer question 1
                    if(count == 3){

                        if( my_ans1.equals("") || my_ans1.equals("null") ){
                            Toast.makeText(getApplicationContext(), "یکی از گزینه ها را برای جواب انتخاب کنید", Toast.LENGTH_SHORT).show();
                        }else{
                            have_answer++;
                        }
                    }

                    //show notifacation for answer question 1 && 2
                    if(count == 6){
                        if(title2==null){
                            have_answer++;
                        }else{
                            if( my_ans1.equals("") || my_ans1.equals("null") ){
                                Toast.makeText(getApplicationContext(), "یکی از گزینه ها را برای جواب انتخاب کنید", Toast.LENGTH_SHORT).show();
                            }else{
                                have_answer++;
                            }

                        }

                        if( my_ans2.equals("") || my_ans2.equals("null") ){
                            Toast.makeText(getApplicationContext(), "یکی از گزینه ها را برای جواب انتخاب کنید", Toast.LENGTH_SHORT).show();
                        }else{
                            have_answer++;
                        }
                    }

                    //show notifacation for answer question 1 && 2
                    if(count == 9){

                        if(title2==null){
                            have_answer++;

                        }else{

                            if( my_ans1.equals("") || my_ans1.equals("null") ){
                                Toast.makeText(getApplicationContext(), "یکی از گزینه ها را برای جواب انتخاب کنید", Toast.LENGTH_SHORT).show();
                            }else{
                                have_answer++;
                            }
                        }

                        if( my_ans2.equals("") || my_ans2.equals("null") ){
                            Toast.makeText(getApplicationContext(), "یکی از گزینه ها را برای جواب انتخاب کنید", Toast.LENGTH_SHORT).show();
                        }else{
                            have_answer++;
                        }

                        if( my_ans3.equals("") || my_ans3.equals("null") ){
                            Toast.makeText(getApplicationContext(), "یکی از گزینه ها را برای جواب انتخاب کنید", Toast.LENGTH_SHORT).show();
                        }else{
                            have_answer++;
                        }
                    }

                    // use  variable is_answer for find true answer
                    boolean is_answer = false;

                    if (count == 3) {
                        if(have_answer == 1){
                            is_answer = true;
                        }
                    }

                    if (count == 6) {
                        if(have_answer == 2){
                            is_answer = true;
                        }
                    }

                    if(count == 9){
                        if(have_answer == 3){
                            is_answer = true;
                        }
                    }


                    if(is_answer) {

                        //  use variable final_answer for set true answer
                        boolean final_answer = false;

                        if(count == 3){

                            if(my_ans1.equals(true_ans1)){
                                final_answer = true;
                            }
                        }

                        // count = 6
                        if(count == 6){

                            if(title2==null){
                                if(my_ans2.equals(true_ans2)){
                                    final_answer = true;
                                }

                            }else{
                                if(my_ans1.equals(true_ans1)){
                                    if(my_ans2.equals(true_ans2)){
                                        final_answer = true;
                                    }
                                }
                            }
                            // cheak 2 answer
                        }

                        // count = 9
                        if(count == 9){

                            if (title2==null){

                                // cheak 3 answer
                                if(my_ans2.equals(true_ans2)){
                                    if(my_ans3.equals(true_ans3)){
                                        final_answer = true;
                                    }
                                }


                            }else {
                                // cheak 3 answer
                                if(my_ans1.equals(true_ans1)){
                                    if(my_ans2.equals(true_ans2)){
                                        if(my_ans3.equals(true_ans3)){
                                            final_answer = true;
                                        }
                                    }
                                }

                            }
                        }

                        // true answer
                        if(final_answer){
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
                            t1 = (TextView)findViewById(R.id.title1);
                            t2 = (TextView)findViewById(R.id.title2);
                            txt1=(TextView)findViewById(R.id.txt1);
                            txt2=(TextView)findViewById(R.id.txt2);
                            txt3=(TextView)findViewById(R.id.txt3);
                            b=(Button)findViewById(R.id.btn1);
                            c=(Button)findViewById(R.id.btn2);
                            d=(Button)findViewById(R.id.btn3);
                            e=(Button)findViewById(R.id.btn4);
                            f=(Button)findViewById(R.id.btn5);
                            h=(Button)findViewById(R.id.btn6);
                            next = (Button) findViewById(R.id.next);
                            p = (ProgressBar)findViewById(R.id.p);

                            // Fragment_true
                            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder1);
                            Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                            linearLayout.setAnimation(slide_down);
                            linearLayout.setVisibility(View.VISIBLE);

                            Fragment_True f1 = new Fragment_True();
                            if(count == 3){
                                f1.txt_true.setText(true_ans1);
                            }
                            if(count == 6){
                                if(title2==null){
                                    f1.txt_true.setText(true_ans2);
                                }else{
                                    f1.txt_true.setText(true_ans1+" / "+true_ans2);
                                }
                            }
                            if(count == 9){
                                if(title2==null) {
                                    f1.txt_true.setText(true_ans2+" / "+true_ans3);
                                }else {
                                    f1.txt_true.setText(true_ans1+" / "+true_ans2+" / "+true_ans3);
                                }
                            }
                            FragmentManager fragMan = getSupportFragmentManager();
                            FragmentTransaction fragTransaction = fragMan.beginTransaction();
                            fragTransaction.add(R.id.fragment1, f1);
                            fragTransaction.commit();

                            // play sound
                            mpt.start();


                        }else {
                            // false answer
                            // Clickable_false
                            t1 = (TextView)findViewById(R.id.title1);
                            t2 = (TextView)findViewById(R.id.title2);
                            txt1=(TextView)findViewById(R.id.txt1);
                            txt2=(TextView)findViewById(R.id.txt2);
                            txt3=(TextView)findViewById(R.id.txt3);
                            b=(Button)findViewById(R.id.btn1);
                            c=(Button)findViewById(R.id.btn2);
                            d=(Button)findViewById(R.id.btn3);
                            e=(Button)findViewById(R.id.btn4);
                            f=(Button)findViewById(R.id.btn5);
                            h=(Button)findViewById(R.id.btn6);
                            next = (Button) findViewById(R.id.next);
                            p = (ProgressBar)findViewById(R.id.p);

                            // Fragment_false
                            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder2);
                            Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                            linearLayout.setAnimation(slide_down);
                            linearLayout.setVisibility(View.VISIBLE);

                            Fragment_False f2 = new Fragment_False();

                            //check count for show false and true your answer
                            if(count == 3){
                                f2.txt_false.setText(true_ans1);
                            }

                            if(count == 6){
                                if(title2==null){
                                    f2.txt_false.setText(true_ans2);
                                }else{
                                    f2.txt_false.setText(true_ans1+" / "+true_ans2);
                                }
                            }

                            if(count == 9){
                                if(title2==null) {
                                    f2.txt_false.setText(true_ans2+" / "+true_ans3);

                                }else {
                                    f2.txt_false.setText(true_ans1+" / "+true_ans2+" / "+true_ans3);
                                }
                            }

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

                                                        // update server - next function
                                                        List<Integer> id_less_new = mPresenter.lesson(id_func.get(next_func));
                                                        update_server(id_less_new.get(0));
                                                    }
                                                    break;
                                                }
                                            }
                                        } else {
                                            End.gofunction = 0;
                                            if (now_less == idlesson) {
                                                int next_less = i + 1;
                                                mPresenter.update_idlesson(id_less.get(next_less));

                                                // update server - next lesson
                                                update_server(id_less.get(next_less));
                                            }
                                        }
                                        break;
                                    }
                                }

                                A6.this.finish();
                                closeOtherMediaPlayer();
                                startActivity(new Intent(A6.this, End.class));
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

                                                    // update server - next function
                                                    List<Integer> id_less_new = mPresenter.lesson(id_func.get(next_func));
                                                    update_server(id_less_new.get(0));
                                                }
                                                break;
                                            }
                                        }
                                    } else {
                                        End.gofunction = 0;
                                        if (now_less == idlesson) {
                                            int next_less = i + 1;
                                            mPresenter.update_idlesson(id_less.get(next_less));

                                            // update server - next lesson
                                            update_server(id_less.get(next_less));
                                        }
                                    }
                                    break;
                                }
                            }
                            A6.this.finish();
                            closeOtherMediaPlayer();
                            startActivity(new Intent(A6.this, End.class));
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
                .getA6Component(new Main_Module(this))
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
            A6.this.finish();
            closeOtherMediaPlayer();
            startActivity(new Intent(A6.this, Lesson.class));
        }
    }

    public void update_server(int idL) {
        try {
            String UserName = mPresenter.get_UserName();
            new Post_UpdateUser(getApplicationContext(), A6.this, haveNetworkConnection(), UserName, idL).post();
        } catch (JSONException e) {}
    }
}