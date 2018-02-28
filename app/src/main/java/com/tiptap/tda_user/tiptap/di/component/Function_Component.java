package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.Function_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.function.Function;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = Function_Module.class )
public interface Function_Component {
    Function inject(Function activity);
}

