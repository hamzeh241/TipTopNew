package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A1_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A1;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A1_Module.class )
public interface A1_Component {
    A1 inject(A1 activity);
}
