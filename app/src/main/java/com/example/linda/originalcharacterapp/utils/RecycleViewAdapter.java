package com.example.linda.originalcharacterapp.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linda.originalcharacterapp.DisplayCharacter;
import com.example.linda.originalcharacterapp.R;
import com.example.linda.originalcharacterapp.model.CharacterInformation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    final private List<CharacterInformation> mDataset;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference characterRef;
    private FirebaseUser firebaseUser;
    private FirebaseStorage firebaseStorage;
    private FirebaseAuth firebaseAuth;
    private Context context;
    OnCharacterItemClickListener characterItemClick;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView cName, cAge, cSpecies, cPersonality, cPowers, cFamily, cBio, currentUser;
        private TextView titleName, titleAge, titleSpecies,titlePersonality, titlePowers, titleFamily, titleBio;
        private ImageButton deleteButton, editButton, shareButton;
        private View divideLines;
        private Context context;
        private DatabaseReference characterRef;
        private FirebaseUser firebaseUser;


        private ViewHolder(View view) {
            super(view);

            divideLines = view.findViewById(R.id.divider);
            image = (ImageView) view.findViewById(R.id.blog_image);
            titleName = (TextView) view.findViewById(R.id.character_desc);
            titleAge = (TextView) view.findViewById (R.id.ocAge);
            titleSpecies = (TextView) view.findViewById (R.id.ocSpeciesName);
            titlePersonality = (TextView) view.findViewById (R.id.ocPersonalityType);
            titlePowers = (TextView) view.findViewById (R.id.ocPowersA);
            titleFamily = (TextView) view.findViewById (R.id.ocFamilyTree);
            titleBio = (TextView) view.findViewById (R.id.ocBiography);

            cName = (TextView) view.findViewById(R.id.displayName);
            cAge = (TextView) view.findViewById(R.id.displayAge);
            cSpecies = (TextView) view.findViewById(R.id.displaySpecies);
            cPersonality = (TextView) view.findViewById(R.id.displayPersonality);
            cPowers = (TextView) view.findViewById(R.id.displayPowers);
            cFamily = (TextView) view.findViewById(R.id.displayFamily);
            cBio = (TextView) view.findViewById(R.id.displayBio);
            editButton = view.findViewById(R.id.editOCButton);
            deleteButton = view.findViewById(R.id.deleteOCButton);
            shareButton = view.findViewById(R.id.shareOCButton);

            shareButton.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });

        }

        public void changeTextView(View view) {
            cName = (EditText) view.findViewById (R.id.displayName);
            cAge = (EditText) view.findViewById (R.id.displayAge);
            cSpecies = (EditText) view.findViewById (R.id.displaySpecies);
            cPersonality = (EditText) view.findViewById (R.id.displayPersonality);
            cPowers = (EditText) view.findViewById (R.id.displayPowers);
            cFamily = (EditText) view.findViewById (R.id.displayFamily);
            cBio = (EditText) view.findViewById (R.id.displayBio);
        }

    } //end of viewholder



    public RecycleViewAdapter(List<CharacterInformation> myDataset, Context context) {
            mDataset = myDataset;
            this.context = context;
        }

        @Override
        public RecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_single, parent, false);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
           final CharacterInformation oc = mDataset.get(position);

                this.characterItemClick = characterItemClick;

                Picasso.get ().load (oc.getPhoto_id ()).placeholder (R.mipmap.ic_launcher).into (holder.image);
                holder.cName.setText (oc.getCharacterName ());
                holder.cAge.setText (oc.getCharacterAge ());
                holder.cSpecies.setText (oc.getCharacterSpecies ());
                holder.cPersonality.setText (oc.getCharacterPersonality ());
                holder.cPowers.setText (oc.getCharacterPowers ());
                holder.cFamily.setText (oc.getCharacterFamily ());
                holder.cBio.setText (oc.getCharacterBio ());

                holder.deleteButton.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        deleteOC(oc.getCharacter_id (), oc.getUser_id (), position);

                    }
                });

            holder.editButton.setOnClickListener(new View.OnClickListener () {
                @Override
                public void onClick(View view) {

                   AppCompatActivity activity = (AppCompatActivity) view.getContext ();
                    Bundle bundle = new Bundle();
                    DisplayCharacter ocFragment = DisplayCharacter.newInstance(oc);
                   // bundle.putParcelable (oc.getCharacter_id(), oc);
                 //   ocFragment.setArguments (bundle);
                    System.out.println("Character instantiated " + oc.getCharacter_id ());
                    activity.getSupportFragmentManager ().beginTransaction ().replace (R.id.fragment_container, ocFragment).commit ();

                }
            });

                System.out.println ("Binding images...");
                Toast.makeText (context, "Binding images " + oc.getCharacterName (), Toast.LENGTH_SHORT).show ();

        }
    public interface OnCharacterItemClickListener{
        void onCharacterClick(int position, CharacterInformation oc);
    }
        @Override
        public int getItemCount() {
            return mDataset.size();
        }

        public void deleteOC(String characterid,String userid, final int position) {
          //  firebaseAuth = FirebaseAuth.getInstance();
            String currentUserID = userid;
            FirebaseDatabase.getInstance().getReference("User Account").child(currentUserID).child("character").child(characterid).removeValue()
                    .addOnCompleteListener(new OnCompleteListener<Void> () {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                //remove item from list alos and refresh recyclerview
                             //   mDataset.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, mDataset.size());

                                Log.d("Delete Ad", "Character has been deleted");
                                Toast.makeText(context,
                                        "Character deleted",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("Delete Ad", "Character couldn't be deleted");
                                Toast.makeText(context,
                                        "Character could not be deleted",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

         }


    }