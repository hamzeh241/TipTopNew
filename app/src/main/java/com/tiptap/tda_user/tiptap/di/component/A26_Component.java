package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A26_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A26;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A26_Module.class )
public interface A26_Component {
    A26 inject(A26 activity);
}