package com.tiptap.tda_user.tiptap.main.activity.view.function;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Function;
import com.tiptap.tda_user.tiptap.main.activity.view.lesson.Lesson;
import java.util.ArrayList;

public class Function_Adapter extends RecyclerView.Adapter<Function_Adapter.ViewHolder> {

    MVP_Function.ProvidedPresenterOps function_presenter;
    int _id;
    private ArrayList<String> Data;
    private ArrayList<Integer> Id;
    private Activity activity;

    public Function_Adapter(MVP_Function.ProvidedPresenterOps ppo, int id_function, Activity a, ArrayList<String> D, ArrayList<Integer> I) {
        function_presenter = ppo;
        _id = id_function;
        activity = a;
        Data = D;
        Id = I;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt);
            imageView = (ImageView) itemView.findViewById(R.id.img1);
            button = (Button) itemView.findViewById(R.id.btn);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.func, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(Data.get(position).toString());

        final int id_function = Id.get(position);
        int first = function_presenter.first();

        switch (id_function){
            case 1:
                holder.imageView.setBackgroundResource(R.drawable.f1);
                break;
            case 2:
                holder.imageView.setBackgroundResource(R.drawable.f2);
                break;
            case 3:
                holder.imageView.setBackgroundResource(R.drawable.f3);
                break;
            case 4:
                holder.imageView.setBackgroundResource(R.drawable.f4);
                break;
            case 5:
                holder.imageView.setBackgroundResource(R.drawable.f5);
                break;
            case 6:
                holder.imageView.setBackgroundResource(R.drawable.f6);
                break;
            case 7:
                holder.imageView.setBackgroundResource(R.drawable.f7);
                break;
            case 8:
                holder.imageView.setBackgroundResource(R.drawable.f8);
                break;
            case 9:
                holder.imageView.setBackgroundResource(R.drawable.f9);
                break;
            case 10:
                holder.imageView.setBackgroundResource(R.drawable.f10);
                break;
            case 11:
                holder.imageView.setBackgroundResource(R.drawable.f11);
                break;
            case 12:
                holder.imageView.setBackgroundResource(R.drawable.f12);
                break;
            case 13:
                holder.imageView.setBackgroundResource(R.drawable.f13);
                break;
            case 14:
                holder.imageView.setBackgroundResource(R.drawable.f14);
                break;
            case 15:
                holder.imageView.setBackgroundResource(R.drawable.f15);
                break;
            case 16:
                holder.imageView.setBackgroundResource(R.drawable.f16);
                break;
        }

        if(_id == 0){
            // first function is ((Start))
            if(id_function == first){
                function_presenter.update_idfunction(first);
                holder.button.setBackgroundResource(R.drawable.btn_blue);
                holder.button.setText("Start");
                holder.button.setTextColor(Color.parseColor("#000000"));
            }else{
                // other function - Button color - disable
                holder.button.setBackgroundResource(R.drawable.btn_gray);
                holder.button.setTextColor(Color.parseColor("#919396"));
                holder.button.setText("locked");
            }
        } else {
            if(id_function <= _id){
                // passed function is ((Redo))
                if(id_function < _id){
                    holder.button.setBackgroundResource(R.drawable.btn_green);
                    holder.button.setText("Redo");
                    holder.button.setTextColor(Color.parseColor("#000000"));
                }
                // now function is ((Start))
                if(id_function == _id){
                    holder.button.setBackgroundResource(R.drawable.btn_blue);
                    holder.button.setText("Start");
                    holder.button.setTextColor(Color.parseColor("#000000"));
                }
            }else{
                // other function - Button color- disable
                holder.button.setBackgroundResource(R.drawable.btn_gray);
                holder.button.setTextColor(Color.parseColor("#919396"));
                holder.button.setText("locked");
            }
        }

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id_function == 1 || id_function <= _id ){
                    Lesson.now_id_function =  id_function;
                    activity.finish();
                    view.getContext().startActivity(new Intent(view.getContext(), Lesson.class));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }
}
