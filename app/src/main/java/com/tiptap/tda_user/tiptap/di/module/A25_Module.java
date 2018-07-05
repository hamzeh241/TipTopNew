package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A25;
import dagger.Module;
import dagger.Provides;

@Module
public class A25_Module {

    private A25 activity;

    public A25_Module(A25 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A25 providesA25Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_Main.ProvidedPresenterOps providedPresenterOps() {
        /*A25_Presenter presenter = new A25_Presenter( activity );
        A25_Model model = new A25_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;*/
        return null;
    }
}
