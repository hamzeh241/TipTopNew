package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
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
import java.util.regex.Pattern;
import javax.inject.Inject;

public class A20 extends BaseActivity
        implements MVP_Main.RequiredViewOps,View.OnClickListener {

    private static final String TAG = A20.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer(getFragmentManager(), A20.class.getName());

    @Inject
    public MVP_Main.ProvidedPresenterOps mPresenter;


    LinearLayout linear;
    TextView t[];
    EditText e[];
    String ans[];
    String z[];
    int xali = 0;
    TextView text;
    String title1detailactivity, title2detailactivity, title1activity;

    int fill = 0, count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a20);

        setupViews();
        setupMVP();

        max = mPresenter.max_Activitynumber(idlesson);

        // first
        if (Act_Status.equals("first")) {
            tbActivity = mPresenter.getActivity(idlesson, activitynumber);
        }
        // second
        if (Act_Status.equals("second")) {
            tbActivity = mPresenter.getActivity2(idactivity);
        }


        // get tbactivity
        tbActivity = mPresenter.getActivity(idlesson, activitynumber);
        idactivity = tbActivity.get_id();
        title1activity = tbActivity.getTitle1();
        int idactivity = tbActivity.get_id();
        tbActivityDetailList = mPresenter.getListActivityDetail(idactivity);
        count = tbActivityDetailList.size();

        // get tbactvity detail
        tbActivityDetailList = mPresenter.getListActivityDetail(idactivity);
        title1detailactivity = tbActivityDetailList.get(0).getTitle1().toString();



        after_setup();
    }

    private void setupViews() {


        t1 = (TextView) findViewById(R.id.title1);
        t2 = (TextView) findViewById(R.id.title2);
        text = (TextView) findViewById(R.id.title);
        next = (Button) findViewById(R.id.next);
        p = (ProgressBar) findViewById(R.id.p);
        p.setMax(100);
        img = (NetworkImageView) findViewById(R.id.text);
        linear = (LinearLayout) findViewById(R.id.linear);
        mpt = MediaPlayer.create (this, R.raw.true_sound);
        mpf =  MediaPlayer.create (this, R.raw.false_sound);
    }

    private void after_setup() {

        if (title1activity==null) {

        } else {

            text.setText(title1activity);
        }

        all = mPresenter.countActivity(idlesson);

        // set all activity false in activitynumber = 1
        if (activitynumber == 1 && Act_Status.equals("first")) {
            mPresenter.false_activitys(idlesson);
        }

        // show passed activity
        List<Integer> p1 = mPresenter.activity_true(idlesson);
        int p2 = p1.size();
        if (p2 == 0) {
            p.setProgress(0);
        } else {
            double d_number = (double) p2 / all;
            int i_number = (int) (d_number * 100);
            p.setProgress(i_number);
        }

        t1.setText(R.string.A20_EN);
        t1.setTextColor(getResources().getColor(R.color.my_black));

        int lang_id = mPresenter.getlanguage();
        switch (lang_id) {
            // فارسی
            case 1:
                t2.setText(R.string.A20_FA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // کردی
            case 2:
                t2.setText(R.string.A20_KU);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // ترکی آذری
            case 3:
                t2.setText(R.string.A20_TA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // چینی
            case 4:
                t2.setText(R.string.A20_CH);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
        }

        //get image

        //get image]
        path1 = tbActivity.getPath1();
        getImage(path1);


        next.setOnClickListener(this);


        //.........................

        String temp = tbActivityDetailList.get(0).getTitle2();
        if (temp.equals("null")) {

        } else {
            int have = 0;
            for (int j = 0; j < temp.length(); j++) {
                if (temp.charAt(j) == '_') {
                    have = 1;
                }
            }
            if (have == 1) {
                String z[] = temp.split("_");
                xali = xali + (z.length);
            } else if (have == 0) {
                xali = xali + 1;
            }
        }


        ans = new String[xali];
        int c = 0;

        String temp1 = tbActivityDetailList.get(0).getTitle2();
        if (temp.equals("null")) {

            // set have for some multiple answer

        } else {
            int have = 0;
            for (int j = 0; j < temp1.length(); j++) {
                if (temp1.charAt(j) == '_') {
                    have = 1;
                }
            }
            if (have == 1) {
                String z[] = temp1.split("_");
                for (int j = 0; j < z.length; j++) {
                    ans[c] = z[j];
                    c++;
                }
            } else if (have == 0) {
                ans[c] = temp1;
                c++;
            }
        }

        e = new EditText[xali];
        int id_e = 0;

        // ------------------------------------------------------------------------------------------------------
        //  title1 in activityDetails for matne asli( matn ba jaye khali)

        // ------------------------------------------------------------------------------------------------------
        // splite textview & edittext
        String w = tbActivityDetailList.get(0).getTitle1();

        w = w.replace("…", "...");
        String[] list_w = w.split(Pattern.quote("..."));

        int start = 0;
        int end = 0;

        // only ...
        if (w.equals("...")) {
            start = 1;
        } else {
            String s_s = w.substring(0, 3);
            if (s_s.equals("...")) {
                start = 1;
            }
            String s_e = w.substring(w.length() - 3, w.length());
            if (s_e.equals("...")) {
                end = 1;
            }
        }

        int t_number = 0;  // number of text view
        int id_w = 0;      // for splite first text view and get for space and dont account for id_w

        if (w.equals("...")) {
            t_number = 0;
        } else {
            if (list_w[0].equals("")) {
                id_w = 1;
                t_number = list_w.length - 1;
            } else {
                t_number = list_w.length;
            }
        }

        int e_number = 0;   // number of edittext for count of jaye xali
        if (t_number > 1) {
            e_number = (t_number - 1) + (start) + (end);
        } else {
            e_number = (start) + (end);
        }

        int total = t_number + e_number;   //total number of textview and edittext
        t = new TextView[t_number];
        int id_t = 0;

        // ------------------------------------------------------------------------------------------------------
        // add  textview and edittext to view
        String now = "txt";
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // only have one
        if (total == 1) {

            // only have one edittext (...)
            if (w.equals("...")) {
                e[id_e] = new EditText(this);
                e[id_e].setLayoutParams(params);
                e[id_e].setInputType(InputType.TYPE_CLASS_TEXT);
                e[id_e].setEms(12);
                e[id_e].setTextSize(16);
                e[id_e].setTextColor(getResources().getColor(R.color.blue));
                e[id_e].addTextChangedListener(new A20.CheckEdit());
                linear.addView(e[id_e]);
                id_e++;

            }
            // only have one textview without ...
            else {
                t[id_t] = new TextView(this);
                t[id_t].setLayoutParams(params);
                t[id_t].setText(list_w[0]);
                t[id_t].setTextSize(16);
                linear.addView(t[id_t]);

            }

        }

        // have more than 1
        else {
            for (int xi = 0; xi < total; xi++) {

                final int finalId_e = id_e;

                // start - first element
                if (xi == 0) {

                    // edittext
                    if (start == 1) {
                        e[id_e] = new EditText(this);
                        e[id_e].setLayoutParams(params);
                        e[id_e].setInputType(InputType.TYPE_CLASS_TEXT);
                        e[id_e].setEms(5);
                        e[id_e].setTextSize(16);
                        e[id_e].setTextColor(getResources().getColor(R.color.blue));
                        e[id_e].addTextChangedListener(new A20.CheckEdit());
                        linear.addView(e[id_e]);
                        id_e++;
                    }

                    // textview
                    if (start == 0) {
                        t[id_t] = new TextView(this);
                        t[id_t].setLayoutParams(params);
                        t[id_t].setText(list_w[id_w]);
                        t[id_t].setTextSize(16);
                        linear.addView(t[id_t]);
                        now = "edt";
                        id_w++;
                        id_t++;
                    }
                }

                // other element - start(first) and end(end of string)
                if (xi != 0 && xi != total - 1) {
                    // textview
                    switch (now) {

                        // textview
                        case "txt":
                            t[id_t] = new TextView(this);
                            t[id_t].setLayoutParams(params);
                            t[id_t].setText(list_w[id_w]);
                            t[id_t].setTextSize(16);
                            linear.addView(t[id_t]);
                            now = "edt";
                            id_w++;
                            id_t++;
                            break;

                        // edittext
                        case "edt":
                            e[id_e] = new EditText(this);
                            e[id_e].setLayoutParams(params);
                            e[id_e].setInputType(InputType.TYPE_CLASS_TEXT);
                            e[id_e].setEms(5);
                            e[id_e].setTextSize(16);
                            e[id_e].setTextColor(getResources().getColor(R.color.blue));
                            e[id_e].addTextChangedListener(new A20.CheckEdit());
                            linear.addView(e[id_e]);
                            now = "txt";
                            id_e++;
                            break;
                    }
                }

                // end - end of element
                if (xi == total - 1) {

                    // edittext
                    if (end == 1) {
                        e[id_e] = new EditText(this);
                        e[id_e].setLayoutParams(params);
                        e[id_e].setInputType(InputType.TYPE_CLASS_TEXT);
                        e[id_e].setEms(5);
                        e[id_e].setTextSize(16);
                        e[id_e].setTextColor(getResources().getColor(R.color.blue));
                        e[id_e].addTextChangedListener(new A20.CheckEdit());
                        linear.addView(e[id_e]);
                        id_e++;
                    }

                    // textview
                    if (end == 0) {
                        t[id_t] = new TextView(this);
                        t[id_t].setLayoutParams(params);
                        t[id_t].setText(list_w[id_w]);
                        t[id_t].setTextSize(16);
                        linear.addView(t[id_t]);
                        now = "edt";
                        id_w++;
                        id_t++;
                    }
                }
            }

        }
    }


    //String w = "...te...";
        /*String w = "te...";
        String w = "...te...er";
        String w = "...tor";
        String w = "...si...r";
        String w = "an...chi...";
        String w = "...am a student.";
        String w = "She ... a banker.";*/





    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.next) {

            switch (next.getText().toString()) {

                case "check":

                    fill = 0;
                    for(int i=0 ; i<e.length ; i++){
                        if (e[i].getText().toString().equals("")) {
                            // nothing
                        }else {
                            fill++;
                        }
                    }

                    if( fill == xali ) {

                        String answer = check();
                        if (answer.equals("")) {

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
                            for(int i=0 ; i<e.length ; i++){
                                e[i].setClickable(false);
                                e[i].setFocusable(false);
                            }

                            // play sound
                            mpt.start();

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
                            for(int i=0 ; i<e.length ; i++){
                                e[i].setClickable(false);
                                e[i].setFocusable(false);
                            }
                            p.setClickable(false);


                            // Fragment_false
                            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder2);
                            Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                            linearLayout.setAnimation(slide_down);
                            linearLayout.setVisibility(View.VISIBLE);

                            Fragment_False f2 = new Fragment_False();
                            f2.t.setText(answer);

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
                    else{
                        Toast.makeText(getApplicationContext(), "جای خالی را پر کنید", Toast.LENGTH_LONG).show();
                    }

                    break;

                case "countinue":
                    if( fill == xali ) {

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

                                    A20.this.finish();
                                    startActivity(new Intent(A20.this, End.class));
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
                                A20.this.finish();
                                startActivity(new Intent(A20.this, End.class));

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

    public String check(){

        String result = "";
        boolean final_answer = true;
        boolean answer[] = new boolean[xali];
        int cc = 0;

        for(int i=0 ; i < e.length ; i++){

            // yek javab
            int baxsh = 0;
            for(int j=0 ; j<ans[i].length() ; j++){
                if(ans[i].charAt(j) == ','){
                    baxsh++;
                }
            }

            z = null;
            switch (baxsh){
                // 1 baxsh
                case 0:
                    z = ans[i].split("/");
                    break;

                // 2 baxsh
                case 1:
                    String[] s = ans[i].split(",");
                    String[] x = s[0].split("/");
                    String[] y = s[1].split("/");

                    z = new String[x.length * y.length];
                    int count2 = 0;
                    for (int ii = 0; ii < x.length; i++) {
                        for (int jj = 0; jj < y.length; jj++) {
                            z[count2] = x[ii] + " " + y[jj];
                            count2++;
                        }
                    }
                    break;

                // 3 baxsh
                case 2:
                    String[] a = ans[i].split(",");
                    String[] b = a[0].split("/");
                    String[] c = a[1].split("/");
                    String[] d = a[2].split("/");

                    z = new String[b.length * c.length* d.length];
                    int count3 = 0;
                    for (int ii = 0; ii < b.length ; ii++) {
                        for (int jj = 0; jj < c.length ; jj++) {
                            for(int k = 0 ; k < d.length ; k++){
                                z[count3] = b[ii] + " " + c[jj] + " " + d[k];
                                count3++;
                            }
                        }
                    }
                    break;
            }

            // moqayese ba javab
            result = result + " / "+ z[0] ;
            for(int j=0 ; j < z.length ; j++){
                String a = nice_string1( e[i].getText().toString() );
                String b = nice_string1( z[j].toString() );
                if(a.equals(b)){
                    answer[cc] = true;
                    cc++;
                }
            }
        }

        for(int x=0 ; x<answer.length ; x++){
            if(answer[x]){

            }else{
                final_answer = false;
            }
        }

        if(final_answer){
            return "";
        }else{
            return result;
        }
    }


    class CheckEdit implements TextWatcher {
        public void afterTextChanged(Editable s) {
            try {
                if(s.toString().equals("") && fill == 0){
                    next.setTextColor(Color.GRAY);
                    next.setBackgroundResource(R.drawable.btn_gray);
                }
                else{
                    fill++;
                    next.setTextColor(Color.WHITE);
                    next.setBackgroundResource(R.drawable.btn_green);
                }
            }
            catch(NumberFormatException nfe){}
        }
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
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
                .getA20Component(new Main_Module(this))
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
        super.onBackPressed();
        back();
    }

    public void back(){
        A20.this.finish();
        startActivity(new Intent(A20.this, Lesson.class));
    }
}
