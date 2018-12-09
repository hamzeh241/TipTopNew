package com.tiptap.tda_user.tiptap.main.activity.ViewModel;

public class aspnet_Users {

    private String UserName;
    private String Password;
    private String Email;
    private int Id_Lesson;
    private int Id_Function;
    private int Id_Language;
    private String Name;
    private String LastName;
    private String Age;
    private String Address;
    private String City;
    private String Countery;
    private String Birthday;

    public aspnet_Users() {}

    public int getId_Function() {
        return Id_Function;
    }

    public void setId_Function(int id_Function) {
        Id_Function = id_Function;
    }

    public int getId_Lesson() {
        return Id_Lesson;
    }

    public void setId_Lesson(int id_Lesson) {
        Id_Lesson = id_Lesson;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getId_Language() {
        return Id_Language;
    }

    public void setId_Language(int id_Language) {
        Id_Language = id_Language;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountery() {
        return Countery;
    }

    public void setCountery(String countery) {
        Countery = countery;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String Birthday) {
        this.Birthday = Birthday;
    }
}