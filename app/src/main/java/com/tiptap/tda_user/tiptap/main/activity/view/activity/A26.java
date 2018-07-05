package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.common.StateMaintainer;
import com.tiptap.tda_user.tiptap.di.module.A26_Module;
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

        tbActivity = mPresenter.getActivity(idlesson, activitynumber);
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
                    if (answer == 1) {
                        Toast.makeText(getApplicationContext(), "CorrectTEST", Toast.LENGTH_LONG).show();
                    } else if (answer == 2) {
                        Toast.makeText(getApplicationContext(), "False", Toast.LENGTH_LONG).show();
                    }

                    next.setTextColor(Color.WHITE);
                    next.setBackgroundResource(R.drawable.btn_green);
                    next.setText("countinue");

                    break;

                case "countinue":

                    if(activitynumber == max){
                        now_less = mPresenter.now_IdLesson();

                        // post

                        // update
                        List<Integer> id_less = mPresenter.lesson(idfunction);
                        List<Integer> id_func =  mPresenter.function();

                        for(int i=0 ; i< id_less.size() ; i++){
                            if(id_less.get(i) == idlesson){
                                if(i == id_less.size()-1){
                                    End.gofunction = 1;
                                    for(int j=0 ; j< id_func.size() ; j++) {
                                        if (id_func.get(j) == idfunction) {
                                            if (now_less == idlesson){
                                                int next_func = j+1;
                                                mPresenter.update_idfunction(id_func.get(next_func));
                                                mPresenter.update_idlesson(0);
                                            }
                                            break;
                                        }
                                    }
                                }
                                else{
                                    End.gofunction = 0;
                                    if (now_less == idlesson){
                                        int next_less = i+1;
                                        mPresenter.update_idlesson(id_less.get(next_less));
                                    }
                                }
                                break;
                            }
                        }
                        A26.this.finish();
                        startActivity(new Intent(A26.this, End.class ));

                    } else {

                        TbActivity tb_new = mPresenter.getActivity(idlesson, ++activitynumber);
                        int id_at_new = tb_new.getId_ActivityType();

                        switch (id_at_new){

                            case 1: break;
                            case 2: break;

                            case 3:
                                //A3.idlesson = idlesson ;
                                //A.idfunction = idfunction ;
                                //A3.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A3.class));
                                break;

                            case 4:
                                A4.idlesson = idlesson ;
                                A4.idfunction = idfunction ;
                                A4.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A4.class));
                                break;

                            case 5:
                                A5.idlesson = idlesson ;
                                A5.idfunction = idfunction ;
                                A5.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A5.class));
                                break;

                            case 6:
                                A6.idlesson = idlesson ;
                                A6.idfunction = idfunction ;
                                A6.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A6.class));
                                break;

                            case 7:
                                A7.idlesson = idlesson ;
                                A7.idfunction = idfunction ;
                                A7.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A7.class));
                                break;

                            case 8:
                                A8.idlesson = idlesson ;
                                A8.idfunction = idfunction ;
                                A8.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A8.class));
                                break;

                            case 9:
                                A9.idlesson = idlesson ;
                                A9.idfunction = idfunction ;
                                A9.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A9.class));
                                break;

                            case 10: break;
                            case 11: break;
                            case 12: break;
                            case 13: break;
                            case 14: break;

                            case 15:
                                //A15.idlesson = idlesson ;
                                //A.idfunction = idfunction ;
                                //A15.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A15.class));
                                break;

                            case 16: break;
                            case 17: break;

                            case 18:
                                A18.idlesson = idlesson ;
                                A18.idfunction = idfunction ;
                                A18.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A18.class));
                                break;

                            case 19:
                                A19.idlesson = idlesson ;
                                A19.idfunction = idfunction ;
                                A19.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A19.class));
                                break;

                            case 20:
                                //A20.idlesson = idlesson ;
                                //A.idfunction = idfunction ;
                                //A20.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A20.class));
                                break;

                            case 21: break;

                            case 22:
                                A22.idlesson = idlesson ;
                                A22.idfunction = idfunction ;
                                A22.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A22.class));
                                break;

                            case 23: break;

                            case 24:
                                A24.idlesson = idlesson ;
                                A24.idfunction = idfunction ;
                                A24.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A24.class));
                                break;

                            case 25:
                                //A25.idlesson = idlesson ;
                                //A.idfunction = idfunction ;
                                //A25.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A25.class));
                                break;

                            case 26:
                                A26.idlesson = idlesson ;
                                A26.idfunction = idfunction ;
                                A26.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A26.class));
                                break;

                            case 27:
                                //A27.idlesson = idlesson ;
                                //A.idfunction = idfunction ;
                                //A27.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A27.class));
                                break;

                            case 28:
                                A28.idlesson = idlesson ;
                                A28.idfunction = idfunction ;
                                A28.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A28.class));
                                break;

                            case 29:
                                A29.idlesson = idlesson ;
                                A29.idfunction = idfunction ;
                                A29.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A29.class));
                                break;

                            case 30:
                                A30.idlesson = idlesson ;
                                A30.idfunction = idfunction ;
                                A30.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A30.class));
                                break;

                            case 31:
                                //A31.idlesson = idlesson ;
                                //A31.idfunction = idfunction ;
                                //A31.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A31.class));
                                break;

                            case 32:
                                //A32.idlesson = idlesson ;
                                //A.idfunction = idfunction ;
                                //A32.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A32.class));
                                break;

                            case 33:
                                A33.idlesson = idlesson ;
                                A33.idfunction = idfunction ;
                                A33.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A33.class));
                                break;

                            case 34:
                                A34.idlesson = idlesson ;
                                A34.idfunction = idfunction ;
                                A34.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A34.class));
                                break;

                            case 35:
                                A35.idlesson = idlesson ;
                                A35.idfunction = idfunction ;
                                A35.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A35.class));
                                break;

                            case 36: break;

                            case 37:
                                //A37.idlesson = idlesson ;
                                //A.idfunction = idfunction ;
                                //A37.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A37.class));
                                break;

                            case 38:
                                //A38.idlesson = idlesson ;
                                //A.idfunction = idfunction ;
                                //A38.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A38.class));
                                break;

                            case 39:
                                A39.idlesson = idlesson ;
                                A39.idfunction = idfunction ;
                                A39.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A39.class));
                                break;

                            case 40:
                                //A40.idlesson = idlesson ;
                                //A.idfunction = idfunction ;
                                //A40.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A40.class));
                                break;

                            case 41:
                                //A41.idlesson = idlesson ;
                                //A.idfunction = idfunction ;
                                //A41.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A41.class));
                                break;

                            case 42:
                                //A42.idlesson = idlesson ;
                                //A.idfunction = idfunction ;
                                //A42.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A42.class));
                                break;

                            case 43:
                                //A43.idlesson = idlesson ;
                                //A.idfunction = idfunction ;
                                //A43.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A43.class));
                                break;

                            case 44:
                                //A44.idlesson = idlesson ;
                                //A.idfunction = idfunction ;
                                //A44.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A44.class));
                                break;

                            case 46:
                                A46.idlesson = idlesson ;
                                A46.idfunction = idfunction ;
                                A46.activitynumber = activitynumber;
                                A26.this.finish();
                                startActivity(new Intent(A26.this,  A46.class));
                                break;
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