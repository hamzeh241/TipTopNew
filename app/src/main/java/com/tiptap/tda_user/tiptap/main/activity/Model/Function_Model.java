package com.tiptap.tda_user.tiptap.main.activity.Model;

import android.content.Context;
import android.database.Cursor;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Function;
import com.tiptap.tda_user.tiptap.main.activity.DB.DBAdapter;
import com.tiptap.tda_user.tiptap.main.activity.DB.PostError;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbFunction;
import java.util.ArrayList;
import java.util.List;
import static com.tiptap.tda_user.tiptap.common.SampleApp.getMethodName;

public class Function_Model implements MVP_Function.ProvidedModelOps {

    DBAdapter dbAdapter;
    Context context;
    private List<TbFunction> FList;
    private MVP_Function.RequiredPresenterOps mPresenter;

    public Function_Model(MVP_Function.RequiredPresenterOps presenter, Context _conContext) {
        mPresenter = presenter;
        context=_conContext;
        dbAdapter=new DBAdapter(context);
        FList = new ArrayList<>();
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        if (!isChangingConfiguration) {
            mPresenter = null;
        }
    }

    @Override
    public String getMaxRowV_Function() {
        String q="SELECT MAX(RowVersion) as RowVersion FROM TbFunction";
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
    public int getMaxId_Function() {
        String q="SELECT [_id] FROM TbFunction ORDER BY _id DESC LIMIT 1";
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
    public int getCount_Function() {
        String q="SELECT Count([_id]) as x FROM TbFunction";
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
    public void Insert_Function(String Q) {
        dbAdapter.ExecuteQ(Q);
    }

    @Override
    public List<TbFunction> getListFunction() {
        try{
            String q = "SELECT [_id],[Title],[RowVersion] FROM [TbFunction]";
            Cursor cursor = dbAdapter.ExecuteQ(q);
            int count=cursor.getCount();
            cursor.moveToFirst();
            for (int i = 0; i < count; i++) {
                TbFunction app = new TbFunction();
                app.set_id(Integer.parseInt(cursor.getString(0)));
                app.setTitle(cursor.getString(1));
                app.setRowVersion(cursor.getString(2));
                FList.add(app);
                cursor.moveToNext();
            }
        }
        catch (Exception ex) {
            new PostError(context,ex.getMessage(),getMethodName()).postError();
        }
        return FList;
    }
}