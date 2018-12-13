package com.example.linda.originalcharacterapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linda.originalcharacterapp.model.CharacterInformation;
import com.example.linda.originalcharacterapp.utils.RecycleViewAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    private List<CharacterInformation> userOCs;
    private TextView currentUsername;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser user;
    private final String TAG = "User";
    private  static int RESULT_LOAD_IMAGE = 1;
    private Uri selectedImage = null;
    private Uri downloadImage;
    private StorageReference storageReference;


    private ImageView userProfile;

    public static HomeFragment newInstance() {
    HomeFragment f = new HomeFragment ();
    return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();

        userProfile = getView().findViewById (R.id.user_profile_image);
        currentUsername = getView().findViewById(R.id.current_username);
        mRecyclerView = (RecyclerView) getView().findViewById (R.id.imagegallery); //recycler view)
        reference = FirebaseDatabase.getInstance().getReference("User Account");

        String userid=user.getUid();

        reference.child(userid).orderByChild(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (user != null) {
                    //  Display username
                    String currentUser= dataSnapshot.child("users").child("username").getValue().toString();
                    String currentImage = dataSnapshot.child("users").child("user_photo_id").getValue().toString();
                    currentUsername.setText(currentUser);
                    Picasso.get ().load (currentImage).placeholder (R.mipmap.ic_launcher).into (userProfile);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Database error");
            }
        });

        userProfile.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
               chooseImage();
            }

        });

        mRecyclerView.setHasFixedSize(true);
        // mLayoutManager = new LinearLayoutManager (this.getActivity());
        mRecyclerView.setLayoutManager(new GridLayoutManager (this.getActivity(),1));
       userOCs = new ArrayList<> ();
       // retrieveUserOCsByChild();
        retrieveUserOCs ();

}

    private void updateUserImage() {
        storageReference = FirebaseStorage.getInstance ().getReference ();



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImage = data.getData ();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap (getActivity().getApplicationContext ().getContentResolver (),
                        selectedImage);
                userProfile.setImageBitmap (bitmap);
            } catch (IOException e) {
                e.printStackTrace ();
            }
            userProfile.setImageURI (selectedImage);  //set the imageview in the box
        }

    }
    private void chooseImage() {
        Intent galleryIntent = new Intent (Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType ("image/*");
        startActivityForResult (galleryIntent, RESULT_LOAD_IMAGE);
    }
        public void retrieveUserOCsByChild() {
        String user_id = user.getUid();
        System.out.println("Revoke the character reference ");
            reference = FirebaseDatabase.getInstance().getReference("User Account");
            reference.child(user_id).child("character").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                    for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                        Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
                        String ocKey = dataSnapshot.getKey();
                     //   CharacterInformation newOC = dataSnapshot.getValue(CharacterInformation.class);
                        userOCs.add(dataSnapshot.getValue(CharacterInformation.class));
                    }

                    String file2 ="http://ghostfinder101.weebly.com/uploads/1/9/7/3/19737887/published/gear-of-diamond_1.png?1541826567";
                    userOCs.add(new CharacterInformation("34", "124",file2, "Diamond", "2002", "Diamond Angel", "Narcissistic","Jahara(friend)", "Flight, shard attacks", "lIVES IN MAIN"));
                    //userOCs.notifyDataSetChanged();
                    mAdapter = new RecycleViewAdapter (userOCs, getActivity()); //where the image is inserted
                    mRecyclerView.setAdapter(mAdapter);

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                    Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());
                    CharacterInformation newOC = dataSnapshot.getValue(CharacterInformation.class);
                    String ocKey = dataSnapshot.getKey();

                    // ...
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());
                    String ocKey = dataSnapshot.getKey();

                    // ...
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                    Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());
                    CharacterInformation newOC = dataSnapshot.getValue(CharacterInformation.class);
                    String ocKey = dataSnapshot.getKey();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w(TAG, "postComments:onCancelled", databaseError.toException());
                    Toast.makeText(getContext (), "Failed to load comments.",
                            Toast.LENGTH_SHORT).show();
                }
            });

        }

        public void retrieveUserOCs() {
            System.out.println("Revoke the character reference ");
            reference = FirebaseDatabase.getInstance().getReference("User Account");
            // final Query query = characterReference;
            reference.child(user.getUid()).child("character").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ////Loop 1 to go through all the child nodes of characters
                    for(DataSnapshot characterSnapshot : dataSnapshot.getChildren()){
                            if (dataSnapshot.getValue() != null ) {
                                CharacterInformation oc = characterSnapshot.getValue (CharacterInformation.class);
                                String ocKey = characterSnapshot.getKey ();
                                System.out.println ("Adding ocs: " + ocKey + " Name: " + oc.getCharacterName ());
                                Log.d ("TAGGING OCS", ocKey + " / " + oc.getCharacterName ());
                                userOCs.add (oc);
                                Toast.makeText (getContext(), "Adding images " + oc.getCharacterName (), Toast.LENGTH_SHORT).show ();
                            } else { //if user haven't added any characters
                                break;
                            }
                    }
                    mAdapter = new RecycleViewAdapter (userOCs, getActivity()); //where the image is inserted
                    mRecyclerView.setAdapter(mAdapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("Database error");
                }

            });
        }
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser == null) {
                startActivity(new Intent (getActivity(), Login.class));

        }
    }


}


