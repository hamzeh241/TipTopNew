package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A27;
import com.tiptap.tda_user.tiptap.main.activity.Model.A27_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A27_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A27;
import dagger.Module;
import dagger.Provides;

@Module
public class A27_Module {

    private A27 activity;

    public A27_Module(A27 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A27 providesA27Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A27.ProvidedPresenterOps providedPresenterOps() {
        A27_Presenter presenter = new A27_Presenter( activity );
        A27_Model model = new A27_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
