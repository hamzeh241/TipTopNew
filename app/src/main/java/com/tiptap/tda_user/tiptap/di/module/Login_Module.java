package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Login;
import com.tiptap.tda_user.tiptap.main.activity.Model.Login_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Login_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.login.Login;
import dagger.Module;
import dagger.Provides;

@Module
public class Login_Module {

    private Login activity;

    public Login_Module(Login activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Login providesLoginActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_Login.ProvidedPresenterOps providedPresenterOps() {
        Login_Presenter presenter = new Login_Presenter( activity );
        Login_Model model = new Login_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}