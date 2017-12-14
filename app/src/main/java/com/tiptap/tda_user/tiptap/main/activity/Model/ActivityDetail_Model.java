package com.tiptap.tda_user.tiptap.main.activity.Model;

import android.content.Context;
import android.database.Cursor;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_ActivityDetail;
import com.tiptap.tda_user.tiptap.main.activity.DB.DBAdapter;
import com.tiptap.tda_user.tiptap.main.activity.DB.PostError;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivityDetail;
import java.util.ArrayList;
import java.util.List;
import static com.tiptap.tda_user.tiptap.common.SampleApp.getMethodName;

public class ActivityDetail_Model implements MVP_ActivityDetail.ProvidedModelOps {

    DBAdapter dbAdapter;
    Context context;
    private List<TbActivityDetail> ADList;
    private MVP_ActivityDetail.RequiredPresenterOps mPresenter;

    public ActivityDetail_Model
            (MVP_ActivityDetail.RequiredPresenterOps presenter, Context _conContext) {
        mPresenter = presenter;
        context=_conContext;
        dbAdapter=new DBAdapter(context);
        ADList = new ArrayList<>();
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        if (!isChangingConfiguration) {
            mPresenter = null;
        }
    }

    @Override
    public String getMaxRowV_ActivityDetail() {
        String q="SELECT MAX(RowVersion) as RowVersion FROM TbActivityDetail";
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
    public int getMaxId_ActivityDetail() {
        String q="SELECT [_id] FROM TbActivityDetail ORDER BY _id DESC LIMIT 1";
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
    public int getCount_ActivityDetail(int aid) {
        String q="SELECT Count([_id]) as x FROM TbActivityDetail where Id_Activity = "+ aid;
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
                app.setIsAnswer(Boolean.parseBoolean(cursor.getString(6)));
                app.setOrferAnswer(Integer.parseInt(cursor.getString(7)));
                app.setOrderPreview(Integer.parseInt(cursor.getString(8)));
                app.setRowVersion(cursor.getString(9));
                ADList.add(app);
                cursor.moveToNext();
            }}
        catch (Exception ex)
        {
            new PostError(context,ex.getMessage(),getMethodName()).postError();

        }
        return ADList;
    }
}
