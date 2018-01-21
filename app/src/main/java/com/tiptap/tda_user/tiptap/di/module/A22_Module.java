package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A22;
import com.tiptap.tda_user.tiptap.main.activity.Model.A22_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A22_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A22;
import dagger.Module;
import dagger.Provides;

@Module
public class A22_Module {

    private A22 activity;

    public A22_Module(A22 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A22 providesA22Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A22.ProvidedPresenterOps providedPresenterOps() {
        A22_Presenter presenter = new A22_Presenter( activity );
        A22_Model model = new A22_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
