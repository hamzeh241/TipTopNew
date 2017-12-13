package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.Login_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.login.Login;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = Login_Module.class )
public interface Login_Component {
    Login inject(Login activity);
}