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
import android.view.View.OnClickListener;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;
import org.json.JSONException;

public class A8 extends BaseActivity
                implements MVP_Main.RequiredViewOps, OnClickListener {

    private static final String TAG = A8.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A8.class.getName());

    @Inject
    public MVP_Main.ProvidedPresenterOps mPresenter;
    int back_pressed = 0;

    TextView t1[],t2[],ti1,ti2;
    String w1[] , w2[];

    String tohi = "-------";
    static int position = 0;

    LinearLayout tt1, tt2, aa1, aa2;
    MediaPlayer mpt, mpf;
    int all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.a8);

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
        title1 = tbActivity.getTitle1();
        title1 = title1.trim();
        title2 = tbActivity.getTitle2();
        title2 = title2.trim();

        after_setup();
    }

    private void setupViews() {

        position = 0;
        ti1 = (TextView)findViewById(R.id.title1);
        ti2 = (TextView)findViewById(R.id.title2);
        tt1 = (LinearLayout) findViewById(R.id.tt1);
        tt2 = (LinearLayout) findViewById(R.id.tt2);
        aa1 = (LinearLayout) findViewById(R.id.aa1);
        aa2 = (LinearLayout) findViewById(R.id.aa2);
        next = (Button) findViewById(R.id.next);
        p = (ProgressBar)findViewById(R.id.p);
        p.setMax(100);
        mpt = MediaPlayer.create (this, R.raw.true_sound);
        mpf =  MediaPlayer.create (this, R.raw.false_sound);
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

        ti1.setText(R.string.A8_EN);
        ti1.setTextColor(getResources().getColor(R.color.my_black));

        int lang_id = mPresenter.getlanguage();
        switch (lang_id){
            // فارسی
            case 1:
                ti2.setText(R.string.A8_FA);
                ti2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // کردی
            case 2:
                ti2.setText(R.string.A8_KU);
                ti2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // ترکی آذری
            case 3:
                ti2.setText(R.string.A8_TA);
                ti2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // چینی
            case 4:
                ti2.setText(R.string.A8_CH);
                ti2.setTextColor(getResources().getColor(R.color.my_black));
                break;
        }

        next.setOnClickListener(this);

        // answer
        int have = 0;
        for(int j=0 ; j<title1.length() ; j++){
            if(title1.charAt(j) == '/'){
                have = 1;
            }
        }
        // two correct answer
        if(have == 1){
            String part[] = title1.split("/");
            w1 = part[0].split(" ");
            w2 = part[1].split(" ");
        }
        // one correct answer
        else if(have == 0){
            w1 = title1.split(" ");
        }

        // words
        String s [] = title2.split("/");
        /*int max_range = s2.length-1;
        int min_range = 0;
        for(int i=0 ; i<s2.length ;  i++) {
            int rnd = new Random().nextInt(max_range - min_range + 1) + min_range;
            String temp = s2[rnd];
            s2[rnd] = s2[i];
            s2[i] = temp;
        }*/

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(4,0,4,0);

        t1 = new TextView[s.length];
        t2 = new TextView[s.length];
        int count = 0;

        for(int i = 0; i < s.length ; i++){

            t1[i] = new TextView(this);
            t1[i].setLayoutParams(params);
            t1[i].setTextColor(getResources().getColor(R.color.black));
            t1[i].setText(s[i]);
            t1[i].setTextColor(getResources().getColor(R.color.my_black));
            t1[i].setTextSize(18);
            t1[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
            if(count <= 4){
                tt1.addView(t1[i]);
            }if(count > 4){
                tt2.addView(t1[i]);
            }
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
            t2[i].setTextColor(getResources().getColor(R.color.my_black));
            t2[i].setTextSize(18);
            if(count <= 4){
                aa1.addView(t2[i]);
            }if(count > 4){
                aa2.addView(t2[i]);
            }
            t2[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    unset_data(t2[finalI],finalI);
                }
            });
            count++;
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
                            ti1.setClickable(false);
                            ti2.setClickable(false);
                            for(int i=0 ; i <t1.length ; i++){
                                t1[i].setClickable(false);
                            }
                            for(int i=0 ; i <t2.length ; i++){
                                t2[i].setClickable(false);
                            }
                            p.setClickable(false);

                            // Fragment_true
                            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder1);
                            Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                            linearLayout.setAnimation(slide_down);
                            linearLayout.setVisibility(View.VISIBLE);

                            Fragment_True f1 = new Fragment_True();
                            f1.txt_true.setText(title1);
                            FragmentManager fragMan = getSupportFragmentManager();
                            FragmentTransaction fragTransaction = fragMan.beginTransaction();
                            fragTransaction.add(R.id.fragment1, f1);
                            fragTransaction.commit();

                            // play sound
                            mpt.start();

                        } else if (answer == false) {

                            // Clickable_false
                            ti1.setClickable(false);
                            ti2.setClickable(false);
                            for(int i=0 ; i <t1.length ; i++){
                                t1[i].setClickable(false);
                            }
                            for(int i=0 ; i <t2.length ; i++){
                                t2[i].setClickable(false);
                            }
                            p.setClickable(false);

                            // Fragment_false
                            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder2);
                            Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                            linearLayout.setAnimation(slide_down);
                            linearLayout.setVisibility(View.VISIBLE);

                            Fragment_False f2 = new Fragment_False();
                            f2.txt_false.setText(title1);
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

                    if(position > 0) {

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
                                    A8.this.finish();
                                    startActivity(new Intent(A8.this, End.class));
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
                                A8.this.finish();
                                startActivity(new Intent(A8.this, End.class));

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
                .getA8Component(new Main_Module(this))
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
                t2[j].setTextColor(getResources().getColor(R.color.my_black));
                t2[j].setTextSize(18);
                // disable
                //t.setTextColor(getResources().getColor(R.color.gray));
                //t.setBackgroundColor(getResources().getColor(R.color.gray));
                t.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_gray));
                t.setClickable(false);
            }
        }
        // position
        position++;
        if(position > 0) {
            next.setTextColor(Color.WHITE);
            next.setBackgroundResource(R.drawable.btn_green);
        }
        if(position == 0){
            next.setTextColor(getResources().getColor(R.color.gray));
            next.setBackgroundResource(R.drawable.btn_gray);
        }
    }

    public void unset_data(TextView a, int ia){
        for(int j = 0 ; j < t1.length ; j++){
            if(a.getText().toString().equals( t1[j].getText().toString() )){
                a.setText(tohi);
                a.setTextColor(getResources().getColor(R.color.my_black));
                // enable
                t1[j].setTextColor(getResources().getColor(R.color.my_black));
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
            next.setBackgroundResource(R.drawable.btn_green);
        }
        if(position == 0){
            next.setTextColor(getResources().getColor(R.color.gray));
            next.setBackgroundResource(R.drawable.btn_gray);
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
        boolean answer1 = true;
        boolean answer2 = true;
        // ? answer
        int have = 0;
        for(int j=0 ; j<title1.length() ; j++){
            if(title1.charAt(j) == '/'){
                have = 1;
            }
        }
        // word to word
        for (int i = 0; i < t2.length; i++) {
            // empty word
            if(t2[i].getText().toString().equals(tohi)){
                answer = false;
                break;
            }else{
                //  2 answer
                if(have == 1){
                    // cheak w1
                    String a = nice_string_A8( t2[i].getText().toString() );
                    String b = nice_string_A8( w1[i] );
                    if (a.equals(b)) {
                    } else {
                        answer1 = false;
                    }
                    // cheak w2
                    String c = nice_string_A8( t2[i].getText().toString() );
                    String d = nice_string_A8( w2[i] );
                    if (c.equals(d)) {
                    } else {
                        answer2 = false;
                    }
                }
                // 1 answer
                else if(have == 0){
                    String a = nice_string_A8( t2[i].getText().toString() );
                    String b = nice_string_A8( w1[i] );
                    if (a.equals(b)) {
                    } else {
                        answer = false;
                    }
                }
            }
        }
        // final answer
        if(answer1 || answer2){
        }else{
            answer = false;
        }
        return answer;
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
            A8.this.finish();
            startActivity(new Intent(A8.this, Lesson.class));
        }
    }

    public void update_server(int idL) {
        try {
            String UserName = mPresenter.get_UserName();
            new Post_UpdateUser(getApplicationContext(), A8.this, haveNetworkConnection(), UserName, idL).post();
        } catch (JSONException e) {}
    }
}