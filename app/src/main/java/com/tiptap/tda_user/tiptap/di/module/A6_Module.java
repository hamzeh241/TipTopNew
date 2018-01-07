package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A6;
import com.tiptap.tda_user.tiptap.main.activity.Model.A6_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A6_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A6;
import dagger.Module;
import dagger.Provides;

@Module
public class A6_Module {

    private A6 activity;

    public A6_Module(A6 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A6 providesA7Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A6.ProvidedPresenterOps providedPresenterOps() {
        A6_Presenter presenter = new A6_Presenter( activity );
        A6_Model model = new A6_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}