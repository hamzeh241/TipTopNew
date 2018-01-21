package com.tiptap.tda_user.tiptap.main.activity.Interface;

import android.content.Context;

public interface MVP_Splash {

    interface RequiredViewOps {
        Context getAppContext();
        Context getActivityContext();
    }

    interface ProvidedPresenterOps {
        void onDestroy(boolean isChangingConfiguration);
        void setView(MVP_Splash.RequiredViewOps view);

        // user
        int getCount_User();
        int Id_Function();
    }

    interface RequiredPresenterOps {
        Context getAppContext();
        Context getActivityContext();
    }

    interface ProvidedModelOps {
        void onDestroy(boolean isChangingConfiguration);

        // user
        int getCount_User();
        int Id_Function();
    }
}