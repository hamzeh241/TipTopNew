package com.tiptap.tda_user.tiptap.di.component;

import com.tiptap.tda_user.tiptap.di.module.Lesson_Module;
import com.tiptap.tda_user.tiptap.di.scope.ActivityScope;
import com.tiptap.tda_user.tiptap.main.activity.view.function_lesson.Lesson;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = Lesson_Module.class )
public interface Lesson_Component {
    Lesson inject(Lesson activity);
}