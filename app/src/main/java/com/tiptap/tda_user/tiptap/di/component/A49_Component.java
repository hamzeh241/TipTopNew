package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.Main_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A49;

import dagger.Subcomponent;

/**
 * Created by tafsiri on 7/14/2018.
 */


@ActivityScope
@Subcomponent(modules = Main_Module.class )
public interface A49_Component {
    A49 inject(A49 activity);
}