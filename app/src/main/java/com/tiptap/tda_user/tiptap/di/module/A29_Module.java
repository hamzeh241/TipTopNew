package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A29;
import com.tiptap.tda_user.tiptap.main.activity.Model.A29_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A29_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A29;
import dagger.Module;
import dagger.Provides;

@Module
public class A29_Module {

    private A29 activity;

    public A29_Module(A29 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A29 providesA29Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A29.ProvidedPresenterOps providedPresenterOps() {
        A29_Presenter presenter = new A29_Presenter( activity );
        A29_Model model = new A29_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
