package com.example.linda.originalcharacterapp;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.linda.originalcharacterapp.model.CharacterInformation;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class CreateOCFragment extends Fragment  implements View.OnClickListener{
    private static int RESULT_LOAD_IMAGE = 1;
    Context context;
    EditText cName, cAge, cSpecies,cPersonality,cFamily,cBiography,cPowers;
    ImageView uploadImage;
    CharacterInformation oc;
    String nameValue, ageValue, speciesValue, familyValue, personalityValue, powerValue, bioValue;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);
        Button buttonLoadImage = (Button) getView().findViewById (R.id.submit_character_button);
        uploadImage = (ImageView) getView().findViewById (R.id.uploadCharacter);

        cName = (EditText) getView().findViewById (R.id.characterName);
        nameValue = cName.getText().toString();

        cAge = (EditText) getView().findViewById (R.id.characterAge);
        ageValue = cAge.getText().toString();

        cSpecies = (EditText) getView().findViewById (R.id.characterSpecies);
        speciesValue = cSpecies.getText().toString();

        cPersonality = (EditText) getView().findViewById (R.id.characterPersonality);
        personalityValue = cPersonality.getText().toString();

        cFamily = (EditText) getView().findViewById (R.id.characterFamily);
        familyValue = cFamily.getText().toString();

        cPowers = (EditText) getView().findViewById (R.id.characterPowers);
        powerValue = cPowers.getText().toString();

        cBiography = (EditText) getView().findViewById (R.id.characterBios);
        bioValue = cBiography.getText().toString();

        uploadImage.setOnClickListener (this);
        // buttonLoadImage.setOnClickListener (this);
        buttonLoadImage.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                createCharacter();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.uploadCharacter:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult (galleryIntent,RESULT_LOAD_IMAGE);
                break;

            case R.id.submit_character_button:
                break;
            //case R.id.buttonDownload
        }


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            uploadImage.setImageURI(selectedImage);  //set the imageview in the box

        }
 /*   @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super method removed
            if (resultCode == RESULT_OK) {
                if (requestCode == 1000) {
                    Uri returnUri = data.getData ();
                    try {
                        Bitmap bitmapImage = MediaStore.Images.Media.getBitmap (getActivity().getContentResolver (), returnUri);
                        uploadImage.setImageBitmap (bitmapImage);
                    } catch(IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
    */
    }

    public void createCharacter() {
        // CharacterInformation oc = new CharacterInformation(uploadImage.getResources (), nameValue, ageValue,speciesValue, personalityValue,familyValue, bioValue );
        //Then the method will add/save the new character to the database

    }

}
