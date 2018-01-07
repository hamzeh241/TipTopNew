package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A4_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A4;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A4_Module.class )
public interface A4_Component {
    A4 inject(A4 activity);
}