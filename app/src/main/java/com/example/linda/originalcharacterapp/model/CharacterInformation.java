package com.example.linda.originalcharacterapp.model;

import android.content.res.Resources;
import android.widget.ImageView;

public class CharacterInformation {
    private String characterName;
    private String characterAge;
    private String characterSpecies;
    private String characterPersonality;
    private String characterPowers;
    private String characterBio;
    private String characterFamily;

    private Resources characterImage;


    public CharacterInformation(Resources characterImage, String characterName, String characterAge,
                                String characterSpecies, String characterPersonality, String characterFamily,
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

    public Resources getCharacterImage() {
        return characterImage;
    }

    public void setCharacterImage(Resources characterImage) {
        this.characterImage = characterImage;
    }

}