package com.tiptap.tda_user.tiptap.main.activity.Interface;

import android.content.Context;

public interface MVP_Enter {

    interface RequiredViewOps {
        Context getAppContext();
        Context getActivityContext();
    }

    interface ProvidedPresenterOps {
        void onDestroy(boolean isChangingConfiguration);
        void setView(MVP_Enter.RequiredViewOps view);

        void Insert_User(String Q);
        void Delete_User();
    }

    interface RequiredPresenterOps {
        Context getAppContext();
        Context getActivityContext();
    }

    interface ProvidedModelOps {
        void onDestroy(boolean isChangingConfiguration);

        void Insert_User(String Q);
        void Delete_User();
    }
}