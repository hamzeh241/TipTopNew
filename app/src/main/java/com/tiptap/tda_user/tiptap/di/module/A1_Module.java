package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A1;
import com.tiptap.tda_user.tiptap.main.activity.Model.A1_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A1_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A1;
import dagger.Module;
import dagger.Provides;

@Module
public class A1_Module {

    private A1 activity;

    public A1_Module(A1 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A1 providesA1Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A1.ProvidedPresenterOps providedPresenterOps() {
        A1_Presenter presenter = new A1_Presenter( activity );
        A1_Model model = new A1_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
