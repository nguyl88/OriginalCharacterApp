package com.example.linda.originalcharacterapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linda.originalcharacterapp.model.CharacterInformation;
import com.example.linda.originalcharacterapp.model.UserInformation;
import com.example.linda.originalcharacterapp.utils.ShowUserRecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OtherProfileActivity extends Fragment {

    private TextView otherUser;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private UserInformation showUser;
    private List <CharacterInformation>userOCs;
    private static final String USER_INFO = "USERINFO";
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView profileImage;

    private DatabaseReference reference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser firebaseUser;

    public static final OtherProfileActivity newInstance(UserInformation user) {
        OtherProfileActivity fragment = new OtherProfileActivity ();
        System.out.println("trasferring user SUCCESS " + user.getUser_id () );
        Bundle args = new Bundle ();
        args.putParcelable (USER_INFO ,user);
        fragment.setArguments (args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();

        otherUser =  getView().findViewById(R.id.otheruser_title);
        profileImage = getView().findViewById(R.id.otheruser_profile_image);
        mRecyclerView = (RecyclerView)getView().findViewById (R.id.recycler_other_user);
        reference = FirebaseDatabase.getInstance().getReference("User Account");

        String userid=showUser.getUser_id();

        reference.child(userid).orderByChild(userid).addListenerForSingleValueEvent(new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                String currentUser= dataSnapshot.child("users").child("username").getValue().toString();
                String currentImage= dataSnapshot.child("users").child("user_photo_id").getValue().toString();

                otherUser.setText(currentUser);
                Picasso.get ().load (currentImage).placeholder (R.mipmap.ic_launcher).into (profileImage);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Database error");
            }
        });
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager (this.getActivity());
        mRecyclerView.setLayoutManager(new GridLayoutManager (this.getActivity(),1));
        mRecyclerView.setLayoutManager(mLayoutManager);
        userOCs = new ArrayList<> ();
        showOtherUserOCs();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getArguments() != null){
            showUser = getArguments ().getParcelable (USER_INFO);
            System.out.println("The bundle is not null and username is name: " + showUser.getUsername ());
        }

       if (savedInstanceState != null) {
            System.out.println("saved instance state isn't a null");
        }
        else {
            System.out.println ("Display Character has an exception");
        }
        return inflater.inflate(R.layout.activity_otherprofile, container, false);
    }

    public void showOtherUserOCs() {
        System.out.println("Revoke the character reference ");
        reference = FirebaseDatabase.getInstance().getReference("User Account");
        // final Query query = characterReference;
        reference.child(showUser.getUser_id()).child("character").addValueEventListener(new ValueEventListener() {
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

                System.out.println("Setting other user profile adapter success");
                mAdapter = new ShowUserRecyclerView (userOCs, getActivity()); //where the image is inserted
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Database error");
            }

        });
    }//end of showotheruseroc method

}
