package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
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
import javax.inject.Inject;

public class A13 extends BaseActivity
        implements MVP_Main.RequiredViewOps, View.OnClickListener{

    private static final String TAG = A13.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A13.class.getName());

    @Inject
    public MVP_Main.ProvidedPresenterOps mPresenter;
    TextView text,txt4,txt5,txt6,txt7,txt8;
    EditText editText;
    String title1detailactivity, title2detailactivity;
    String temp[];
    int count;
    int back_pressed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.a13);

        // hide keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
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
        // get question
        title1 = tbActivity.getTitle1();
        //get answer
        title2 = tbActivity.getTitle2();
        //get image
        path1 = tbActivity.getPath1();
        //get music
        path2 = tbActivity.getPath2();
        max = mPresenter.max_Activitynumber(idlesson);

        // get tbactvity detail
        tbActivityDetailList = mPresenter.getListActivityDetail(idactivity);
        title1detailactivity = tbActivityDetailList.get(0).getTitle1().toString();
        count = tbActivityDetailList.size();

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
        editText = (EditText) findViewById(R.id.edittxt);
        txt3 = (TextView)findViewById(R.id.txt3);
        txt4 = (TextView) findViewById(R.id.txt4);
        txt5 = (TextView)findViewById(R.id.txt5);
        txt6 = (TextView)findViewById(R.id.txt6);
        txt7 = (TextView)findViewById(R.id.txt7);
        txt8 = (TextView)findViewById(R.id.txt8);
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

        t1.setText(R.string.A13_EN);
        t1.setTextColor(getResources().getColor(R.color.my_black));

        //Choising Language
        int lang_id = mPresenter.getlanguage();
        switch (lang_id) {
            // فارسی
            case 1:
                t2.setText(R.string.A13_FA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // کردی
            case 2:
                t2.setText(R.string.A13_KU);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // ترکی آذری
            case 3:
                t2.setText(R.string.A13_TA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // چینی
            case 4:
                t2.setText(R.string.A13_CH);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
        }

        //get image
        String img_url = url_download+path1;
        Glide.with(this).load(img_url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .placeholder(R.drawable.ph).error(R.drawable.e).into(img);

        // set text for textbox
        temp=getTextView(title1);
        txt1.setText(temp[0]);
        SetTextForTextViews();

        //set OnClickListener
        editText.addTextChangedListener(new CheckEdit());
        next.setOnClickListener(this);

    }

    public  void  SetTextForTextViews(){
        TextView textArray[]=new TextView[count];
        for(int i=0;i<count;i++){
            textArray[i]=  new TextView(this);
            textArray[i].setText(tbActivityDetailList.get(i).getTitle1().toString());
        }
        switch (count) {
            case 0:
            case 1:
                txt3.setVisibility(View.VISIBLE);
                txt3.setText(textArray[0].getText().toString());
                break;
            case 2:
                txt3.setVisibility(View.VISIBLE);
                txt4.setVisibility(View.VISIBLE);
                txt3.setText(textArray[0].getText().toString());
                txt4.setText(textArray[1].getText().toString());
                break;
            case 3:
                txt3.setVisibility(View.VISIBLE);
                txt4.setVisibility(View.VISIBLE);
                txt5.setVisibility(View.VISIBLE);
                txt3.setText(textArray[0].getText().toString());
                txt4.setText(textArray[1].getText().toString());
                txt5.setText(textArray[2].getText().toString());
                break;
            case 4:
                txt3.setVisibility(View.VISIBLE);
                txt4.setVisibility(View.VISIBLE);
                txt5.setVisibility(View.VISIBLE);
                txt6.setVisibility(View.VISIBLE);
                txt3.setText(textArray[0].getText().toString());
                txt4.setText(textArray[1].getText().toString());
                txt5.setText(textArray[2].getText().toString());
                txt6.setText(textArray[3].getText().toString());
                break;
            case 5:
                txt3.setVisibility(View.VISIBLE);
                txt4.setVisibility(View.VISIBLE);
                txt5.setVisibility(View.VISIBLE);
                txt6.setVisibility(View.VISIBLE);
                txt7.setVisibility(View.VISIBLE);
                txt3.setText(textArray[0].getText().toString());
                txt4.setText(textArray[1].getText().toString());
                txt5.setText(textArray[2].getText().toString());
                txt6.setText(textArray[3].getText().toString());
                txt7.setText(textArray[4].getText().toString());
                break;
            case 6:
                txt3.setVisibility(View.VISIBLE);
                txt4.setVisibility(View.VISIBLE);
                txt5.setVisibility(View.VISIBLE);
                txt6.setVisibility(View.VISIBLE);
                txt7.setVisibility(View.VISIBLE);
                txt8.setVisibility(View.VISIBLE);
                txt3.setText(textArray[0].getText().toString());
                txt4.setText(textArray[1].getText().toString());
                txt5.setText(textArray[2].getText().toString());
                txt6.setText(textArray[3].getText().toString());
                txt7.setText(textArray[4].getText().toString());
                txt8.setText(textArray[5].getText().toString());
                break;
        }
    }
    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.next){
            switch (next.getText().toString()) {
                case "check":
                    boolean answer = cheak(title2,editText.getText().toString());
                    if (answer) {

                        //play sound
                        if(haveNetworkConnection()){
                            MediaPlayer mediaPlayer = new MediaPlayer();
                            try {
                                mediaPlayer.setDataSource(url_download+path2);
                                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                mediaPlayer.prepare();
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Error_Media", Toast.LENGTH_LONG).show();
                            }
                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                public void onPrepared(MediaPlayer mp) {
                                    try{
                                        if(!(mp.isPlaying())){
                                            mp.start();
                                        }
                                    }catch (Exception e){
                                        Toast.makeText(getApplicationContext(), "Error_Play", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                        }

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
                        txt1.setClickable(false);
                        editText.setClickable(false);
                        txt3.setClickable(false);
                        txt4.setClickable(false);
                        txt5.setClickable(false);
                        txt6.setClickable(false);
                        txt7.setClickable(false);
                        txt8.setClickable(false);

                        // Fragment_true
                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder1);
                        Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                        linearLayout.setAnimation(slide_down);
                        linearLayout.setVisibility(View.VISIBLE);

                        Fragment_True f1 = new Fragment_True();
                        f1.txt_true.setText(title2);
                        FragmentManager fragMan = getSupportFragmentManager();
                        FragmentTransaction fragTransaction = fragMan.beginTransaction();
                        fragTransaction.add(R.id.fragment1, f1);
                        fragTransaction.commit();

                    }
                    else {

                        // Clickable_false
                        p.setClickable(false);
                        t1.setClickable(false);
                        t2.setClickable(false);
                        img.setClickable(false);
                        txt1.setClickable(false);
                        editText.setClickable(false);
                        txt3.setClickable(false);
                        txt4.setClickable(false);
                        txt5.setClickable(false);
                        txt6.setClickable(false);
                        txt7.setClickable(false);
                        txt8.setClickable(false);

                        // Fragment_false
                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder2);
                        Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                        linearLayout.setAnimation(slide_down);
                        linearLayout.setVisibility(View.VISIBLE);

                        Fragment_False f2 = new Fragment_False();
                        f2.txt_false.setText(title2);
                        FragmentManager fragMan = getSupportFragmentManager();
                        FragmentTransaction fragTransaction = fragMan.beginTransaction();
                        fragTransaction.add(R.id.fragment2, f2);
                        fragTransaction.commit();
                    }

                    next.setTextColor(Color.WHITE);
                    next.setBackgroundResource(R.drawable.btn_green);
                    next.setText("countinue");

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
                                A13.this.finish();
                                closeOtherMediaPlayer();
                                startActivity(new Intent(A13.this, End.class));
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
                            A13.this.finish();
                            closeOtherMediaPlayer();
                            startActivity(new Intent(A13.this, End.class));

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
                    if(str.charAt(j+1) == '.'){
                        break;
                    }
                } else {
                    part[0] += str.charAt(j);
                }
            }
            part[1] = "................";
        }
        part[0]=part[0].trim();
        return part;
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

    //Cheaking the userAnswer with the CorrectAnswer
    public boolean cheak(String correctAnswer, String userAnswer){
        boolean flag=false;
        if (correctAnswer.equals("null")) {
        }else {
            if (nice_string2(userAnswer).equals(nice_string2(correctAnswer)))
                flag = true;
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
                .getA13Component(new Main_Module(this))
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
            A13.this.finish();
            closeOtherMediaPlayer();
            startActivity(new Intent(A13.this, Lesson.class));
        }
    }

    public void update_server(int idL) {
        try {
            String UserName = mPresenter.get_UserName();
            new Post_UpdateUser(getApplicationContext(), A13.this, haveNetworkConnection(), UserName, idL).post();
        } catch (JSONException e) {}
    }
}