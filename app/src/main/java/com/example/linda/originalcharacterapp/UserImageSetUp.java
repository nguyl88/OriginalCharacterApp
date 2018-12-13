package com.example.linda.originalcharacterapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

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

public class UserImageSetUp extends AppCompatActivity  {

    private Button uploadImageButton;
    private ImageView userImage;
    private Uri selectedImage = null;
    private Uri downloadImage;
    private static int RESULT_LOAD_IMAGE = 1;

    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.content_user_image_setup);

        firebaseAuth = FirebaseAuth.getInstance ();
        firebaseUser = firebaseAuth.getInstance ().getCurrentUser ();
        databaseReference = FirebaseDatabase.getInstance ().getReference ().child ("User Account");
        storageReference = FirebaseStorage.getInstance ().getReference ();

        uploadImageButton = findViewById (R.id.uploadImage);
        userImage = findViewById(R.id.uploadUserImage);

        userImage.setOnClickListener ( new View.OnClickListener (){
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        uploadImageButton.setOnClickListener( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if (userImage != null) {
                    uploadProfileImage ();
                }
                }
        }
        );
    }

    private void chooseImage() {
        Intent galleryIntent = new Intent (Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType ("image/*");
        startActivityForResult (galleryIntent, RESULT_LOAD_IMAGE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImage = data.getData ();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap (this.getApplicationContext ().getContentResolver (),
                        selectedImage);
                userImage.setImageBitmap (bitmap);
            } catch (IOException e) {
                e.printStackTrace ();
            }
            userImage.setImageURI (selectedImage);  //set the imageview in the box
        }

    }
    private void uploadProfileImage() {
        storageReference = storageReference.child ("user_image").child (firebaseAuth.getUid () +".png");
        storageReference.putFile (selectedImage)

                .addOnSuccessListener (new OnSuccessListener<UploadTask.TaskSnapshot> () {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        downloadImage = taskSnapshot.getUploadSessionUri ();

                        // String downloadPhoto = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                        storageReference.getDownloadUrl ().addOnSuccessListener (new OnSuccessListener<Uri> () {
                            @Override
                            public void onSuccess(Uri downloadPhotoUrl) {
                                String photoReference = downloadPhotoUrl.toString();
                                databaseReference.child(firebaseAuth.getUid()).child("users").child("user_photo_id").setValue(photoReference).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        System.out.println("PHOTO URL SUCCESSFULLY added to the database!");


                                    }
                                })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                         System.out.println("Writing images to database has failed");
                                         return;
                                            }
                                        });

                                finish();
                                startActivity (new Intent (UserImageSetUp.this, MainUserActivity.class));
                            }
                        });

                    }
                })
                .addOnFailureListener (new OnFailureListener () {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println ("User image failed");
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



}
