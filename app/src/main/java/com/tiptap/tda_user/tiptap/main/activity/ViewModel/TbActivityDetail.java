package com.tiptap.tda_user.tiptap.main.activity.ViewModel;

public class TbActivityDetail {

    private int _id;
    private String Path1;
    private String Path2;
    private int Id_Activity;
    private String Title1;
    private String Title2;
    private boolean IsAnswer;
    private int OrferAnswer;
    private int OrderPreview;
    private String RowVersion;

    public TbActivityDetail(){}

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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

    public int getId_Activity() {
        return Id_Activity;
    }

    public void setId_Activity(int id_Activity) {
        Id_Activity = id_Activity;
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

    public boolean getIsAnswer() {
        return IsAnswer;
    }

    public void setIsAnswer(boolean answer) {
        IsAnswer = answer;
    }

    public int getOrferAnswer() {
        return OrferAnswer;
    }

    public void setOrferAnswer(int orferAnswer) {
        OrferAnswer = orferAnswer;
    }

    public int getOrderPreview() {
        return OrderPreview;
    }

    public void setOrderPreview(int orderPreview) {
        OrderPreview = orderPreview;
    }

    public String getRowVersion() {
        return RowVersion;
    }

    public void setRowVersion(String rowVersion) {
        RowVersion = rowVersion;
    }
}