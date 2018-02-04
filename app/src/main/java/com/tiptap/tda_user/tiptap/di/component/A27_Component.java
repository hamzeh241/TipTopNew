package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A27_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A27;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A27_Module.class )
public interface A27_Component {
    A27 inject(A27 activity);
}