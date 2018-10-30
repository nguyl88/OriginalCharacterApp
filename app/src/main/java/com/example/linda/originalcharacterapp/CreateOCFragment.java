package com.example.linda.originalcharacterapp;


import android.content.Context;
import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.app.Activity.RESULT_OK;

public class CreateOCFragment extends Fragment  implements View.OnClickListener{
    private static int RESULT_LOAD_IMAGE = 1;
    private Context context;
    private EditText cName, cAge, cSpecies,cPersonality,cFamily,cBiography,cPowers;
    private ImageView uploadImage;
    private CharacterInformation oc;
    private String nameValue, ageValue, speciesValue, familyValue, personalityValue, powerValue, bioValue;
    private FirebaseStorage storage;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);
        storage = FirebaseStorage.getInstance(); //storage initilization
        includesForCreateReference();

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
                createCharacter();
                break;
        }


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData ();
            uploadImage.setImageURI (selectedImage);  //set the imageview in the box

        }
    }
        public void includesForCreateReference() {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            StorageReference imagesRef = storageRef.child("images");

            StorageReference spaceRef = storageRef.child("images/space.jpg");
            imagesRef = spaceRef.getParent();
            StorageReference rootRef = spaceRef.getRoot();
            StorageReference earthRef = spaceRef.getParent().child("earth.jpg");

            StorageReference nullRef = spaceRef.getRoot().getParent();

            spaceRef.getPath();
            spaceRef.getName();
            spaceRef.getBucket();

            storageRef = storage.getReference();

            imagesRef = storageRef.child("images");

            String fileName = "space.jpg";
            spaceRef = imagesRef.child(fileName);

            String path = spaceRef.getPath();
            String name = spaceRef.getName();
            imagesRef = spaceRef.getParent();
        }

    private void uploadCharacter(Uri uri) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        storageRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).putFile(uri);

    }
    public void createCharacter() {
        FirebaseAuth.getInstance().getCurrentUser().getUid(); //get user id to save the objects
        String imageUpload = uploadImage.getResources().toString ();
        CharacterInformation oc = new CharacterInformation(imageUpload, nameValue, ageValue,speciesValue,  personalityValue,powerValue,familyValue, bioValue );


    }

}
