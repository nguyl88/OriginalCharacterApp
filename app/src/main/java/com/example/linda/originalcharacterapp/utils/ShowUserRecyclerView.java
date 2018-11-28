package com.example.linda.originalcharacterapp.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linda.originalcharacterapp.R;
import com.example.linda.originalcharacterapp.model.CharacterInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShowUserRecyclerView extends RecyclerView.Adapter<ShowUserRecyclerView.ShowViewHolder>{

    final private List<CharacterInformation> mDataset;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference characterRef;
    private FirebaseUser firebaseUser;
    private FirebaseStorage firebaseStorage;
    private FirebaseAuth firebaseAuth;
    private Context context;

    public static class ShowViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView cName, cAge, cSpecies, cPersonality, cPowers, cFamily, cBio, currentUser;
        private TextView titleName, titleAge, titleSpecies,titlePersonality, titlePowers, titleFamily, titleBio;
        private ImageButton likeButton;
        private View divideLines;
        private Context context;
        private DatabaseReference characterRef;
        private FirebaseUser firebaseUser;


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
            likeButton= view.findViewById(R.id.likeButton);

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
        System.out.println("Adding other user character" + oc.getCharacterName());
        Picasso.get ().load (oc.getPhoto_id ()).placeholder (R.mipmap.ic_launcher).into (holder.image);
        holder.cName.setText (oc.getCharacterName ());
        holder.cAge.setText (oc.getCharacterAge ());
        holder.cSpecies.setText (oc.getCharacterSpecies ());
        holder.cPersonality.setText (oc.getCharacterPersonality ());
        holder.cPowers.setText (oc.getCharacterPowers ());
        holder.cFamily.setText (oc.getCharacterFamily ());
        holder.cBio.setText (oc.getCharacterBio ());

        holder.likeButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                System.out.println("Like button is pressed");

            }
        });

        System.out.println ("Binding images..." + oc.getCharacter_id ());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
