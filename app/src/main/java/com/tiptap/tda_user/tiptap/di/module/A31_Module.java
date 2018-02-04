package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A31;
import com.tiptap.tda_user.tiptap.main.activity.Model.A31_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A31_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A31;
import dagger.Module;
import dagger.Provides;

@Module
public class A31_Module {

    private A31 activity;

    public A31_Module(A31 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A31 providesA31Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A31.ProvidedPresenterOps providedPresenterOps() {
        /*A31_Presenter presenter = new A31_Presenter( activity );
        A31_Model model = new A31_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;*/
        return null;
    }
}
