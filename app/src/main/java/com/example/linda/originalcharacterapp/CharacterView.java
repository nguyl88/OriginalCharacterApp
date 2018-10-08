package com.example.linda.originalcharacterapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.linda.originalcharacterapp.model.CharacterInformation;

public class CharacterView extends AppCompatActivity {
/*
    Display the character's information
 */

    private Context context;
    private EditText cName, cAge, cSpecies,cPersonality,cFamily,cBiography,cPowers;
    private ImageView currentImage;
    private CharacterInformation oc;
    private String nameValue, ageValue, speciesValue, familyValue, personalityValue, powerValue, bioValue;
    private Switch editSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_characterview);

 //       Button editCharacterButton = (Button) findViewById (R.id.edit_character_button);
         editSwitch = (Switch) findViewById (R.id.editMode);
        Boolean isChecked = editSwitch.isChecked(); //true

        Button deleteCharacterButton = (Button) findViewById (R.id.delete_character_button);
        currentImage = (ImageView) findViewById (R.id.currentDisplayCharacter);

        cName = (EditText) findViewById (R.id.characterName);
        nameValue = cName.getText().toString();

        cAge = (EditText) findViewById (R.id.characterAge);
        ageValue = cAge.getText().toString();

        cSpecies = (EditText) findViewById (R.id.characterSpecies);
        speciesValue = cSpecies.getText().toString();

        cPersonality = (EditText) findViewById (R.id.characterPersonality);
        personalityValue = cPersonality.getText().toString();

        cFamily = (EditText) findViewById (R.id.characterFamily);
        familyValue = cFamily.getText().toString();

        cPowers = (EditText) findViewById (R.id.characterPowers);
        powerValue = cPowers.getText().toString();

        cBiography = (EditText) findViewById (R.id.characterBios);
        bioValue = cBiography.getText().toString();

       // currentImage.setOnClickListener (this); //ability to change

        deleteCharacterButton.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                deleteCharacter();
            }
        });
    }

    public void deleteCharacter() {

    }

    public void editCharacter() {
        if(editSwitch.isChecked()) {
            //User changes character's info and save and overwrite the character's info in the database.

        } else {
            //user is not in edit mode anymore.
        }
    }
}
