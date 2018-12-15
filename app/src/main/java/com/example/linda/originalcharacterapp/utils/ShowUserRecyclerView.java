package com.example.linda.originalcharacterapp.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linda.originalcharacterapp.R;
import com.example.linda.originalcharacterapp.model.CharacterInformation;
import com.example.linda.originalcharacterapp.model.Likes;
import com.example.linda.originalcharacterapp.model.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class ShowUserRecyclerView extends RecyclerView.Adapter<ShowUserRecyclerView.ShowViewHolder> {

    final private List<CharacterInformation> mDataset;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mainRef;
    private FirebaseUser firebaseUser;
    private FirebaseStorage firebaseStorage;
    private FirebaseAuth firebaseAuth;
    private Context context;
    private LikeHeart heart;
    private GestureDetector gestureDetector;
    private Boolean likedByCurrentUser;
    private StringBuilder listOfUserLikes;
    private UserInformation currentUser;
    private String showUserLikes;
    private CharacterInformation currentOC;
    private ShowUserRecyclerView.ShowViewHolder mainHolder;


    public static class ShowViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView cName, cAge, cSpecies, cPersonality, cPowers, cFamily, cBio, currentUser;
        private TextView titleName, titleAge, titleSpecies, titlePersonality, titlePowers, titleFamily, titleBio;
        private TextView likedUsersList;
        private ImageView likeButton, redLikeButton;
        private View divideLines;
        private Context context;
        private DatabaseReference characterRef;
        private FirebaseUser firebaseUser;
        private LikeHeart heart;
        private GestureDetector gestureDetector;


        private ShowViewHolder(View view) {
            super (view);

            divideLines = view.findViewById (R.id.useroc_divider);
            image = (ImageView) view.findViewById (R.id.otheruser_image);
            titleName = (TextView) view.findViewById (R.id.user_ocname);
            titleAge = (TextView) view.findViewById (R.id.user_ocage);
            titleSpecies = (TextView) view.findViewById (R.id.user_ocSpeciesName);
            titlePersonality = (TextView) view.findViewById (R.id.user_ocPersonalityType);
            titlePowers = (TextView) view.findViewById (R.id.user_ocPowersA);
            titleFamily = (TextView) view.findViewById (R.id.user_ocFamilyTree);
            titleBio = (TextView) view.findViewById (R.id.user_ocBiography);

            cName = (TextView) view.findViewById (R.id.user_displayName);
            cAge = (TextView) view.findViewById (R.id.user_displayAge);
            cSpecies = (TextView) view.findViewById (R.id.user_displaySpecies);
            cPersonality = (TextView) view.findViewById (R.id.user_displayPersonality);
            cPowers = (TextView) view.findViewById (R.id.user_displayPowers);
            cFamily = (TextView) view.findViewById (R.id.user_displayFamily);
            cBio = (TextView) view.findViewById (R.id.user_displayBio);
            likeButton = view.findViewById (R.id.likesButton);
            redLikeButton = view.findViewById (R.id.redLikeButton);

            likedUsersList = view.findViewById (R.id.userlikes);

        }

    } //end of viewholder

    public ShowUserRecyclerView(List<CharacterInformation> myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    @Override
    public ShowUserRecyclerView.ShowViewHolder onCreateViewHolder(ViewGroup parent,
                                                                  int viewType) {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.recyclerview_otheruser, parent, false);
        ShowUserRecyclerView.ShowViewHolder vh = new ShowUserRecyclerView.ShowViewHolder (view);
        return vh;
    }


    @Override
    public void onBindViewHolder(ShowUserRecyclerView.ShowViewHolder holder, final int position) {

        final CharacterInformation oc = mDataset.get (position);
        heart = new LikeHeart (holder.likeButton, holder.redLikeButton);
        currentOC = mDataset.get (position);
        mainHolder = holder;

        gestureDetector = new GestureDetector (context, new GestureListener ());

        System.out.println ("Adding other user character" + oc.getCharacterName ());
        Picasso.get ().load (oc.getPhoto_id ()).placeholder (R.mipmap.ic_launcher).into (holder.image);
        holder.cName.setText (oc.getCharacterName ());
        holder.cAge.setText (oc.getCharacterAge ());
        holder.cSpecies.setText (oc.getCharacterSpecies ());
        holder.cPersonality.setText (oc.getCharacterPersonality ());
        holder.cPowers.setText (oc.getCharacterPowers ());
        holder.cFamily.setText (oc.getCharacterFamily ());
        holder.cBio.setText (oc.getCharacterBio ());



        getLikeString (); //display buttons

        System.out.println ("Binding images..." + oc.getCharacter_id ());
    }


    class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private ImageView image;
        private CharacterInformation oc;
        private ShowUserRecyclerView.ShowViewHolder holder;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
        /*    //   getCharacterPhotoLikes(image, holder);
            DatabaseReference reference = FirebaseDatabase.getInstance ().getReference ("User Accounts");
           *//* Query query = reference.child (currentOC.getUser_id ())
                    .child ("character")
                    .child (currentOC.getCharacter_id ())
                    .child ("likes");*//*
            //  .orderByChild(currentOC.getCharacter_id ()).equalTo(R.string.likes);

            Log.d ("Double tap on the heart", "Likes");
            Query query  = reference.child(currentOC.getUser_id()).child("likedposts").child(currentOC.getCharacter_id());

            query.addListenerForSingleValueEvent (new ValueEventListener () {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    for (DataSnapshot singleSnapshot : dataSnapshot.getChildren ()) {
                        String keyID = singleSnapshot.getKey ();
                        System.out.println ("Passing through the getCharacterPhotoLikes method");
                        //case 1 user liked the photo
                        if (likedByCurrentUser && singleSnapshot.getValue (Likes.class).getUser_id ()
                                .equals (FirebaseAuth.getInstance ().getCurrentUser ().getUid ())) {

                            //removing the likes when user taps
                            mainRef.child (currentOC.getUser_id ()).child ("character")
                                    .child (currentOC.getCharacter_id ())
                                    .child ("likes")
                                    .child (keyID).removeValue ();

                            heart.toggleLike ();
                            getLikeString ();

                        }
                        //case 2 user did not like the photo
                        else if (!likedByCurrentUser) {
                            //add new like when user taps
                            addNewLikes ();
                            break;

                        }

                    }
                    if (!dataSnapshot.exists ()) {
                        addNewLikes ();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });*/
            addNewLikes ();
            heart.toggleLike ();
            return true;

        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size ();
    }

    private void getCharacterPhotoLikes() {
        mainRef = FirebaseDatabase.getInstance ().getReference ("User Accounts");

        DatabaseReference reference = FirebaseDatabase.getInstance ().getReference ("User Accounts");
        Query query = reference.child (currentOC.getUser_id ())
                .child ("character").child (currentOC.getCharacter_id ());
//                                .orderByChild(currentOC.getCharacter_id ()).equalTo(R.string.likes);

        query.addListenerForSingleValueEvent (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren ()) {
                    String keyID = singleSnapshot.getKey ();
                    System.out.println ("Passing through the getCharacterPhotoLikes method");
                    //case 1 user liked the photo
                    if (likedByCurrentUser && singleSnapshot.getValue (Likes.class).getUser_id ()
                            .equals (FirebaseAuth.getInstance ().getCurrentUser ().getUid ())) {

                        //removing the likes when user taps
                        mainRef.child (currentOC.getUser_id ()).child ("character")
                                .child (currentOC.getCharacter_id ())
                                .child ("likes")
                                .child (keyID).removeValue ();

                        heart.toggleLike ();
                        getLikeString ();

                    }
                    //case 2 user did not like the photo
                    else if (!likedByCurrentUser) {
                        //add new like when user taps
                        addNewLikes ();
                        break;

                    }

                }
                if (!dataSnapshot.exists ()) {
                    addNewLikes ();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addNewLikes() {
        Log.d ("Adding new likes", "Likes");

        mainRef = FirebaseDatabase.getInstance ().getReference ("User Accounts");

        String newLikeId = mainRef.push ().getKey ();
       Likes likes = new Likes (currentUser.getUser_id ());


        mainRef.child(currentOC.getUser_id()).child("likedposts").child(currentOC.getCharacter_id())
               .child(newLikeId).setValue(likes);

     //  mainRef.child (currentOC.getUser_id ()).child ("character").child (currentOC.getCharacter_id ())
              //  .child ("likes").child (newLikeId).setValue (likes);

        heart.toggleLike ();
        getLikeString ();

    }

    private void getLikeString() {
        DatabaseReference reference = FirebaseDatabase.getInstance ().getReference ("User Accounts");
     //   Query query = reference.child (currentOC.getUser_id ()).child ("character").
       //         orderByChild (currentOC.getCharacter_id ()).equalTo ("likes");

        Query query  = reference.child(currentOC.getUser_id()).child("likedposts").child(currentOC.getCharacter_id());
        query.addListenerForSingleValueEvent (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listOfUserLikes = new StringBuilder ();

                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren ()) {
                    DatabaseReference reference = FirebaseDatabase.getInstance ().getReference ("User Accounts");
                   /* Query query = reference.child (currentOC.getUser_id ()).child ("character")
                            .child (currentOC.getCharacter_id ()).child ("likes").
                                    orderByChild ("user_id").equalTo (singleSnapshot.getValue (Likes.class).getUser_id ());*/

                    Query query  = reference.child(currentOC.getUser_id()).child("likedposts").child(currentOC.getCharacter_id())
                    .orderByChild("user_id").equalTo(singleSnapshot.getValue(Likes.class).getUser_id());
                    query.addListenerForSingleValueEvent (new ValueEventListener () {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot singleSnapshot : dataSnapshot.getChildren ()) {
                                Log.d (TAG, "Found user by id for likes " + singleSnapshot.getValue (UserInformation.class).getEmail ());

                                listOfUserLikes.append (singleSnapshot.getValue (UserInformation.class).getUsername ());
                                listOfUserLikes.append (", ");

                                String[] splitUsers = listOfUserLikes.toString ().split (",");

                                //User account settings need to be created
                                if (listOfUserLikes.toString ().contains (currentUser.getUsername ())) {
                                    likedByCurrentUser = true;
                                } else {
                                    likedByCurrentUser = false;
                                }

                                int length = splitUsers.length;

                                if (length == 1) {

                                    showUserLikes = "Liked by " + splitUsers[0];

                                } else if (length == 2) {
                                    showUserLikes = "Liked by " + splitUsers[0] + " and " + splitUsers[1];
                                } else if (length > 2) {
                                    showUserLikes = "Liked by " + splitUsers[0]
                                            + ", " + splitUsers[1]
                                            + " and " + splitUsers[2];
                                }

                                Log.d (TAG, "onDataChange: likes string: " + showUserLikes);

                            }

                            if (!dataSnapshot.exists ()) {
                                showUserLikes = "";
                                likedByCurrentUser = false;
                                likesPost ();
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void testToggle() {

    }
    private void likesPost() {
        //setupwidget
        if(likedByCurrentUser == true) {

            mainHolder.likeButton.setVisibility (View.GONE);
            mainHolder.redLikeButton.setVisibility(View.VISIBLE);
            mainHolder.redLikeButton.setOnTouchListener (new View.OnTouchListener () {
                @Override
                public boolean onTouch(View v, MotionEvent motionEvent) {
                    Log.d (TAG, "Red heart is touched.");
                    return gestureDetector.onTouchEvent (motionEvent);
                }
            });
        }
        else {
            mainHolder.redLikeButton.setVisibility (View.GONE);
            mainHolder.likeButton.setVisibility(View.VISIBLE);
            mainHolder.likeButton.setOnTouchListener (new View.OnTouchListener () {
                @Override
                public boolean onTouch(View v, MotionEvent motionEvent) {
                    Log.d (TAG, "White heart is touched.");
                    return gestureDetector.onTouchEvent (motionEvent);
                }

            });

        }
    }

    private void getCurrentUser(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User Account");
        Query query = reference
                .child(firebaseAuth.getUid ())
                .orderByChild("user_id")
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for ( DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){
                    currentUser = singleSnapshot.getValue(UserInformation.class);
                }
                // getLikesString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: query cancelled.");
            }
        });
    }



}
