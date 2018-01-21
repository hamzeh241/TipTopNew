package com.tiptap.tda_user.tiptap.main.activity.Interface;

import android.content.Context;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbFunction;
import java.util.List;

public interface MVP_Function {

    interface RequiredViewOps {
        Context getAppContext();
        Context getActivityContext();
    }

    interface ProvidedPresenterOps {
        void onDestroy(boolean isChangingConfiguration);
        void setView(MVP_Function.RequiredViewOps view);

        // function
        String getMaxRowV_Function();
        int getCount_Function();
        void Insert_Function(String Q);
        List<TbFunction> getListFunction();
        List<Integer> ListFunction();

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

        // function
        String getMaxRowV_Function();
        int getCount_Function();
        void Insert_Function(String Q);
        List<TbFunction> getListFunction();
        List<Integer> ListFunction();

        // user
        int Id_Function();
        int first();
        void update_idfunction(int id);
    }
}