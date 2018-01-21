package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A5_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A5;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A5_Module.class )
public interface A5_Component {
    A5 inject(A5 activity);
}