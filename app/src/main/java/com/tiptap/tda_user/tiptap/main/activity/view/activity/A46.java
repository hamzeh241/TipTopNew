package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
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
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.A46_Module;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A46;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A46_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivityDetail;
import com.tiptap.tda_user.tiptap.main.activity.view.lesson.Lesson;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import javax.inject.Inject;

public class A46 extends AppCompatActivity
        implements MVP_A46.RequiredViewOps,OnClickListener {

    private static final String TAG = A46.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A46.class.getName());

    @Inject
    public MVP_A46.ProvidedPresenterOps mPresenter;

    public static int idlesson;
    public static int idfunction;
    public static int activitynumber;
    public static String Act_Status;
    public static int idactivity;
    TbActivity tbActivity;
    int max,now_less;
    List<TbActivityDetail> tbActivityDetailList;
    LinearLayout l[];
    int line = 0;
    TextView t[];
    String title1;
    TextView t1,t2;
    String ad1,ad2,ad3;
    String z1[],z2[],z3[];
    EditText e[];
    Button next;
    ProgressBar p;
    int fill=0, count=0;
    int all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a46);

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
        tbActivityDetailList = mPresenter.getListActivityDetail(idactivity);
        count = tbActivityDetailList.size();
        switch (count){
            case 1:
                ad1 = tbActivityDetailList.get(0).getTitle1();
                break;
            case 2:
                ad1 = tbActivityDetailList.get(0).getTitle1();
                ad2 = tbActivityDetailList.get(1).getTitle1();
                break;
            case 3:
                ad1 = tbActivityDetailList.get(0).getTitle1();
                ad2 = tbActivityDetailList.get(1).getTitle1();
                ad3 = tbActivityDetailList.get(2).getTitle1();
                break;
        }

        after_setup();
    }

    private void setupViews() {

        t1 = (TextView)findViewById(R.id.title1);
        t2 = (TextView)findViewById(R.id.title2);

        next = (Button) findViewById(R.id.next);
        p = (ProgressBar)findViewById(R.id.p);
        p.setMax(100);

        LinearLayout l1= (LinearLayout)findViewById(R.id.l1);
        LinearLayout l2 = (LinearLayout)findViewById(R.id.l2);
        LinearLayout l3 = (LinearLayout)findViewById(R.id.l3);
        LinearLayout l4  = (LinearLayout)findViewById(R.id.l4);
        LinearLayout l5 = (LinearLayout)findViewById(R.id.l5);
        LinearLayout l6 = (LinearLayout)findViewById(R.id.l6);
        LinearLayout l7 = (LinearLayout)findViewById(R.id.l7);
        LinearLayout l8 = (LinearLayout)findViewById(R.id.l8);

        l = new LinearLayout[]{l1, l2, l3, l4, l5, l6, l7, l8};
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

        t1.setText(R.string.A46_EN);
        t1.setTextColor(getResources().getColor(R.color.my_black));

        int lang_id = mPresenter.getlanguage();
        switch (lang_id){
            // فارسی
            case 1:
                t2.setText(R.string.A46_FA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // کردی
            case 2:
                t2.setText(R.string.A46_KU);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // ترکی آذری
            case 3:
                t2.setText(R.string.A46_TA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // چینی
            case 4:
                t2.setText(R.string.A46_CH);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
        }

        next.setOnClickListener(this);

        switch (count){

            // 1 ja xali
            /////////////////////////////////  ad1 (1)  /////////////////////////////
            case 1:

                int baxsh11 = 0;
                for(int i=0 ; i<ad1.length() ; i++){
                    if(ad1.charAt(i) == ','){
                        baxsh11++;
                    }
                }

                switch (baxsh11){
                    // 1 baxsh
                    case 0:
                        z1 = ad1.split("/");
                        break;

                    // 2 baxsh
                    case 1:
                        String[] s = ad1.split(",");
                        String[] x = s[0].split("/");
                        String[] y = s[1].split("/");

                        z1 = new String[x.length * y.length];
                        int count2 = 0;
                        for (int i = 0; i < x.length; i++) {
                            for (int j = 0; j < y.length; j++) {
                                z1[count2] = x[i] + " " + y[j];
                                count2++;
                            }
                        }
                        break;

                    // 3 baxsh
                    case 2:
                        String[] a = ad1.split(",");
                        String[] b = a[0].split("/");
                        String[] c = a[1].split("/");
                        String[] d = a[2].split("/");

                        z1 = new String[b.length * c.length* d.length];
                        int count3 = 0;
                        for (int i = 0; i < b.length ; i++) {
                            for (int j = 0; j < c.length ; j++) {
                                for(int k = 0 ; k < d.length ; k++){
                                    z1[count3] = b[i] + " " + c[j] + " " + d[k];
                                    count3++;
                                }
                            }
                        }
                        break;
                }
                break;

            // 2 ja xali
            /////////////////////////////////  ad1 (2)  /////////////////////////////
            case 2:

                int baxsh21 = 0;
                for(int i=0 ; i<ad1.length() ; i++){
                    if(ad1.charAt(i) == ','){
                        baxsh21++;
                    }
                }

                switch (baxsh21){
                    // 1 baxsh
                    case 0:
                        z1 = ad1.split("/");
                        break;

                    // 2 baxsh
                    case 1:
                        String[] s = ad1.split(",");
                        String[] x = s[0].split("/");
                        String[] y = s[1].split("/");

                        z1 = new String[x.length * y.length];
                        int count2 = 0;
                        for (int i = 0; i < x.length; i++) {
                            for (int j = 0; j < y.length; j++) {
                                z1[count2] = x[i] + " " + y[j];
                                count2++;
                            }
                        }
                        break;

                    // 3 baxsh
                    case 2:
                        String[] a = ad1.split(",");
                        String[] b = a[0].split("/");
                        String[] c = a[1].split("/");
                        String[] d = a[2].split("/");

                        z1 = new String[b.length * c.length* d.length];
                        int count3 = 0;
                        for (int i = 0; i < b.length ; i++) {
                            for (int j = 0; j < c.length ; j++) {
                                for(int k = 0 ; k < d.length ; k++){
                                    z1[count3] = b[i] + " " + c[j] + " " + d[k];
                                    count3++;
                                }
                            }
                        }
                        break;
                }

                /////////////////////////////////  ad2 (2)  /////////////////////////////
                int baxsh22 = 0;
                for(int i=0 ; i<ad2.length() ; i++){
                    if(ad2.charAt(i) == ','){
                        baxsh22++;
                    }
                }

                switch (baxsh22){
                    // 1 baxsh
                    case 0:
                        z2 = ad2.split("/");
                        break;

                    // 2 baxsh
                    case 1:
                        String[] s = ad2.split(",");
                        String[] x = s[0].split("/");
                        String[] y = s[1].split("/");

                        z2 = new String[x.length * y.length];
                        int count2 = 0;
                        for (int i = 0; i < x.length; i++) {
                            for (int j = 0; j < y.length; j++) {
                                z2[count2] = x[i] + " " + y[j];
                                count2++;
                            }
                        }
                        break;

                    // 3 baxsh
                    case 2:
                        String[] a = ad2.split(",");
                        String[] b = a[0].split("/");
                        String[] c = a[1].split("/");
                        String[] d = a[2].split("/");

                        z2 = new String[b.length * c.length* d.length];
                        int count3 = 0;
                        for (int i = 0; i < b.length ; i++) {
                            for (int j = 0; j < c.length ; j++) {
                                for(int k = 0 ; k < d.length ; k++){
                                    z1[count3] = b[i] + " " + c[j] + " " + d[k];
                                    count3++;
                                }
                            }
                        }
                        break;
                }

                break;

            // 3 ja xali
            /////////////////////////////////  ad1 (3)  /////////////////////////////
            case 3:

                int baxsh31 = 0;
                for(int i=0 ; i<ad1.length() ; i++){
                    if(ad1.charAt(i) == ','){
                        baxsh31++;
                    }
                }

                switch (baxsh31){
                    // 1 baxsh
                    case 0:
                        z1 = ad1.split("/");
                        break;

                    // 2 baxsh
                    case 1:
                        String[] s = ad1.split(",");
                        String[] x = s[0].split("/");
                        String[] y = s[1].split("/");

                        z1 = new String[x.length * y.length];
                        int count2 = 0;
                        for (int i = 0; i < x.length; i++) {
                            for (int j = 0; j < y.length; j++) {
                                z1[count2] = x[i] + " " + y[j];
                                count2++;
                            }
                        }
                        break;

                    // 3 baxsh
                    case 2:
                        String[] a = ad1.split(",");
                        String[] b = a[0].split("/");
                        String[] c = a[1].split("/");
                        String[] d = a[2].split("/");

                        z1 = new String[b.length * c.length* d.length];
                        int count3 = 0;
                        for (int i = 0; i < b.length ; i++) {
                            for (int j = 0; j < c.length ; j++) {
                                for(int k = 0 ; k < d.length ; k++){
                                    z1[count3] = b[i] + " " + c[j] + " " + d[k];
                                    count3++;
                                }
                            }
                        }
                        break;
                }

                /////////////////////////////////  ad2 (3)  /////////////////////////////
                int baxsh32 = 0;
                for(int i=0 ; i<ad2.length() ; i++){
                    if(ad2.charAt(i) == ','){
                        baxsh32++;
                    }
                }

                switch (baxsh32){
                    // 1 baxsh
                    case 0:
                        z2 = ad2.split("/");
                        break;

                    // 2 baxsh
                    case 1:
                        String[] s = ad2.split(",");
                        String[] x = s[0].split("/");
                        String[] y = s[1].split("/");

                        z2 = new String[x.length * y.length];
                        int count2 = 0;
                        for (int i = 0; i < x.length; i++) {
                            for (int j = 0; j < y.length; j++) {
                                z1[count2] = x[i] + " " +y[j];
                                count2++;
                            }
                        }
                        break;

                    // 3 baxsh
                    case 2:
                        String[] a = ad2.split(",");
                        String[] b = a[0].split("/");
                        String[] c = a[1].split("/");
                        String[] d = a[2].split("/");

                        z2 = new String[b.length * c.length* d.length];
                        int count3 = 0;
                        for (int i = 0; i < b.length ; i++) {
                            for (int j = 0; j < c.length ; j++) {
                                for(int k = 0 ; k < d.length ; k++){
                                    z2[count3] = b[i] + " " + c[j] + " " + d[k];
                                    count3++;
                                }
                            }
                        }
                        break;
                }

                /////////////////////////////////  ad3 (3) /////////////////////////////
                int baxsh33 = 0;
                for(int i=0 ; i<ad3.length() ; i++){
                    if(ad3.charAt(i) == ','){
                        baxsh33++;
                    }
                }

                switch (baxsh33){
                    // 1 baxsh
                    case 0:
                        z3 = ad3.split("/");
                        break;

                    // 2 baxsh
                    case 1:
                        String[] s = ad3.split(",");
                        String[] x = s[0].split("/");
                        String[] y = s[1].split("/");

                        z3 = new String[x.length * y.length];
                        int count2 = 0;
                        for (int i = 0; i < x.length; i++) {
                            for (int j = 0; j < y.length; j++) {
                                z3[count2] = x[i] + " " + y[j];
                                count2++;
                            }
                        }
                        break;

                    // 3 baxsh
                    case 2:
                        String[] a = ad3.split(",");
                        String[] b = a[0].split("/");
                        String[] c = a[1].split("/");
                        String[] d = a[2].split("/");

                        z3 = new String[b.length * c.length* d.length];
                        int count3 = 0;
                        for (int i = 0; i < b.length ; i++) {
                            for (int j = 0; j < c.length ; j++) {
                                for(int k = 0 ; k < d.length ; k++){
                                    z3[count3] = b[i] + " " +c[j] + " " + d[k];
                                    count3++;
                                }
                            }
                        }
                        break;
                }
                break;
        }

        String w = title1;
        String [] list_w = w.split(Pattern.quote("..."));

        int start = 0;
        int end = 0;
        String s_s = w.substring(0, 3);
        if(s_s.equals("...")){start = 1;}
        String s_e = w.substring(w.length()-3, w.length());
        if(s_e.equals("...")){end = 1;}

        int t_number = 0;
        int id_w = 0;
        if(list_w[0].equals("")){
            id_w = 1;
            t_number = list_w.length-1;
        }else{
            t_number = list_w.length;
        }

        int e_number = 0;
        if(t_number > 1){
            e_number = (t_number-1)+(start)+(end);
        }else{
            e_number = (start)+(end);
        }

        int total = t_number + e_number;

        t = new TextView[t_number];
        int id_t = 0;
        e = new EditText[e_number];
        int id_e = 0;

        String now = "txt";

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        int added = 0;

        for(int i=0 ; i<total ; i++){

            final int finalId_e = id_e;

            // start
            if(i == 0 ){
                if(start == 1){
                    e[id_e] = new EditText(this);
                    e[id_e].setLayoutParams(params);
                    e[id_e].setEms(4);
                    e[id_e].setTextSize(16);
                    e[id_e].addTextChangedListener(new CheckEdit());
                    l[line].addView(e[id_e]);
                    line++;
                    /*added = added + 5;
                    if( 1 <= added && added <= 24 ){
                        l1.addView(e[id_e]);
                    }
                    if( 25 <= added && added <= 48 ){
                        l2.addView(e[id_e]);
                    }
                    if( 49 <= added && added <= 72 ){
                        l3.addView(e[id_e]);
                    }
                    if( 73 <= added && added <= 96 ){
                        l4.addView(e[id_e]);
                    }
                    if( 97 <= added && added <= 120 ){
                        l5.addView(e[id_e]);
                    }
                    if( 121 <= added && added <= 144 ){
                        l6.addView(e[id_e]);
                    }*/
                    id_e++;
                }

                if(start == 0){
                    t[id_t] = new TextView(this);
                    t[id_t].setLayoutParams(params);
                    t[id_t].setText(list_w[id_w]);
                    t[id_t].setTextSize(16);
                    l[line].addView(t[id_t]);
                    line++;
                    /*added = added + list_w[id_w].length();
                    if( 1 <= added && added <= 24 ){
                        l1.addView(t[id_t]);
                    }
                    if( 25 <= added && added <= 48 ){
                        l2.addView(t[id_t]);
                    }
                    if( 49 <= added && added <= 72 ){
                        l3.addView(t[id_t]);
                    }
                    if( 73 <= added && added <= 96 ){
                        l4.addView(t[id_t]);
                    }
                    if( 97 <= added && added <= 120 ){
                        l5.addView(t[id_t]);
                    }
                    if( 121 <= added && added <= 144 ){
                        l6.addView(t[id_t]);
                    }*/
                    now = "edt";
                    id_w++;
                    id_t++;
                }
            }

            if(i!=0 && i!=total-1){
                // textview
                switch (now) {
                    case "txt":
                        t[id_t] = new TextView(this);
                        t[id_t].setLayoutParams(params);
                        t[id_t].setText(list_w[id_w]);
                        t[id_t].setTextSize(16);
                        l[line].addView(t[id_t]);
                        line++;
                        /*added = added + list_w[id_w].length();
                        if( 1 <= added && added <= 24 ){
                            l1.addView(t[id_t]);
                        }
                        if( 25 <= added && added <= 48 ){
                            l2.addView(t[id_t]);
                        }
                        if( 49 <= added && added <= 72 ){
                            l3.addView(t[id_t]);
                        }
                        if( 73 <= added && added <= 96 ){
                            l4.addView(t[id_t]);
                        }
                        if( 97 <= added && added <= 120 ){
                            l5.addView(t[id_t]);
                        }
                        if( 121 <= added && added <= 144 ){
                            l6.addView(t[id_t]);
                        }*/
                        now = "edt";
                        id_w++;
                        id_t++;
                        break;

                    case "edt":
                        e[id_e] = new EditText(this);
                        e[id_e].setLayoutParams(params);
                        e[id_e].setEms(4);
                        e[id_e].setTextSize(16);
                        e[id_e].addTextChangedListener(new CheckEdit());
                        l[line].addView(e[id_e]);
                        line++;
                        /*added = added + 5;
                        if( 1 <= added && added <= 24 ){
                            l1.addView(e[id_e]);
                        }
                        if( 25 <= added && added <= 48 ){
                            l2.addView(e[id_e]);
                        }
                        if( 49 <= added && added <= 72 ){
                            l3.addView(e[id_e]);
                        }
                        if( 73 <= added && added <= 96 ){
                            l4.addView(e[id_e]);
                        }
                        if( 97 <= added && added <= 120 ){
                            l5.addView(e[id_e]);
                        }
                        if( 121 <= added && added <= 144 ){
                            l6.addView(e[id_e]);
                        }*/
                        now = "txt";
                        id_e++;
                        break;
                }
            }

            // end
            if(i == total-1){
                if(end == 1){
                    e[id_e] = new EditText(this);
                    e[id_e].setLayoutParams(params);
                    e[id_e].setEms(4);
                    e[id_e].setTextSize(16);
                    e[id_e].addTextChangedListener(new CheckEdit());
                    l[line].addView(e[id_e]);
                    line++;
                    /*added = added + 5;
                    if( 0 <= added && added <= 24 ){
                        l1.addView(e[id_e]);
                    }
                    if( 25 <= added && added <= 48 ){
                        l2.addView(e[id_e]);
                    }
                    if( 49 <= added && added <= 72 ){
                        l3.addView(e[id_e]);
                    }
                    if( 73 <= added && added <= 96 ){
                        l4.addView(e[id_e]);
                    }
                    if( 97 <= added && added <= 120 ){
                        l5.addView(e[id_e]);
                    }
                    if( 121 <= added && added <= 144 ){
                        l6.addView(e[id_e]);
                    }*/
                    id_e++;
                }

                if(end == 0){
                    t[id_t] = new TextView(this);
                    t[id_t].setLayoutParams(params);
                    t[id_t].setText(list_w[id_w]);
                    t[id_t].setTextSize(16);
                    l[line].addView(t[id_t]);
                    line++;
                    /*added = added + list_w[id_w].length();
                    if( 1 <= added && added <= 24 ){
                        l1.addView(t[id_t]);
                    }
                    if( 25 <= added && added <= 48 ){
                        l2.addView(t[id_t]);
                    }
                    if( 49 <= added && added <= 72 ){
                        l3.addView(t[id_t]);
                    }
                    if( 73 <= added && added <= 96 ){
                        l4.addView(t[id_t]);
                    }
                    if( 97 <= added && added <= 120 ){
                        l5.addView(t[id_t]);
                    }
                    if( 121 <= added && added <= 144 ){
                        l6.addView(t[id_t]);
                    }*/
                    now = "edt";
                    id_w++;
                    id_t++;
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

                    if( fill == count ) {

                        boolean answer = cheak();
                        if (answer) {

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
                            switch (count){
                                case 1:
                                    f2.t.setText(z1[0]);
                                    break;

                                case 2:
                                    f2.t.setText(z1[0]+" / "+z2[0]);
                                    break;

                                case 3 :
                                    f2.t.setText(z1[0]+" / "+z2[0]+" / "+z3[0]);
                                    break;
                            }
                            FragmentManager fragMan = getSupportFragmentManager();
                            FragmentTransaction fragTransaction = fragMan.beginTransaction();
                            fragTransaction.add(R.id.fragment2, f2);
                            fragTransaction.commit();

                        }

                        next.setTextColor(Color.WHITE);
                        next.setBackgroundResource(R.drawable.btn_green);
                        next.setText("countinue");
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "please fill the blankes", Toast.LENGTH_LONG).show();
                    }

                    break;

                case "countinue":
                    if( fill == count ) {

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

                                    A46.this.finish();
                                    startActivity(new Intent(A46.this, End.class));
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
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A3.class));
                                            break;

                                        case 4:
                                            A4.idlesson = idlesson ;
                                            A4.idfunction = idfunction ;
                                            A4.idactivity = id_act;
                                            A4.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A4.class));
                                            break;

                                        case 5:
                                            A5.idlesson = idlesson ;
                                            A5.idfunction = idfunction ;
                                            A5.idactivity = id_act;
                                            A5.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A5.class));
                                            break;

                                        case 6:
                                            A6.idlesson = idlesson ;
                                            A6.idfunction = idfunction ;
                                            A6.idactivity = id_act;
                                            A6.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A6.class));
                                            break;

                                        case 7:
                                            A7.idlesson = idlesson ;
                                            A7.idfunction = idfunction ;
                                            A7.idactivity = id_act;
                                            A7.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A7.class));
                                            break;

                                        case 8:
                                            A8.idlesson = idlesson ;
                                            A8.idfunction = idfunction ;
                                            A8.idactivity = id_act;
                                            A8.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A8.class));
                                            break;

                                        case 9:
                                            A9.idlesson = idlesson ;
                                            A9.idfunction = idfunction ;
                                            A9.idactivity = id_act;
                                            A9.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A9.class));
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
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A15.class));
                                            break;

                                        case 16: break;
                                        case 17: break;

                                        case 18:
                                            A18.idlesson = idlesson ;
                                            A18.idfunction = idfunction ;
                                            A18.idactivity = id_act;
                                            A18.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A18.class));
                                            break;

                                        case 19:
                                            A19.idlesson = idlesson ;
                                            A19.idfunction = idfunction ;
                                            A19.idactivity = id_act;
                                            A19.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A19.class));
                                            break;

                                        case 20:
                                            A20.idlesson = idlesson ;
                                            A20.idfunction = idfunction ;
                                            A20.idactivity = id_act;
                                            A20.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A20.class));
                                            break;

                                        case 21: break;

                                        case 22:
                                            A22.idlesson = idlesson ;
                                            A22.idfunction = idfunction ;
                                            A22.idactivity = id_act;
                                            A22.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A22.class));
                                            break;

                                        case 23: break;

                                        case 24:
                                            A24.idlesson = idlesson ;
                                            A24.idfunction = idfunction ;
                                            A24.idactivity = id_act;
                                            A24.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A24.class));
                                            break;

                                        case 25:
                                            //A25.idlesson = idlesson ;
                                            // A.idfunction = idfunction ;
                                            //A6.idactivity = id_act;
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A25.class));
                                            break;

                                        case 26:
                                            A26.idlesson = idlesson ;
                                            A26.idfunction = idfunction ;
                                            A26.idactivity = id_act;
                                            A26.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A26.class));
                                            break;

                                        case 27:
                                            A27.idlesson = idlesson ;
                                            A27.idfunction = idfunction ;
                                            A27.idactivity = id_act;
                                            A27.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A27.class));
                                            break;

                                        case 28:
                                            A28.idlesson = idlesson ;
                                            A28.idfunction = idfunction ;
                                            A28.idactivity = id_act;
                                            A28.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A28.class));
                                            break;

                                        case 29:
                                            A29.idlesson = idlesson ;
                                            A29.idfunction = idfunction ;
                                            A29.idactivity = id_act;
                                            A29.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A29.class));
                                            break;

                                        case 30:
                                            A30.idlesson = idlesson ;
                                            A30.idfunction = idfunction ;
                                            A30.idactivity = id_act;
                                            A30.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A30.class));
                                            break;

                                        case 31:
                                            //A31.idlesson = idlesson ;
                                            // A.idfunction = idfunction ;
                                            //A6.idactivity = id_act;
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A31.class));
                                            break;

                                        case 32:
                                            A32.idlesson = idlesson ;
                                            A32.idfunction = idfunction ;
                                            A32.idactivity = id_act;
                                            A32.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A32.class));
                                            break;

                                        case 33:
                                            A33.idlesson = idlesson ;
                                            A33.idfunction = idfunction ;
                                            A33.idactivity = id_act;
                                            A33.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A33.class));
                                            break;

                                        case 34:
                                            A34.idlesson = idlesson ;
                                            A34.idfunction = idfunction ;
                                            A34.idactivity = id_act;
                                            A34.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A34.class));
                                            break;

                                        case 35:
                                            A35.idlesson = idlesson ;
                                            A35.idfunction = idfunction ;
                                            A35.idactivity = id_act;
                                            A35.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A35.class));
                                            break;

                                        case 36: break;

                                        case 37:
                                            A37.idlesson = idlesson ;
                                            A37.idfunction = idfunction ;
                                            A37.idactivity = id_act;
                                            A37.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A37.class));
                                            break;

                                        case 38:
                                            //A38.idlesson = idlesson ;
                                            // A.idfunction = idfunction ;
                                            //A6.idactivity = id_act;
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A38.class));
                                            break;

                                        case 39:
                                            A39.idlesson = idlesson ;
                                            A39.idfunction = idfunction ;
                                            A39.idactivity = id_act;
                                            A39.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A39.class));

                                            break;

                                        case 40:
                                            //A40.idlesson = idlesson ;
                                            // A.idfunction = idfunction ;
                                            //A6.idactivity = id_act;
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A40.class));
                                            break;

                                        case 41:
                                            //A41.idlesson = idlesson ;
                                            // A.idfunction = idfunction ;
                                            //A6.idactivity = id_act;
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A41.class));
                                            break;

                                        case 42:
                                            //A42.idlesson = idlesson ;
                                            // A.idfunction = idfunction ;
                                            //A6.idactivity = id_act;
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A42.class));
                                            break;

                                        case 43:
                                            //A43.idlesson = idlesson ;
                                            // A.idfunction = idfunction ;
                                            //A6.idactivity = id_act;
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A43.class));
                                            break;

                                        case 44:
                                            //A44.idlesson = idlesson ;
                                            // A.idfunction = idfunction ;
                                            //A6.idactivity = id_act;
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A44.class));
                                            break;

                                        case 46:
                                            A46.idlesson = idlesson ;
                                            A46.idfunction = idfunction ;
                                            A46.idactivity = id_act;
                                            A46.Act_Status = "second";
                                            A46.this.finish();
                                            startActivity(new Intent(A46.this,  A46.class));
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
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A3.class));
                                        break;

                                    case 4:
                                        A4.idlesson = idlesson ;
                                        A4.idfunction = idfunction ;
                                        A4.activitynumber = activitynumber;
                                        A4.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A4.class));
                                        break;

                                    case 5:
                                        A5.idlesson = idlesson ;
                                        A5.idfunction = idfunction ;
                                        A5.activitynumber = activitynumber;
                                        A5.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A5.class));
                                        break;

                                    case 6:
                                        A6.idlesson = idlesson ;
                                        A6.idfunction = idfunction ;
                                        A6.activitynumber = activitynumber;
                                        A6.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A6.class));
                                        break;

                                    case 7:
                                        A7.idlesson = idlesson ;
                                        A7.idfunction = idfunction ;
                                        A7.activitynumber = activitynumber;
                                        A7.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A7.class));
                                        break;

                                    case 8:
                                        A8.idlesson = idlesson ;
                                        A8.idfunction = idfunction ;
                                        A8.activitynumber = activitynumber;
                                        A8.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A8.class));
                                        break;

                                    case 9:
                                        A9.idlesson = idlesson ;
                                        A9.idfunction = idfunction ;
                                        A9.activitynumber = activitynumber;
                                        A9.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A9.class));
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
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A15.class));
                                        break;

                                    case 16: break;
                                    case 17: break;

                                    case 18:
                                        A18.idlesson = idlesson ;
                                        A18.idfunction = idfunction ;
                                        A18.activitynumber = activitynumber;
                                        A18.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A18.class));
                                        break;

                                    case 19:
                                        A19.idlesson = idlesson ;
                                        A19.idfunction = idfunction ;
                                        A19.activitynumber = activitynumber;
                                        A19.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A19.class));
                                        break;

                                    case 20:
                                        A20.idlesson = idlesson ;
                                        A20.idfunction = idfunction ;
                                        A20.activitynumber = activitynumber;
                                        A20.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A20.class));
                                        break;

                                    case 21: break;

                                    case 22:
                                        A22.idlesson = idlesson ;
                                        A22.idfunction = idfunction ;
                                        A22.activitynumber = activitynumber;
                                        A22.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A22.class));
                                        break;

                                    case 23: break;

                                    case 24:
                                        A24.idlesson = idlesson ;
                                        A24.idfunction = idfunction ;
                                        A24.activitynumber = activitynumber;
                                        A24.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A24.class));
                                        break;

                                    case 25:
                                        //A25.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A25.activitynumber = activitynumber;
                                        A25.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A25.class));
                                        break;

                                    case 26:
                                        A26.idlesson = idlesson ;
                                        A26.idfunction = idfunction ;
                                        A26.activitynumber = activitynumber;
                                        A26.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A26.class));
                                        break;

                                    case 27:
                                        A27.idlesson = idlesson ;
                                        A27.idfunction = idfunction ;
                                        A27.activitynumber = activitynumber;
                                        A27.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A27.class));
                                        break;

                                    case 28:
                                        A28.idlesson = idlesson ;
                                        A28.idfunction = idfunction ;
                                        A28.activitynumber = activitynumber;
                                        A28.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A28.class));
                                        break;

                                    case 29:
                                        A29.idlesson = idlesson ;
                                        A29.idfunction = idfunction ;
                                        A29.activitynumber = activitynumber;
                                        A29.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A29.class));
                                        break;

                                    case 30:
                                        A30.idlesson = idlesson ;
                                        A30.idfunction = idfunction ;
                                        A30.activitynumber = activitynumber;
                                        A30.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A30.class));
                                        break;

                                    case 31:
                                        //A31.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A31.activitynumber = activitynumber;
                                        //A31.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A31.class));
                                        break;

                                    case 32:
                                        A32.idlesson = idlesson ;
                                        A32.idfunction = idfunction ;
                                        A32.activitynumber = activitynumber;
                                        A32.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A32.class));
                                        break;

                                    case 33:
                                        A33.idlesson = idlesson ;
                                        A33.idfunction = idfunction ;
                                        A33.activitynumber = activitynumber;
                                        A33.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A33.class));
                                        break;

                                    case 34:
                                        A34.idlesson = idlesson ;
                                        A34.idfunction = idfunction ;
                                        A34.activitynumber = activitynumber;
                                        A34.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A34.class));
                                        break;

                                    case 35:
                                        A35.idlesson = idlesson ;
                                        A35.idfunction = idfunction ;
                                        A35.activitynumber = activitynumber;
                                        A35.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A35.class));
                                        break;

                                    case 36: break;

                                    case 37:
                                        A37.idlesson = idlesson ;
                                        A37.idfunction = idfunction ;
                                        A37.activitynumber = activitynumber;
                                        A37.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A37.class));
                                        break;

                                    case 38:
                                        //A38.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A38.activitynumber = activitynumber;
                                        //  A38.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A38.class));
                                        break;

                                    case 39:
                                        A39.idlesson = idlesson ;
                                        A39.idfunction = idfunction ;
                                        A39.activitynumber = activitynumber;
                                        A39.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A39.class));

                                        break;

                                    case 40:
                                        //A40.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A40.activitynumber = activitynumber;
                                        //  A40.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A40.class));
                                        break;

                                    case 41:
                                        //A41.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A41.activitynumber = activitynumber;
                                        //  A41.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A41.class));
                                        break;

                                    case 42:
                                        //A42.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A42.activitynumber = activitynumber;
                                        //  A42.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A42.class));
                                        break;

                                    case 43:
                                        //A43.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A43.activitynumber = activitynumber;
                                        //  A43.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A43.class));
                                        break;

                                    case 44:
                                        //A44.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A44.activitynumber = activitynumber;
                                        //  A44.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A44.class));
                                        break;

                                    case 46:
                                        A46.idlesson = idlesson ;
                                        A46.idfunction = idfunction ;
                                        A46.activitynumber = activitynumber;
                                        A46.Act_Status = "first";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A46.class));
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
                                A46.this.finish();
                                startActivity(new Intent(A46.this, End.class));

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
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A3.class));
                                        break;

                                    case 4:
                                        A4.idlesson = idlesson ;
                                        A4.idfunction = idfunction ;
                                        A4.idactivity = id_act;
                                        A4.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A4.class));
                                        break;

                                    case 5:
                                        A5.idlesson = idlesson ;
                                        A5.idfunction = idfunction ;
                                        A5.idactivity = id_act;
                                        A5.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A5.class));
                                        break;

                                    case 6:
                                        A6.idlesson = idlesson ;
                                        A6.idfunction = idfunction ;
                                        A6.idactivity = id_act;
                                        A6.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A6.class));
                                        break;

                                    case 7:
                                        A7.idlesson = idlesson ;
                                        A7.idfunction = idfunction ;
                                        A7.idactivity = id_act;
                                        A7.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A7.class));
                                        break;

                                    case 8:
                                        A8.idlesson = idlesson ;
                                        A8.idfunction = idfunction ;
                                        A8.idactivity = id_act;
                                        A8.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A8.class));
                                        break;

                                    case 9:
                                        A9.idlesson = idlesson ;
                                        A9.idfunction = idfunction ;
                                        A9.idactivity = id_act;
                                        A9.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A9.class));
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
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A15.class));
                                        break;

                                    case 16: break;
                                    case 17: break;

                                    case 18:
                                        A18.idlesson = idlesson ;
                                        A18.idfunction = idfunction ;
                                        A18.idactivity = id_act;
                                        A18.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A18.class));
                                        break;

                                    case 19:
                                        A19.idlesson = idlesson ;
                                        A19.idfunction = idfunction ;
                                        A19.idactivity = id_act;
                                        A19.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A19.class));
                                        break;

                                    case 20:
                                        A20.idlesson = idlesson ;
                                        A20.idfunction = idfunction ;
                                        A20.idactivity = id_act;
                                        A20.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A20.class));
                                        break;

                                    case 21: break;

                                    case 22:
                                        A22.idlesson = idlesson ;
                                        A22.idfunction = idfunction ;
                                        A22.idactivity = id_act;
                                        A22.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A22.class));
                                        break;

                                    case 23: break;

                                    case 24:
                                        A24.idlesson = idlesson ;
                                        A24.idfunction = idfunction ;
                                        A24.idactivity = id_act;
                                        A24.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A24.class));
                                        break;

                                    case 25:
                                        //A25.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A6.idactivity = id_act;
                                        //A9.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A25.class));
                                        break;

                                    case 26:
                                        A26.idlesson = idlesson ;
                                        A26.idfunction = idfunction ;
                                        A26.idactivity = id_act;
                                        A26.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A26.class));
                                        break;

                                    case 27:
                                        A27.idlesson = idlesson ;
                                        A27.idfunction = idfunction ;
                                        A27.idactivity = id_act;
                                        A27.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A27.class));
                                        break;

                                    case 28:
                                        A28.idlesson = idlesson ;
                                        A28.idfunction = idfunction ;
                                        A28.idactivity = id_act;
                                        A28.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A28.class));
                                        break;

                                    case 29:
                                        A29.idlesson = idlesson ;
                                        A29.idfunction = idfunction ;
                                        A29.idactivity = id_act;
                                        A29.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A29.class));
                                        break;

                                    case 30:
                                        A30.idlesson = idlesson ;
                                        A30.idfunction = idfunction ;
                                        A30.idactivity = id_act;
                                        A30.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A30.class));
                                        break;

                                    case 31:
                                        //A31.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A29.idactivity = id_act;
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A31.class));
                                        break;

                                    case 32:
                                        A32.idlesson = idlesson ;
                                        A32.idfunction = idfunction ;
                                        A32.idactivity = id_act;
                                        A32.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A32.class));
                                        break;

                                    case 33:
                                        A33.idlesson = idlesson ;
                                        A33.idfunction = idfunction ;
                                        A33.idactivity = id_act;
                                        A33.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A33.class));
                                        break;

                                    case 34:
                                        A34.idlesson = idlesson ;
                                        A34.idfunction = idfunction ;
                                        A34.idactivity = id_act;
                                        A34.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A34.class));
                                        break;

                                    case 35:
                                        A35.idlesson = idlesson ;
                                        A35.idfunction = idfunction ;
                                        A35.idactivity = id_act;
                                        A35.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A35.class));
                                        break;

                                    case 36: break;

                                    case 37:
                                        A37.idlesson = idlesson ;
                                        A37.idfunction = idfunction ;
                                        A37.idactivity = id_act;
                                        A37.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A37.class));
                                        break;

                                    case 38:
                                        //A38.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A29.idactivity = id_act;
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A38.class));
                                        break;

                                    case 39:
                                        A39.idlesson = idlesson ;
                                        A39.idfunction = idfunction ;
                                        A39.idactivity = id_act;
                                        A39.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A39.class));

                                        break;

                                    case 40:
                                        //A40.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A29.idactivity = id_act;
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A40.class));
                                        break;

                                    case 41:
                                        //A41.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A29.idactivity = id_act;
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A41.class));
                                        break;

                                    case 42:
                                        //A42.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A29.idactivity = id_act;
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A42.class));
                                        break;

                                    case 43:
                                        //A43.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A29.idactivity = id_act;
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A43.class));
                                        break;

                                    case 44:
                                        //A44.idlesson = idlesson ;
                                        // A.idfunction = idfunction ;
                                        //A29.idactivity = id_act;
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A44.class));
                                        break;

                                    case 46:
                                        A46.idlesson = idlesson ;
                                        A46.idfunction = idfunction ;
                                        A46.idactivity = id_act;
                                        A46.Act_Status = "second";
                                        A46.this.finish();
                                        startActivity(new Intent(A46.this,  A46.class));
                                        break;
                                }
                            }
                        }
                    }

                    break;
            }
        }
    }

    public boolean cheak(){
        boolean final_answer = false;
        boolean answer[];
        switch (count){
            case 1:
                answer = new boolean[1];
                for(int i = 0 ; i < z1.length ; i++){
                    String a = nice_string(e[0].getText().toString());
                    String b = nice_string(z1[i]);
                    if(a.equals(b)){
                        answer[0] = true;
                    }
                }
                if(answer[0]){
                    final_answer = true;
                }
                break;

            case 2 :
                answer = new boolean[2];
                for(int i = 0 ; i < z1.length ; i++){
                    String a = nice_string(e[0].getText().toString());
                    String b = nice_string(z1[i]);
                    if(a.equals(b)){
                        answer[0] = true;
                    }
                }
                for(int i = 0 ; i < z2.length ; i++){
                    String a = nice_string(e[1].getText().toString());
                    String b = nice_string(z2[i]);
                    if(a.equals(b)){
                        answer[1] = true;
                    }
                }
                if(answer[0]){
                    if(answer[1]){
                        final_answer = true;
                    }
                }
                break;

            case 3:
                answer = new boolean[3];
                for(int i = 0 ; i < z1.length ; i++){
                    String a = nice_string(e[0].getText().toString());
                    String b = nice_string(z1[i]);
                    if(a.equals(b)){
                        answer[0] = true;
                    }
                }
                for(int i = 0 ; i < z2.length ; i++){
                    String a = nice_string(e[1].getText().toString());
                    String b = nice_string(z2[i]);
                    if(a.equals(b)){
                        answer[1] = true;
                    }
                }

                for(int i = 0 ; i < z3.length ; i++){
                    String a = nice_string(e[2].getText().toString());
                    String b = nice_string(z3[i]);
                    if(a.equals(b)){
                        answer[2] = true;
                    }
                }
                if(answer[0]){
                    if(answer[1]) {
                        if (answer[2]) {
                            final_answer = true;
                        }
                    }
                }
                break;
        }
        return final_answer;
   }

    public String nice_string (String a){
        // space in first or end
        String b = a.trim();
        // other space
        b = b.replace(" ", "");
        // other
        b = b.replace(".", "");
        b = b.replace("!", "");
        b = b.replace("?", "");
        b = b.replace("؟", "");
        b = b.replace(",", "");
        b = b.replace("’", "");
        b = b.replace("'", "");
        b = b.replace("\n", "");
        // lowerCase
        b = b.toLowerCase();
        return b;
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
        mStateMaintainer.put(A46_Presenter.class.getSimpleName(), mPresenter);
    }

    private void reinitialize() {
        Log.d(TAG, "reinitialize");
        mPresenter = mStateMaintainer.get(A46_Presenter.class.getSimpleName());
        mPresenter.setView(this);
        if ( mPresenter == null )
            setupComponent();
    }

    private void setupComponent(){
        Log.d(TAG, "setupComponent");
        SampleApp.get(this)
                .getAppComponent()
                .getA46Component(new A46_Module(this))
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
        A46.this.finish();
        startActivity(new Intent(A46.this, Lesson.class));
    }
}
