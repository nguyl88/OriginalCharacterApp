package com.example.linda.originalcharacterapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.linda.originalcharacterapp.model.CharacterInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class DisplayCharacter extends Fragment {

    private Context context;
    private EditText cName, cAge, cSpecies,cPersonality,cFamily,cBiography,cPowers;
    private ImageView currentImage;
    private CharacterInformation oc;
    private ImageButton editButton;
    private String displayTag = "DISPLAY_TAG";
    private static final String CHARACTER_INFO = "CharacterInformation";
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseUser firebaseUser;

    private OnFragmentInteractionListener mListener;
    public DisplayCharacter() {

    }
    public static final DisplayCharacter newInstance(CharacterInformation character) {
        DisplayCharacter fragment = new DisplayCharacter ();
        System.out.println("trasferring character SUCCESS " + character.getCharacter_id () );
        Bundle args = new Bundle ();
        args.putParcelable (CHARACTER_INFO, character);
        fragment.setArguments (args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Bundle bundle = this.getArguments();
        if (getArguments() != null){
            oc = getArguments ().getParcelable (CHARACTER_INFO);
            System.out.println("The bundle is not null and character name: " + oc.getCharacterName());
        }

        if (savedInstanceState != null) {
            System.out.println("saved instance state isn't a null");
        }
        else {
            System.out.println ("Display Character has an exception");
        }
        return inflater.inflate (R.layout.fragment_display_character, container, false);

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);

            context = getActivity ();
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            database = FirebaseDatabase.getInstance ();
            reference = FirebaseDatabase.getInstance ().getReference ("User Account");

            currentImage = (ImageView) getView ().findViewById (R.id.currentDisplayCharacter);
            Picasso.get ().load (oc.getPhoto_id ()).placeholder (R.mipmap.ic_launcher).into (currentImage);

            cName = getView ().findViewById (R.id.editCharName);
            cName.setText (oc.getCharacterName ());

            System.out.println(oc.getCharacterName () + " does exist");
            cName.setVisibility (View.VISIBLE);

            cAge = getView ().findViewById (R.id.editAge);
            cAge.setText(oc.getCharacterAge ());
            cAge.setVisibility (View.VISIBLE);

            cSpecies = getView ().findViewById (R.id.editSpecies);
            cSpecies.setText(oc.getCharacterSpecies());
            cSpecies.setVisibility (View.VISIBLE);

            cPersonality = getView ().findViewById (R.id.editPersonality);
            cPersonality.setText(oc.getCharacterPersonality());
            cPersonality.setVisibility (View.VISIBLE);

            cFamily = getView ().findViewById (R.id.editFamily);
            cFamily.setText(oc.getCharacterFamily());
            cFamily.setVisibility (View.VISIBLE);


            cPowers = getView ().findViewById (R.id.editPowers);
            cPowers.setText(oc.getCharacterPowers());
            cPowers.setVisibility (View.VISIBLE);

            cBiography = getView ().findViewById (R.id.editBios);
            cBiography.setText(oc.getCharacterBio());
            cBiography.setVisibility (View.VISIBLE);

            editButton = getView().findViewById(R.id.editButton);
            editButton.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    editOC(editButton, v, oc.getCharacter_id (), oc.getUser_id ());
                }
            });

    }
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction (uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach (context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException (context.toString ()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
    public interface OnCharacterSelectedListener {
            public void onCharacterSelected(int position);
    }

  public void editOC(final ImageButton editButton, View view, String characterid, String userid) {
        String currentUserId = userid;
        String nameValue, ageValue, speciesValue, familyValue, personalityValue, powerValue, bioValue;
        nameValue = cName.getText().toString();
        ageValue = cAge.getText().toString();
        speciesValue = cSpecies.getText().toString();
        familyValue = cFamily.getText().toString();
        personalityValue = cPersonality.getText().toString();
        powerValue = cPowers.getText().toString();
        bioValue = cBiography.getText().toString();

                          //Set the view
                          cName.setText(nameValue);
                          cAge.setText(ageValue);
                          cSpecies.setText(speciesValue);
                          cFamily.setText(familyValue);
                          cPersonality.setText(personalityValue);
                          cPowers.setText(powerValue);
                          cBiography.setText(bioValue);
                          //Update database reference

      reference.child(currentUserId).child("character").child(characterid).child("characterName").setValue(nameValue);
      reference.child(currentUserId).child("character").child(characterid).child("characterAge").setValue(ageValue);
      reference.child(currentUserId).child("character").child(characterid).child("characterSpecies").setValue(speciesValue);
      reference.child(currentUserId).child("character").child(characterid).child("characterPersonality").setValue(personalityValue);
      reference.child(currentUserId).child("character").child(characterid).child("characterPowers").setValue(powerValue);
      reference.child(currentUserId).child("character").child(characterid).child("characterFamily").setValue(familyValue);
      reference.child(currentUserId).child("character").child(characterid).child("characterBio").setValue(bioValue);

      System.out.println("Character ID: " + characterid + " is saved ");
      Toast.makeText(context, "Character updated", Toast.LENGTH_SHORT).show();

      }

}
