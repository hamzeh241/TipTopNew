package com.tiptap.tda_user.tiptap.main.activity.Presenter;

import android.content.Context;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A39;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivityDetail;
import java.lang.ref.WeakReference;
import java.util.List;

public class A39_Presenter
        implements MVP_A39.ProvidedPresenterOps, MVP_A39.RequiredPresenterOps {

    private WeakReference<MVP_A39.RequiredViewOps> mView;
    private MVP_A39.ProvidedModelOps mModel;

    public A39_Presenter(MVP_A39.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    private MVP_A39.RequiredViewOps getView() throws NullPointerException{
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
    public void setView(MVP_A39.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    public void setModel(MVP_A39.ProvidedModelOps model) {
        mModel = model;
    }

    /*  ------ ProvidedPresenterOps ------  */


    @Override
    public TbActivity getActivity(int id_lesson, int activityNumber) {
        return mModel.getActivity(id_lesson, activityNumber);
    }

    @Override
    public int max_Activitynumber(int id_lesson) {
        return mModel.max_Activitynumber(id_lesson);
    }

    @Override
    public List<Integer> lesson(int fid) {
        return mModel.lesson(fid);
    }

    @Override
    public List<Integer> function() {
        return mModel.function();
    }

    @Override
    public void update_idlesson(int id_lesson) {
        mModel.update_idlesson(id_lesson);
    }

    @Override
    public void update_idfunction(int id_function) {
        mModel.update_idfunction(id_function);
    }

    @Override
    public int now_IdLesson() {
        return mModel.now_IdLesson();
    }

    @Override
    public List<TbActivityDetail> getListActivityDetail(int id_activity) {
        return mModel.getListActivityDetail(id_activity);
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