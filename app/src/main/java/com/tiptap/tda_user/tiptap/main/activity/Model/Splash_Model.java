package com.tiptap.tda_user.tiptap.main.activity.Model;

import android.content.Context;
import android.database.Cursor;
import com.tiptap.tda_user.tiptap.main.activity.DB.DBAdapter;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Splash;
public class Splash_Model implements MVP_Splash.ProvidedModelOps {

    private MVP_Splash.RequiredPresenterOps mPresenter;
    DBAdapter dbAdapter;
    Context context;

    public Splash_Model(MVP_Splash.RequiredPresenterOps presenter, Context _conContext) {
        mPresenter = presenter;
        context=_conContext;
        dbAdapter=new DBAdapter(context);
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        if (!isChangingConfiguration) {
            mPresenter = null;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////

    @Override
    public int getCount_User() {
        String q = "SELECT [UserName] FROM [aspnet_Users]";
        Cursor cursor = dbAdapter.ExecuteQ(q);
        int count = cursor.getCount();
        return count;
    }
}