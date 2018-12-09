package com.tiptap.tda_user.tiptap.main.activity.Cls;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.tiptap.tda_user.tiptap.main.activity.DB.BaseSetingApi;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Function;
import com.tiptap.tda_user.tiptap.main.activity.view.function.Function;
import com.tiptap.tda_user.tiptap.main.activity.view.function.Function_Adapter;
import java.util.ArrayList;

public class Set_Function extends BaseSetingApi {

    MVP_Function.ProvidedPresenterOps function_presenter;
    int _id_function;
    Activity mactivity;
    RecyclerView mRecyclerView;
    Function_Adapter mAdapter;
    ArrayList<String> Data;
    ArrayList<Integer> Id;
    int scroll_point = Function.id_function;

    public Set_Function(MVP_Function.ProvidedPresenterOps ppo, Activity c, int id_function, RecyclerView r, Function_Adapter fa, ArrayList<String> d, ArrayList<Integer> i) {
        _id_function = id_function;
        mactivity = c;
        function_presenter = ppo;
        mRecyclerView = r;
        mAdapter = fa;
        Data = d;
        Id = i;
    }

    public void load() {
        for (int i = 0; i < function_presenter.getCount_Function(); i++) {
            Id.add(function_presenter.getListFunction().get(i).get_id());
            Data.add(function_presenter.getListFunction().get(i).getTitle());
        }
        mAdapter = new Function_Adapter(function_presenter, _id_function, mactivity, Data, Id);
        mRecyclerView.setAdapter(mAdapter);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.smoothScrollToPosition(scroll_point);
            }
        }, 200);
    }
}
