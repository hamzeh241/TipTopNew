package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A7_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A7;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A7_Module.class )
public interface A7_Component {
    A7 inject(A7 activity);
}
