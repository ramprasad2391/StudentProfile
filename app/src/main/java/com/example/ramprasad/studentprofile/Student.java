package com.example.ramprasad.studentprofile;


import android.os.Parcelable;
import android.os.Parcel;

public class Student implements Parcelable {

    String name;
    String email;
    String language;
    boolean search;
    int mood;
    int edit_counter;

    public Student() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public int describeContents() {
        return 0;
    }

    public int getEdit_counter() {
        return edit_counter;
    }

    public void setEdit_counter(int edit_counter) {
        this.edit_counter = edit_counter;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(email);
        out.writeString(language);
        out.writeByte((byte) (search ? 1 : 0));
        out.writeInt(mood);
        out.writeInt(edit_counter);
    }

    public static final Parcelable.Creator<Student> CREATOR
            = new Parcelable.Creator<Student>() {
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    private Student(Parcel in) {
        name = in.readString();
        email = in.readString();
        language = in.readString();
        search = in.readByte() != 0;
        mood = in.readInt();
        edit_counter = in.readInt();
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", language='" + language + '\'' +
                ", search=" + search +
                ", mood=" + mood +
                ", edit_counter=" + edit_counter +
                '}';
    }
}

