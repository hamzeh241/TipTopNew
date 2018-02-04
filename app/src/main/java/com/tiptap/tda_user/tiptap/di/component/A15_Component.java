package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A15_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A15;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A15_Module.class )
public interface A15_Component {
    A15 inject(A15 activity);
}