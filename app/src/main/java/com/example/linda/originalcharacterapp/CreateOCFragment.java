package com.example.linda.originalcharacterapp;


import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.linda.originalcharacterapp.model.CharacterInformation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class CreateOCFragment extends Fragment  implements View.OnClickListener{
    private static int RESULT_LOAD_IMAGE = 1;
    private Context context;
    private EditText cName, cAge, cSpecies,cPersonality,cFamily,cBiography,cPowers;
    private ImageView uploadImage;
    private CharacterInformation oc;
    private String nameValue, ageValue, speciesValue, familyValue, personalityValue, powerValue, bioValue;
    private Uri selectedImage = null;
    private Uri downloadImage;
    private Bitmap compressedImageFile;

    //Firebase
    private FirebaseStorage storage;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;


    private String currentUserID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);

       // storageReference = FirebaseStorage.getInstance(); //storage initilization
        storage = FirebaseStorage.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        //storageReference =  FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance ().getReference ();
       // currentUserID =  firebaseAuth.getCurrentUser().getUid();

        Button buttonLoadImage = (Button) getView().findViewById (R.id.submit_character_button);
        uploadImage = (ImageView) getView().findViewById (R.id.uploadCharacter);
        cName = (EditText) getView().findViewById (R.id.characterName);
        cAge = (EditText) getView().findViewById (R.id.characterAge);
        cSpecies = (EditText) getView().findViewById (R.id.characterSpecies);
        cPersonality = (EditText) getView().findViewById (R.id.characterPersonality);
        cFamily = (EditText) getView().findViewById (R.id.characterFamily);
        cPowers = (EditText) getView().findViewById (R.id.characterPowers);
        cBiography = (EditText) getView().findViewById (R.id.characterBios);

        uploadImage.setOnClickListener (this);
        // buttonLoadImage.setOnClickListener (this);
        buttonLoadImage.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                uploadOC ();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.uploadCharacter:
                chooseImage();
                break;

            case R.id.submit_character_button:
                uploadOC();
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImage = data.getData ();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(),
                        selectedImage);
                uploadImage.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            uploadImage.setImageURI (selectedImage);  //set the imageview in the box
        }

    }

    private void chooseImage() {
       Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
      //  Intent intent = new Intent();
        galleryIntent.setType("image/*");
   //     galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult (galleryIntent,RESULT_LOAD_IMAGE);

      //  startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), RESULT_LOAD_IMAGE);
    }

    private void uploadOC() {
        //String values
        nameValue = cName.getText ().toString ().trim ();
        ageValue = cAge.getText ().toString ().trim ();
        speciesValue = cSpecies.getText ().toString ().trim();
        personalityValue = cPersonality.getText ().toString ().trim();
        familyValue = cFamily.getText ().toString ().trim();
        powerValue = cPowers.getText ().toString ().trim();
        bioValue = cBiography.getText ().toString ().trim();
        currentUserID = firebaseAuth.getCurrentUser().getUid();


        if (!TextUtils.isEmpty (nameValue) && selectedImage != null) {
          //  StorageReference fileReference = storageReference.child(System.currentTimeMillis()+ "." + getFileExtension(downloadImage));

            storageReference = storageReference.child("characterimage").child (currentUserID + "/" + UUID.randomUUID ().toString ()+ ".png");

                storageReference.putFile (selectedImage)

                         .addOnSuccessListener (new OnSuccessListener<UploadTask.TaskSnapshot> () {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                               downloadImage = taskSnapshot.getUploadSessionUri ();

                             //  String downloadPhoto = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri downloadPhotoUrl) {
                                        String characterId = databaseReference.child("characters").push ().getKey(); //creates unique random id
                                CharacterInformation newCharacter =  new CharacterInformation (currentUserID, characterId, downloadPhotoUrl.toString(),nameValue, ageValue, speciesValue,
                                        personalityValue, familyValue, powerValue, bioValue);

                                Map<String, Object> postValue = newCharacter.toMap();
                                Map<String, Object> childUpdates = new HashMap<> ();
                                databaseReference.child("User Account").child(currentUserID).child("character").child(characterId).setValue(newCharacter);

                               // childUpdates.put(nameValue, newCharacter);
                                databaseReference.updateChildren (childUpdates);
                                Toast.makeText (getActivity (), "Uploaded" + newCharacter.getCharacterName(), Toast.LENGTH_SHORT).show ();
                            }
                         });

                            }
                        })
                        .addOnFailureListener (new OnFailureListener () {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText (getActivity (), "Failed " + e.getMessage (), Toast.LENGTH_SHORT).show ();
                            }
                        })
                        .addOnProgressListener (new OnProgressListener<UploadTask.TaskSnapshot> () {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0 * taskSnapshot.getBytesTransferred () / taskSnapshot
                                        .getTotalByteCount ());
                            }
                        });

        }
        else {
            Toast.makeText (getActivity (), "Character needs name", Toast.LENGTH_SHORT).show ();
        }
    }

    private String getFileExtension(Uri uri) {
        Context applicationContext = CreateOCFragment.getContextOfApplication();
        ContentResolver cR =  applicationContext.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    public static Context contextOfApplication;
    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }

}
