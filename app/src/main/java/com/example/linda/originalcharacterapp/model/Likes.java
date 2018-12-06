package com.example.linda.originalcharacterapp.model;

public class Likes {
    private int likes_id;
    private String user_id;
    private boolean likes;

    public Likes() {

    }
    public Likes(String user_id, int likes_id) {
        this.likes = false;
        this.likes_id = likes_id;
        this.user_id = user_id;

    }

    public int getLikes_id() {
        return likes_id;
    }

    public void setLikes_id(int likes_id) {
        this.likes_id = likes_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean isLikes() {
        return true;
    }

    public void setLikes(boolean likes) {
        this.likes = likes;
    }
}
