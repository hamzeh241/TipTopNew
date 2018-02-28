package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A48;
import com.tiptap.tda_user.tiptap.main.activity.Model.A48_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A48_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A48;
import dagger.Module;
import dagger.Provides;

@Module
public class A48_Module {

    private A48 activity;

    public A48_Module(A48 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A48 providesA48Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A48.ProvidedPresenterOps providedPresenterOps() {
        A48_Presenter presenter = new A48_Presenter( activity );
        A48_Model model = new A48_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
