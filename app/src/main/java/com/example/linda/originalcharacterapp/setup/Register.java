package com.example.linda.originalcharacterapp.setup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linda.originalcharacterapp.MainUserActivity;
import com.example.linda.originalcharacterapp.R;
import com.example.linda.originalcharacterapp.model.UserInformation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity implements View.OnClickListener {

    private EditText txtUsername, txtEmail, txtPassword;
    private static final String TAG = "Register";
    private static int RESULT_LOAD_IMAGE = 1;
    private TextView existAccount;
    private ImageView userImage;

    //Firebase
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.register_layout);
        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance ();
        firebaseUser = firebaseAuth.getInstance ().getCurrentUser ();
        databaseReference = FirebaseDatabase.getInstance ().getReference ().child ("User Account");

        txtUsername = (EditText) findViewById (R.id.txt_username);
        txtEmail = (EditText) findViewById (R.id.txt_email);
        txtPassword = (EditText) findViewById (R.id.txt_password);
        existAccount = findViewById (R.id.existing_account);
        progressBar = findViewById (R.id.progress_register);

        Button createButton = (Button) findViewById (R.id.create_account);
        Button goBackButton = (Button) findViewById (R.id.tologin);
        createButton.setOnClickListener (this);
        String newEmail = txtEmail.getText ().toString ().trim ();
        String newPassword = txtPassword.getText ().toString ().trim ();
        String newUsername = txtUsername.getText ().toString ().trim ();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()) {

            case R.id.create_account:
                String newEmail = txtEmail.getText ().toString ().trim ();
                String newPassword = txtPassword.getText ().toString ().trim ();
                String newUsername = txtUsername.getText ().toString ().trim ();
                registerUser (newEmail, newPassword, newUsername);
                break;

            case R.id.existing_account:
                finish ();
                startActivity (new Intent (this, Login.class));
                break;

            case R.id.tologin:
                goBack ();
                break;
        }
    }

    private void registerUser(final String newEmail, final String newPassword, final String newUsername) {

        if (TextUtils.isEmpty (newEmail)) {
            Toast.makeText (this, "Please enter email", Toast.LENGTH_SHORT).show ();
            return;
        }

        if (TextUtils.isEmpty (newPassword)) {
            Toast.makeText (this, "Please enter password", Toast.LENGTH_SHORT).show ();
            return;
        }
        if (TextUtils.isEmpty (newUsername)) {
            Toast.makeText (this, "Please enter username", Toast.LENGTH_SHORT).show ();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher (newEmail).matches ()) {
            txtEmail.setError ("Please enter a valid email");
            txtEmail.requestFocus ();
            return;
        }

        if (newPassword.length () < 6) {
            txtPassword.setError ("Minimum length of password should be 6");
            txtPassword.requestFocus ();
            return;
        }

        progressBar.setVisibility (View.VISIBLE);

        firebaseAuth.createUserWithEmailAndPassword (newEmail, newPassword).addOnCompleteListener (new OnCompleteListener<AuthResult> () {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility (View.GONE);

                if (task.isSuccessful ()) {
                    //   setUserInfo(newUsername);
                    String userid = firebaseAuth.getCurrentUser ().getUid (); //Get user id
                    UserInformation newUser = new UserInformation(userid, newUsername, newEmail, newPassword);
                    DatabaseReference current_user_ref = databaseReference.child (userid);
                          current_user_ref.child("users").setValue(newUser);

                    finish ();
                    startActivity (new Intent (Register.this, UserImageSetUp.class));
                } else {

                    if (task.getException () instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText (getApplicationContext (), "You are already registered", Toast.LENGTH_SHORT).show ();

                    } else {
                        Toast.makeText (getApplicationContext (), task.getException ().getMessage (), Toast.LENGTH_SHORT).show ();
                    }

                }
            }
        });


    }

    private void setUserInfo(String newUsername) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder ().setDisplayName (newUsername).build ();
        firebaseUser.updateProfile (profileUpdates)
                .addOnCompleteListener (new OnCompleteListener<Void> () {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful ()) {
                            Log.d (TAG, "User profile updated.");
                        }
                    }
                });

    }

    private void openAccount() {
        Intent intent = new Intent (Register.this, MainUserActivity.class);
        startActivity (intent);
    }

    private boolean notEmpty() {
        if (txtEmail.length () != 0 && txtPassword.length () != 0 && txtUsername.length () != 0)
            return true;
        else return false;
    }

    private void goBack() {
        Intent intent = new Intent (Register.this, Login.class);
        startActivity (intent);
    }

    private void toastMessage(String message) {
        Toast.makeText (this, message, Toast.LENGTH_SHORT).show ();
    }

  /*  @Override
    public void onStart() {
        super.onStart ();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser ();
        if (currentUser != null) {
            openAccount ();
        }
        //  firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop ();
        if (mAuthListener != null) {
            firebaseAuth.removeAuthStateListener (mAuthListener);
        }
    }*/
}
