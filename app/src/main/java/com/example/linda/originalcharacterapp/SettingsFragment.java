package com.example.linda.originalcharacterapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.linda.originalcharacterapp.model.CharacterInformation;
import com.example.linda.originalcharacterapp.setup.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class SettingsFragment extends Fragment {
    private Button changeEmailButton, logoutButton, deleteAccountButton, changePasswordButton, changeUsernameButton;
    private EditText txtUsername, txtEmail, txtPassword, confirmPassword, confirmPassword2, newPasswordField;
    private MainUserActivity listener;
    private static final String TAG = "SettingsFragment";
    //need database to change the user's data by email id and delete account

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_settings, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseAuth =  FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("User Account");



        changeEmailButton = (Button) getView ().findViewById (R.id.change_email_button);
        changePasswordButton = (Button) getView ().findViewById (R.id.change_password_button);
        changeUsernameButton = (Button) getView ().findViewById (R.id.change_username_button);
        deleteAccountButton = (Button) getView ().findViewById (R.id.deleteAccountbutton);

        logoutButton = (Button) getView ().findViewById (R.id.logoutbutton);

        txtUsername = (EditText) getView ().findViewById (R.id.txt_username);
        txtEmail = (EditText) getView ().findViewById (R.id.txt_email);
        txtPassword = (EditText) getView ().findViewById (R.id.txt_password); //old password field
        confirmPassword = (EditText) getView ().findViewById (R.id.confirm_password);
        newPasswordField = (EditText) getView ().findViewById (R.id.newPassword);
        confirmPassword2 = (EditText) getView ().findViewById (R.id.confirm_password2);

        changeUsernameButton.setOnClickListener (new View.OnClickListener() {
            String newUsername;
            @Override
            public void onClick(View view) {
                if (txtUsername.getText().toString() != "") {
                    newUsername = txtUsername.getText().toString();
                    reference.child(firebaseUser.getUid()).child("users").child("username").setValue(newUsername);
                    Toast.makeText(getContext(), "Username Change", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getContext(), "No changes made yet", Toast.LENGTH_SHORT).show();
                }

            }
        });

        changePasswordButton.setOnClickListener (new View.OnClickListener() {
            String newPassword, confirmPasswordSure, oldPassword;
            @Override
            public void onClick(View view) {

                if (firebaseAuth.getCurrentUser() != null) {
                    newPassword = newPasswordField.getText().toString();
                    oldPassword = txtPassword.getText().toString();
                    confirmPasswordSure = confirmPassword.getText().toString();

                    if(newPassword.equals(confirmPasswordSure)) {
                        AuthCredential credential = EmailAuthProvider
                                .getCredential (firebaseUser.getEmail (), txtPassword.getText ().toString ());


                        // Prompt the user to re-provide their sign-in credentials
                        firebaseUser.reauthenticate (credential)
                                .addOnCompleteListener (new OnCompleteListener<Void> () {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Log.d (TAG, "User re-authenticated.");
                                        if (task.isSuccessful ()) {
                                            firebaseUser.updatePassword (newPassword)
                                                    .addOnCompleteListener (new OnCompleteListener<Void> () {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful ()) {
                                                                Toast.makeText (getContext (), "Password Change", Toast.LENGTH_SHORT).show ();
                                                                Log.d (TAG, "User password updated.");
                                                            }
                                                        }
                                                    });
                                        } else {
                                            Toast.makeText (getContext (), "Password Incorrect", Toast.LENGTH_SHORT).show ();
                                        }
                                    }
                                });
                    }
                    reference.child(firebaseUser.getUid()).child("users").child("password").setValue(newPassword); //update reference

                }
                else {
                    Toast.makeText(getContext(), "No changes made yet", Toast.LENGTH_SHORT).show();
                }

            }
        });

        changeEmailButton.setOnClickListener (new View.OnClickListener() {
            String newEmail;
            @Override
            public void onClick(View view) {
                if(firebaseAuth.getCurrentUser() != null) {
                    String email = firebaseUser.getEmail();
                    String confirmPass = confirmPassword2.getText().toString();

                    // Check if user's email is verified
                    boolean emailVerified = firebaseUser.isEmailVerified();
                    String uid = firebaseUser.getUid();//Check unique id

                    if(txtEmail.getText().toString() != null ) {
                        newEmail = txtEmail.getText().toString();

                        AuthCredential credential = EmailAuthProvider
                                .getCredential(firebaseUser.getEmail(), confirmPass);

                        firebaseUser.reauthenticate(credential)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        firebaseUser.updateEmail(newEmail)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete (@NonNull Task < Void > task) {
                                                        if (task.isSuccessful ()) {
                                                            Toast.makeText (getContext (), "Email Change", Toast.LENGTH_SHORT).show ();
                                                            Log.d (TAG, "User email address updated.");
                                                        }
                                                    }
                                                });
                                        Log.d(TAG, "User re-authenticated.");
                                    }
                                });

                        reference.child(firebaseUser.getUid()).child("users").child("email").setValue(newEmail); //update reference

                    }
                    else {
                        Toast.makeText(getContext(), "No changes made yet", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        deleteAccountButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                StorageReference deleteAccountImages = storageRef.child("user_image").child(firebaseUser.getUid() + ".png");
                deleteAccountImages.delete(); //StorageReference userimage delete

                deleteAllCharacterImages ();
                reference.child(firebaseUser.getUid ()).removeValue(); //delete value first before deleting the user auth
                firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "User account deleted.");
                                    Intent intent = new Intent (getActivity (), Login.class); //returns to login page
                                    startActivity (intent);
                                }
                            }
                        });
            }
        });


        logoutButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if (firebaseAuth.getCurrentUser () != null)
                    firebaseAuth.signOut ();
                Intent intent = new Intent (getActivity (), Login.class);
                startActivity (intent);
            }
        });

    }
    private void deleteAllCharacterImages() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        final StorageReference deleteOCImages = storageRef.child("characterimage").child(firebaseUser.getUid());

        reference = FirebaseDatabase.getInstance().getReference("User Account");
        reference.child(firebaseUser.getUid()).child("character").addValueEventListener(new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot characterSnapshot : dataSnapshot.getChildren()){
                    if (dataSnapshot.getValue() != null ) {
                        CharacterInformation oc = characterSnapshot.getValue (CharacterInformation.class);
                        String ocKey = characterSnapshot.getKey ();
                        Toast.makeText (getContext(), "Deleting image " + ocKey, Toast.LENGTH_SHORT).show ();
                        deleteOCImages.child(ocKey + ".png").delete(); //StorageReference userimage delete

                    } else { //if user haven't added any characters
                        break;
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Database error");
            }

        });


    }

    @Override
    public void onDetach() {
        super.onDetach ();
        this.listener = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach (context);
        if (context instanceof Activity) {
            this.listener = (MainUserActivity) context;
        }
    }


}
