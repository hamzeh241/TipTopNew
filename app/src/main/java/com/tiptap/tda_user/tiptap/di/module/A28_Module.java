package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A28;
import com.tiptap.tda_user.tiptap.main.activity.Model.A28_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A28_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A28;
import dagger.Module;
import dagger.Provides;

@Module
public class A28_Module {

    private A28 activity;

    public A28_Module(A28 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A28 providesA28Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A28.ProvidedPresenterOps providedPresenterOps() {
        A28_Presenter presenter = new A28_Presenter( activity );
        A28_Model model = new A28_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
