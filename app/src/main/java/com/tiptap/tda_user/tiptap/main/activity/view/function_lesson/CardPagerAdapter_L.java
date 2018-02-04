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

        ImageView img = (ImageView) view.findViewById(R.id.img);
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
                    btn.setBackgroundColor(Color.parseColor("#3CB371"));
                    btn.setText("Start");
                }
            }else if(luser != 0){
                int nluser = lesson_presenter.lesson_number(luser);
                if(number_lesson < nluser){
                    btn.setBackgroundColor(Color.parseColor("#CCCC00"));
                    btn.setText("Redo");
                }
                if(number_lesson == nluser) {
                    btn.setBackgroundColor(Color.parseColor("#3CB371"));
                    btn.setText("Start");
                }
            }
        }
        if(func < fuser){
            btn.setBackgroundColor(Color.parseColor("#CCCC00"));
            btn.setText("Redo");
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_lesson = item.getId();
                // int number_lesson = lesson_presenter.lesson_number(id_lesson);
                // int number_id = lesson_presenter.lesson_number(luser);

                if(btn.getText().equals("Redo")){
                    int id_activity_type = lesson_presenter.activity_Type(id_lesson);
                    go_activity(view, id_activity_type, id_lesson);
                }

                if(btn.getText().equals("Start")){
                    new Get_Activity(id_lesson, haveNetworkConnection(), lesson_presenter, _context, _activity);
                    new Get_ActivityDetail(id_lesson, haveNetworkConnection(), lesson_presenter, _context, _activity);

                    int id_activity_type = lesson_presenter.activity_Type(id_lesson);
                    Toast.makeText(_context, ""+id_activity_type, Toast.LENGTH_LONG).show();
                    go_activity(view, id_activity_type, id_lesson);
                }

               /* if(number_lesson <= number_id || number_lesson == 1){

                    new Get_Activity(id_lesson, haveNetworkConnection(), lesson_presenter, _context, _activity);

                    new Get_ActivityDetail(id_lesson, haveNetworkConnection(), lesson_presenter, _context, _activity);

                    int id_activity_type = lesson_presenter.activity_Type(id_lesson);
                    go_activity(view, id_activity_type, id_lesson);
                }*/

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

    public void go_activity(View view, int id_at, int id_l){

        switch (id_at){

            case 1: break;
            case 2: break;

            case 3:
                A3.idlesson = id_l;
                A3.idfunction = func ;
                A3.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A3.class));
                _activity.finish();
                break;

            case 4:
                A4.idlesson = id_l;
                A4.idfunction = func;
                A4.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A4.class));
                _activity.finish();
                break;

            case 5:
                A5.idlesson = id_l;
                A5.idfunction = func;
                A5.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A5.class));
                _activity.finish();
                break;

            case 6:
                A6.idlesson = id_l;
                A6.idfunction = func;
                A6.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A6.class));
                _activity.finish();
                break;

            case 7:
                A7.idlesson = id_l;
                A7.idfunction = func;
                A7.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A7.class));
                _activity.finish();
                break;

            case 8:
                A8.idlesson = id_l;
                A8.idfunction = func;
                A8.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A8.class));
                _activity.finish();
                break;

            case 9:
                A9.idlesson = id_l;
                A9.idfunction = func;
                A9.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A9.class));
                _activity.finish();
                break;

            case 10: break;
            case 11: break;
            case 12: break;
            case 13: break;
            case 14: break;

            case 15:
                //A15.idlesson = id_l;
                //A.idfunction = idfunction ;
                //A15.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A15.class));
                _activity.finish();
                break;

            case 16: break;
            case 17: break;

            case 18:
                A18.idlesson = id_l;
                A18.idfunction = func;
                A18.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A18.class));
                _activity.finish();
                break;

            case 19:
                A19.idlesson = id_l;
                A19.idfunction = func;
                A19.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A19.class));
                _activity.finish();
                break;

            case 20:
                //A20.idlesson = id_l;
                //A.idfunction = idfunction ;
                //A20.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A20.class));
                _activity.finish();
                break;

            case 21: break;

            case 22:
                A22.idlesson = id_l;
                A22.idfunction = func;
                A22.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A22.class));
                _activity.finish();
                break;

            case 23: break;

            case 24:
                A24.idlesson = id_l;
                A24.idfunction = func;
                A24.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A24.class));
                _activity.finish();
                break;

            case 25:
                //A25.idlesson = id_l;
                //A.idfunction = idfunction ;
                //A25.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A25.class));
                break;

            case 26:
                A26.idlesson = id_l;
                A26.idfunction = func;
                A26.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A26.class));
                _activity.finish();
                break;

            case 27:
                A27.idlesson = id_l;
                A27.idfunction = func ;
                A27.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A27.class));
                _activity.finish();
                break;

            case 28:
                A28.idlesson = id_l;
                A28.idfunction = func;
                A28.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A28.class));
                _activity.finish();
                break;

            case 29:
                A29.idlesson = id_l;
                A29.idfunction = func;
                A29.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A29.class));
                _activity.finish();
                break;

            case 30:
                A30.idlesson = id_l;
                A30.idfunction = func;
                A30.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A30.class));
                _activity.finish();
                break;

            case 31:
                //A31.idlesson = id_l;
                //A.idfunction = idfunction ;
                //A31.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A31.class));
                _activity.finish();
                break;

            case 32:
                A32.idlesson = id_l;
                A32.idfunction = func;
                A32.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A32.class));
                _activity.finish();
                break;

            case 33:
                A33.idlesson = id_l;
                A33.idfunction = func;
                A33.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A33.class));
                _activity.finish();
                break;

            case 34:
                A34.idlesson = id_l;
                A34.idfunction = func;
                A34.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A34.class));
                _activity.finish();
                break;

            case 35:
                A35.idlesson = id_l;
                A35.idfunction = func;
                A35.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A35.class));
                _activity.finish();
                break;

            case 36: break;

            case 37:
                A37.idlesson = id_l;
                A37.idfunction = func ;
                A37.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A37.class));
                _activity.finish();
                break;

            case 38:
                //A38.idlesson = id_l;
                //A.idfunction = idfunction ;
                //A38.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A38.class));
                _activity.finish();
                break;

            case 39:
                A39.idlesson = id_l;
                A39.idfunction = func;
                A39.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A39.class));
                _activity.finish();
                break;

            case 40:
                //A40.idlesson = id_l;
                //A.idfunction = idfunction ;
                //A40.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A40.class));
                _activity.finish();
                break;

            case 41:
                //A41.idlesson = id_l;
                //A.idfunction = idfunction ;
                //A41.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A41.class));
                _activity.finish();
                break;

            case 42:
                //A42.idlesson = id_l;
                //A.idfunction = idfunction ;
                //A42.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A42.class));
                _activity.finish();
                break;

            case 43:
                //A43.idlesson = id_l;
                //A.idfunction = idfunction ;
                //A43.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A43.class));
                _activity.finish();
                break;

            case 44:
                //A44.idlesson = id_l;
                //A.idfunction = idfunction ;
                //A44.activitynumber = 1;
                view.getContext().startActivity(new Intent(view.getContext(), A44.class));
                _activity.finish();
                break;
        }
    }
}