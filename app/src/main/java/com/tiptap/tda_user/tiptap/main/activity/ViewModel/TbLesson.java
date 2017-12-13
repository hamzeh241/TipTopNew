package com.tiptap.tda_user.tiptap.main.activity.ViewModel;


public class TbLesson {

    private int _id;
    private int Id_Function;
    private int LessonNumber;
    private String RowVersion;

    public TbLesson(){}

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getId_Function() {
        return Id_Function;
    }

    public void setId_Function(int id_Function) {
        Id_Function = id_Function;
    }

    public int getLessonNumber() {
        return LessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        LessonNumber = lessonNumber;
    }

    public String getRowVersion() {
        return RowVersion;
    }

    public void setRowVersion(String rowVersion) {
        RowVersion = rowVersion;
    }
}