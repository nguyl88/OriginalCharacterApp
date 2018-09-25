package com.example.linda.originalcharacterapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.view.View;

//This is the homepage activity for user interactions with other things
//This will have the logout button, change information, delete account with a warning screen
//User will create their OC (character) which will lead to another activity page
//If network is included, then I will add the search bar to search for other users in the database
//Once the gridview image display the image, the user will click on the image to reveal the uploaded
//character biography.

public class HomeActivity extends AppCompatActivity {
    // references to the temporary images.
    private Integer[] mThumbIds = new Integer[]{
            R.drawable.search, R.mipmap.ghostfinder101avatar,
            R.mipmap.ghostfinderchibis, R.drawable.setting,
            R.drawable.setting, R.drawable.setting,
            R.drawable.setting, R.drawable.search,
            R.drawable.logout, R.drawable.logout,
            R.drawable.setting, R.drawable.logout,
            R.drawable.logout, R.drawable.search,

    };

    private ImageView imageSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button createButton = (Button) findViewById(R.id.create_character_button);
        createButton.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                createACharacter();
            }
        });

        TextView c = (TextView) findViewById(R.id.gallery_title);
        ImageButton settingsB = (ImageButton) findViewById(R.id.settingsButton);
        ImageButton searchB = (ImageButton) findViewById(R.id.searchButton);
        ImageButton logoutB = (ImageButton) findViewById(R.id.logoutButton);

      //  imageSelection = findViewById(R.id.selectImage);
        GridView gridview = (GridView) findViewById(R.id.user_grid_view);
        gridview.setAdapter(new ImageAdapter(this, mThumbIds));
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(HomeActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void createACharacter() {
        Intent intent = new Intent (HomeActivity.this, CreateCharacter.class);
        startActivity (intent);
    }

}
