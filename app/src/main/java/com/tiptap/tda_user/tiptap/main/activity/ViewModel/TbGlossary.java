package com.tiptap.tda_user.tiptap.main.activity.ViewModel;

public class TbGlossary {

    private int _id;
    private int Id_Language;
    private String TableName;
    private String ColumeName;
    private int RowNumber;
    private String NewTitle;
    private String RowVersion;

    public TbGlossary() {}

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getId_Language() {
        return Id_Language;
    }

    public void setId_Language(int id_Language) {
        Id_Language = id_Language;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    public String getColumeName() {
        return ColumeName;
    }

    public void setColumeName(String columeName) {
        ColumeName = columeName;
    }

    public int getRowNumber() {
        return RowNumber;
    }

    public void setRowNumber(int rowNumber) {
        RowNumber = rowNumber;
    }

    public String getNewTitle() {
        return NewTitle;
    }

    public void setNewTitle(String newTitle) {
        NewTitle = newTitle;
    }

    public String getRowVersion() {
        return RowVersion;
    }

    public void setRowVersion(String rowVersion) {
        RowVersion = rowVersion;
    }
}
