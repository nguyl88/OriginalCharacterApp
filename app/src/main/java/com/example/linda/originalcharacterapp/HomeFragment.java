package com.example.linda.originalcharacterapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
   /* private Integer[] testImages = new Integer[]{
            R.drawable.search, R.mipmap.ghostfinder101avatar,
            R.mipmap.ghostfinderchibis, R.drawable.setting,
            R.drawable.setting, R.drawable.setting,
            R.drawable.setting, R.drawable.search,
            R.drawable.logout, R.drawable.logout,
            R.drawable.setting, R.drawable.logout,
            R.drawable.logout, R.drawable.search,

    };*/
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

    private ImageView imageSelection;

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
        user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();

        currentUsername = getView().findViewById(R.id.current_username);
        mRecyclerView = (RecyclerView) getView().findViewById (R.id.imagegallery);
        reference = FirebaseDatabase.getInstance().getReference("User Account");

        String userid=user.getUid();
        reference.child(userid).orderByChild(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (user != null) {
                    //  Display username
                    String currentUser= dataSnapshot.child("users").child("username").getValue().toString();
                    currentUsername.setText(currentUser);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Database error");
            }
        });

        mRecyclerView.setHasFixedSize(true);
        // mLayoutManager = new LinearLayoutManager (this.getActivity());
        mRecyclerView.setLayoutManager(new GridLayoutManager (this.getActivity(),1));
       userOCs = new ArrayList<> ();
       // retrieveUserOCsByChild();
        retrieveUserOCs ();

}
        public void retrieveUserOCsByChild() {
        String user_id = user.getUid();
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

            DatabaseReference characterReference = reference.child(user.getUid()).child("character");
            // final Query query = characterReference;
            reference.child(user.getUid()).orderByChild("character").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ////Loop 1 to go through all the child nodes of characters
                    for(DataSnapshot characterSnapshot : dataSnapshot.getChildren()){
                            if (dataSnapshot.exists ()) {
                                CharacterInformation oc = characterSnapshot.getValue (CharacterInformation.class);
                                String ocKey = characterSnapshot.getKey ();
                                System.out.println ("Adding ocs: " + ocKey + " Name: " + oc.getCharacterName ());
                                Log.d ("TAGGING OCS", ocKey + " / " + oc.getCharacterName ());
                                userOCs.add (oc);
                                Toast.makeText (getActivity (), "Adding images " + oc.getCharacterName (), Toast.LENGTH_SHORT).show ();
                            } else { //if user haven't added any characters
                                break;
                            }
                    }
                    String file2 ="http://ghostfinder101.weebly.com/uploads/1/9/7/3/19737887/published/gear-of-diamond_1.png?1541826567";
                    userOCs.add(new CharacterInformation("34", "124",file2, "Diamond", "2002", "Diamond Angel", "Narcissistic","Jahara(friend)", "Flight, shard attacks", "lIVES IN MAIN"));
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

    public static class viewGalleryHolder extends RecyclerView.ViewHolder {
        View itemView;
        public viewGalleryHolder(View itemView) {
            super(itemView);
            itemView = itemView;
        }

        public void setTitle(String title) {
            TextView postTitle = (TextView) itemView.findViewById (R.id.characterName);
            postTitle.setText(title);
        }

        public void setCharacterInfo() {

        }

    }


}


