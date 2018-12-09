package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Enter;
import com.tiptap.tda_user.tiptap.main.activity.Model.Enter_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Enter_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.LR.Enter;
import dagger.Module;
import dagger.Provides;

@Module
public class Enter_Module {

    private Enter activity;

    public Enter_Module(Enter activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Enter providesEnterActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_Enter.ProvidedPresenterOps providedPresenterOps() {
        Enter_Presenter presenter = new Enter_Presenter( activity );
        Enter_Model model = new Enter_Model( presenter, activity );
        presenter.setModel( model );
        return presenter;
    }
}
