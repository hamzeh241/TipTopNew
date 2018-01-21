package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A33_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A33;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A33_Module.class )
public interface A33_Component {
    A33 inject(A33 activity);
}