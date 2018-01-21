package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A9;
import com.tiptap.tda_user.tiptap.main.activity.Model.A9_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A9_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A9;

import dagger.Module;
import dagger.Provides;

@Module
public class A9_Module {

    private A9 activity;

    public A9_Module(A9 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A9 providesA9Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A9.ProvidedPresenterOps providedPresenterOps() {
        A9_Presenter presenter = new A9_Presenter( activity );
        A9_Model model = new A9_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
