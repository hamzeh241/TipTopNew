package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A38;
import com.tiptap.tda_user.tiptap.main.activity.Model.A38_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A38_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A38;
import dagger.Module;
import dagger.Provides;

@Module
public class A38_Module {

    private A38 activity;

    public A38_Module(A38 activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A38 providesA38Activity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_A38.ProvidedPresenterOps providedPresenterOps() {
       /* A38_Presenter presenter = new A38_Presenter( activity );
        A38_Model model = new A38_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;*/
        return null;
    }
}
