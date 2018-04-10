package com.tiptap.tda_user.tiptap.main.activity.ViewModel;

public class aspnet_Users {

    private String UserName;
    private String NameInApp;
    private String Password;
    private String Email;
    private int Id_Lesson;
    private int Id_Function;
    private int Id_Language;

    public aspnet_Users() {}

    public String getNameInApp() {
        return NameInApp;
    }

    public void setNameInApp(String nameInApp) {
        NameInApp = nameInApp;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getId_Lesson() {
        return Id_Lesson;
    }

    public void setId_Lesson(int id_Lesson) {
        Id_Lesson = id_Lesson;
    }

    public int getId_Function() {
        return Id_Function;
    }

    public void setId_Function(int id_Function) {
        Id_Function = id_Function;
    }

    public int getId_Language() {
        return Id_Language;
    }

    public void setId_Language(int id_Language) {
        Id_Language = id_Language;
    }
}