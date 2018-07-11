package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A45_Module;
import com.tiptap.tda_user.tiptap.di.module.Main_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A45;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = A45_Module.class )
public interface A45_Component {
    A45 inject(A45 activity);
}