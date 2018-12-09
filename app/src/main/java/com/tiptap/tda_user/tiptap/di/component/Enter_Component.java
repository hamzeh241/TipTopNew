package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.Enter_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.LR.Enter;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = Enter_Module.class )
public interface Enter_Component {
    Enter inject(Enter activity);
}