package com.example.linda.originalcharacterapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.List;

public class CharacterWP implements Parcelable {
    private String user_id;
    private String photo_id;
    private String characterName;
    private String characterAge;
    private String characterSpecies;
    private String characterPersonality;
    private String characterPowers;
    private String characterBio;
    private String characterFamily;
    private String character_id;
    private List<Likes> likes;
    private HashMap<String, CharacterInformation> characterMap;


    protected CharacterWP(Parcel in) {
        user_id = in.readString ();
        photo_id = in.readString ();
        characterName = in.readString ();
        characterAge = in.readString ();
        characterSpecies = in.readString ();
        characterPersonality = in.readString ();
        characterPowers = in.readString ();
        characterBio = in.readString ();
        character_id = in.readString ();
    }

    public static final Creator<CharacterWP> CREATOR = new Creator<CharacterWP> () {
        @Override
        public CharacterWP createFromParcel(Parcel in) {
            return new CharacterWP (in);
        }

        @Override
        public CharacterWP[] newArray(int size) {
            return new CharacterWP[size];
        }
    };


    public static Creator<CharacterWP> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString (user_id);
        dest.writeString (photo_id);
        dest.writeString (characterName);
        dest.writeString (characterAge);
        dest.writeString (characterSpecies);
        dest.writeString (characterPersonality);
        dest.writeString (characterFamily);
        dest.writeString (characterPowers);
        dest.writeString (characterBio);
        dest.writeString (character_id);

    }

    @Override
    public String toString() {
        return "CharacterInformation{" +
                "user_id='" + user_id + '\'' +
                ", photo_id='" + photo_id + '\'' +
                ", characterName='" + characterName + '\'' +
                ", characterAge='" + characterAge + '\'' +
                ", characterSpecies='" + characterSpecies + '\'' +
                ", characterPersonality='" + characterPersonality + '\'' +
                ", characterPowers='" + characterPowers + '\'' +
                ", characterBio='" + characterBio + '\'' +
                ", characterFamily='" + characterFamily + '\'' +
                ", likes=" + likes +
                '}';
    }
}