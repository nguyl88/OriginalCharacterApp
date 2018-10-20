package com.example.linda.originalcharacterapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.linda.originalcharacterapp.model.CharacterInformation;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DisplayCharacter.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DisplayCharacter#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayCharacter extends Fragment {

    private Context context;
    private TextView cName, cAge, cSpecies,cPersonality,cFamily,cBiography,cPowers;
   // private EditText editName, editAge, editSpecies,editPersonality,editFamily,editBio,editPowers;
    private ImageView currentImage;
    private CharacterInformation oc;
    private String nameValue, ageValue, speciesValue, familyValue, personalityValue, powerValue, bioValue;
    private Switch editSwitch;

    private OnFragmentInteractionListener mListener;

    public DisplayCharacter() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static DisplayCharacter newInstance(ImageView image) {
        DisplayCharacter fragment = new DisplayCharacter ();
        Bundle args = new Bundle ();
      //  args.putString (ARG_PARAM1, param1);
    //    args.putString (ARG_PARAM2, param2);
        fragment.setArguments (args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_display_character, container, false);

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);

        editSwitch = (Switch) getView().findViewById (R.id.editMode);
        Boolean isChecked = editSwitch.isChecked(); //true

        editSwitch.setOnCheckedChangeListener (new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editCharacter();
            }
        });

        Button deleteCharacterButton = (Button) getView().findViewById (R.id.delete_character_button);
        currentImage = (ImageView) getView().findViewById (R.id.currentDisplayCharacter);

        cName = getView().findViewById (R.id.characterName);
        cName.setVisibility(View.VISIBLE);
       // nameValue = cName.getText().toString();

        cAge = getView().findViewById (R.id.characterAge);
        cAge.setVisibility(View.VISIBLE);
       // ageValue = cAge.getText().toString();

        cSpecies = getView().findViewById (R.id.characterSpecies);
        cSpecies.setVisibility(View.VISIBLE);
        //speciesValue = cSpecies.getText().toString();

        cPersonality = getView().findViewById (R.id.characterPersonality);
        cPersonality.setVisibility(View.VISIBLE);
        //personalityValue = cPersonality.getText().toString();

        cFamily =  getView().findViewById (R.id.characterFamily);
        cFamily.setVisibility(View.VISIBLE);
      //  familyValue = cFamily.getText().toString();

        cPowers =  getView().findViewById (R.id.characterPowers);
        cPowers.setVisibility(View.VISIBLE);
      //  powerValue = cPowers.getText().toString();

        cBiography = getView().findViewById (R.id.characterBios);
        cBiography.setVisibility(View.VISIBLE);
       // bioValue = cBiography.getText().toString();

        deleteCharacterButton.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                deleteCharacter();
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    public void deleteCharacter() {

    }

    public void editCharacter() {
        if(editSwitch.isChecked()) {
            //User changes character's info and save and overwrite the character's info in the database.
            cName.setVisibility(View.GONE);
            cName = (EditText) getView().findViewById(R.id.editCharName);
            cName.setVisibility(View.VISIBLE);
            cName.setText(cName.getText());

            cAge.setVisibility(View.GONE);
            cAge = (EditText) getView().findViewById(R.id.editAge);
            cAge.setVisibility(View.VISIBLE);
            cAge.setText(cAge.getText());

            cSpecies.setVisibility(View.GONE);
            cSpecies = (EditText) getView().findViewById(R.id.editSpecies);
            cSpecies.setVisibility(View.VISIBLE);
            cSpecies.setText(cSpecies.getText());

            cFamily.setVisibility(View.GONE);
            cFamily = (EditText) getView().findViewById(R.id.editFamily);
            cFamily.setVisibility(View.VISIBLE);
            cFamily.setText(cFamily.getText());

            cPersonality.setVisibility(View.GONE);
            cPersonality = (EditText) getView().findViewById(R.id.editPersonality);
            cPersonality.setVisibility(View.VISIBLE);
            cPersonality.setText(cPersonality.getText());

            cPowers.setVisibility(View.GONE);
            cPowers = (EditText) getView().findViewById(R.id.editPowers);
            cPowers.setVisibility(View.VISIBLE);
            cPowers.setText(cPowers.getText());

            cBiography.setVisibility(View.GONE);
            cBiography = (EditText) getView().findViewById(R.id.editBios);
            cBiography.setVisibility(View.VISIBLE);
            cBiography.setText(cBiography.getText());

            //            //then update the text into the database

        } else if (!editSwitch.isChecked()) {
            //It does not need to show the updated text in TextView
            // But it will be updated once the text is in the data and display the updated text
            cName.setVisibility(View.INVISIBLE);
            cName = (TextView) getView().findViewById(R.id.characterName);
            cName.setVisibility(View.VISIBLE);

            cAge.setVisibility(View.GONE);
            cAge.setText(cAge.getText());
            cAge = (TextView) getView().findViewById(R.id.characterAge);
            cAge.setVisibility(View.VISIBLE);

            cSpecies.setVisibility(View.GONE);
            cSpecies.setText(cSpecies.getText());
            cSpecies = (TextView) getView().findViewById(R.id.characterSpecies);
            cSpecies.setVisibility(View.VISIBLE);

            cFamily.setVisibility(View.GONE);
            cFamily = (TextView) getView().findViewById(R.id.characterFamily);
            cFamily.setVisibility(View.VISIBLE);

            cPersonality.setVisibility(View.GONE);
            cPersonality = (TextView) getView().findViewById(R.id.characterPersonality);
            cPersonality.setVisibility(View.VISIBLE);

            cPowers.setVisibility(View.GONE);
            cPowers = (TextView) getView().findViewById(R.id.characterPowers);
            cPowers.setVisibility(View.VISIBLE);

            cBiography.setVisibility(View.GONE);
            cBiography = (TextView) getView().findViewById(R.id.characterBios);
            cBiography.setVisibility(View.VISIBLE);
        }
    }
}
