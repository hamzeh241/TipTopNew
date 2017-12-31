package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_A39;
import com.tiptap.tda_user.tiptap.main.activity.Model.A39_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.A39_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A39;
import dagger.Module;
import dagger.Provides;

@Module
public class A39_Module {

    private A39 activity;

    public A39_Module(A39 activity) {
            this.activity = activity;
        }

    @Provides
    @ActivityScope
    A39 providesA39Activity() {
            return activity;
        }

    @Provides
    @ActivityScope
    MVP_A39.ProvidedPresenterOps providedPresenterOps() {
            //A39_Presenter presenter = new A39_Presenter( activity );
            //A39_Model model = new A39_Model( presenter , activity );
           // presenter.setModel( model );
           // return presenter;
            return null;
        }
}
