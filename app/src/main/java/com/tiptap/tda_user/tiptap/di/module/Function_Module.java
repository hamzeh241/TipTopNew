package com.tiptap.tda_user.tiptap.di.module;

import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Function;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.Model.Function_Model;
import com.tiptap.tda_user.tiptap.main.activity.Presenter.Function_Presenter;
import com.tiptap.tda_user.tiptap.main.activity.view.function_lesson.Function;
import dagger.Module;
import dagger.Provides;

@Module
public class Function_Module {

    private Function activity;

    public Function_Module(Function activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Function providesFunctionActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MVP_Function.ProvidedPresenterOps providedPresenterOps() {
        Function_Presenter presenter = new Function_Presenter( activity );
        Function_Model model = new Function_Model( presenter , activity );
        presenter.setModel( model );
        return presenter;
    }
}