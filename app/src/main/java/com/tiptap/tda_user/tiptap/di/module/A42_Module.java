package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A42;

import dagger.Module;
import dagger.Provides;

@Module
public class A42_Module {

    private A42 activity;

    public A42_Module(A42 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A42 providesA42Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_Main.ProvidedPresenterOps providedPresenterOps() {
       /* A42_Presenter presenter = new A42_Presenter( activity );
        A42_Model model = new A42_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;*/
        return null;
    }
}
