package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A26;
import com.tiptap.tda_user.tiptap.main.activity.Model.A26_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A26_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A26;
import dagger.Module;
import dagger.Provides;

@Module
public class A26_Module {

    private A26 activity;

    public A26_Module(A26 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A26 providesA26Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A26.ProvidedPresenterOps providedPresenterOps() {
        A26_Presenter presenter = new A26_Presenter( activity );
        A26_Model model = new A26_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}
