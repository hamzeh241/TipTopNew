package com.tiptap.tda_user.tiptap.main.activity.Model;

import android.content.Context;
import android.database.Cursor;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Lesson;
import com.tiptap.tda_user.tiptap.main.activity.DB.DBAdapter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbLesson;
import com.tiptap.tda_user.tiptap.main.activity.DB.PostError;
import java.util.ArrayList;
import java.util.List;
import static com.tiptap.tda_user.tiptap.common.SampleApp.getMethodName;

public class Lesson_Model implements MVP_Lesson.ProvidedModelOps{

    DBAdapter dbAdapter;
    Context context;
    private List<TbLesson> LList;
    private MVP_Lesson.RequiredPresenterOps mPresenter;

    public Lesson_Model
            (MVP_Lesson.RequiredPresenterOps presenter, Context _conContext) {
        this.mPresenter = presenter;
        context=_conContext;
        dbAdapter=new DBAdapter(context);
        LList = new ArrayList<>();
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        if (!isChangingConfiguration) {
            mPresenter = null;
        }
    }

    @Override
    public String getMaxRowV_Lesson() {
        String q="SELECT MAX(RowVersion) as RowVersion FROM TbLesson";
        Cursor cursor=dbAdapter.ExecuteQ(q);
        int count=cursor.getCount();
        cursor.moveToFirst();
        String id="0x0";
        for (int i = 0; i < count; i++) {
            id=cursor.getString(0);
        }
        if(null==id)
            id="0x0";
        return id;
    }

    @Override
    public int getMaxId_Lesson() {
        String q="SELECT [_id] FROM TbLesson ORDER BY _id DESC LIMIT 1";
        Cursor cursor=dbAdapter.ExecuteQ(q);
        int count=cursor.getCount();
        cursor.moveToFirst();
        int id=0;
        for (int i = 0; i < count; i++) {
            id=cursor.getInt(0);
        }
        return id;
    }

    @Override
    public int getCount_Lesson(int fid) {
        String q="SELECT Count([_id]) as x FROM TbLesson where Id_Function = "+ fid;
        Cursor cursor=dbAdapter.ExecuteQ(q);
        int count=cursor.getCount();
        cursor.moveToFirst();
        int id=0;
        for (int i = 0; i < count; i++) {
            id=cursor.getInt(0);
        }
        return id;
    }

    @Override
    public void Insert_Lesson(String Q) {
        dbAdapter.ExecuteQ(Q);
    }

    @Override
    public List<TbLesson> getListLesson(int fid) {
        try{
            String q = "SELECT [_id],[Id_Function],[LessonNumber],[RowVersion] FROM [TbLesson] where Id_Function = "+ fid;
            Cursor cursor = dbAdapter.ExecuteQ(q);
            int count=cursor.getCount();
            cursor.moveToFirst();
            for (int i = 0; i < count; i++) {
                TbLesson app = new TbLesson();
                app.set_id(Integer.parseInt(cursor.getString(0)));
                app.setId_Function(Integer.parseInt(cursor.getString(1)));
                app.setLessonNumber(Integer.parseInt(cursor.getString(2)));
                app.setRowVersion(cursor.getString(3));
                LList.add(app);
                cursor.moveToNext();
            }}
        catch (Exception ex)
        {
            new PostError(context,ex.getMessage(),getMethodName()).postError();

        }
        return LList;
    }
}