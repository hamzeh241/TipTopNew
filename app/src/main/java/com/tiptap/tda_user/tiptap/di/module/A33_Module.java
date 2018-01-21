package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A33;
import com.tiptap.tda_user.tiptap.main.activity.Model.A33_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A33_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A33;
import dagger.Module;
import dagger.Provides;

@Module
public class A33_Module {

    private A33 activity;

    public A33_Module(A33 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A33 providesA34Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A33.ProvidedPresenterOps providedPresenterOps() {
        A33_Presenter presenter = new A33_Presenter( activity );
        A33_Model model = new A33_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
