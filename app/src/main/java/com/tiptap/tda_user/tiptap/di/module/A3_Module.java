package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A3;
import com.tiptap.tda_user.tiptap.main.activity.Model.A3_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A3_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A3;
import dagger.Module;
import dagger.Provides;

@Module
public class A3_Module {

    private A3 activity;

    public A3_Module(A3 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A3 providesA3Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A3.ProvidedPresenterOps providedPresenterOps() {
        A3_Presenter presenter = new A3_Presenter( activity );
        A3_Model model = new A3_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}