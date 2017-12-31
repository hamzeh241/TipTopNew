package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A34;
import com.tiptap.tda_user.tiptap.main.activity.Model.A34_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A34_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A34;
import dagger.Module;
import dagger.Provides;

@Module
public class A34_Module {

    private A34 activity;

    public A34_Module(A34 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A34 providesA34Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A34.ProvidedPresenterOps providedPresenterOps() {
        A34_Presenter presenter = new A34_Presenter( activity );
        A34_Model model = new A34_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
