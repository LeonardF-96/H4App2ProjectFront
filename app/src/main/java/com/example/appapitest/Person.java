package com.example.appapitest;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable
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

    public Person(int id, String name, int phone, String address, String note, Boolean favorite, int hairId, int programLanguageId) {
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

    //Parcelable constructor - for sending a Person object with Intent
    protected Person(Parcel in)
    {
        id = in.readInt();
        name = in.readString();
        phone = in.readInt();
        address = in.readString();
        note = in.readString();
        favorite = in.readByte() != 0;
        hairId = in.readInt();
        programLanguageId = in.readInt();
        hairColor = in.readString();
        programmingLanguage = in.readString();
    }
    //Creator for Parcelable
    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }
        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
    //Implement Parcelable methods
    @Override
    public int describeContents()
    {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(phone);
        dest.writeString(address);
        dest.writeString(note);
        dest.writeByte((byte) (favorite ? 1 : 0));
        dest.writeInt(hairId);
        dest.writeInt(programLanguageId);
        dest.writeString(hairColor);
        dest.writeString(programmingLanguage);
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