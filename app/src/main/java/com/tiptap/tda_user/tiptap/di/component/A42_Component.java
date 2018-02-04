package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A42_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A42;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A42_Module.class )
public interface A42_Component {
    A42 inject(A42 activity);
}