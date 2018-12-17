package com.example.linda.originalcharacterapp.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linda.originalcharacterapp.EditCharacter;
import com.example.linda.originalcharacterapp.R;
import com.example.linda.originalcharacterapp.model.CharacterInformation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>{
    final private List<CharacterInformation> mDataset;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference characterRef;
    private FirebaseUser firebaseUser;
    private FirebaseStorage firebaseStorage;
    private FirebaseAuth firebaseAuth;
    private Context context;
    private ShareActionProvider shareActionProvider;
    FirebaseStorage storage = FirebaseStorage.getInstance();
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
        public RecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

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
               Context viewImage = holder.image.getContext();
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
                    EditCharacter ocFragment = EditCharacter.newInstance(oc);
                    System.out.println("Character instantiated " + oc.getCharacter_id ());
                    activity.getSupportFragmentManager ().beginTransaction ().replace (R.id.fragment_container, ocFragment).commit ();

                }
            });

            holder.shareButton.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {

                    shareMessage(v, oc.getPhoto_id (), oc);

                }
            });
                System.out.println ("Binding images..." + oc.getCharacter_id ());

        }

    public interface OnCharacterItemClickListener{
        void onCharacterClick(int position, CharacterInformation oc);
    }
        @Override
        public int getItemCount() {
            return mDataset.size();
        }

        public void deleteOC(final String characterid, final String userid, final int position) {
            final String currentUserID = userid;
            StorageReference storageRef = storage.getReference();
            StorageReference toDeleteRef = storageRef.child("characterimage").child(currentUserID ).child(characterid + ".png");

            toDeleteRef.delete().addOnSuccessListener(new OnSuccessListener<Void> () {
                @Override
                public void onSuccess(Void aVoid) {
                    FirebaseDatabase.getInstance().getReference("User Account")
                            .child(currentUserID).child("character").
                            child(characterid).removeValue().addOnCompleteListener(new OnCompleteListener<Void> () {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                //remove item from list alos and refresh recyclerview
                                //   mDataset.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, mDataset.size());

                                Log.d("Delete Ad", "Character deleted");
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
                    Log.d("Delete image success", "Character image is deleted");
                }
            }).addOnFailureListener(new OnFailureListener () {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.d("Delete unsuccess", "Character image couldn't be deleted");
                }
            });

         }

    public boolean onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.share_menu, menu);
        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.share);
        // Fetch and store ShareActionProvider
        shareActionProvider = (ShareActionProvider) item.getActionProvider();
        return true;
    }


    public void shareMessage(View view, String photo_id, CharacterInformation oc) {
       // Uri uri = Uri.fromFile(imagePath);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.setType("image/*");
        String shareBodyText = "Hello! I created a character from the CSE248 PROJECT: OC - CENTRAL APP. \n" +oc.getCharacterName() +
                "\n" + oc.getCharacterAge() +"\n" + oc.getCharacterSpecies() + "\n" + oc.getCharacterPersonality() +
                "\n" + oc.getCharacterFamily() + "\n" + oc.getCharacterPowers() + "\n" + oc.getCharacterBio();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject/Title");
        sharingIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(photo_id));
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    }