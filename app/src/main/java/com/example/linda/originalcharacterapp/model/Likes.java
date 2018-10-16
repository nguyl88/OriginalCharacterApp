package com.example.linda.originalcharacterapp.model;

public class Likes {
    private int likes_id;
    private int character_id;
    private boolean likes;

    public Likes(int character_id, int likes_id) {
        this.likes = false;
        this.likes_id = likes_id;
        this.character_id = character_id;

    }

    public int getLikes_id() {
        return likes_id;
    }

    public void setLikes_id(int likes_id) {
        this.likes_id = likes_id;
    }

    public int getCharacter_id() {
        return character_id;
    }

    public void setCharacter_id(int character_id) {
        this.character_id = character_id;
    }

    public boolean isLikes() {
        return true;
    }

    public void setLikes(boolean likes) {
        this.likes = likes;
    }
}
