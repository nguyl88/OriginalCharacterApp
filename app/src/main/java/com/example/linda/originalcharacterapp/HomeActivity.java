package com.example.linda.originalcharacterapp;

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

public class HomeActivity extends AppCompatActivity {
    // references to our images
    private Integer[] mThumbIds = new Integer[]{
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7
    };

    private ImageView imageSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button createButton = (Button) findViewById(R.id.create_character_button);
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

}
