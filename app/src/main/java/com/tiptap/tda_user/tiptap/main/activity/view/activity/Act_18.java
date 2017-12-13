package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;

import com.tiptap.tda_user.tiptap.R;

import java.util.ArrayList;
import java.util.Collections;

public class Act_18 extends AppCompatActivity {

    Button next;
    private RecyclerView mRecyclerView;
    private ArrayList<String> mDataList;
    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_18);

        initRecView();

        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Act_18.this.finish();
                startActivity(new Intent(Act_18.this, Act_19.class));
            }
        });
    }

    private void initRecView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDataList = new ArrayList<String>();
        addDummmyData(mDataList);
        mAdapter = new Adapter(this, mDataList);
        mRecyclerView.setAdapter(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                return makeMovementFlags(dragFlags, 0);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Collections.swap(mDataList, viewHolder.getAdapterPosition(), target.getAdapterPosition());
                mAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {}
        });

        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void addDummmyData(ArrayList<String> mDataList) {
        mDataList.add("A: Nice to meet you Alex");
        mDataList.add("B: nice to");
        mDataList.add("A: meet you too");
        mDataList.add("A: Hello, I’m John");
        mDataList.add("B: Hi, I’m Alex");
        mDataList.add("A: I’m Ok,");
        mDataList.add("B: Fine thanks, what");
        mDataList.add("A: How’s it going?  ");
        mDataList.add("A: about you?");
    }
}