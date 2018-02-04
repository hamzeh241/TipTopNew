package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A46_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A46;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A46_Module.class )
public interface A46_Component {
    A46 inject(A46 activity);
}