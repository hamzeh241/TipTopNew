package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.Language_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.language.Language;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = Language_Module.class )
public interface Language_Component {
   Language inject(Language activity);
}