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
import com.tiptap.tda_user.tiptap.main.activity.view.activity.Act_1;

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

                    int id_activity_type = lesson_presenter.activity_Type(id_lesson);

                    new Get_Activity(id_lesson, haveNetworkConnection(), lesson_presenter, _context, _activity);
                    Toast.makeText(_context, lesson_presenter.getCount_Activity(id_lesson) + " : activity", Toast.LENGTH_LONG).show();

                    new Get_ActivityDetail(id_lesson, haveNetworkConnection(), lesson_presenter, _context, _activity);
                    Toast.makeText(_context, lesson_presenter.getCount_Activity(id_lesson) + " : activity_DETAIL", Toast.LENGTH_LONG).show();

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

            ////////////////////////////////////////////////////////////////////////////////
            /*                                     گوش دادن                                 */
            ////////////////////////////////////////////////////////////////////////////////
            case 1:
                // گوش دادن 1
                // یک تصویر و متن ثابت چندین صوت با جواب دادن
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            case 2:
                // گوش دادن 2
                // چندین تصویر و متن و صوت با جواب دادن
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            case 3:
                // گوش دادن 3
                // فقط گوش دادن و بدون احتیاج به جواب دادن
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            case 4:
                // گوش دادن 4
                // شنیدن چندین عبارت و تکرار همان عبارت شنیده شده به صورت تایپی
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            case 5:
                // گوش دادن 5
                // گوش دادن به چندین متن و تکرارآنها
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            case 6:
                // گوش دادن 6
                // یک عکس با چند متن و تکرار صوتی متن صحیح
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            ////////////////////////////////////////////////////////////////////////////////
            /*                                     ترجمه کردن                               */
            ////////////////////////////////////////////////////////////////////////////////
            case 7:
                // ترجمه کردن 1
                // تطبیق عبارات یک عکس ثابت
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            case 8:
                // ترجمه کردن 2
                // ترجمه تایپ کردن
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            case 9:
                // ترجمه کردن 3
                // تطبیق عبارات بدون عکس
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            ////////////////////////////////////////////////////////////////////////////////
            /*                                     مکالمه                                   */
            ////////////////////////////////////////////////////////////////////////////////
            case 10:
                // مکالمه 1
                // گوش دادن به یک عبارت بدون عکس و بدون جواب
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            case 11:
                // مکالمه 2
                // گوش دادن به یک عبارت با یک عکس و بدون جواب
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            case 12:
                // مکالمه 3
                // گوش دادن به چندین عبارت و جواب دادن صوتی بدون عکس همراه متن
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            case 13:
                // مکالمه 4
                // گوش دادن به چندین عبارت و جواب دادن صوتی با یک عکس همراه متن
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            case 14:
                // مکالمه 5
                // گوش دادن به چندین عبارت و جواب دادن تایپی بدون عکس همراه متن
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            case 15:
                // مکالمه 6
                // گوش دادن به چندین عبارت و جواب دادن تایپی با یک عکس همراه متن
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            ////////////////////////////////////////////////////////////////////////////////
            /*                                     مرتب کردن                                */
            ////////////////////////////////////////////////////////////////////////////////
            case 16:
                // مرتب کردن 1
                // مرتب کردن چندین عبارت درهم
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            case 17:
                // مرتب کردن 2
                // مرتب کردن چندین عبارت درهم با یک عکس
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            ////////////////////////////////////////////////////////////////////////////////
            /*                                     پر کردن جای خالی                          */
            ////////////////////////////////////////////////////////////////////////////////
            case 18:
                // پرکردن جای خالی 1
                // دیدن یک عبارت و پرکردن جاهای خالی به صورت تایپی
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            case 19:
                // پرکردن جای خالی 2
                // دیدن یک عبارت و پرکردن جاهای خالی به صورت انتخابی
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            case 20:
                // پرکردن جای خالی 3
                // دیدن عکس و پرکردن جاهای خالی (حروف)به صورت تایپی
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            case 21:
                // پرکردن جای خالی 4
                // دیدن چندین عبارت متنی و عکس و پرکردن جاهای خالی به صورت تایپی
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            case 22:
                // پرکردن جای خالی 5
                // یک عکس با دو سوال و جواب دادن به صورت تایپی
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            ////////////////////////////////////////////////////////////////////////////////
            /*                                      گوش دادن و نوشتن                          */
            ////////////////////////////////////////////////////////////////////////////////

            case 23:
                // گوش دادن و نوشتن
                // گوش دادن به یک متن و عکس و پرکردن جاهای خالی به صورت تایپی
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            ////////////////////////////////////////////////////////////////////////////////
            /*                                      تست                                   */
            ////////////////////////////////////////////////////////////////////////////////

            case 24:
                // تست 1
                // دیدن چندین جواب متنی و انتخاب جواب درست
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;

            case 25:
                // تست 2
                // دیدن چندین جواب متنی و عکس و انتخاب جواب درست
                view.getContext().startActivity(new Intent(view.getContext(), Act_1.class));
                break;
        }
    }
}