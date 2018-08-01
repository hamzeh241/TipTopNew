package com.tiptap.tda_user.tiptap.di.component;


import com.tiptap.tda_user.tiptap.di.module.Main_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A3;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = Main_Module.class )
public interface A3_Component {
    A3 inject(A3 activity);
}