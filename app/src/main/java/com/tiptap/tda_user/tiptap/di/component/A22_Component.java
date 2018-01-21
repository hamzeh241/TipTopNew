package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A22_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A22;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A22_Module.class )
public interface A22_Component {
    A22 inject(A22 activity);
}