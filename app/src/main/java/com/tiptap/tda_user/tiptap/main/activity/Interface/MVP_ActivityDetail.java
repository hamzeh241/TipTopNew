package com.tiptap.tda_user.tiptap.main.activity.Interface;

import android.content.Context;

import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivityDetail;

import java.util.List;

public interface MVP_ActivityDetail {

    interface RequiredViewOps {
        Context getAppContext();
        Context getActivityContext();
    }

    interface ProvidedPresenterOps {
        void onDestroy(boolean isChangingConfiguration);
        void setView(MVP_Lesson.RequiredViewOps view);
        // void NewActivity(String Text);
        //Activity getActivity(int position);
        //int getActivitesCount();
        //void Empty();
    }

    interface RequiredPresenterOps {
        Context getAppContext();
        Context getActivityContext();
    }

    interface ProvidedModelOps {
        void onDestroy(boolean isChangingConfiguration);
        String getMaxRowV_ActivityDetail();
        int getMaxId_ActivityDetail();
        int getCount_ActivityDetail(int aid);
        void Insert_ActivityDetail(String Q);
        List<TbActivityDetail> getListActivityDetail(int aid);
    }
}