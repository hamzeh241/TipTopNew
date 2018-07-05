package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.Model.Main_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Main_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A8;
import dagger.Module;
import dagger.Provides;

@Module
public class A8_Module {

    private A8 activity;

    public A8_Module(A8 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A8 providesA8Activity() {
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
