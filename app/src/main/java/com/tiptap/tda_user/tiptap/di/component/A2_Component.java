package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.A2_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A2;

import dagger.Subcomponent;

/**
 * Created by tafsiri on 6/25/2018.
 */

@ActivityScope
@Subcomponent(modules = A2_Module.class )
public interface A2_Component {
    A2 inject(A2 activity);
}
