package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A7;
import com.tiptap.tda_user.tiptap.main.activity.Model.A7_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A7_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A7;
import dagger.Module;
import dagger.Provides;

@Module
public class A7_Module {

    private A7 activity;

    public A7_Module(A7 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A7 providesA7Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A7.ProvidedPresenterOps providedPresenterOps() {
        A7_Presenter presenter = new A7_Presenter( activity );
        A7_Model model = new A7_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
