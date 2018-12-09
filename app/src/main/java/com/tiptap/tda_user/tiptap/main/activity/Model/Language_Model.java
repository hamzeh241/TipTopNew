package com.tiptap.tda_user.tiptap.main.activity.Model;

import android.content.Context;
import com.tiptap.tda_user.tiptap.main.activity.DB.DBAdapter;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Language;

public class Language_Model implements MVP_Language.ProvidedModelOps{

    DBAdapter dbAdapter;
    Context context;
    private MVP_Language.RequiredPresenterOps mPresenter;

    public Language_Model(MVP_Language.RequiredPresenterOps presenter, Context _conContext) {
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
}