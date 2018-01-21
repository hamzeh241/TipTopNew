package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A30_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A30;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A30_Module.class )
public interface A30_Component {
    A30 inject(A30 activity);
}