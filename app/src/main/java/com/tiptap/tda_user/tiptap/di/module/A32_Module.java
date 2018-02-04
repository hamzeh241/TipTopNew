package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A32;
import com.tiptap.tda_user.tiptap.main.activity.Model.A32_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A32_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A32;
import dagger.Module;
import dagger.Provides;

@Module
public class A32_Module {

    private A32 activity;

    public A32_Module(A32 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A32 providesA32Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A32.ProvidedPresenterOps providedPresenterOps() {
        A32_Presenter presenter = new A32_Presenter( activity );
        A32_Model model = new A32_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
