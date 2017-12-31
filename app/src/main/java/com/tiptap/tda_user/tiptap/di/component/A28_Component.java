package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A28_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A28;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A28_Module.class )
public interface A28_Component {
    A28 inject(A28 activity);
}
