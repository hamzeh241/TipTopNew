package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.Main_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A24;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = Main_Module.class )
public interface A24_Component {
    A24 inject(A24 activity);
}