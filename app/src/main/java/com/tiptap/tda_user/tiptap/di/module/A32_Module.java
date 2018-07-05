package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.Model.Main_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Main_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A32;
import dagger.Module;
import dagger.Provides;

@Module
public class A32_Module {

    private A32 activity;

    public A32_Module(A32 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A32 providesA32Activity() {
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
