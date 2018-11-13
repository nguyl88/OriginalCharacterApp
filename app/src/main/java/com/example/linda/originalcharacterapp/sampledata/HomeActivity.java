/*
package com.example.linda.originalcharacterapp.sampledata;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.linda.originalcharacterapp.R;
import com.example.linda.originalcharacterapp.utils.RecycleViewAdapter;

//This is the homepage activity for user interactions with other things
//This will have the logout button, change information, delete account with a warning screen
//User will create their OC (character) which will lead to another activity page
//If network is included, then I will add the search bar to search for other users in the database
//Once the gridview image display the image, the user will click on the image to reveal the uploaded
//character biography.

public class HomeActivity extends AppCompatActivity {
    // references to the temporary images.
    private Integer[] testImages = new Integer[]{
            R.drawable.search, R.mipmap.ghostfinder101avatar,
            R.mipmap.ghostfinderchibis, R.drawable.setting,
            R.drawable.setting, R.drawable.setting,
            R.drawable.setting, R.drawable.search,
            R.drawable.logout, R.drawable.logout,
            R.drawable.setting, R.drawable.logout,
            R.drawable.logout, R.drawable.search,

    };


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ImageView imageSelection;
    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

      //  imageSelection = findViewById(R.id.selectImage);
//        GridView gridview = (GridView) findViewById(R.id.user_grid_view);
//        gridview.setAdapter(new ImageAdapter (this, testImages));
//        gridview.setOnItemClickListener(new OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                Toast.makeText(HomeActivity.this, "" + position,
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
        mRecyclerView = (RecyclerView) findViewById(R.id.imagegallery);
        // use this setting to improve performance if you know that changes in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager (this);
       // mRecyclerView.setLayoutManager (mLayoutManager);
        mRecyclerView.setLayoutManager(new GridLayoutManager (this,2));

        // specify an adapter (see also next example)
        mAdapter = new RecycleViewAdapter (testImages);
        mRecyclerView.setAdapter(mAdapter);


    }


}
*/
