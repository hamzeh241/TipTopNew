package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A45;
import com.tiptap.tda_user.tiptap.main.activity.Model.A45_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A45_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A45;
import dagger.Module;
import dagger.Provides;

@Module
public class A45_Module {

    private A45 activity;

    public A45_Module(A45 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A45 providesA45Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A45.ProvidedPresenterOps providedPresenterOps() {
        A45_Presenter presenter = new A45_Presenter( activity );
        A45_Model model = new A45_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
