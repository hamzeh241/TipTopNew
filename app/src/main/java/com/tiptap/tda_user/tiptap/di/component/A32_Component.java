package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A32_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A32;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A32_Module.class )
public interface A32_Component {
    A32 inject(A32 activity);
}