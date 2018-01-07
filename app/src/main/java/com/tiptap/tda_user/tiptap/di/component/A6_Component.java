package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A6_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A6;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A6_Module.class )
public interface A6_Component {
    A6 inject(A6 activity);
}