package com.example.linda.originalcharacterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.linda.originalcharacterapp.model.UserInformation;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//This will open the register format for the user to enter the information
public class Register extends AppCompatActivity  {
  //  SQLiteOpenHelper openHelper;
  //  SQLiteDatabase db;

    EditText txtUsername, txtEmail,txtPassword;
    UserInformation newUser;
    String newUsername, newEmail, newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button createButton = (Button)findViewById(R.id.create_account);

      /*    createButton.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                createAccount();

        }); }*/
        txtUsername = (EditText)findViewById(R.id.txt_username);
     //   newUsername = txtUsername.getText().toString();

        txtEmail = (EditText)findViewById(R.id.txt_email);
    //    newEmail = txtEmail.getText().toString();

        txtPassword = (EditText)findViewById(R.id.txt_password);
      //  newPassword = txtPassword.getText().toString();

        setContentView(R.layout.register_layout);
    }
    private void createAccount() {
        Intent intent = new Intent (Register.this, HomeActivity.class);
        startActivity (intent);
        //  newUser = new UserInformation(newUsername, newEmail, newPassword);

        // Log.v("Create information", "User created for " + txtEmail.getText());

    }
/*

    private boolean isEmailValid(String email) {
        //replace with your logic
        if (!email.contains("@")) {
            System.out.println("Account does not exist");
            createAccount ();  //opens the user's profile
        }
        System.out.println("Valid email");
        return email.contains("@");

    }

    private boolean isPasswordValid(String password) {

        Pattern pattern = Pattern.compile("[0-9]");
        Pattern pattern2 = Pattern.compile("[A-Z]");

        Matcher numberMatch = pattern.matcher(password);
        Matcher upperMatch = pattern2.matcher(password);

        if (password.length() < 8) {
            System.out.println("Must have at least 8 characters long");
//            passwordAlert.setTitle("Must have at least 8 characters long");
//            passwordAlert.setContentText("Password must be 8 characters long");
//            passwordAlert.showAndWait();
            return false;
        }
        else if (!numberMatch.find()){
            System.out.println("Password must have at least one number");

        */
/*    passwordAlert.setTitle("Digit");
            passwordAlert.setContentText("Password must have at least have a number");
            passwordAlert.showAndWait();*//*

            return false;
        }
        else if (!upperMatch.find()) {
//            passwordAlert.setTitle("Uppercase");
//            passwordAlert.setContentText("Password must have at least have an uppercase");
//            passwordAlert.showAndWait();
            System.out.println("Password must have at least one uppercase");
            return false;
        }
        return true;
    }
*/




}
