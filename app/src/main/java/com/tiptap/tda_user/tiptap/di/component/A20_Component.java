package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A20_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A20;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A20_Module.class )
public interface A20_Component {
    A20 inject(A20 activity);
}