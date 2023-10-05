package com.example.appapitest;

public class Person
{
    int id;
    String name;
    int phone;
    String address;
    String note;
    Boolean favorite;
    int hairId;
    int programLanguageId;
    String hairColor;
    String programmingLanguage;

    public Person() {}

    public Person(int id, String name, int phone, String address, String note, Boolean favorite, int hairId, int programLanguageId, String hairColor, String programmingLanguage) {
        super();
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.favorite = favorite;
        this.hairId = hairId;
        this.programLanguageId = programLanguageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public int getHairId() {
        return hairId;
    }

    public void setHairId(int hairId) {
        this.hairId = hairId;
    }

    public int getProgramLanguageId() {
        return programLanguageId;
    }

    public void setProgramLanguageId(int programLanguageId) {
        this.programLanguageId = programLanguageId;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
}