package com.tiptap.tda_user.tiptap.main.activity.ViewModel;

public class TbFunction {

    private int _id;
    private String Title;
    private String RowVersion;

    public TbFunction(){}

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getRowVersion() {
        return RowVersion;
    }

    public void setRowVersion(String rowVersion) {
        RowVersion = rowVersion;
    }
}
