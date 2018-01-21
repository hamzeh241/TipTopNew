package com.tiptap.tda_user.tiptap.main.activity.Model;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.tiptap.tda_user.tiptap.main.activity.DB.DBAdapter;
import com.tiptap.tda_user.tiptap.main.activity.DB.PostError;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A19;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivityDetail;
import java.util.ArrayList;
import java.util.List;
import static com.tiptap.tda_user.tiptap.common.SampleApp.getMethodName;

public class A19_Model implements MVP_A19.ProvidedModelOps {

    private MVP_A19.RequiredPresenterOps mPresenter;
    DBAdapter dbAdapter;
    Context context;
    private TbActivity act;
    private List<TbActivityDetail> ad_List;
    List<Integer> less;
    List<Integer> func;

    public A19_Model(MVP_A19.RequiredPresenterOps presenter, Context _conContext) {
        mPresenter = presenter;
        context = _conContext;
        dbAdapter = new DBAdapter(context);
        ad_List = new ArrayList<>();
        less = new ArrayList<>();
        func = new ArrayList<>();
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        if (!isChangingConfiguration) {
            mPresenter = null;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////

    @Override
    public TbActivity getActivity(int id_lesson, int activityNumber) {
        try {
            String q = "SELECT [_id],[Id_Lesson],[ActivityNumber],[Id_ActivityType],[Title1],[Title2],[Path1],[Path2],[IsNote],[RowVersion] FROM [TbActivity] where Id_Lesson = " + id_lesson + " and ActivityNumber = " + activityNumber;
            Cursor cursor = dbAdapter.ExecuteQ(q);
            int count = cursor.getCount();
            cursor.moveToFirst();
            for (int i = 0; i < count; i++) {
                TbActivity app = new TbActivity();
                app.set_id(Integer.parseInt(cursor.getString(0)));
                app.setId_Lesson(Integer.parseInt(cursor.getString(1)));
                app.setActivityNumber(Integer.parseInt(cursor.getString(2)));
                app.setId_ActivityType(Integer.parseInt(cursor.getString(3)));
                app.setTitle1(cursor.getString(4));
                app.setTitle2(cursor.getString(5));
                app.setPath1(cursor.getString(6));
                app.setPath2(cursor.getString(7));
                app.setIsNote(Boolean.parseBoolean(cursor.getString(8)));
                app.setRowVersion(cursor.getString(9));
                act = app;
                cursor.moveToNext();
            }
        } catch (Exception ex) {
            new PostError(context, ex.getMessage(), getMethodName()).postError();
        }
        return act;
    }

    @Override
    public int max_Activitynumber(int id_lesson) {
        String q="SELECT MAX(ActivityNumber) as ActivityNumber FROM TbActivity where Id_Lesson = "+ id_lesson;
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
    public List<Integer> lesson(int fid) {
        try {
            String q = "SELECT [_id] FROM [TbLesson] where [Id_Function]= "+ fid +" ORDER BY [LessonNumber] ASC";
            Cursor cursor = dbAdapter.ExecuteQ(q);
            int count = cursor.getCount();
            cursor.moveToFirst();
            for (int i = 0; i < count; i++) {
                less.add(cursor.getInt(0));
                cursor.moveToNext();
            }
        } catch (Exception ex) {
            new PostError(context, ex.getMessage(), getMethodName()).postError();
        }
        return less;
    }

    @Override
    public void update_idlesson(int id_lesson) {
        Toast.makeText(context, "update lesson"+id_lesson, Toast.LENGTH_LONG).show();
        String q = "update [aspnet_Users] set [Id_Lesson] = "+id_lesson;
        Cursor cursor = dbAdapter.ExecuteQ(q);
        int count = cursor.getCount();
        cursor.moveToFirst();
    }

    @Override
    public int now_IdLesson() {
        String q="SELECT [Id_Lesson] FROM [aspnet_Users]";
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
    public List<TbActivityDetail> getListActivityDetail(int id_activity) {
        try {
            String q = "SELECT [_id],[Path1],[Path2],[Id_Activity],[Title1],[Title2],[IsAnswer],[OrferAnswer],[OrderPreview],[RowVersion] FROM [TbActivityDetail] where Id_Activity = " + id_activity;
            Cursor cursor = dbAdapter.ExecuteQ(q);
            int count = cursor.getCount();
            cursor.moveToFirst();
            for (int i = 0; i < count; i++) {
                TbActivityDetail app = new TbActivityDetail();
                app.set_id(Integer.parseInt(cursor.getString(0)));
                app.setPath1(cursor.getString(1));
                app.setPath2(cursor.getString(2));
                app.setId_Activity(Integer.parseInt(cursor.getString(3)));
                app.setTitle1(cursor.getString(4));
                app.setTitle2(cursor.getString(5));
                app.setIsAnswer(Boolean.parseBoolean(cursor.getString(6)));
                app.setOrferAnswer(Integer.parseInt(cursor.getString(7)));
                app.setOrderPreview(Integer.parseInt(cursor.getString(8)));
                app.setRowVersion(cursor.getString(9));
                ad_List.add(app);
                cursor.moveToNext();
            }
        } catch (Exception ex) {
            new PostError(context, ex.getMessage(), getMethodName()).postError();
        }
        return ad_List;
    }

    @Override
    public int count_ActivityDetail(int id_activity) {
        String q="SELECT Count([_id]) as x FROM TbActivityDetail where Id_Activity = " + id_activity;
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
    public List<Integer> function() {
        try {
            String q = "SELECT [_id] FROM [TbFunction]";
            Cursor cursor = dbAdapter.ExecuteQ(q);
            int count = cursor.getCount();
            cursor.moveToFirst();
            for (int i = 0; i < count; i++) {
                func.add(cursor.getInt(0));
                cursor.moveToNext();
            }
        } catch (Exception ex) {
            new PostError(context, ex.getMessage(), getMethodName()).postError();
        }
        return func;
    }

    @Override
    public void update_idfunction(int id_function) {
        Toast.makeText(context, "update function"+id_function, Toast.LENGTH_LONG).show();
        String q = "update [aspnet_Users] set [Id_Function] = "+id_function;
        Cursor cursor = dbAdapter.ExecuteQ(q);
        int count = cursor.getCount();
        cursor.moveToFirst();
    }
}