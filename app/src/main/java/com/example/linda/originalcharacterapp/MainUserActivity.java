package com.example.linda.originalcharacterapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainUserActivity extends AppCompatActivity implements DisplayCharacter.OnFragmentInteractionListener {
    private Fragment fragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            boolean val;
            switch (menuItem.getItemId ()) {
                case R.id.navigation_home: //home is the homepage of your current chracter gallery
                    fragment = new HomeFragment ();
                    loadFragment(fragment);
                    val = true;
                    break;

                case R.id.navigation_dashboard:
                    val = true;
                    fragment = new SearchActivity();
                    loadFragment(fragment);
                    break;
                case R.id.create_character:
                    fragment = new CreateOCFragment ();
                    loadFragment(fragment);
                    val = true;
                    break;
                case R.id.navigation_likes:
                    fragment = new LikesFragment ();
                    loadFragment(fragment);
                    val = true;
                    break;
                case R.id.setting_activity:
                    fragment = new SettingsFragment ();
                    loadFragment(fragment);
                    val = true;
                    break;
            }
            val = false;
            loadFragment(fragment);
            return val;
        }
    };
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate (savedInstanceState);
            setContentView (R.layout.activity_main_user);

            BottomNavigationView navigation = (BottomNavigationView) findViewById (R.id.navigation);
            navigation.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener);
            loadFragment (new HomeFragment ()); //will load the default activity which is the user's gallery
        }

        private void loadFragment(Fragment fragment) {
            // load fragment
            FragmentTransaction transaction = getSupportFragmentManager ().beginTransaction ();
            transaction.replace (R.id.fragment_container, fragment);
            transaction.addToBackStack (null);
            transaction.commit ();
        }

        @Override
         public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
         }
    @Override
    public void onFragmentInteraction(Uri uri){

    }
}
