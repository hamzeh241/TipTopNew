package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A20;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A20_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A20;
import dagger.Module;
import dagger.Provides;

@Module
public class A20_Module {

    private A20 activity;

    public A20_Module(A20 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A20 providesA20Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A20.ProvidedPresenterOps providedPresenterOps() {
        /*A20_Presenter presenter = new A20_Presenter( activity );
        A20_Model model = new A20_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;*/
        return null;
    }
}
