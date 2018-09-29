package com.example.linda.originalcharacterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SettingActivity extends AppCompatActivity {
    Button saveButton, logoutButton;
    EditText txtUsername, txtEmail,txtPassword;
    //need database to change the user's data by email id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_setting);

        saveButton = (Button)findViewById(R.id.save_setting);
        logoutButton = (Button)findViewById(R.id.logoutbutton);

        txtUsername = (EditText)findViewById(R.id.txt_username);
        txtEmail = (EditText)findViewById(R.id.txt_email);
        txtPassword = (EditText)findViewById(R.id.txt_password);

        setContentView(R.layout.setting_activity);

    }
}
