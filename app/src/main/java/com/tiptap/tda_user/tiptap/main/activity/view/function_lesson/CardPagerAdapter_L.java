package com.tiptap.tda_user.tiptap.main.activity.view.function_lesson;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.tiptap.tda_user.tiptap.main.activity.Api.Get_Activity;
import com.tiptap.tda_user.tiptap.main.activity.Api.Get_ActivityDetail;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Lesson;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A15;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A18;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A19;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A20;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A22;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A24;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A25;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A26;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A27;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A28;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A29;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A3;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A30;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A31;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A32;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A33;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A34;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A35;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A37;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A38;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A39;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A4;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A40;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A41;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A42;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A43;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A44;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A5;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A6;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A7;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A8;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A9;

import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter_L extends PagerAdapter implements CardAdapter {

    MVP_Lesson.ProvidedPresenterOps lesson_presenter;
    int _id;
    Context _context;
    Activity _activity;
    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;

    public CardPagerAdapter_L() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItem(MVP_Lesson.ProvidedPresenterOps ppo,int id, Context context, Activity activity, CardItem item) {
        lesson_presenter = ppo;
        _id = id;
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

        ImageView img = (ImageView) view.findViewById(R.id.img);
        Button btn = (Button) view.findViewById(R.id.next);

        int id_lesson = item.getId();
        int number_lesson = lesson_presenter.lesson_number(id_lesson);

        if(_id == 0){
            if(number_lesson == 1){
                btn.setBackgroundColor(Color.parseColor("#3CB371"));
                btn.setText("Start");
            }

        }else if(_id != 0){
            int number_id = lesson_presenter.lesson_number(_id);
            if(number_lesson < number_id){
                btn.setBackgroundColor(Color.parseColor("#CCCC00"));
                btn.setText("Redo");
            }
            if(number_lesson == number_id){
                btn.setBackgroundColor(Color.parseColor("#3CB371"));
                btn.setText("Start");
            }
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_lesson = item.getId();
                int number_lesson = lesson_presenter.lesson_number(id_lesson);
                int number_id = lesson_presenter.lesson_number(_id);
                if(number_lesson <= number_id || number_lesson == 1){

                    new Get_Activity(id_lesson, haveNetworkConnection(), lesson_presenter, _context, _activity);
                    Toast.makeText(_context, lesson_presenter.getCount_Activity(id_lesson) + " : activity", Toast.LENGTH_LONG).show();

                    new Get_ActivityDetail(id_lesson, haveNetworkConnection(), lesson_presenter, _context, _activity);
                    Toast.makeText(_context, lesson_presenter.getCount_ActivityDetail(id_lesson) + " : activity_DETAIL", Toast.LENGTH_LONG).show();

                    int id_activity_type = lesson_presenter.activity_Type(id_lesson);

                    go_activity(view, id_activity_type);
                }

            }
        });
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public void go_activity(View view, int id_at){
        switch (id_at){

            case 1: break;
            case 2: break;

            case 3:
                view.getContext().startActivity(new Intent(view.getContext(), A3.class));
                break;

            case 4:
                view.getContext().startActivity(new Intent(view.getContext(), A4.class));
                break;

            case 5:
                view.getContext().startActivity(new Intent(view.getContext(), A5.class));
                break;

            case 6:
                view.getContext().startActivity(new Intent(view.getContext(), A6.class));
                break;

            case 7:
                view.getContext().startActivity(new Intent(view.getContext(), A7.class));
                break;

            case 8:
                view.getContext().startActivity(new Intent(view.getContext(), A8.class));
                break;

            case 9:
                view.getContext().startActivity(new Intent(view.getContext(), A9.class));
                break;

            case 10: break;
            case 11: break;
            case 12: break;
            case 13: break;
            case 14: break;

            case 15:
                view.getContext().startActivity(new Intent(view.getContext(), A15.class));
                break;

            case 16: break;
            case 17: break;

            case 18:
                view.getContext().startActivity(new Intent(view.getContext(), A18.class));
                break;

            case 19:
                view.getContext().startActivity(new Intent(view.getContext(), A19.class));
                break;

            case 20:
                view.getContext().startActivity(new Intent(view.getContext(), A20.class));
                break;

            case 21: break;

            case 22:
                view.getContext().startActivity(new Intent(view.getContext(), A22.class));
                break;

            case 23: break;

            case 24:
                view.getContext().startActivity(new Intent(view.getContext(), A24.class));
                break;

            case 25:
                view.getContext().startActivity(new Intent(view.getContext(), A25.class));
                break;

            case 26:
                view.getContext().startActivity(new Intent(view.getContext(), A26.class));
                break;

            case 27:
                view.getContext().startActivity(new Intent(view.getContext(), A27.class));
                break;

            case 28:
                view.getContext().startActivity(new Intent(view.getContext(), A28.class));
                break;

            case 29:
                view.getContext().startActivity(new Intent(view.getContext(), A29.class));
                break;

            case 30:
                view.getContext().startActivity(new Intent(view.getContext(), A30.class));
                break;

            case 31:
                view.getContext().startActivity(new Intent(view.getContext(), A31.class));
                break;

            case 32:
                view.getContext().startActivity(new Intent(view.getContext(), A32.class));
                break;

            case 33:
                view.getContext().startActivity(new Intent(view.getContext(), A33.class));
                break;

            case 34:
                view.getContext().startActivity(new Intent(view.getContext(), A34.class));
                break;

            case 35:
                view.getContext().startActivity(new Intent(view.getContext(), A35.class));
                break;

            case 36: break;

            case 37:
                view.getContext().startActivity(new Intent(view.getContext(), A37.class));
                break;

            case 38:
                view.getContext().startActivity(new Intent(view.getContext(), A38.class));
                break;

            case 39:
                view.getContext().startActivity(new Intent(view.getContext(), A39.class));
                break;

            case 40:
                view.getContext().startActivity(new Intent(view.getContext(), A40.class));
                break;

            case 41:
                view.getContext().startActivity(new Intent(view.getContext(), A41.class));
                break;

            case 42:
                view.getContext().startActivity(new Intent(view.getContext(), A42.class));
                break;

            case 43:
                view.getContext().startActivity(new Intent(view.getContext(), A43.class));
                break;

            case 44:
                view.getContext().startActivity(new Intent(view.getContext(), A44.class));
                break;
        }
    }
}