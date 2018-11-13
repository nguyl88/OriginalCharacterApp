package com.example.linda.originalcharacterapp.utils;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linda.originalcharacterapp.DisplayCharacter;
import com.example.linda.originalcharacterapp.R;
import com.example.linda.originalcharacterapp.model.CharacterInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
        private List<CharacterInformation> mDataset;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private FirebaseStorage firebaseStorage;
    private FirebaseAuth firebaseAuth;
    private Context context;


        public static class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView image;
            private TextView cName, cAge, cSpecies, cPersonality, cPowers, cFamily, cBio, currentUser;
            private TextView titleName, titleAge, titleSpecies,titlePersonality, titlePowers, titleFamily, titleBio;
            private ImageButton deleteButton, editButton, shareButton;
            private View divideLines;
            private Context context;

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

            }

        }

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
        public void onBindViewHolder(ViewHolder holder, int position) {

            CharacterInformation oc = mDataset.get(position);

                Picasso.get ().load (oc.getPhoto_id ()).placeholder (R.mipmap.ic_launcher).into (holder.image);
                holder.cName.setText (oc.getCharacterName ());
                holder.cAge.setText (oc.getCharacterAge ());
                holder.cSpecies.setText (oc.getCharacterSpecies ());
                holder.cPersonality.setText (oc.getCharacterPersonality ());
                holder.cPowers.setText (oc.getCharacterPowers ());
                holder.cFamily.setText (oc.getCharacterFamily ());
                holder.cBio.setText (oc.getCharacterBio ());


                System.out.println ("Binding images...");
                Toast.makeText (context, "Binding images " + oc.getCharacterName (), Toast.LENGTH_SHORT).show ();
                holder.image.setOnClickListener (new View.OnClickListener () {

                    @Override
                    public void onClick(View v) {
                        AppCompatActivity activity = (AppCompatActivity) v.getContext ();
                        DisplayCharacter ocFragment = new DisplayCharacter ();
                        activity.getSupportFragmentManager ().beginTransaction ().replace (R.id.fragment_container, ocFragment).addToBackStack (null).commit ();
                    }
                });

        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }

    }