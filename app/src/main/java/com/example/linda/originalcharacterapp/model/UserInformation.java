package com.example.linda.originalcharacterapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class UserInformation implements Parcelable {
    private String username;
    private String email;
    private String password;
    private String user_id;
    private String user_photo_id;

    public UserInformation() {

    }

    public UserInformation(String user_id, String username, String email, String password, String user_photo_id) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.user_photo_id = user_photo_id;
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

    public String getUser_photo_id() {
        return user_photo_id;
    }

    public void setUser_photo_id(String user_photo_id) {
        this.user_photo_id = user_photo_id;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("user_id", user_id);
        result.put("user_photo_id", user_photo_id);
        result.put("username", username);
        result.put("email", email);
        result.put("password", password);



        return result;
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
        user_photo_id = in.readString();
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
        dest.writeString(user_photo_id);



    }
}
