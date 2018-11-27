package com.example.linda.originalcharacterapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterInformation implements Parcelable {
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


    public CharacterInformation()  {

    }


    public CharacterInformation(String user_id,String character_id, String photo_id, String characterName, String characterAge,
                               String characterSpecies, String characterPersonality, String characterFamily,String characterPowers,
                                String characterBio) {
        this.user_id = user_id;
        this.photo_id = photo_id; //character image
        this.characterName = characterName;
        this.characterAge = characterAge;
        this.characterSpecies = characterSpecies;
        this.characterPersonality = characterPersonality;
        this.characterPowers = characterPowers;
        this.characterFamily = characterFamily;
        this.characterBio = characterBio;
        this.character_id = character_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }

    public List<Likes> getLikes() {
        return likes;
    }

    public void setLikes(List<Likes> likes) {
        this.likes = likes;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterAge() {
        return characterAge;
    }

    public void setCharacterAge(String characterAge) {
        this.characterAge = characterAge;
    }

    public String getCharacterSpecies() {
        return characterSpecies;
    }

    public String getCharacterFamily() {
        return characterFamily;
    }

    public void setCharacterFamily(String characterFamily) {
        this.characterFamily = characterFamily;
    }

    public void setCharacterSpecies(String characterSpecies) {
        this.characterSpecies = characterSpecies;
    }

    public String getCharacterPersonality() {
        return characterPersonality;
    }

    public void setCharacterPersonality(String characterPersonality) {
        this.characterPersonality = characterPersonality;
    }

    public String getCharacterPowers() {
        return characterPowers;
    }

    public void setCharacterPowers(String characterPowers) {
        this.characterPowers = characterPowers;
    }

    public String getCharacterBio() {
        return characterBio;
    }

    public void setCharacterBio(String characterBio) {
        this.characterBio = characterBio;
    }

    public String getCharacter_id() {
        return character_id;
    }

    public void setCharacter_id(String character_id) {
        this.character_id = character_id;
    }

    public HashMap<String, CharacterInformation> getCharacterMap() {
        return characterMap;
    }

    public void setCharacterMap(HashMap<String, CharacterInformation> characterMap) {

        this.characterMap = characterMap;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("user_id", user_id);
        result.put("photo_id", photo_id);
        result.put("character_id", character_id);
        result.put("characterName", characterName);
        result.put("characterAge", characterAge);
        result.put("Species", characterSpecies);
        result.put("Personality", characterPersonality);
        result.put("Family", characterFamily);
        result.put("Powers", characterPowers);
        result.put("Biography", characterBio);


        return result;
    }

    protected CharacterInformation(Parcel in) {
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

    public static final Creator<CharacterInformation> CREATOR = new Creator<CharacterInformation> () {
        @Override
        public CharacterInformation createFromParcel(Parcel in) {
            return new CharacterInformation (in);
        }

        @Override
        public CharacterInformation[] newArray(int size) {
            return new CharacterInformation[size];
        }
    };


    public static Creator<CharacterInformation> getCREATOR() {
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

}
