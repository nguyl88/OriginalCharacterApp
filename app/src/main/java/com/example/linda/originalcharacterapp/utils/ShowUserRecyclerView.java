package com.example.linda.originalcharacterapp.utils;

import android.content.Context;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class ShowUserRecyclerView extends RecyclerView.Adapter<ShowUserRecyclerView.ShowViewHolder>{

    final private List<CharacterInformation> mDataset;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference characterRef;
    private FirebaseUser firebaseUser;
    private FirebaseStorage firebaseStorage;
    private FirebaseAuth firebaseAuth;
    private Context context;
    private LikeHeart heart;
    private GestureDetector gestureDetector;



    public static class ShowViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView cName, cAge, cSpecies, cPersonality, cPowers, cFamily, cBio, currentUser;
        private TextView titleName, titleAge, titleSpecies,titlePersonality, titlePowers, titleFamily, titleBio;
        private ImageView likeButton, redLikeButton;
        private View divideLines;
        private Context context;
        private DatabaseReference characterRef;
        private FirebaseUser firebaseUser;
        private LikeHeart heart;
        private GestureDetector gestureDetector;

        private ShowViewHolder(View view) {
            super(view);

            divideLines = view.findViewById(R.id.useroc_divider);
            image = (ImageView) view.findViewById(R.id.otheruser_image);
            titleName = (TextView) view.findViewById(R.id.user_ocname);
            titleAge = (TextView) view.findViewById (R.id.user_ocage);
            titleSpecies = (TextView) view.findViewById (R.id.user_ocSpeciesName);
            titlePersonality = (TextView) view.findViewById (R.id.user_ocPersonalityType);
            titlePowers = (TextView) view.findViewById (R.id.user_ocPowersA);
            titleFamily = (TextView) view.findViewById (R.id.user_ocFamilyTree);
            titleBio = (TextView) view.findViewById (R.id.user_ocBiography);

            cName = (TextView) view.findViewById(R.id.user_displayName);
            cAge = (TextView) view.findViewById(R.id.user_displayAge);
            cSpecies = (TextView) view.findViewById(R.id.user_displaySpecies);
            cPersonality = (TextView) view.findViewById(R.id.user_displayPersonality);
            cPowers = (TextView) view.findViewById(R.id.user_displayPowers);
            cFamily = (TextView) view.findViewById(R.id.user_displayFamily);
            cBio = (TextView) view.findViewById(R.id.user_displayBio);
            likeButton= view.findViewById(R.id.likesButton);
            redLikeButton = view.findViewById(R.id.redLikeButton);

            redLikeButton.setVisibility (View.GONE);
            likeButton.setVisibility(View.VISIBLE);

        }

    } //end of viewholder

    public ShowUserRecyclerView(List<CharacterInformation> myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    @Override
    public ShowUserRecyclerView.ShowViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_otheruser, parent, false);
        ShowUserRecyclerView.ShowViewHolder vh = new ShowUserRecyclerView.ShowViewHolder (view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ShowUserRecyclerView.ShowViewHolder holder, final int position) {

        final CharacterInformation oc = mDataset.get(position);
        heart = new LikeHeart (holder.likeButton, holder.redLikeButton);

        gestureDetector = new GestureDetector(context, new GestureListener ());

        System.out.println("Adding other user character" + oc.getCharacterName());
        Picasso.get ().load (oc.getPhoto_id ()).placeholder (R.mipmap.ic_launcher).into (holder.image);
        holder.cName.setText (oc.getCharacterName ());
        holder.cAge.setText (oc.getCharacterAge ());
        holder.cSpecies.setText (oc.getCharacterSpecies ());
        holder.cPersonality.setText (oc.getCharacterPersonality ());
        holder.cPowers.setText (oc.getCharacterPowers ());
        holder.cFamily.setText (oc.getCharacterFamily ());
        holder.cBio.setText (oc.getCharacterBio ());

        likePost(holder); //display buttons

        System.out.println ("Binding images..." + oc.getCharacter_id ());
    }

    public class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User Accounts");
            Query query = reference.child(firebaseUser.getUid()).
                    orderByChild(image.getCharacterId()).equalTo(image.getUserId());


            heart.toggleLike ();
            return true;
        }
    }

    private void likePost(ShowUserRecyclerView.ShowViewHolder holder) {
        holder.likeButton.setOnTouchListener (new View.OnTouchListener () {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                Log.d(TAG, "White heart is touched.");
                return gestureDetector.onTouchEvent(motionEvent);
            }

        });

        holder.redLikeButton.setOnTouchListener (new View.OnTouchListener () {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                Log.d(TAG, "Red heart is touched.");
                return  gestureDetector.onTouchEvent(motionEvent);
            }
            });
        }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
