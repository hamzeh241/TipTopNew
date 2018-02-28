package com.tiptap.tda_user.tiptap.main.activity.Interface;

import android.content.Context;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivityDetail;
import java.util.List;

public interface MVP_A33 {

    interface RequiredViewOps {
        Context getAppContext();
        Context getActivityContext();
    }

    interface ProvidedPresenterOps {
        void onDestroy(boolean isChangingConfiguration);
        void setView(MVP_A33.RequiredViewOps view);

        // Activity
        TbActivity getActivity(int id_lesson , int activityNumber);
        int countActivity(int id_lesson);
        TbActivity getActivity2(int id_activity);
        int max_Activitynumber(int id_lesson);
        List<Integer> lesson(int fid);
        List<Integer> activity_false(int lid);
        List<Integer> activity_true(int lid);
        List<Integer> function();
        void update_idlesson(int id_lesson);
        void update_idfunction(int id_function);
        void update_activity(int id_activity);
        void false_activitys(int id_lesson);
        int getlanguage();

        // user
        int now_IdLesson();
        String userName();

        // ActivityDetail
        List<TbActivityDetail> getListActivityDetail(int id_activity);
    }

    interface RequiredPresenterOps {
        Context getAppContext();
        Context getActivityContext();
    }

    interface ProvidedModelOps {
        void onDestroy(boolean isChangingConfiguration);

        // Activity
        TbActivity getActivity(int id_lesson , int activityNumber);
        int countActivity(int id_lesson);
        TbActivity getActivity2(int id_activity);
        int max_Activitynumber(int id_lesson);
        List<Integer> lesson(int fid);
        List<Integer> activity_false(int lid);
        List<Integer> activity_true(int lid);
        List<Integer> function();
        void update_idlesson(int id_lesson);
        void update_idfunction(int id_function);
        void update_activity(int id_activity);
        void false_activitys(int id_lesson);
        int getlanguage();

        // user
        int now_IdLesson();
        String userName();

        // ActivityDetail
        List<TbActivityDetail> getListActivityDetail(int id_activity);
    }
}
