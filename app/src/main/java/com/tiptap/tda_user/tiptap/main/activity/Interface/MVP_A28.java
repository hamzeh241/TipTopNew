package com.tiptap.tda_user.tiptap.main.activity.Interface;

import android.content.Context;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivityDetail;
import java.util.List;

public interface MVP_A28 {

    interface RequiredViewOps {
        Context getAppContext();
        Context getActivityContext();
    }

    interface ProvidedPresenterOps {
        void onDestroy(boolean isChangingConfiguration);
        void setView(MVP_A28.RequiredViewOps view);

        // Activity
        TbActivity getActivity(int id_lesson , int activityNumber);
        int max_Activitynumber(int id_lesson);

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

        // ActivityDetail
        List<TbActivityDetail> getListActivityDetail(int id_activity);
    }
}