package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A8;
import com.tiptap.tda_user.tiptap.main.activity.Model.A8_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A8_Presenter;
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
    MVP_A8.ProvidedPresenterOps providedPresenterOps() {
        A8_Presenter presenter = new A8_Presenter( activity );
        A8_Model model = new A8_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
