package com.tiptap.tda_user.tiptap.main.activity.Interface;

import android.content.Context;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbLanguage;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbUser;
import java.util.List;

public interface MVP_Splash {

    interface RequiredViewOps {
        Context getAppContext();
        Context getActivityContext();
    }

    interface ProvidedPresenterOps {
        void onDestroy(boolean isChangingConfiguration);
        void setView(RequiredViewOps view);

        // language
        List<TbLanguage> getLanguages();

        // user
        int getCount_User();
        TbUser getUser();

        // Activity
        String getMaxRowV_Activity();
        int getMaxId_Activity();
        void Insert_Activity(String Q);
    }

    interface RequiredPresenterOps {
        Context getAppContext();
        Context getActivityContext();
    }

    interface ProvidedModelOps {
        void onDestroy(boolean isChangingConfiguration);

        // language
        List<TbLanguage> getLanguages();

        // user
        int getCount_User();
        TbUser getUser();

        // Activity
        String getMaxRowV_Activity();
        int getMaxId_Activity();
        void Insert_Activity(String Q);
    }
}