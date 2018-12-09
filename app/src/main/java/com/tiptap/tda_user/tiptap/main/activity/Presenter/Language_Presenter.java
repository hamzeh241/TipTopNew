package com.tiptap.tda_user.tiptap.main.activity.Presenter;

import android.content.Context;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Language;
import java.lang.ref.WeakReference;

public class Language_Presenter
        implements MVP_Language.ProvidedPresenterOps, MVP_Language.RequiredPresenterOps {

    private WeakReference<MVP_Language.RequiredViewOps> mView;
    private MVP_Language.ProvidedModelOps mModel;

    public Language_Presenter(MVP_Language.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    private MVP_Language.RequiredViewOps getView() throws NullPointerException{
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
    public void setView(MVP_Language.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    public void setModel(MVP_Language.ProvidedModelOps model) {
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