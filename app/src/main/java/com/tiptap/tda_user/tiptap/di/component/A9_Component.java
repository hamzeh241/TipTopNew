package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A9_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A9;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A9_Module.class )
public interface A9_Component {
    A9 inject(A9 activity);
}