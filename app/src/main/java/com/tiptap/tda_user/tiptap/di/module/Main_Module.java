package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.Model.Main_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Main_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.BaseActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class Main_Module {

    private BaseActivity activity;

    public Main_Module(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    BaseActivity providesActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_Main.ProvidedPresenterOps providedPresenterOps() {
       Main_Presenter presenter = new Main_Presenter( activity );
       Main_Model model = new Main_Model( presenter , activity );
       presenter.setModel( model );
       return presenter;
    }
}
