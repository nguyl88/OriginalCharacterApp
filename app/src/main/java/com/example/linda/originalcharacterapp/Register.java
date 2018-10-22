package com.example.linda.originalcharacterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linda.originalcharacterapp.data.UserHelper;
import com.example.linda.originalcharacterapp.model.UserInformation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

//This will open the register format for the user to enter the information
public class Register extends AppCompatActivity implements View.OnClickListener{
  //  SQLiteOpenHelper openHelper;
  //  SQLiteDatabase db;

    private EditText txtUsername, txtEmail,txtPassword;
    private UserInformation newUser;
//    private String newUsername, newEmail, newPassword;
    private  UserHelper databaseHelper;
    private static final String TAG  = "Register";
    private TextView existAccount;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        txtUsername = (EditText)findViewById(R.id.txt_username);
        txtEmail = (EditText)findViewById(R.id.txt_email);
        txtPassword = (EditText)findViewById(R.id.txt_password);
        databaseHelper = new UserHelper(this);
        existAccount = findViewById(R.id.existing_account);
        progressBar = findViewById(R.id.progress_register);

        Button createButton = (Button)findViewById(R.id.create_account);
        createButton.setOnClickListener(this);

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_account:
                registerUser();
                break;

            case R.id.existing_account:
                finish();
                startActivity(new Intent(this, Login.class));
                break;
        }
    }

    private void registerUser() {

        String newEmail = txtEmail.getText().toString().trim();
        String newPassword = txtPassword.getText().toString().trim();
        String newUsername = txtUsername.getText().toString().trim();
        if (TextUtils.isEmpty(newEmail)) {
        Toast.makeText (this, "Please enter email", Toast.LENGTH_SHORT).show();
        return;
        }

        if (TextUtils.isEmpty(newPassword)) {
            Toast.makeText (this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(newUsername)) {
            Toast.makeText (this, "Please enter username", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()) {
            txtEmail.setError("Please enter a valid email");
            txtEmail.requestFocus();
            return;
        }

        if (newPassword.length() < 6) {
            txtPassword.setError("Minimum lenght of password should be 6");
            txtPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.createUserWithEmailAndPassword(newEmail, newPassword).addOnCompleteListener(new OnCompleteListener<AuthResult> () {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(Register.this, MainUserActivity.class));
                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });




    }

    private void openAccount() {
        Intent intent = new Intent (Register.this, MainUserActivity.class);
        startActivity (intent);
    }
    private boolean notEmpty() {
        if (txtEmail.length() != 0 && txtPassword.length() != 0 && txtUsername.length() != 0)
        return true;
        else return false;
    }

    public void createUserData(UserInformation newUser) {
        boolean insertData = databaseHelper.insertUserData(newUser);

        if (insertData) {
            toastMessage ("New user successfully inserted");
        } else toastMessage ("New user did not insert. ");
    }
    private void toastMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }




}
