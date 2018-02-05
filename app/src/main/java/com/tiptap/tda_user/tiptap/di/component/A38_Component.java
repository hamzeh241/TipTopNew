package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A38_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A38;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A38_Module.class )
public interface A38_Component {
    A38 inject(A38 activity);
}