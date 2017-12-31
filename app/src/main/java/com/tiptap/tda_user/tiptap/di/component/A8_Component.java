package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A8_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A8;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A8_Module.class )
public interface A8_Component {
    A8 inject(A8 activity);
}