package com.tiptap.tda_user.tiptap.main.activity.Presenter;

import android.content.Context;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Login;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbLanguage;
import java.lang.ref.WeakReference;
import java.util.List;

public class Login_Presenter
        implements MVP_Login.ProvidedPresenterOps, MVP_Login.RequiredPresenterOps {

    private WeakReference<MVP_Login.RequiredViewOps> mView;
    private MVP_Login.ProvidedModelOps mModel;

    public Login_Presenter(MVP_Login.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    private MVP_Login.RequiredViewOps getView() throws NullPointerException{
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
    public void setView(MVP_Login.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    public void setModel(MVP_Login.ProvidedModelOps model) {
        mModel = model;
    }

     /*  ------ ProvidedPresenterOps ------  */

    @Override
    public List<TbLanguage> getLanguages() {
        return mModel.getLanguages();
    }

    @Override
    public void Insert_User(String Q) {
        mModel.Insert_User(Q);
    }

    @Override
    public String getMaxRowV_Glossary() {
        return mModel.getMaxRowV_Glossary();
    }

    @Override
    public void Insert_Glossary(String Q) {
        mModel.Insert_Glossary(Q);
    }

    @Override
    public List<Integer> ListGlossary() {
        return mModel.ListGlossary();
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
