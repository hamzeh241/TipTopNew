package com.tiptap.tda_user.tiptap.main.activity.Cls;

import android.content.Context;
import android.support.v4.view.ViewPager;

import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Lesson;
import com.tiptap.tda_user.tiptap.main.activity.view.function_lesson.CardItem;
import com.tiptap.tda_user.tiptap.main.activity.view.function_lesson.CardPagerAdapter_L;
import com.tiptap.tda_user.tiptap.main.activity.view.function_lesson.ShadowTransformer;
import com.tiptap.tda_user.tiptap.main.activity.DB.BaseSetingApi;
import com.tiptap.tda_user.tiptap.main.activity.Model.Lesson_Model;

public class Set_Lesson extends BaseSetingApi {
    MVP_Lesson.ProvidedPresenterOps lesson_presenter;
    int _id;
    ViewPager mViewPager;
    CardPagerAdapter_L mCardAdapter;
    ShadowTransformer mCardShadowTransformer;

    public Set_Lesson(MVP_Lesson.ProvidedPresenterOps ppo,int id, ViewPager viewPager, CardPagerAdapter_L cardAdapter, ShadowTransformer shadowTransformer) {
        lesson_presenter = ppo;
        _id = id;
        mViewPager = viewPager;
        mCardAdapter = cardAdapter;
        mCardShadowTransformer = shadowTransformer;
    }

    public void load() {
        if(lesson_presenter.getCount_Lesson(_id) != 0){
            for (int i = 0; i < lesson_presenter.getCount_Lesson(_id); i++) {
                mCardAdapter.addCardItem(new CardItem(lesson_presenter.getListLesson(_id).get(i).get_id(),lesson_presenter.getListLesson(_id).get(i).getLessonNumber()+""));
            }
            mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
            mCardShadowTransformer.enableScaling(true);
            mViewPager.setAdapter(mCardAdapter);
            mViewPager.setPageTransformer(false, mCardShadowTransformer);
            mViewPager.setOffscreenPageLimit(3);
        }
    }
}