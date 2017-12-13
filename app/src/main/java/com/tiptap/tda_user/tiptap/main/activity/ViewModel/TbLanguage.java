package com.tiptap.tda_user.tiptap.main.activity.ViewModel;

public class TbLanguage {

    private int _id;
    private String Language;
    private String RowVersion;

    public TbLanguage() {}

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getRowVersion() {
        return RowVersion;
    }

    public void setRowVersion(String rowVersion) {
        RowVersion = rowVersion;
    }
}
