package com.tiptap.tda_user.tiptap.main.activity.Interface;

import android.content.Context;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivityDetail;
import java.util.List;

public interface MVP_A18 {

    interface RequiredViewOps {
        Context getAppContext();
        Context getActivityContext();
    }

    interface ProvidedPresenterOps {
        void onDestroy(boolean isChangingConfiguration);
        void setView(MVP_A18.RequiredViewOps view);

        // Activity
        TbActivity getActivity(int id_lesson , int activityNumber);
        int max_Activitynumber(int id_lesson);
        List<Integer> lesson(int fid);
        List<Integer> function();
        void update_idlesson(int id_lesson);
        void update_idfunction(int id_function);

        // user
        int now_IdLesson();

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
        int max_Activitynumber(int id_lesson);
        List<Integer> lesson(int fid);
        List<Integer> function();
        void update_idlesson(int id_lesson);
        void update_idfunction(int id_function);

        // user
        int now_IdLesson();

        // ActivityDetail
        List<TbActivityDetail> getListActivityDetail(int id_activity);
    }
}
