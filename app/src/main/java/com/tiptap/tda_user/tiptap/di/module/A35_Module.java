package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A35;
import com.tiptap.tda_user.tiptap.main.activity.Model.A35_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A35_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A35;
import dagger.Module;
import dagger.Provides;

@Module
public class A35_Module {

    private A35 activity;

    public A35_Module(A35 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A35 providesA35Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A35.ProvidedPresenterOps providedPresenterOps() {
        A35_Presenter presenter = new A35_Presenter( activity );
        A35_Model model = new A35_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
