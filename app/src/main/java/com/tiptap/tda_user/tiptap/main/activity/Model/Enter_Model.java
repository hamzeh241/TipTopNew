package com.tiptap.tda_user.tiptap.main.activity.Model;

import android.content.Context;
import android.database.Cursor;

import com.tiptap.tda_user.tiptap.main.activity.DB.DBAdapter;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Enter;

public class Enter_Model implements MVP_Enter.ProvidedModelOps{

    DBAdapter dbAdapter;
    Context context;
    private MVP_Enter.RequiredPresenterOps mPresenter;

    public Enter_Model(MVP_Enter.RequiredPresenterOps presenter, Context _conContext) {
        this.mPresenter = presenter;
        context=_conContext;
        dbAdapter=new DBAdapter(context);
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        if (!isChangingConfiguration) {
            mPresenter = null;
        }
    }

    @Override
    public void Insert_User(String Q) {
        dbAdapter.ExecuteQ(Q);
    }

    @Override
    public void Delete_User() {
        String q1 = "select UserName from [aspnet_Users]";
        Cursor cursor1 = dbAdapter.ExecuteQ(q1);
        int count = cursor1.getCount();
        cursor1.moveToFirst();
        if(count == 0){
            // no user
        }else{
            String q2 = "DELETE * FROM [aspnet_Users]";
            Cursor cursor2 = dbAdapter.ExecuteQ(q2);
        }
    }
}