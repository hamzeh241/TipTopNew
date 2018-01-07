package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A19;
import com.tiptap.tda_user.tiptap.main.activity.Model.A19_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A19_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A19;
import dagger.Module;
import dagger.Provides;

@Module
public class A19_Module {

    private A19 activity;

    public A19_Module(A19 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A19 providesA19Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A19.ProvidedPresenterOps providedPresenterOps() {
        A19_Presenter presenter = new A19_Presenter( activity );
        A19_Model model = new A19_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
