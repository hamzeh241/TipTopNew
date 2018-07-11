package com.tiptap.tda_user.tiptap.main.activity.Presenter;

import android.content.Context;

import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivityDetail;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by tafsiri on 7/1/2018.
 */

public class Main_Presenter implements MVP_Main.ProvidedPresenterOps, MVP_Main.RequiredPresenterOps {
    private WeakReference<MVP_Main.RequiredViewOps> mView;
    private MVP_Main.ProvidedModelOps mModel;

    public Main_Presenter(MVP_Main.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    private MVP_Main.RequiredViewOps getView() throws NullPointerException{
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
    public void setView(MVP_Main.RequiredViewOps view) {
            mView = new WeakReference<>(view);
    }
    public void setModel(MVP_Main.ProvidedModelOps model) {
        mModel = model;
    }

    @Override
    public TbActivity getActivity(int id_lesson, int activityNumber) {
        return mModel.getActivity(id_lesson, activityNumber);
    }

    @Override
    public int countActivity(int id_lesson) {
        return mModel.countActivity(id_lesson);
    }

    @Override
    public TbActivity getActivity2(int id_activity) {
        return mModel.getActivity2(id_activity);
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
    public List<Integer> activity_false(int lid) {
        return mModel.activity_false(lid);
    }

    @Override
    public List<Integer> activity_true(int lid) {
        return mModel.activity_true(lid);
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
    public void update_activity(int id_activity) {
        mModel.update_activity(id_activity);
    }

    @Override
    public void false_activitys(int id_lesson) {
        mModel.false_activitys(id_lesson);
    }

    @Override
    public int getlanguage() {
        return mModel.getlanguage();
    }

    @Override
    public int now_IdLesson() {
        return mModel.now_IdLesson();
    }

    @Override
    public List<TbActivityDetail> getListActivityDetail(int id_activity) {
        return mModel.getListActivityDetail(id_activity);
    }

    @Override
    public int count_ActivityDetail(int id_activity) {
        return mModel.count_ActivityDetail(id_activity);
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