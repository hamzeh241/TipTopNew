package com.tiptap.tda_user.tiptap.main.activity.Presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Lesson;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbLesson;

import java.lang.ref.WeakReference;
import java.util.List;

public class Lesson_Presenter
        implements MVP_Lesson.ProvidedPresenterOps, MVP_Lesson.RequiredPresenterOps {

    private WeakReference<MVP_Lesson.RequiredViewOps> mView;
    private MVP_Lesson.ProvidedModelOps mModel;

    public Lesson_Presenter(MVP_Lesson.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    private MVP_Lesson.RequiredViewOps getView() throws NullPointerException{
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
    public void setView(MVP_Lesson.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    public void setModel(MVP_Lesson.ProvidedModelOps model) {
        mModel = model;
    }

     /*  ------ ProvidedPresenterOps ------  */

    @Override
    public String getMaxRowV_Lesson() {
        return mModel.getMaxRowV_Lesson();
    }

    @Override
    public int getMaxId_Lesson() {
        return mModel.getMaxId_Lesson();
    }

    @Override
    public int getCount_Lesson(int fid) {
        return mModel.getCount_Lesson(fid);
    }

    @Override
    public void Insert_Lesson(String Q) {
        mModel.Insert_Lesson(Q);
    }

    @Override
    public List<TbLesson> getListLesson(int fid) {
        return mModel.getListLesson(fid);
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