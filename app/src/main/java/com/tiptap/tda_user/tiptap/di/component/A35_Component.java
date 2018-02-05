package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A35_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A35;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A35_Module.class )
public interface A35_Component {
    A35 inject(A35 activity);
}