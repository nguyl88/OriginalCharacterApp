package com.example.linda.originalcharacterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CharacterView extends AppCompatActivity {
/*
    Display the uploaded character from the gridview gallery image
    Display the character's information
    Display edit/delete button.
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_characterview);
    }
}
