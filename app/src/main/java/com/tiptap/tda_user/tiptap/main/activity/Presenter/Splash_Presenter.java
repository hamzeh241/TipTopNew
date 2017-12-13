package com.tiptap.tda_user.tiptap.main.activity.Presenter;

import android.content.Context;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Splash;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbLanguage;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbUser;
import java.lang.ref.WeakReference;
import java.util.List;

public class Splash_Presenter
        implements MVP_Splash.ProvidedPresenterOps, MVP_Splash.RequiredPresenterOps{

    private WeakReference<MVP_Splash.RequiredViewOps> mView;
    private MVP_Splash.ProvidedModelOps mModel;

    public Splash_Presenter(MVP_Splash.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    private MVP_Splash.RequiredViewOps getView() throws NullPointerException{
        if ( mView != null )
            return mView.get();
        else
            throw new NullPointerException("View is unavailable");
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        mView = null;
        mModel.onDestroy(isChangingConfiguration);
        if ( !isChangingConfiguration ) {
            mModel = null;
        }
    }

    @Override
    public void setView(MVP_Splash.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    public void setModel(MVP_Splash.ProvidedModelOps model) {
        mModel = model;
        // start to load data
        //loadData();
    }

    /*  ------ ProvidedPresenterOps ------  */

    @Override
    public List<TbLanguage> getLanguages() {
        return mModel.getLanguages();
    }

    @Override
    public int getCount_User() {
        return mModel.getCount_User();
    }

    @Override
    public TbUser getUser() {
        return mModel.getUser();
    }

    @Override
    public String getMaxRowV_Activity() {
        return mModel.getMaxRowV_Activity();
    }

    @Override
    public int getMaxId_Activity() {
        return mModel.getMaxId_Activity();
    }

    @Override
    public void Insert_Activity(String Q) {
        mModel.Insert_Activity(Q);
    }

    /*  ------ RequiredPresenterOps ------  */
    @Override
    public Context getAppContext() {
        try {
            return getView().getAppContext();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public Context getActivityContext() {
        try {
            return getView().getActivityContext();
        } catch (NullPointerException e) {
            return null;
        }
    }
}