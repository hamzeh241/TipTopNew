package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A34_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A34;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A34_Module.class )
public interface A34_Component {
    A34 inject(A34 activity);
}
