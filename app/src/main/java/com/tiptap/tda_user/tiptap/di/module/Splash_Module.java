package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Splash;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Model.Splash_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Splash_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.splash.Splash;
import dagger.Module;
import dagger.Provides;

@Module
public class Splash_Module {

    private Splash activity;

    public Splash_Module(Splash activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Splash providesSplashActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_Splash.ProvidedPresenterOps providedPresenterOps() {
        Splash_Presenter presenter = new Splash_Presenter( activity );
        Splash_Model model = new Splash_Model( presenter );
        presenter.setModel( model );
        return presenter;
    }
}
