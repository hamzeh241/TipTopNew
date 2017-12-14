package com.tiptap.tda_user.tiptap.main.activity.Interface;

import android.content.Context;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbLanguage;
import java.util.List;

public interface MVP_Login {

    interface RequiredViewOps {
        Context getAppContext();
        Context getActivityContext();
    }

    interface ProvidedPresenterOps {
        void onDestroy(boolean isChangingConfiguration);
        void setView(MVP_Login.RequiredViewOps view);

        // language
        List<TbLanguage> getLanguages();

        // user
        void Insert_User(String Q);

        // Glossary
        String getMaxRowV_Glossary();
        int getMaxId_Glossary();
        void Insert_Glossary(String Q);

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
        void Insert_User(String Q);

        // Glossary
        String getMaxRowV_Glossary();
        int getMaxId_Glossary();
        void Insert_Glossary(String Q);
    }
}