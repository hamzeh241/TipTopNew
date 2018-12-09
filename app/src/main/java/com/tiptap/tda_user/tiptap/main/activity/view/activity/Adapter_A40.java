package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tiptap.tda_user.tiptap.R;

import java.util.ArrayList;

public class Adapter_A40 extends RecyclerView.Adapter<Adapter_A40.ViewHolder> {
    private ArrayList<String> mDataList;
    private Context context;

    public Adapter_A40(Context context, ArrayList<String> mDataList) {
        this.context = context;
        this.mDataList = mDataList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_a40, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(mDataList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}