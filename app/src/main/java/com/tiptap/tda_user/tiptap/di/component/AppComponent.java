package com.tiptap.tda_user.tiptap.di.component;

import android.app.Application;
import com.tiptap.tda_user.tiptap.di.module.AppModule;
import com.tiptap.tda_user.tiptap.di.module.Enter_Module;
import com.tiptap.tda_user.tiptap.di.module.Function_Module;
import com.tiptap.tda_user.tiptap.di.module.Language_Module;
import com.tiptap.tda_user.tiptap.di.module.Lesson_Module;
import com.tiptap.tda_user.tiptap.di.module.Login_Module;
import com.tiptap.tda_user.tiptap.di.module.Main_Module;
import com.tiptap.tda_user.tiptap.di.module.Splash_Module;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Application application();

    Function_Component getFunctionComponent(Function_Module module);
    Lesson_Component getLessonComponent(Lesson_Module module);
    Enter_Component getEnterComponent(Enter_Module module);
    Splash_Component getSplashComponent(Splash_Module module);
    Login_Component getLoginComponent(Login_Module module);
    Language_Component getLanguageComponent(Language_Module module);

    A1_Component getA1Component(Main_Module module);
    A2_Component getA2Component(Main_Module module);
    A3_Component getA3Component(Main_Module module);
    A4_Component getA4Component(Main_Module module);
    A5_Component getA5Component(Main_Module module);
    A6_Component getA6Component(Main_Module module);
    A7_Component getA7Component(Main_Module module);
    A8_Component getA8Component(Main_Module module);
    A10_Component getA10Component(Main_Module module);
    A11_Component getA11Component(Main_Module module);
    A12_Component getA12Component(Main_Module module);
    A13_Component getA13Component(Main_Module module);
    A14_Component getA14Component(Main_Module module);
    A15_Component getA15Component(Main_Module module);
    A16_Component getA16Component(Main_Module module);
    A17_Component getA17Component(Main_Module module);
    A18_Component getA18Component(Main_Module module);
    A19_Component getA19Component(Main_Module module);
    A20_Component getA20Component(Main_Module module);
    A21_Component getA21Component(Main_Module module);
    A22_Component getA22Component(Main_Module module);
    A24_Component getA24Component(Main_Module module);
    A25_Component getA25Component(Main_Module module);
    A26_Component getA26Component(Main_Module module);
    A27_Component getA27Component(Main_Module module);
    A28_Component getA28Component(Main_Module module);
    A29_Component getA29Component(Main_Module module);
    A32_Component getA32Component(Main_Module module);
    A33_Component getA33Component(Main_Module module);
    A37_Component getA37Component(Main_Module module);
    A38_Component getA38Component(Main_Module module);
    A39_Component getA39Component(Main_Module module);
    A40_Component getA40Component(Main_Module module);
    A41_Component getA41Component(Main_Module module);
    A42_Component getA42Component(Main_Module module);
    A45_Component getA45Component(Main_Module module);
    A46_Component getA46Component(Main_Module module);
    A47_Component getA47Component(Main_Module module);
    A48_Component getA48Component(Main_Module module);
    A49_Component getA49Component(Main_Module module);
}