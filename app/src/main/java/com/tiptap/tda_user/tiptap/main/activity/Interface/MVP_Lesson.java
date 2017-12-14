package com.tiptap.tda_user.tiptap.main.activity.Interface;

import android.content.Context;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivityDetail;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbLesson;
import java.util.List;

public interface MVP_Lesson {

    interface RequiredViewOps {
        Context getAppContext();
        Context getActivityContext();
    }

    interface ProvidedPresenterOps {
        void onDestroy(boolean isChangingConfiguration);
        void setView(MVP_Lesson.RequiredViewOps view);

        // lesson
        String getMaxRowV_Lesson();
        int getMaxId_Lesson();
        int getCount_Lesson(int fid);
        void Insert_Lesson(String Q);
        List<TbLesson> getListLesson(int fid);

        // activity
        String getMaxRowV_Activity();
        int getMaxId_Activity();
        void Insert_Activity(String Q);
        List<TbActivity> getListActivity(int lid);

        // activity_detail
        String getMaxRowV_ActivityDetail();
        int getMaxId_ActivityDetail();
        int getCount_ActivityDetail(int fid);
        void Insert_ActivityDetail(String Q);
        List<TbActivityDetail> getListActivityDetail(int lid);
    }

    interface RequiredPresenterOps {
        Context getAppContext();
        Context getActivityContext();
    }

    interface ProvidedModelOps {
        void onDestroy(boolean isChangingConfiguration);

        // lesson
        String getMaxRowV_Lesson();
        int getMaxId_Lesson();
        int getCount_Lesson(int fid);
        void Insert_Lesson(String Q);
        List<TbLesson> getListLesson(int fid);

        // activity
        String getMaxRowV_Activity();
        int getMaxId_Activity();
        void Insert_Activity(String Q);
        List<TbActivity> getListActivity(int lid);

        // activity_detail
        String getMaxRowV_ActivityDetail();
        int getMaxId_ActivityDetail();
        int getCount_ActivityDetail(int fid);
        void Insert_ActivityDetail(String Q);
        List<TbActivityDetail> getListActivityDetail(int lid);
    }
}