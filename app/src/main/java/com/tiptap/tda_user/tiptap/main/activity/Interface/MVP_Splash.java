package com.tiptap.tda_user.tiptap.main.activity.Interface;

import android.content.Context;

import com.tiptap.tda_user.tiptap.main.activity.ViewModel.aspnet_Users;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbLanguage;

import java.util.List;

public interface MVP_Splash {

    interface RequiredViewOps {
        Context getAppContext();
        Context getActivityContext();
    }

    interface ProvidedPresenterOps {
        void onDestroy(boolean isChangingConfiguration);
        void setView(RequiredViewOps view);

        // user
        int getCount_User();
    }

    interface RequiredPresenterOps {
        Context getAppContext();
        Context getActivityContext();
    }

    interface ProvidedModelOps {
        void onDestroy(boolean isChangingConfiguration);

        // user
        int getCount_User();
    }
}