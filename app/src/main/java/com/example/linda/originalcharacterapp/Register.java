package com.example.linda.originalcharacterapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

//This will open the register format for the user to enter the information
public class Register extends AppCompatActivity {
  //  SQLiteOpenHelper openHelper;
  //  SQLiteDatabase db;
    Button createButton;
    EditText txtUsername, txtEmail,txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createButton = (Button)findViewById(R.id.create_account);

        txtUsername = (EditText)findViewById(R.id.txt_username);
        txtEmail = (EditText)findViewById(R.id.txt_email);
        txtPassword = (EditText)findViewById(R.id.txt_password);

        setContentView(R.layout.register_layout);
    }
}
