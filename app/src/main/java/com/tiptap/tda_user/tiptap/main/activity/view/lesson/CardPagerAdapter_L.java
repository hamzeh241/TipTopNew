package com.tiptap.tda_user.tiptap.main.activity.view.lesson;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.tiptap.tda_user.tiptap.R;
import com.tiptap.tda_user.tiptap.main.activity.Api.Get_Activity;
import com.tiptap.tda_user.tiptap.main.activity.DB.BaseSetingApi;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Lesson;
import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter_L extends PagerAdapter implements CardAdapter {

    MVP_Lesson.ProvidedPresenterOps lesson_presenter;
    Context _context;
    Activity _activity;
    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;
    int func;

    public CardPagerAdapter_L() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItem(MVP_Lesson.ProvidedPresenterOps ppo, int nid, Context context, Activity activity, CardItem item) {
        lesson_presenter = ppo;
        func = nid;
        _context = context;
        _activity = activity;
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
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.less, container, false);
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

    private void bind(final CardItem item, View view) {

        TextView titleTextView = (TextView) view.findViewById(R.id.title);
        titleTextView.setText(item.getTitle());

        ImageView img = (ImageView) view.findViewById(R.id.img1);
        final Button btn = (Button) view.findViewById(R.id.next);

        int id_lesson = item.getId();
        int number_lesson = lesson_presenter.lesson_number(id_lesson);

        int fuser = lesson_presenter.Id_Function();
        final int luser = lesson_presenter.Id_Lesson();

        if(func == fuser){
            if(luser == 0){
                if(number_lesson == 1){
                    int first = lesson_presenter.lesson_id(func,1);
                    lesson_presenter.update_idlesson(first);
                    btn.setBackgroundResource(R.drawable.btn_blue);
                    btn.setText("Start");
                    btn.setTextColor(Color.parseColor("#000000"));
                    // save lesson current position
                    Lesson.CurrentPosition = 0;
                }
            }else if(luser != 0){
                int nluser = lesson_presenter.lesson_number(luser);
                if(number_lesson < nluser){
                    btn.setBackgroundResource(R.drawable.btn_green);
                    btn.setText("Redo");
                    btn.setTextColor(Color.parseColor("#000000"));
                    // save lesson current position
                    Lesson.CurrentPosition = 0;
                }
                if(number_lesson == nluser) {
                    btn.setBackgroundResource(R.drawable.btn_blue);
                    btn.setText("Start");
                    btn.setTextColor(Color.parseColor("#000000"));
                    // save lesson current position
                    Lesson.CurrentPosition = number_lesson-1;
                }
            }
        }
        if(func < fuser){
            btn.setBackgroundResource(R.drawable.btn_green);
            btn.setText("Redo");
            btn.setTextColor(Color.parseColor("#000000"));
            // save lesson current position
            Lesson.CurrentPosition = 0;
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_lesson = item.getId();
                BaseSetingApi baseSetingApi=new BaseSetingApi(_context,_activity);
                if(btn.getText().equals("Redo")){
                    int id_activity_type = lesson_presenter.activity_Type(id_lesson);
                    baseSetingApi.go_activity(view, id_activity_type, id_lesson,func,_activity);
                }
                else if(btn.getText().equals("Start")){
                    new Get_Activity(id_lesson, lesson_presenter,_context,_activity,view,func,baseSetingApi.haveNetworkConnection());
                }
            }
        });
    }
}