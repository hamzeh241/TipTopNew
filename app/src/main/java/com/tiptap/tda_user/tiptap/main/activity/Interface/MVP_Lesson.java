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
        int getCount_Lesson(int fid);
        void Insert_Lesson(String Q);
        List<TbLesson> getListLesson(int fid);
        List<Integer> ListLesson();
        List<Integer> ListActivity();
        List<Integer> ListActivityDetail();
        int lesson_number(int lid);
        int lesson_id(int fid , int lid);
        int Id_Lesson();
        void update_idlesson(int id_lesson);
        int findFunction(int lid);

        // activity
        String getMaxRowV_Activity();
        int getCount_Activity(int lid);
        void Insert_Activity(String Q);
        List<TbActivity> getListActivity(int lid);
        int activity_Type(int lid);
        String your_name();

        // activity_detail
        String getMaxRowV_ActivityDetail();
        int getCount_ActivityDetail();
        void Insert_ActivityDetail(String Q);
        List<TbActivityDetail> getListActivityDetail(int aid);

        // user
        int Id_Function();
        int first();
        void update_idfunction(int id);
    }

    interface RequiredPresenterOps {
        Context getAppContext();
        Context getActivityContext();
    }

    interface ProvidedModelOps {
        void onDestroy(boolean isChangingConfiguration);

        // lesson
        String getMaxRowV_Lesson();
        int getCount_Lesson(int fid);
        void Insert_Lesson(String Q);
        List<TbLesson> getListLesson(int fid);
        List<Integer> ListLesson();
        List<Integer> ListActivity();
        List<Integer> ListActivityDetail();
        int lesson_number(int lid);
        int lesson_id(int fid , int lid);
        int Id_Lesson();
        void update_idlesson(int id_lesson);
        int findFunction(int lid);

        // activity
        String getMaxRowV_Activity();
        int getCount_Activity(int lid);
        void Insert_Activity(String Q);
        List<TbActivity> getListActivity(int aid);
        int activity_Type(int lid);
        String your_name();

        // activity_detail
        String getMaxRowV_ActivityDetail();
        int getCount_ActivityDetail();
        void Insert_ActivityDetail(String Q);
        List<TbActivityDetail> getListActivityDetail(int aid);

        // user
        int Id_Function();
        int first();
        void update_idfunction(int id);
    }
}