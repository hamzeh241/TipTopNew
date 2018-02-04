package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A15;
import com.tiptap.tda_user.tiptap.main.activity.Model.A15_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A15_Presenter;
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
    MVP_A15.ProvidedPresenterOps providedPresenterOps() {
        A15_Presenter presenter = new A15_Presenter( activity );
        A15_Model model = new A15_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
