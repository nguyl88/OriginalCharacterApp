package com.example.linda.originalcharacterapp.sampledata;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.linda.originalcharacterapp.R;

public class SettingActivity extends AppCompatActivity {
    Button saveButton, logoutButton,deleteAccountButton;
    EditText txtUsername, txtEmail,txtPassword;
    //need database to change the user's data by email id and delete account
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.setting_activity);

        saveButton = (Button)findViewById(R.id.save_setting);
        deleteAccountButton = (Button) findViewById (R.id.deleteAccountbutton);
        logoutButton = (Button)findViewById(R.id.logoutbutton);

        txtUsername = (EditText)findViewById(R.id.txt_username);
        txtEmail = (EditText)findViewById(R.id.txt_email);
        txtPassword = (EditText)findViewById(R.id.txt_password);


    }


}
