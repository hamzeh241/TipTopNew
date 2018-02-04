package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A31_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A31;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A31_Module.class )
public interface A31_Component {
    A31 inject(A31 activity);
}