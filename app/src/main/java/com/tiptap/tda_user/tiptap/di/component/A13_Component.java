package com.tiptap.tda_user.tiptap.di.component;

/**
 * Created by tafsiri on 7/21/2018.
 */

import com.tiptap.tda_user.tiptap.di.module.Main_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A13;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = Main_Module.class )
public interface A13_Component {
    A13 inject(A13 activity);
}