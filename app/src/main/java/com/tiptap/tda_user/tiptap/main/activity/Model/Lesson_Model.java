package com.tiptap.tda_user.tiptap.main.activity.Model;

import android.content.Context;
import android.database.Cursor;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Lesson;
import com.tiptap.tda_user.tiptap.main.activity.DB.DBAdapter;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivityDetail;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbLesson;
import com.tiptap.tda_user.tiptap.main.activity.DB.PostError;
import java.util.ArrayList;
import java.util.List;
import static com.tiptap.tda_user.tiptap.common.SampleApp.getMethodName;

public class Lesson_Model implements MVP_Lesson.ProvidedModelOps{

    DBAdapter dbAdapter;
    Context context;
    private List<TbLesson> LList;
    private List<TbActivity> AList;
    private List<TbActivityDetail> ADList;
    private List<Integer> idlesson;
    private List<Integer> idactivity;
    private List<Integer> idactivitydetail;
    private MVP_Lesson.RequiredPresenterOps mPresenter;

    public Lesson_Model
            (MVP_Lesson.RequiredPresenterOps presenter, Context _conContext) {
        this.mPresenter = presenter;
        context=_conContext;
        dbAdapter=new DBAdapter(context);
        LList = new ArrayList<>();
        AList = new ArrayList<>();
        ADList = new ArrayList<>();
        idlesson = new ArrayList<>();
        idactivity = new ArrayList<>();
        idactivitydetail = new ArrayList<>();
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        if (!isChangingConfiguration) {
            mPresenter = null;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////

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
            }
        } catch (Exception ex) {
            new PostError(context,ex.getMessage(),getMethodName()).postError();

        }
        return LList;
    }

    @Override
    public List<Integer> ListLesson() {
        try{
            String q = "SELECT [_id] FROM [TbLesson]";
            Cursor cursor = dbAdapter.ExecuteQ(q);
            int count=cursor.getCount();
            cursor.moveToFirst();
            for (int i = 0; i < count; i++) {
                idlesson.add(Integer.parseInt(cursor.getString(0)));
                cursor.moveToNext();
            }
        } catch (Exception ex) {
            new PostError(context,ex.getMessage(),getMethodName()).postError();
        }
        return idlesson;
    }

    @Override
    public List<Integer> ListActivity() {
        try{
            String q = "SELECT [_id] FROM [TbActivity]";
            Cursor cursor = dbAdapter.ExecuteQ(q);
            int count=cursor.getCount();
            cursor.moveToFirst();
            for (int i = 0; i < count; i++) {
                idactivity.add(Integer.parseInt(cursor.getString(0)));
                cursor.moveToNext();
            }
        } catch (Exception ex) {
            new PostError(context,ex.getMessage(),getMethodName()).postError();
        }
        return idactivity;
    }

    @Override
    public List<Integer> ListActivityDetail() {
        try{
            String q = "SELECT [_id] FROM [TbActivityDetail]";
            Cursor cursor = dbAdapter.ExecuteQ(q);
            int count=cursor.getCount();
            cursor.moveToFirst();
            for (int i = 0; i < count; i++) {
                idactivitydetail.add(Integer.parseInt(cursor.getString(0)));
                cursor.moveToNext();
            }
        } catch (Exception ex) {
            new PostError(context,ex.getMessage(),getMethodName()).postError();
        }
        return idactivitydetail;
    }

    @Override
    public int lesson_number(int lid) {
        String q="SELECT LessonNumber FROM TbLesson where _id = "+ lid;
        Cursor cursor=dbAdapter.ExecuteQ(q);
        int count=cursor.getCount();
        cursor.moveToFirst();
        int num = 0;
        for (int i = 0; i < count; i++) {
            num=cursor.getInt(0);
        }
        return num;
    }

    @Override
    public int lesson_id(int fid , int lid) {
        String q="SELECT _id FROM TbLesson where Id_Function = "+ fid + " and LessonNumber = " + lid;
        Cursor cursor=dbAdapter.ExecuteQ(q);
        int count=cursor.getCount();
        cursor.moveToFirst();
        int num = 0;
        for (int i = 0; i < count; i++) {
            num=cursor.getInt(0);
        }
        return num;
    }

    /////////////////////////////////////////////////////////////////////////////////

    @Override
    public String getMaxRowV_Activity() {
        String q="SELECT MAX(RowVersion) as RowVersion FROM TbActivity";
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
    public int getCount_Activity(int lid) {
        String q="SELECT Count([_id]) as x FROM TbActivity where Id_Lesson = "+ lid;
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
    public void Insert_Activity(String Q) {
        dbAdapter.ExecuteQ(Q);
    }

    @Override
    public List<TbActivity> getListActivity(int lid) {
        try{
            String q = "SELECT [_id],[Id_Lesson],[ActivityNumber],[Id_ActivityType],[Title1],[Title2],[Path1],[Path2],[IsNote],[RowVersion] FROM [TbActivity] where Id_Lesson = "+ lid;
            Cursor cursor = dbAdapter.ExecuteQ(q);
            int count=cursor.getCount();
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
                AList.add(app);
                cursor.moveToNext();
            }}
        catch (Exception ex) {
            new PostError(context,ex.getMessage(),getMethodName()).postError();
        }
        return AList;
    }

    @Override
    public int activity_Type(int lid) {
        String q="SELECT Id_ActivityType FROM [TbActivity] WHERE ActivityNumber = 1 AND Id_Lesson = "+ lid;
        Cursor cursor=dbAdapter.ExecuteQ(q);
        int count=cursor.getCount();
        cursor.moveToFirst();
        int id=0;
        for (int i = 0; i < count; i++) {
            id=cursor.getInt(0);
        }
        return id;
    }

    /////////////////////////////////////////////////////////////////////////////////

    @Override
    public String getMaxRowV_ActivityDetail() {
        String q="SELECT MAX(RowVersion) as RowVersion FROM [TbActivityDetail]";
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
    public int getCount_ActivityDetail() {
        String q="SELECT Count([_id]) as x FROM TbActivityDetail";
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
    public void Insert_ActivityDetail(String Q) {
        dbAdapter.ExecuteQ(Q);
    }

    @Override
    public List<TbActivityDetail> getListActivityDetail(int aid) {
        try{
            String q = "SELECT [_id],[Path1],[Path2],[Id_Activity],[Title1],[Title2],[IsAnswer],[OrferAnswer],[OrderPreview],[RowVersion] FROM [TbActivityDetail] where Id_Activity = "+ aid;
            Cursor cursor = dbAdapter.ExecuteQ(q);
            int count=cursor.getCount();
            cursor.moveToFirst();
            for (int i = 0; i < count; i++) {
                TbActivityDetail app = new TbActivityDetail();
                app.set_id(Integer.parseInt(cursor.getString(0)));
                app.setPath1(cursor.getString(1));
                app.setPath2(cursor.getString(2));
                app.setId_Activity(Integer.parseInt(cursor.getString(3)));
                app.setTitle1(cursor.getString(4));
                app.setTitle2(cursor.getString(5));
                app.setIsAnswer(cursor.getString(6));
                app.setOrferAnswer(cursor.getString(7));
                app.setOrderPreview(cursor.getString(8));
                app.setRowVersion(cursor.getString(9));
                ADList.add(app);
                cursor.moveToNext();
            }
        } catch (Exception ex) {
            new PostError(context,ex.getMessage(),getMethodName()).postError();
        }
        return ADList;
    }

    @Override
    public int Id_Function() {
        String q = "SELECT [Id_Function] FROM [aspnet_Users]";
        Cursor cursor = dbAdapter.ExecuteQ(q);
        int count = cursor.getCount();
        cursor.moveToFirst();
        int id = 0;
        for (int i = 0; i < count; i++) {
            id = cursor.getInt(0);
        }
        return id;
    }

    @Override
    public int first() {
        String q = "SELECT [_id] FROM [TbFunction]";
        Cursor cursor = dbAdapter.ExecuteQ(q);
        int count = cursor.getCount();
        cursor.moveToFirst();
        int id = 0;
        id = cursor.getInt(0);
        return id;
    }

    @Override
    public void update_idfunction(int id) {
        String q = "update [aspnet_Users] set [Id_Function] = "+id;
        Cursor cursor = dbAdapter.ExecuteQ(q);
        int count = cursor.getCount();
        cursor.moveToFirst();
    }

    /////////////////////////////////////////////////////////////////////////////////

    @Override
    public int Id_Lesson() {
        String q = "SELECT [Id_Lesson] FROM [aspnet_Users]";
        Cursor cursor = dbAdapter.ExecuteQ(q);
        int count = cursor.getCount();
        cursor.moveToFirst();
        int id = 0;
        for (int i = 0; i < count; i++) {
            id = cursor.getInt(0);
        }
        return id;
    }

    @Override
    public void update_idlesson(int id_lesson) {
        String q = "update [aspnet_Users] set [Id_Lesson] = "+id_lesson;
        Cursor cursor = dbAdapter.ExecuteQ(q);
        int count = cursor.getCount();
        cursor.moveToFirst();
    }

    @Override
    public int findFunction(int lid) {
        String q="SELECT Id_Function FROM [TbLesson] WHERE _id = "+ lid;
        Cursor cursor=dbAdapter.ExecuteQ(q);
        int count=cursor.getCount();
        cursor.moveToFirst();
        int id=0;
        for (int i = 0; i < count; i++) {
            id=cursor.getInt(0);
        }
        return id;
    }
}