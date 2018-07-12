package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
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
import com.tiptap.tda_user.tiptap.di.module.A26_Module;
import com.tiptap.tda_user.tiptap.di.module.Main_Module;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Main_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import javax.inject.Inject;
import android.view.View.OnClickListener;

public class A26 extends BaseActivity implements MVP_Main.RequiredViewOps, OnClickListener {

    private static final String TAG = A26.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer( getFragmentManager(), A26.class.getName());

    @Inject
    public MVP_Main.ProvidedPresenterOps mPresenter;

    RecyclerView mRecyclerView;
    ArrayList<String> mDataList;
    Adapter_A26 mAdapter;
    String part_id1 [][];
    String part_id2 [][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a26);

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

        title1 = tbActivity.getTitle1();
        after_setup();
    }

    private void setupViews() {
        p = (ProgressBar)findViewById(R.id.p);
        p.setMax(100);
        t1 = (TextView)findViewById(R.id.title1);
        t2 = (TextView)findViewById(R.id.title2);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDataList = new ArrayList<String>();
        next = (Button) findViewById(R.id.next);
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

        t1.setText(R.string.A26_EN);
        t1.setTextColor(getResources().getColor(R.color.my_black));

        int lang_id = mPresenter.getlanguage();
        switch (lang_id){
            // فارسی
            case 1:
                t2.setText(R.string.A26_FA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // کردی
            case 2:
                t2.setText(R.string.A26_KU);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // ترکی آذری
            case 3:
                t2.setText(R.string.A26_TA);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
            // چینی
            case 4:
                t2.setText(R.string.A26_CH);
                t2.setTextColor(getResources().getColor(R.color.my_black));
                break;
        }

        next.setOnClickListener(this);

        addData(mDataList);
        mAdapter = new Adapter_A26(this, mDataList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 15, false));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                return makeMovementFlags(dragFlags, 0);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                int start = viewHolder.getAdapterPosition();
                int end = target.getAdapterPosition();
                String temp0 = part_id2[start][0];
                String temp1 = part_id2[start][1];
                part_id2[start][0] = part_id2[end][0];
                part_id2[start][1] = part_id2[end][1];
                part_id2[end][0] = temp0;
                part_id2[end][1] = temp1;

                Collections.swap(mDataList, viewHolder.getAdapterPosition(), target.getAdapterPosition());
                mAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {}
        });
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.next) {

            switch (next.getText().toString()) {

                case "check":

                    int answer = cheak();

                    if (answer==1) {

                        // update - true
                        mPresenter.update_activity(idactivity);

                        // show passed activity
                        List<Integer> passed1 = mPresenter.activity_true(idlesson);
                        int passed2 = passed1.size();
                        if (passed2 == 0) {
                            p.setProgress(0);
                        } else {
                            double d_number = (double) passed2 / all;
                            int i_number = (int) (d_number * 100);
                            p.setProgress(i_number);
                        }

                        // Clickable_false
                        t1.setClickable(false);
                        t2.setClickable(false);
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

                    }else if (answer == 2) {

                        // Clickable_false
                        t1.setClickable(false);
                        t2.setClickable(false);
                        p.setClickable(false);

                        // Fragment_false
                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.holder2);
                        Animation slide_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slideup);
                        linearLayout.setAnimation(slide_down);
                        linearLayout.setVisibility(View.VISIBLE);

                        Fragment_False f2 = new Fragment_False();
                        f2.t.setText("please put the Sentences in correct arrange");
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
                                    A26.this.finish();
                                    startActivity(new Intent(A26.this, End.class));
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
                                A26.this.finish();
                                startActivity(new Intent(A26.this, End.class));

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



    private void addData(ArrayList<String> mDataList) {
        String text = title1;
        String part[] = text.split(Pattern.quote("\n"));
        part_id1 = new String[part.length][2];
        part_id2 = new String[part.length][2];

        for(int i=0 ; i<part.length ; i++){
            part_id1 [i][0] = part[i];
            part_id1 [i][1] = i+"";
            part_id2 [i][0] = part[i];
            part_id2 [i][1] = i+"";
        }

        int max_range = part.length-1;
        int min_range = 0;
        for(int i=0 ; i<part.length ;  i++) {
            int rnd = new Random().nextInt(max_range - min_range + 1) + min_range;
            String rnd0 = part_id2[rnd][0];
            String rnd1 = part_id2[rnd][1];
            part_id2[rnd][0] = part_id2[i][0];
            part_id2[rnd][1] = part_id2[i][1];
            part_id2[i][0] = rnd0;
            part_id2[i][1] = rnd1;
        }

        for(int i=0 ; i<part.length ;  i++){
            mDataList.add(part_id2[i][0]);
        }
    }

    public int cheak(){
        int result = 0;
        for(int i=0 ; i<part_id1.length ; i++){
            if(part_id1[i][1].equals(part_id2[i][1])){
                result = 1;
            }else {
                result = 2;
            }
        }
        return result;
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
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
                .getA26Component(new A26_Module(this))
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
}