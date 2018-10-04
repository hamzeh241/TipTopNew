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
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.regex.Pattern;
import javax.inject.Inject;

public class A29 extends BaseActivity
        implements MVP_Main.RequiredViewOps,View.OnClickListener {

    private static final String TAG = A29.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A29.class.getName());

    @Inject
    public MVP_Main.ProvidedPresenterOps mPresenter;

    LinearLayout l;
    TextView t[],text;
    EditText e[];
    String ans[];
    String z[];
    int xali = 0;
    int fill=0, count=0;
    String txt, for_frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.a29);

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
        txt = tbActivity.getTitle1();

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
        text =(TextView)findViewById(R.id.title);
        l = (LinearLayout)findViewById(R.id.l);
        mpt = MediaPlayer.create (this, R.raw.true_sound);
        mpf =  MediaPlayer.create (this, R.raw.false_sound);
    }

    private void after_setup() {

        text.setText(txt);

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

        t1.setText(R.string.A29_EN);
        t1.setTextColor(getResources().getColor(R.color.my_black));

        int lang_id = mPresenter.getlanguage();
        switch (lang_id){
            // فارسی
            case 1:
                t2.setText(R.string.A29_FA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // کردی
            case 2:
                t2.setText(R.string.A29_KU);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // ترکی آذری
            case 3:
                t2.setText(R.string.A29_TA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // چینی
            case 4:
                t2.setText(R.string.A29_CH);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
        }

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
                    e[id_e].setInputType(InputType.TYPE_CLASS_TEXT);
                    e[id_e].setEms(12);
                    e[id_e].setTextSize(16);
                    e[id_e].setTextColor(getResources().getColor(R.color.blue));
                    e[id_e].addTextChangedListener(new CheckEdit());
                    l.addView(e[id_e]);
                    id_e++;
                }
                // only text without ...
                else{
                    t[id_t] = new TextView(this);
                    t[id_t].setLayoutParams(params);
                    t[id_t].setText(list_w[0]);
                    t[id_t].setTextSize(16);
                    l.addView(t[id_t]);
                }

            }else{
                for(int xi=0 ; xi<total ; xi++){

                    final int finalId_e = id_e;

                    // start
                    if(xi == 0){
                        if(start == 1){
                            e[id_e] = new EditText(this);
                            e[id_e].setLayoutParams(params);
                            e[id_e].setInputType(InputType.TYPE_CLASS_TEXT);
                            e[id_e].setEms(5);
                            e[id_e].setTextSize(16);
                            e[id_e].setTextColor(getResources().getColor(R.color.blue));
                            e[id_e].addTextChangedListener(new CheckEdit());
                            l.addView(e[id_e]);
                            id_e++;
                        }

                        if(start == 0){
                            t[id_t] = new TextView(this);
                            t[id_t].setLayoutParams(params);
                            t[id_t].setText(list_w[id_w]);
                            t[id_t].setTextSize(16);
                            l.addView(t[id_t]);
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
                                l.addView(t[id_t]);
                                now = "edt";
                                id_w++;
                                id_t++;
                                break;

                            case "edt":
                                e[id_e] = new EditText(this);
                                e[id_e].setLayoutParams(params);
                                e[id_e].setInputType(InputType.TYPE_CLASS_TEXT);
                                e[id_e].setEms(5);
                                e[id_e].setTextSize(16);
                                e[id_e].setTextColor(getResources().getColor(R.color.blue));
                                e[id_e].addTextChangedListener(new CheckEdit());
                                l.addView(e[id_e]);
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
                            e[id_e].setInputType(InputType.TYPE_CLASS_TEXT);
                            e[id_e].setEms(5);
                            e[id_e].setTextSize(16);
                            e[id_e].setTextColor(getResources().getColor(R.color.blue));
                            e[id_e].addTextChangedListener(new CheckEdit());
                            l.addView(e[id_e]);
                            id_e++;
                        }

                        if(end == 0){
                            t[id_t] = new TextView(this);
                            t[id_t].setLayoutParams(params);
                            t[id_t].setText(list_w[id_w]);
                            t[id_t].setTextSize(16);
                            l.addView(t[id_t]);
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

                        String answer = cheak();
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

                            // play sound
                            mpt.start();

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
                            f2.txt_false.setText(answer);
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
                        Toast.makeText(getApplicationContext(), "جاهای خالی را پر کنید", Toast.LENGTH_LONG).show();
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

                                    A29.this.finish();
                                    startActivity(new Intent(A29.this, End.class));
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
                                A29.this.finish();
                                startActivity(new Intent(A29.this, End.class));
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

    public String cheak(){

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
                String a = nice_string2( e[i].getText().toString() );
                String b = nice_string2( z[j].toString() );
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

        for_frag = result;

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
                .getA29Component(new Main_Module(this))
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
        A29.this.finish();
        startActivity(new Intent(A29.this, Lesson.class));
    }

    public void update_server(int idL) {
        try {
            String UserName = mPresenter.get_UserName();
            new Post_UpdateUser(getApplicationContext(), A29.this, haveNetworkConnection(), UserName, idL).post();
        } catch (JSONException e) {}
    }
}