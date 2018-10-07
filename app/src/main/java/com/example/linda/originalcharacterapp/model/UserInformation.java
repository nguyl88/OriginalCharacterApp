package com.example.linda.originalcharacterapp.model;

public class UserInformation {
    private String username;
    private String email;
    private String password;
    private int id;


    public UserInformation(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserInformation(int id, String username, String email, String password) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
