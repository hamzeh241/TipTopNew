package com.tiptap.tda_user.tiptap.main.activity.Presenter;

import android.content.Context;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Function;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbFunction;
import java.lang.ref.WeakReference;
import java.util.List;

public class Function_Presenter
        implements MVP_Function.ProvidedPresenterOps, MVP_Function.RequiredPresenterOps {

    private WeakReference<MVP_Function.RequiredViewOps> mView;
    private MVP_Function.ProvidedModelOps mModel;

    public Function_Presenter(MVP_Function.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    private MVP_Function.RequiredViewOps getView() throws NullPointerException{
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
    public void setView(MVP_Function.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    public void setModel(MVP_Function.ProvidedModelOps model) {
        mModel = model;
    }

    /*  ------ ProvidedPresenterOps ------  */

    @Override
    public String getMaxRowV_Function() {
        return mModel.getMaxRowV_Function();
    }

    @Override
    public int getMaxId_Function() {
        return mModel.getMaxId_Function();
    }

    @Override
    public int getCount_Function() {
        return mModel.getCount_Function();
    }

    @Override
    public void Insert_Function(String Q) {
        mModel.Insert_Function(Q);
    }

    @Override
    public List<TbFunction> getListFunction() {
        return mModel.getListFunction();
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