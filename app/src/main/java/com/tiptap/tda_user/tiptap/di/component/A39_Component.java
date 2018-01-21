package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A39_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A39;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A39_Module.class )
public interface A39_Component {
    A39 inject(A39 activity);
}