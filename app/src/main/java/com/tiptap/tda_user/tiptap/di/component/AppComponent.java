package com.tiptap.tda_user.tiptap.di.component;

import android.app.Application;
import com.tiptap.tda_user.tiptap.di.module.A19_Module;
import com.tiptap.tda_user.tiptap.di.module.A28_Module;
import com.tiptap.tda_user.tiptap.di.module.A34_Module;
import com.tiptap.tda_user.tiptap.di.module.A39_Module;
import com.tiptap.tda_user.tiptap.di.module.A7_Module;
import com.tiptap.tda_user.tiptap.di.module.A8_Module;
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
    A7_Component getA7Component(A7_Module module);
    A8_Component getA8Component(A8_Module module);
    A19_Component getA19Component(A19_Module module);
    A28_Component getA28Component(A28_Module module);
    A34_Component getA34Component(A34_Module module);
    A39_Component getA39Component(A39_Module module);
}