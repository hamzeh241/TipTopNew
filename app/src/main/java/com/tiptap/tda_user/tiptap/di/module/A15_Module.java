package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.Model.Main_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Main_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A15;
import dagger.Module;
import dagger.Provides;

@Module
public class A15_Module {

    private A15 activity;

    public A15_Module(A15 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A15 providesA15Activity() {
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
