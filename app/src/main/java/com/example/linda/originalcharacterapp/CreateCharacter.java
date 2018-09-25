package com.example.linda.originalcharacterapp;

import android.content.Context;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class CreateCharacter extends AppCompatActivity implements View.OnClickListener {
    private static int RESULT_LOAD_IMAGE = 1;
    Context context;
    EditText cName, cAge, cSpecies,cPersonality,cFamily,cBiography,cPowers;
    ImageView uploadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.create_character);

        Button buttonLoadImage = (Button) findViewById (R.id.submit_character_button);
        uploadImage = (ImageView) findViewById (R.id.uploadCharacter);
        cName = (EditText) findViewById (R.id.characterName);
        cAge = (EditText) findViewById (R.id.characterAge);
        cSpecies = (EditText) findViewById (R.id.characterSpecies);
        cPersonality = (EditText) findViewById (R.id.characterPersonality);
        cFamily = (EditText) findViewById (R.id.characterFamily);
        cBiography = (EditText) findViewById (R.id.characterBios);
        cPowers = (EditText) findViewById (R.id.characterPowers);

        uploadImage.setOnClickListener (this);
        buttonLoadImage.setOnClickListener (this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            uploadImage.setImageURI(selectedImage);

        }
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


}
