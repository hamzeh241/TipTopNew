package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A37_Module;
import com.tiptap.tda_user.tiptap.di.module.A3_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A37;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A37_Module.class )
public interface A37_Component {
    A37 inject(A37 activity);
}