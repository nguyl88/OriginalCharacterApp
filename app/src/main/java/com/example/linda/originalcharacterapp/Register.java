package com.example.linda.originalcharacterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.linda.originalcharacterapp.data.UserHelper;
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
    UserHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        txtUsername = (EditText)findViewById(R.id.txt_username);
        txtEmail = (EditText)findViewById(R.id.txt_email);
        txtPassword = (EditText)findViewById(R.id.txt_password);
        databaseHelper = new UserHelper(this);

        Button createButton = (Button)findViewById(R.id.create_account);

        createButton.setOnClickListener(new View.OnClickListener () {

            @Override
            public void onClick(View view) {
                UserInformation newUser = new UserInformation(newUsername, newEmail, newPassword);

                newUsername = txtUsername.getText().toString();
                newEmail = txtEmail.getText().toString();
                newPassword = txtPassword.getText().toString();

                if(isEmailValid() && isPasswordValid() && notEmpty()) {
                    createUserData(newUser);
                } else {
                    toastMessage("You need to put something in the textfield");
                }
            }

        });

        createButton.setOnClickListener(new View.OnClickListener () {

            @Override
            public void onClick(View view) {
                openAccount();
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


    private boolean isEmailValid() {
        //replace with your logic
        if (!newEmail.contains("@")) {
            System.out.println("Account does not exist");
            openAccount ();  //opens the user's profile
        }
        System.out.println("Valid email");
        return newEmail.contains("@");

    }

    private boolean isPasswordValid() {

        Pattern pattern = Pattern.compile("[0-9]");
        Pattern pattern2 = Pattern.compile("[A-Z]");

        Matcher numberMatch = pattern.matcher(newPassword);
        Matcher upperMatch = pattern2.matcher(newPassword);

        if (newPassword.length() < 8) {
            System.out.println("Must have at least 8 characters long");
            return false;
        }
        else if (!numberMatch.find()) {
            System.out.println ("Password must have at least one number");
        }
        return true;
    }



}
