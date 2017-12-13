package com.tiptap.tda_user.tiptap.main.activity.Interface;

import android.content.Context;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import java.util.List;

public interface MVP_Activity {

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
        String getMaxRowV_Activity();
        int getMaxId_Activity();
        int getCount_Activity(int lid);
        void Insert_Activity(String Q);
        List<TbActivity> getListActivity(int lid);
    }
}