package com.example.linda.originalcharacterapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInformation implements Parcelable {
    private String username;
    private String email;
    private String password;
    private String user_id;


    public UserInformation(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserInformation(String user_id, String username, String email, String password) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static final Creator<UserInformation> CREATOR = new Creator<UserInformation>() {
        @Override
        public UserInformation createFromParcel(Parcel in) {
            return new UserInformation(in);
        }

        @Override
        public UserInformation[] newArray(int size) {
            return new UserInformation[size];
        }
    };
    public static Creator<UserInformation> getCREATOR() {
        return CREATOR;
    }
    protected UserInformation (Parcel in) {
        user_id = in.readString();
        password = in.readString();
        email = in.readString();
        username = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user_id);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(username);



    }
}
