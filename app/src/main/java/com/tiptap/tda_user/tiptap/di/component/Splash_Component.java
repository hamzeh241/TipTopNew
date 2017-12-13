package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.Splash_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.splash.Splash;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = Splash_Module.class )
public interface Splash_Component {
    Splash inject(Splash activity);
}