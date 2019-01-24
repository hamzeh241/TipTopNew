package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
import java.util.regex.Pattern;
import javax.inject.Inject;

public class A25 extends BaseActivity
        implements MVP_Main.RequiredViewOps,View.OnClickListener, View.OnTouchListener {

    private static final String TAG = A25.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A25.class.getName());

    @Inject
    public MVP_Main.ProvidedPresenterOps mPresenter;
    String true_txt="";
    TextView text;
    String answer, title1detailactivity;
    int count;
    int back_pressed = 0;
    TextView t[];
    EditText e[];
    int xali = 0;
    String ans[];
    LinearLayout l1;
    String for_frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.a25);

        setupMVP();

        // first
        if (Act_Status.equals("first")) {
            tbActivity = mPresenter.getActivity(idlesson, activitynumber);
        }
        // second
        if (Act_Status.equals("second")) {
            tbActivity = mPresenter.getActivity2(idactivity);
        }

        // get tbactivity
        idactivity = tbActivity.get_id();
        title1 = tbActivity.getTitle1();
        path1 = tbActivity.getPath1();
        max = mPresenter.max_Activitynumber(idlesson);

        // get tbactvity detail
        tbActivityDetailList = mPresenter.getListActivityDetail(idactivity);
        title1detailactivity = tbActivityDetailList.get(0).getTitle1().toString();
        count = mPresenter.count_ActivityDetail(idactivity);

        true_txt =  tbActivityDetailList.get(0).getTitle2().toString();

        setupViews();
        after_setup();
    }

    private void setupViews() {
        p = (ProgressBar)findViewById(R.id.p);
        p.setMax(100);
        t1 = (TextView)findViewById(R.id.title1);
        t2 = (TextView)findViewById(R.id.title2);
        img = (ImageView) findViewById(R.id.img);
        txt1 = (TextView)findViewById(R.id.txt1);
        next = (Button)findViewById(R.id.next);
        l1 = (LinearLayout)findViewById(R.id.l1);
    }
    private void after_setup() {

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

        t1.setText(R.string.A25_EN);
        t1.setTextColor(getResources().getColor(R.color.my_black));

        //Choising Language
        int lang_id = mPresenter.getlanguage();
        switch (lang_id) {
            // فارسی
            case 1:
                t2.setText(R.string.A25_FA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // کردی
            case 2:
                t2.setText(R.string.A25_KU);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // ترکی آذری
            case 3:
                t2.setText(R.string.A25_TA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // چینی
            case 4:
                t2.setText(R.string.A25_CH);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
        }

        String img_url = url_download+path1;
        Glide.with(this).load(img_url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .placeholder(R.drawable.ph).error(R.drawable.e).into(img);

        txt1.setText(title1);
        next.setOnClickListener(this);

         /* ------------------------------------------------------------------------------------------------------ */
        // each row - title2

        for(int i=0 ; i<count ; i++) {
            String temp = tbActivityDetailList.get(i).getTitle2();
            if (temp.equals("null")) {

            }else{
                int have = 0;
                for(int j=0 ; j<temp.length() ; j++){
                    if(temp.charAt(j) == '_'){
                        have = 1;
                    }
                }
                if(have == 1){
                    String z[] = temp.split("_");
                    xali = xali + (z.length);
                }
                else if(have == 0){
                    xali = xali + 1;
                }
            }
        }

        ans = new String[xali];
        int c = 0;
        for(int i=0 ; i<count ; i++) {
            String temp = tbActivityDetailList.get(i).getTitle2();
            if (temp.equals("null")) {

            }else{
                int have = 0;
                for(int j=0 ; j<temp.length() ; j++){
                    if(temp.charAt(j) == '_'){
                        have = 1;
                    }
                }
                if(have == 1){
                    String z[] = temp.split("_");
                    for(int j=0 ; j<z.length ; j++){
                        ans[c] = z[j];
                        c++;
                    }
                }
                else if(have == 0){
                    ans[c] = temp;
                    c++;
                }
            }
        }

        e = new EditText[xali];
        int id_e = 0;

        // ------------------------------------------------------------------------------------------------------
        // each row - title1
        for(int i=0 ; i<count ; i++) {

            // ------------------------------------------------------------------------------------------------------
            // splite textview & edittext

            String w = tbActivityDetailList.get(i).getTitle1();
            w = w.replace("…", "...");
            String[] list_w = w.split(Pattern.quote("..."));

            int start = 0;
            int end = 0;

            // only ...
            if(w.equals("...")){
                start = 1;
            }else{
                String s_s = w.substring(0, 3);
                if (s_s.equals("...")) {
                    start = 1;
                }
                String s_e = w.substring(w.length() - 3, w.length());
                if (s_e.equals("...")) {
                    end = 1;
                }
            }

            int t_number = 0;
            int id_w = 0;

            if(w.equals("...")){
                t_number = 0;
            }else{
                if (list_w[0].equals("")) {
                    id_w = 1;
                    t_number = list_w.length - 1;
                } else {
                    t_number = list_w.length;
                }
            }

            int e_number = 0;
            if (t_number > 1) {
                e_number = (t_number - 1) + (start) + (end);
            } else {
                e_number = (start) + (end);
            }

            int total = t_number + e_number;
            t = new TextView[t_number];
            int id_t = 0;

            // ------------------------------------------------------------------------------------------------------
            // add to view
            String now = "txt";
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            if(total == 1){
                // only ...
                if(w.equals("...")) {
                    e[id_e] = new EditText(this);
                    e[id_e].setLayoutParams(params);
                    //e[id_e].setInputType(InputType.TYPE_CLASS_TEXT);
                    e[id_e].setEms(12);
                    e[id_e].setTextSize(16);
                    e[id_e].setTextColor(getResources().getColor(R.color.blue));
                    e[id_e].addTextChangedListener(new A25.CheckEdit());
                    l1.addView(e[id_e]);
                    id_e++;
                }
                // only text without ...
                else{
                    t[id_t] = new TextView(this);
                    t[id_t].setLayoutParams(params);
                    t[id_t].setText(list_w[0]);
                    t[id_t].setTextSize(16);
                    l1.addView(t[id_t]);
                }

            }else{
                for(int xi=0 ; xi<total ; xi++){

                    final int finalId_e = id_e;

                    // start
                    if(xi == 0){
                        if(start == 1){
                            e[id_e] = new EditText(this);
                            e[id_e].setLayoutParams(params);
                            //e[id_e].setInputType(InputType.TYPE_CLASS_TEXT);
                            e[id_e].setEms(5);
                            e[id_e].setTextSize(16);
                            e[id_e].setTextColor(getResources().getColor(R.color.blue));
                            e[id_e].addTextChangedListener(new A25.CheckEdit());
                            l1.addView(e[id_e]);
                            id_e++;
                        }

                        if(start == 0){
                            t[id_t] = new TextView(this);
                            t[id_t].setLayoutParams(params);
                            t[id_t].setText(list_w[id_w]);
                            t[id_t].setTextSize(16);
                            l1.addView(t[id_t]);
                            now = "edt";
                            id_w++;
                            id_t++;
                        }
                    }

                    if(xi!=0 && xi!=total-1){
                        // textview
                        switch (now) {
                            case "txt":
                                t[id_t] = new TextView(this);
                                t[id_t].setLayoutParams(params);
                                t[id_t].setText(list_w[id_w]);
                                t[id_t].setTextSize(16);
                                l1.addView(t[id_t]);
                                now = "edt";
                                id_w++;
                                id_t++;
                                break;

                            case "edt":
                                e[id_e] = new EditText(this);
                                e[id_e].setLayoutParams(params);
                                //e[id_e].setInputType(InputType.TYPE_CLASS_TEXT);
                                e[id_e].setEms(5);
                                e[id_e].setTextSize(16);
                                e[id_e].setTextColor(getResources().getColor(R.color.blue));
                                e[id_e].addTextChangedListener(new A25.CheckEdit());
                                l1.addView(e[id_e]);
                                now = "txt";
                                id_e++;
                                break;
                        }
                    }

                    // end
                    if(xi == total-1){
                        if(end == 1){
                            e[id_e] = new EditText(this);
                            e[id_e].setLayoutParams(params);
                            //e[id_e].setInputType(InputType.TYPE_CLASS_TEXT);
                            e[id_e].setEms(5);
                            e[id_e].setTextSize(16);
                            e[id_e].setTextColor(getResources().getColor(R.color.blue));
                            e[id_e].addTextChangedListener(new A25.CheckEdit());
                            l1.addView(e[id_e]);
                            id_e++;
                        }

                        if(end == 0){
                            t[id_t] = new TextView(this);
                            t[id_t].setLayoutParams(params);
                            t[id_t].setText(list_w[id_w]);
                            t[id_t].setTextSize(16);
                            l1.addView(t[id_t]);
                            now = "edt";
                            id_w++;
                            id_t++;
                        }
                    }
                }
            }
        }
    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.next){
            switch (next.getText().toString()) {
                case "check":

                    int fill = 0;
                    for(int i=0 ; i<e.length ; i++){
                        if(e[i].getText().toString().equals("")){

                        }else{
                            fill++;
                        }
                    }
                    if( fill == e.length ) {

                        boolean final_answer = cheak();

                        if (final_answer) {
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
                            txt1.setClickable(false);
                            img.setClickable(false);
                            p.setClickable(false);

                            // Fragment_true
                            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder1);
                            Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                            linearLayout.setAnimation(slide_down);
                            linearLayout.setVisibility(View.VISIBLE);

                            Fragment_True f1 = new Fragment_True();
                            f1.txt_true.setText(for_frag);
                            FragmentManager fragMan = getSupportFragmentManager();
                            FragmentTransaction fragTransaction = fragMan.beginTransaction();
                            fragTransaction.add(R.id.fragment1, f1);
                            fragTransaction.commit();


                        } else {

                            // Clickable_false
                            t1.setClickable(false);
                            t2.setClickable(false);
                            txt1.setClickable(false);
                            img.setClickable(false);
                            p.setClickable(false);

                            // Fragment_false
                            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder2);
                            Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                            linearLayout.setAnimation(slide_down);
                            linearLayout.setVisibility(View.VISIBLE);

                            Fragment_False f2 = new Fragment_False();
                            f2.txt_false.setText(for_frag);
                            FragmentManager fragMan = getSupportFragmentManager();
                            FragmentTransaction fragTransaction = fragMan.beginTransaction();
                            fragTransaction.add(R.id.fragment2, f2);
                            fragTransaction.commit();
                        }

                        next.setTextColor(Color.WHITE);
                        next.setBackgroundResource(R.drawable.btn_green);
                        next.setText("countinue");
                    }

                    break;

                case "countinue":

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
                                A25.this.finish();
                                closeOtherMediaPlayer();
                                startActivity(new Intent(A25.this, End.class));
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
                            A25.this.finish();
                            closeOtherMediaPlayer();
                            startActivity(new Intent(A25.this, End.class));

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

                    break;
            }
        }
    }
    public boolean cheak(){
        boolean result = true;
        boolean flag[] = new boolean[e.length];

        for(int x=0 ; x<e.length ; x++){

            String userAnswer = nice_string1( e[x].getText().toString() );
            String true_ans = true_txt;

            if (true_ans.equals("null")) {

            }else {
                int have = 0;
                for (int j = 0; j < true_ans.length(); j++) {
                    if (true_ans.charAt(j) == '/') {
                        have = 1;
                    }
                }
                if (have == 1) {
                    String part[] = true_ans.split(Pattern.quote("/"));
                    for_frag = part[0];
                    for (int i = 0; i < part.length; i++) {
                        if (userAnswer.equals(nice_string1(part[i])))
                            flag[x] = true;
                    }
                } else {
                    for_frag = true_ans;
                    if (userAnswer.equals(nice_string1(true_ans)))
                        flag[x] = true;
                }
            }
        }

        for(int z=0 ; z<flag.length ; z++){
            if(flag[z]){

            }else{
                result = false;
            }
        }

        return result;
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
                .getA25Component(new Main_Module(this))
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

    class CheckEdit implements TextWatcher {
        public void afterTextChanged(Editable s) {
            try {
                if(s.toString().equals("")){
                    next.setTextColor(Color.GRAY);
                    next.setBackgroundResource(R.drawable.btn_gray);
                }
                else{
                    next.setTextColor(Color.WHITE);
                    next.setBackgroundResource(R.drawable.btn_green);
                }
            }
            catch(NumberFormatException nfe){}
        }
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
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
            A25.this.finish();
            closeOtherMediaPlayer();
            startActivity(new Intent(A25.this, Lesson.class));
        }
    }

    public void update_server(int idL) {
        try {
            String UserName = mPresenter.get_UserName();
            new Post_UpdateUser(getApplicationContext(), A25.this, haveNetworkConnection(), UserName, idL).post();
        } catch (JSONException e) {}
    }
}