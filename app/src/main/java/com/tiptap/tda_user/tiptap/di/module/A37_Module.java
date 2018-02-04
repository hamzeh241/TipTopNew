package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A37;
import com.tiptap.tda_user.tiptap.main.activity.Model.A37_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A37_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A37;

import dagger.Module;
import dagger.Provides;

@Module
public class A37_Module {

    private A37 activity;

    public A37_Module(A37 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A37 providesA37Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A37.ProvidedPresenterOps providedPresenterOps() {
        A37_Presenter presenter = new A37_Presenter( activity );
        A37_Model model = new A37_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
