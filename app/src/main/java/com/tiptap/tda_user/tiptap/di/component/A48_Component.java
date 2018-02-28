package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A48_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A48;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A48_Module.class )
public interface A48_Component {
    A48 inject(A48 activity);
}