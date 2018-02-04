package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A46;
import com.tiptap.tda_user.tiptap.main.activity.Model.A46_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A46_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A46;
import dagger.Module;
import dagger.Provides;

@Module
public class A46_Module {

    private A46 activity;

    public A46_Module(A46 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A46 providesA46Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A46.ProvidedPresenterOps providedPresenterOps() {
        A46_Presenter presenter = new A46_Presenter( activity );
        A46_Model model = new A46_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
