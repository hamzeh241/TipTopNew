package com.tiptap.tda_user.tiptap.main.activity.Interface;

import android.content.Context;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbLesson;
import java.util.List;

public interface MVP_Lesson {

    interface RequiredViewOps {
        Context getAppContext();
        Context getActivityContext();
    }

    interface ProvidedPresenterOps {
        void onDestroy(boolean isChangingConfiguration);
        void setView(MVP_Lesson.RequiredViewOps view);
        String getMaxRowV_Lesson();
        int getMaxId_Lesson();
        int getCount_Lesson(int fid);
        void Insert_Lesson(String Q);
        List<TbLesson> getListLesson(int fid);
    }

    interface RequiredPresenterOps {
        Context getAppContext();
        Context getActivityContext();
    }

    interface ProvidedModelOps {
        void onDestroy(boolean isChangingConfiguration);
        String getMaxRowV_Lesson();
        int getMaxId_Lesson();
        int getCount_Lesson(int fid);
        void Insert_Lesson(String Q);
        List<TbLesson> getListLesson(int fid);
    }
}