package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.tiptap.tda_user.tiptap.R;
import java.util.ArrayList;

public class Adapter_A26 extends RecyclerView.Adapter<Adapter_A26.ViewHolder> {
    private ArrayList<String> mDataList;
    private Context context;

    public Adapter_A26(AppCompatActivity mainActivity, ArrayList<String> mDataList) {
        this.context = mainActivity;
        this.mDataList = mDataList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt);
            imageView = (ImageView) itemView.findViewById(R.id.img);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(mDataList.get(position).toString());
        if(position == 0){
            holder.imageView.setBackgroundResource(R.drawable.p1);
        }else{
            if(position%2 == 0){
                holder.imageView.setBackgroundResource(R.drawable.p1);
            }
            if(position%2 != 0){
               // holder.imageView.setBackgroundResource(R.drawable.p2);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}