package com.tiptap.tda_user.tiptap.main.activity.ViewModel;

public class TbActivity {

    private int _id;
    private int Id_Lesson;
    private int ActivityNumber;
    private int Id_ActivityType;
    private String Title1;
    private String Title2;
    private String Path1;
    private String Path2;
    private boolean IsNote;
    private String RowVersion;
    private int Status;

    public TbActivity() {}

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getId_Lesson() {
        return Id_Lesson;
    }

    public void setId_Lesson(int id_Lesson) {
        Id_Lesson = id_Lesson;
    }

    public int getActivityNumber() {
        return ActivityNumber;
    }

    public void setActivityNumber(int activityNumber) {
        ActivityNumber = activityNumber;
    }

    public int getId_ActivityType() {
        return Id_ActivityType;
    }

    public void setId_ActivityType(int id_ActivityType) {
        Id_ActivityType = id_ActivityType;
    }

    public String getTitle1() {
        return Title1;
    }

    public void setTitle1(String title1) {
        Title1 = title1;
    }

    public String getTitle2() {
        return Title2;
    }

    public void setTitle2(String title2) {
        Title2 = title2;
    }

    public String getPath1() {
        return Path1;
    }

    public void setPath1(String path1) {
        Path1 = path1;
    }

    public String getPath2() {
        return Path2;
    }

    public void setPath2(String path2) {
        Path2 = path2;
    }

    public boolean getIsNote() {
        return IsNote;
    }

    public void setIsNote(boolean note) {
        IsNote = note;
    }

    public String getRowVersion() {
        return RowVersion;
    }

    public void setRowVersion(String rowVersion) {
        RowVersion = rowVersion;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
