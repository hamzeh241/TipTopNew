package com.tiptap.tda_user.tiptap.main.activity.Presenter;

import android.content.Context;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Enter;
import java.lang.ref.WeakReference;

public class Enter_Presenter
        implements MVP_Enter.ProvidedPresenterOps, MVP_Enter.RequiredPresenterOps {

    private WeakReference<MVP_Enter.RequiredViewOps> mView;
    private MVP_Enter.ProvidedModelOps mModel;

    public Enter_Presenter(MVP_Enter.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    private MVP_Enter.RequiredViewOps getView() throws NullPointerException{
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
    public void setView(MVP_Enter.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void Insert_User(String Q) {
        mModel.Insert_User(Q);
    }

    @Override
    public void Delete_User() {
        mModel.Delete_User();
    }

    public void setModel(MVP_Enter.ProvidedModelOps model) {
        mModel = model;
    }

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