package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.Main_Module;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Main_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.view.BaseActivity;
import com.tiptap.tda_user.tiptap.main.activity.view.lesson.Lesson;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;
import javax.inject.Inject;

public class A47 extends BaseActivity
        implements MVP_Main.RequiredViewOps,View.OnClickListener, View.OnTouchListener {

    private static final String TAG = A47.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A47.class.getName());

    @Inject
    public MVP_Main.ProvidedPresenterOps mPresenter;
    ArrayList<String> you_say = new ArrayList<>();
    ArrayList<String> you_say1 = new ArrayList<>();
    TextView text,txt4,txt5,txt6,txt7;
    Button voice,voice1;
    String answer, title1detailactivity, title2detailactivity,answer1,answer2;
    String temp[];
    int count;
    String for_false="";
    String first;
    int back_pressed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a47);

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
        title2detailactivity = tbActivityDetailList.get(1).getTitle1().toString();
        answer1 = tbActivityDetailList.get(0).getTitle2().toString();
        answer2 = tbActivityDetailList.get(1).getTitle2().toString();
        count = mPresenter.count_ActivityDetail(idactivity);

        setupViews();
        after_setup();
    }

    private void setupViews() {
        p = (ProgressBar)findViewById(R.id.p);
        p.setMax(100);
        t1 = (TextView)findViewById(R.id.title1);
        t2 = (TextView)findViewById(R.id.title2);
        img = (ImageView) findViewById(R.id.img);
        voice=(Button)findViewById(R.id.voice);
        txt1 = (TextView)findViewById(R.id.txt1);
        txt2 = (TextView)findViewById(R.id.txt2);
        voice1=(Button)findViewById(R.id.voice1);
        txt3 = (TextView)findViewById(R.id.txt3);
        txt4 = (TextView)findViewById(R.id.txt4);
        txt5 = (TextView)findViewById(R.id.txt5);
        txt6 = (TextView)findViewById(R.id.txt6);
        txt7 = (TextView)findViewById(R.id.txt7);
       // txt8 = (TextView)findViewById(R.id.txt8);
        next = (Button)findViewById(R.id.next);
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

        t1.setText(R.string.A47_EN);
        t1.setTextColor(getResources().getColor(R.color.my_black));

        //Choosing Language
        int lang_id = mPresenter.getlanguage();
        switch (lang_id) {
            // فارسی
            case 1:
                t2.setText(R.string.A47_FA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // کردی
            case 2:
                t2.setText(R.string.A47_KU);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // ترکی آذری
            case 3:
                t2.setText(R.string.A47_TA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // چینی
            case 4:
                t2.setText(R.string.A47_CH);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
        }

        String img_url = url_download+path1;
        Glide.with(this).load(img_url).placeholder(R.drawable.ph).error(R.drawable.e).into(img);

        // set text for textbox
        temp=getTextView(title1detailactivity);
        txt1.setText(temp[0]);  first = nice_string1(temp[0]);
        txt2.setText(temp[1]);

        temp=getTextView(title2detailactivity);
        txt3.setText(temp[0]);
        txt4.setText(temp[1]);

        temp=getTextForTextViews(title1);
        txt5.setText(temp[0]);
        txt6.setText(temp[1]);
        txt7.setText(temp[2]);
        //txt8.setText(temp[3]);
        //set OnClickListener
        voice.setOnClickListener(this);
        voice1.setOnClickListener(this);
        next.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.voice) {
            if (haveNetworkConnection()) {
                promptSpeechInput(101);

            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }

        } if (view.getId() == R.id.voice1) {
            if (haveNetworkConnection()) {
                promptSpeechInput(102);

            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }

        }
        if(view.getId() == R.id.next){
            switch (next.getText().toString()) {
                case "check":
                    if( (you_say.size()>=1)&&(you_say1.size()>=1) ) {

                        boolean one = cheak(answer1,txt2.getText().toString());
                        boolean two = cheak(answer2,txt4.getText().toString());

                        boolean one_with = cheak_with(answer1,txt2.getText().toString());
                        boolean two_with = cheak_with(answer2,txt4.getText().toString());

                        int count_true = 0;
                        if(one){count_true++;}
                        if(two){count_true++;}
                        if(one_with){count_true++;}
                        if(two_with){count_true++;}

                        if (count_true == 2) {

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
                            p.setClickable(false);
                            t1.setClickable(false);
                            t2.setClickable(false);
                            img.setClickable(false);
                            voice.setClickable(false);
                            txt1.setClickable(false);
                            txt2.setClickable(false);
                            voice1.setClickable(false);
                            txt3.setClickable(false);
                            txt4.setClickable(false);
                            txt5.setClickable(false);
                            txt6.setClickable(false);
                            txt7.setClickable(false);
                            //txt8.setClickable(false);

                            // Fragment_true
                            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder1);
                            Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                            linearLayout.setAnimation(slide_down);
                            linearLayout.setVisibility(View.VISIBLE);

                            Fragment_True f1 = new Fragment_True();
                            f1.txt_true.setText(for_false);
                            FragmentManager fragMan = getSupportFragmentManager();
                            FragmentTransaction fragTransaction = fragMan.beginTransaction();
                            fragTransaction.add(R.id.fragment1, f1);
                            fragTransaction.commit();

                        }else {
                            // Clickable_false
                            p.setClickable(false);
                            t1.setClickable(false);
                            t2.setClickable(false);
                            img.setClickable(false);
                            voice.setClickable(false);
                            txt1.setClickable(false);
                            txt2.setClickable(false);
                            voice1.setClickable(false);
                            txt3.setClickable(false);
                            txt4.setClickable(false);
                            txt5.setClickable(false);
                            txt6.setClickable(false);
                            txt7.setClickable(false);
                            //txt8.setClickable(false);

                            // Fragment_false
                            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder2);
                            Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                            linearLayout.setAnimation(slide_down);
                            linearLayout.setVisibility(View.VISIBLE);

                            Fragment_False f2 = new Fragment_False();
                            f2.txt_false.setText(for_false);
                            FragmentManager fragMan = getSupportFragmentManager();
                            FragmentTransaction fragTransaction = fragMan.beginTransaction();
                            fragTransaction.add(R.id.fragment2, f2);
                            fragTransaction.commit();
                        }

                        next.setTextColor(Color.WHITE);
                        next.setBackgroundResource(R.drawable.btn_green);
                        next.setText("countinue");

                    }else {
                        Toast.makeText(getApplicationContext(),"جاهای خالی را پر کنید",Toast.LENGTH_LONG).show();
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
                                A47.this.finish();
                                startActivity(new Intent(A47.this, End.class));
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
                            A47.this.finish();
                            startActivity(new Intent(A47.this, End.class));

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
    // get text from data base to show in textviews
    public String[] getTextView(String str){

        String[] part=new String[2];
        part[0]=" ";
        if (str.equals("null")) {

        }else if (str.contains("...")) {

            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '.') {
                    break;
                } else {
              if(str.charAt(j)==' '){

              }else
                    part[0] += str.charAt(j);
                }
            }
            part[1] = "................";
        }
   //     part[0]=nice_string1(part[0]);
        part[0]=part[0].trim();
        return  part;
    }
    public String[] getTextForTextViews(String str){
        String part[]=null;
        if (str.equals("null")) {

        }else {
            int have = 0;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '/') {
                    have = 1;
                }
            }
            if (have == 1) {
                 part = str.split(Pattern.quote("/"));

            }
        }
        return  part;
    }
    //Cheaking the userAnswer with the CorrectAnswer
    public boolean cheak(String correctAnswer, String userAnswer){

        String x[] = correctAnswer.split("/");
        for_false = for_false + " / " + (x[0]);

        String _userAnswer = nice_string2( userAnswer );
        String _correctAnswer = nice_string2( correctAnswer );

        boolean flag=false;
        if (_correctAnswer.equals("null")) {

        }else {
            int have = 0;
            for (int j = 0; j < _correctAnswer.length(); j++) {
                if (_correctAnswer.charAt(j) == '/') {
                    have = 1;
                }
            }
            if (have == 1) {
                String part[] = _correctAnswer.split(Pattern.quote("/"));
                for (int i = 0; i < part.length; i++) {
                    if (_userAnswer.equals(part[i]))
                        flag = true;
                }
            } else {
                if (_userAnswer.equals(_correctAnswer))
                    flag = true;
            }
        }
        return  flag;
    }

    public boolean cheak_with(String correctAnswer, String userAnswer){

        String _userAnswer = nice_string2( userAnswer );
        String _correctAnswer = nice_string2( correctAnswer );

        boolean flag=false;
        if (_correctAnswer.equals("null")) {

        }else {
            int have = 0;
            for (int j = 0; j < _correctAnswer.length(); j++) {
                if (_correctAnswer.charAt(j) == '/') {
                    have = 1;
                }
            }
            if (have == 1) {
                String part[] = _correctAnswer.split(Pattern.quote("/"));
                for (int i = 0; i < part.length; i++) {
                    if (_userAnswer.equals(first+part[i]))
                        flag = true;
                }
            } else {
                if (_userAnswer.equals(first+_correctAnswer))
                    flag = true;
            }
        }

        return  flag;
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
                .getA47Component(new Main_Module(this))
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

    private void promptSpeechInput(int code) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, code);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 101: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    you_say = result;
                    int have_result = 0;
                    for(int z=0 ; z<you_say.size() ; z++){
                        String a1 = nice_string1(you_say.get(z));
                        String a2 = nice_string1(answer1);
                        if(a1.equals(a2)){
                            have_result = 1;
                            txt2.setTextColor(getResources().getColor(R.color.red));
                            txt2.setText(you_say.get(z));
                        }
                    }
                    if(have_result == 0){
                        txt2.setTextColor(getResources().getColor(R.color.red));
                        txt2.setText(you_say.get(0));
                    }

                    next.setTextColor(Color.WHITE);
                    next.setBackgroundResource(R.drawable.btn_green);
                }
                break;
            }

            case 102: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    you_say1 = result;
                    int have_result = 0;
                    for(int z=0 ; z<you_say1.size() ; z++){
                        String a1 = nice_string1(you_say1.get(z));
                        String a2 = nice_string1(answer2);
                        if(a1.equals(a2)){
                            have_result = 1;
                            txt4.setTextColor(getResources().getColor(R.color.red));
                            txt4.setText(you_say1.get(z));
                        }
                    }
                    if(have_result == 0){
                        txt4.setTextColor(getResources().getColor(R.color.red));
                        txt4.setText(you_say1.get(0));
                    }

                    next.setTextColor(Color.WHITE);
                    next.setBackgroundResource(R.drawable.btn_green);
                }
                break;
            }
        }

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
            A47.this.finish();
            startActivity(new Intent(A47.this, Lesson.class));
        }
    }
}