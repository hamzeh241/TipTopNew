package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A30;
import com.tiptap.tda_user.tiptap.main.activity.Model.A30_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A30_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A30;
import dagger.Module;
import dagger.Provides;

@Module
public class A30_Module {

    private A30 activity;

    public A30_Module(A30 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A30 providesA30Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A30.ProvidedPresenterOps providedPresenterOps() {
        A30_Presenter presenter = new A30_Presenter( activity );
        A30_Model model = new A30_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
