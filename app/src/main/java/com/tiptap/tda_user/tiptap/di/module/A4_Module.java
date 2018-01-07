package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A4;
import com.tiptap.tda_user.tiptap.main.activity.Model.A4_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A4_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A4;

import dagger.Module;
import dagger.Provides;

@Module
public class A4_Module {

    private A4 activity;

    public A4_Module(A4 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A4 providesA4Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A4.ProvidedPresenterOps providedPresenterOps() {
        A4_Presenter presenter = new A4_Presenter( activity );
        A4_Model model = new A4_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
