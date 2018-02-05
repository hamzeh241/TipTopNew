package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A5;
import com.tiptap.tda_user.tiptap.main.activity.Model.A5_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A5_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A5;
import dagger.Module;
import dagger.Provides;

@Module
public class A5_Module {

    private A5 activity;

    public A5_Module(A5 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A5 providesA5Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A5.ProvidedPresenterOps providedPresenterOps() {
        A5_Presenter presenter = new A5_Presenter( activity );
        A5_Model model = new A5_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}