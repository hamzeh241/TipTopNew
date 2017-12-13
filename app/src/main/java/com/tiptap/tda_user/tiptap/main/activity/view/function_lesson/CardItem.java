package com.tiptap.tda_user.tiptap.main.activity.view.function_lesson;

public class CardItem {
    private int Id;
    private String Title;

    public CardItem(int id, String title) {
        Id = id;
        Title = title;
    }

    public String getTitle() {
        return Title;
    }
    public int getId() {
        return Id;
    }
}