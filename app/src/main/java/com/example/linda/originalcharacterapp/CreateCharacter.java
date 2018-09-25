package com.example.linda.originalcharacterapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

        /*// Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale( ,
                    Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(context,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
*/

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

/*    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }*/



    }


}
