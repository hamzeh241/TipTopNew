package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A29_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A29;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A29_Module.class )
public interface A29_Component {
    A29 inject(A29 activity);
}