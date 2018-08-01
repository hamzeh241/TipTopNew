package com.tiptap.tda_user.tiptap.main.activity.Cls;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Lesson;
import com.tiptap.tda_user.tiptap.main.activity.view.lesson.CardItem;
import com.tiptap.tda_user.tiptap.main.activity.view.lesson.CardPagerAdapter_L;
import com.tiptap.tda_user.tiptap.main.activity.view.lesson.ShadowTransformer;
import com.tiptap.tda_user.tiptap.main.activity.DB.BaseSetingApi;

public class Set_Lesson extends BaseSetingApi {
    MVP_Lesson.ProvidedPresenterOps lesson_presenter;
    Context _context;
    Activity _activity;
    int _nid;
    ViewPager mViewPager;
    CardPagerAdapter_L mCardAdapter;
    ShadowTransformer mCardShadowTransformer;

    public Set_Lesson(MVP_Lesson.ProvidedPresenterOps ppo, Context context, Activity activity, int nid, ViewPager viewPager, CardPagerAdapter_L cardAdapter, ShadowTransformer shadowTransformer) {
        lesson_presenter = ppo;
        _context = context;
        _activity = activity;
        _nid = nid;
        mViewPager = viewPager;
        mCardAdapter = cardAdapter;
        mCardShadowTransformer = shadowTransformer;
    }

    public void load() {
        //Toast.makeText(_context, _nid+" : fid", Toast.LENGTH_LONG).show();
        if(lesson_presenter.getCount_Lesson(_nid) != 0){
            //Toast.makeText(_context, lesson_presenter.getCount_Lesson(_nid)+" : size fid", Toast.LENGTH_LONG).show();
            for (int i = 0; i < lesson_presenter.getCount_Lesson(_nid); i++) {
                mCardAdapter.addCardItem(lesson_presenter, _nid, _context, _activity, new CardItem(lesson_presenter.getListLesson(_nid).get(i).get_id(),lesson_presenter.getListLesson(_nid).get(i).getLessonNumber()+""));
            }
            mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
            mCardShadowTransformer.enableScaling(true);
            mViewPager.setAdapter(mCardAdapter);
            mViewPager.setPageTransformer(false, mCardShadowTransformer);
            mViewPager.setOffscreenPageLimit(3);
        }
    }
}