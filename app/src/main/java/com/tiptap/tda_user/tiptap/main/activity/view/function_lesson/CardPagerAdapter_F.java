package com.tiptap.tda_user.tiptap.main.activity.view.function_lesson;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tiptap.tda_user.tiptap.R;
import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter_F extends PagerAdapter implements CardAdapter {

    int _id;
    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;

    public CardPagerAdapter_F() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItem(int id_function, CardItem item) {
        _id = id_function;
        mViews.add(null);
        mData.add(item);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.func, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(final ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(final CardItem item, final View view) {

        TextView titleTextView = (TextView) view.findViewById(R.id.title);
        titleTextView.setText(item.getTitle());

        ImageView img = (ImageView) view.findViewById(R.id.img);
        final Button btn = (Button) view.findViewById(R.id.next);

        final int id_function = item.getId();
        if(_id == 0){
            if(id_function == 1){
                btn.setBackgroundColor(Color.parseColor("#3CB371"));
                btn.setText("Start");
            }
        } else if(_id != 0){
            if(id_function < _id){
                btn.setBackgroundColor(Color.parseColor("#CCCC00"));
                btn.setText("Redo");
            }
            if(id_function == _id){
                btn.setBackgroundColor(Color.parseColor("#3CB371"));
                btn.setText("Start");
            }
        }

        btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(id_function <= _id || id_function == 1){
                        Lesson.id_function =  id_function;

                        view.getContext().startActivity(new Intent(view.getContext(), Lesson.class));
                    }
            }
        });
    }
}