package com.example.linda.originalcharacterapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

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
    private List<Likes> likes;

    private String characterImage;

    public CharacterInformation()  {

    }
    public CharacterInformation(String characterImage, String characterName, String characterAge,
                                String characterSpecies,String characterPowers, String characterPersonality, String characterFamily,
                                String characterBio) {
        this.characterName = characterName;
        this.characterAge = characterAge;
        this.characterSpecies = characterSpecies;
        this.characterPersonality = characterPersonality;
        this.characterPowers = characterPowers;
        this.characterFamily = characterFamily;
        this.characterBio = characterBio;
        this.characterImage = characterImage;
    }

    public CharacterInformation(String user_id,String photo_id,String characterImage, String characterName, String characterAge,
                                String characterPowers,String characterSpecies, String characterPersonality, String characterFamily,
                                String characterBio) {
        this.user_id = user_id;
        this.photo_id = photo_id;

        this.characterName = characterName;
        this.characterAge = characterAge;
        this.characterSpecies = characterSpecies;
        this.characterPersonality = characterPersonality;
        this.characterPowers = characterPowers;
        this.characterFamily = characterFamily;
        this.characterBio = characterBio;
        this.characterImage = characterImage;
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

    public String getCharacterImage() {
        return characterImage;
    }

    public void setCharacterImage(String characterImage) {
        this.characterImage = characterImage;
    }
    protected CharacterInformation(Parcel in) {
        user_id = in.readString();
        photo_id= in.readString();
        characterImage= in.readString();
        characterName = in.readString();
        characterAge = in.readString();
        characterSpecies = in.readString();
        characterPersonality= in.readString();
        characterPowers= in.readString();
        characterBio = in.readString();
    }
    public static final Creator<CharacterInformation> CREATOR = new Creator<CharacterInformation>() {
        @Override
        public CharacterInformation createFromParcel(Parcel in) {
            return new CharacterInformation(in);
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
        dest.writeString(user_id);
        dest.writeString(photo_id);
        dest.writeString(characterImage);
        dest.writeString(characterName);
        dest.writeString(characterAge);
        dest.writeString(characterSpecies);
        dest.writeString(characterPersonality);
        dest.writeString(characterFamily);
        dest.writeString(characterPowers);
        dest.writeString(characterBio);

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
                ", characterImage='" + characterImage + '\'' +
                '}';
    }
}
