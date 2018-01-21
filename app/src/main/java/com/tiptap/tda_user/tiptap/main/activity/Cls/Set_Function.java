package com.tiptap.tda_user.tiptap.main.activity.Cls;

import android.support.v4.view.ViewPager;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Function;
import com.tiptap.tda_user.tiptap.main.activity.view.function_lesson.CardItem;
import com.tiptap.tda_user.tiptap.main.activity.view.function_lesson.CardPagerAdapter_F;
import com.tiptap.tda_user.tiptap.main.activity.view.function_lesson.ShadowTransformer;
import com.tiptap.tda_user.tiptap.main.activity.DB.BaseSetingApi;

public class Set_Function extends BaseSetingApi {
    MVP_Function.ProvidedPresenterOps function_presenter;
    int _id_function;
    ViewPager mViewPager;
    CardPagerAdapter_F mCardAdapter;
    ShadowTransformer mCardShadowTransformer;

    public Set_Function(int id_function, MVP_Function.ProvidedPresenterOps ppo, ViewPager viewPager, CardPagerAdapter_F cardAdapter, ShadowTransformer shadowTransformer) {
        _id_function = id_function;
        function_presenter = ppo;
        mViewPager = viewPager;
        mCardAdapter = cardAdapter;
        mCardShadowTransformer = shadowTransformer;
    }

    public void load() {
        for (int i = 0; i < function_presenter.getCount_Function(); i++) {
            mCardAdapter.addCardItem(function_presenter, _id_function, new CardItem(function_presenter.getListFunction().get(i).get_id(), function_presenter.getListFunction().get(i).getTitle()));
        }
        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mCardShadowTransformer.enableScaling(true);
        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
    }
}