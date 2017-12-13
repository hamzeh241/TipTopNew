package com.tiptap.tda_user.tiptap.main.activity.Model;

import android.content.Context;
import android.database.Cursor;
import com.tiptap.tda_user.tiptap.main.activity.DB.DBAdapter;
import com.tiptap.tda_user.tiptap.main.activity.DB.PostError;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Splash;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbLanguage;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbUser;
import java.util.List;
import static com.tiptap.tda_user.tiptap.common.SampleApp.getMethodName;

public class Splash_Model implements MVP_Splash.ProvidedModelOps {

    private MVP_Splash.RequiredPresenterOps mPresenter;
    DBAdapter dbAdapter;
    Context context;
    private List<TbLanguage> LList;
    private List<TbUser> UList;

    public Splash_Model(MVP_Splash.RequiredPresenterOps presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        if (!isChangingConfiguration) {
            mPresenter = null;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////

    @Override
    public List<TbLanguage> getLanguages() {
        try{
            String q = "SELECT [_Id],[Language],[RowVersion] FROM [TbLanguage]";
            Cursor cursor = dbAdapter.ExecuteQ(q);
            int count=cursor.getCount();
            cursor.moveToFirst();
            for (int i = 0; i < count; i++) {
                TbLanguage app = new TbLanguage();
                app.set_id(Integer.parseInt(cursor.getString(0)));
                app.setLanguage(cursor.getString(1));
                app.setRowVersion(cursor.getString(2));
                LList.add(app);
                cursor.moveToNext();
            }
        }
        catch (Exception ex) {
            new PostError(context,ex.getMessage(),getMethodName()).postError();
        }
        return LList;
    }

    /////////////////////////////////////////////////////////////////////////////////

    @Override
    public int getCount_User() {
        String q="SELECT Count([UserName]) as x FROM TbUser";

        Cursor cursor = dbAdapter.ExecuteQ(q);
        int count=cursor.getCount();
        cursor.moveToFirst();
        int id=0;
        for (int i = 0; i < count; i++) {
            id=cursor.getInt(0);
        }
        return id;
    }

    @Override
    public TbUser getUser() {
        try{
            String q = "SELECT [UserName],[Password],[Email],[Id_Lesson],[Id_Language] FROM [TbUser]";
            Cursor cursor = dbAdapter.ExecuteQ(q);
            int count=cursor.getCount();
            cursor.moveToFirst();
            for (int i = 0; i < count; i++) {
                TbUser app = new TbUser();
                app.setUserName(cursor.getString(0));
                app.setPassword(cursor.getString(1));
                app.setEmail(cursor.getString(2));
                app.setId_Lesson(Integer.parseInt(cursor.getString(3)));
                app.setId_Language(Integer.parseInt(cursor.getString(4)));
                UList.add(app);
                cursor.moveToNext();
            }
        }
        catch (Exception ex) {
            new PostError(context,ex.getMessage(),getMethodName()).postError();
        }
        return UList.get(0);
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
    public int getMaxId_Activity() {
        String q="SELECT [_id] FROM TbActivity ORDER BY _id DESC LIMIT 1";
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
}