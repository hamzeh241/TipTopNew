package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A24;
import com.tiptap.tda_user.tiptap.main.activity.Model.A24_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A24_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A24;
import dagger.Module;
import dagger.Provides;

@Module
public class A24_Module {

    private A24 activity;

    public A24_Module(A24 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A24 providesA24Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A24.ProvidedPresenterOps providedPresenterOps() {
        A24_Presenter presenter = new A24_Presenter( activity );
        A24_Model model = new A24_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
