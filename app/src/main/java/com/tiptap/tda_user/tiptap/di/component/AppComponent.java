package com.tiptap.tda_user.tiptap.di.component;

import android.app.Application;

import com.tiptap.tda_user.tiptap.di.module.A18_Module;
import com.tiptap.tda_user.tiptap.di.module.A19_Module;
import com.tiptap.tda_user.tiptap.di.module.A22_Module;
import com.tiptap.tda_user.tiptap.di.module.A24_Module;
import com.tiptap.tda_user.tiptap.di.module.A26_Module;
import com.tiptap.tda_user.tiptap.di.module.A28_Module;
import com.tiptap.tda_user.tiptap.di.module.A29_Module;
import com.tiptap.tda_user.tiptap.di.module.A30_Module;
import com.tiptap.tda_user.tiptap.di.module.A33_Module;
import com.tiptap.tda_user.tiptap.di.module.A34_Module;
import com.tiptap.tda_user.tiptap.di.module.A35_Module;
import com.tiptap.tda_user.tiptap.di.module.A39_Module;
import com.tiptap.tda_user.tiptap.di.module.A4_Module;
import com.tiptap.tda_user.tiptap.di.module.A5_Module;
import com.tiptap.tda_user.tiptap.di.module.A6_Module;
import com.tiptap.tda_user.tiptap.di.module.A7_Module;
import com.tiptap.tda_user.tiptap.di.module.A8_Module;
import com.tiptap.tda_user.tiptap.di.module.A9_Module;
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

    A4_Component getA4Component(A4_Module module);
    A5_Component getA5Component(A5_Module module);
    A6_Component getA6Component(A6_Module module);
    A7_Component getA7Component(A7_Module module);
    A8_Component getA8Component(A8_Module module);
    A9_Component getA9Component(A9_Module module);
    A18_Component getA18Component(A18_Module module);
    A19_Component getA19Component(A19_Module module);
    A22_Component getA22Component(A22_Module module);
    A24_Component getA24Component(A24_Module module);
    A26_Component getA26Component(A26_Module module);
    A28_Component getA28Component(A28_Module module);
    A29_Component getA29Component(A29_Module module);
    A30_Component getA30Component(A30_Module module);
    A33_Component getA33Component(A33_Module module);
    A34_Component getA34Component(A34_Module module);
    A35_Component getA35Component(A35_Module module);
    A39_Component getA39Component(A39_Module module);
}