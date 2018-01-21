package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A18;
import com.tiptap.tda_user.tiptap.main.activity.Model.A18_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A18_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A18;

import dagger.Module;
import dagger.Provides;

@Module
public class A18_Module {

    private A18 activity;

    public A18_Module(A18 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A18 providesA18Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A18.ProvidedPresenterOps providedPresenterOps() {
        A18_Presenter presenter = new A18_Presenter( activity );
        A18_Model model = new A18_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
