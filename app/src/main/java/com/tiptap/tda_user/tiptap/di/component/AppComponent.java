package com.tiptap.tda_user.tiptap.di.component;

import android.app.Application;
import com.tiptap.tda_user.tiptap.di.module.AppModule;
import com.tiptap.tda_user.tiptap.di.module.Function_Module;
import com.tiptap.tda_user.tiptap.di.module.Lesson_Module;
import com.tiptap.tda_user.tiptap.di.module.Login_Module;
import com.tiptap.tda_user.tiptap.di.module.Splash_Module;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Application application();
    Function_Component getFunctionComponent(Function_Module module);
    Lesson_Component getLessonComponent(Lesson_Module module);
    Splash_Component getSplashComponent(Splash_Module module);
    Login_Component getLoginComponent(Login_Module module);
}