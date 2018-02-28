package com.tiptap.tda_user.tiptap.main.activity.Cls;

import android.app.Activity;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.tiptap.tda_user.tiptap.main.activity.DB.BaseSetingApi;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Function;
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
        //mRecyclerView.addItemDecoration(new Set_Function.GridSpacingItemDecoration(1, 15, false));
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
}
