package com.tiptap.tda_user.tiptap.di.component;


import com.tiptap.tda_user.tiptap.di.module.Main_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A25;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = Main_Module.class )
public interface A25_Component {
    A25 inject(A25 activity);
}