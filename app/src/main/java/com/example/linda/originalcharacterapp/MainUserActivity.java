package com.example.linda.originalcharacterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainUserActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private Fragment fragment;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate (savedInstanceState);
            setContentView (R.layout.activity_main_user);

            BottomNavigationView navigation = (BottomNavigationView) findViewById (R.id.navigation);
            navigation.setOnNavigationItemSelectedListener (this);
            //loadFragment (new MainFragment ()); //will load the default activity which is the user's gallery
        }

        private boolean loadFragment(Fragment fragment) {
            if (fragment != null) {
                getSupportFragmentManager ().beginTransaction ().replace (R.id.fragment_container, fragment).commit ();
                return true;
            }
            return false;
        }
    @Override
    public boolean  onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId ()) {
            case R.id.navigation_home: //home is the homepage of your current chracter gallery
                fragment = new MainFragment();
                Intent homeIntent = new Intent(this, HomeActivity.class);
                startActivity(homeIntent);
            break;
            case R.id.navigation_dashboard:
               break;
            case R.id.create_character:
                fragment = new CreateOCFragment ();
                Intent createIntent = new Intent(this, CreateCharacter.class);
                startActivity(createIntent);
                break;
            case R.id.setting_activity:
                fragment = new SettingsFragment ();
                Intent settingIntent = new Intent(this, SettingActivity.class);
                startActivity(settingIntent);
                break;
        }
        return false;
    }
}
