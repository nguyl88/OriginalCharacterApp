package com.example.linda.originalcharacterapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    // TODO: Rename and change types and number of parameters
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

        //       Button editCharacterButton = (Button) findViewById (R.id.edit_character_button);
        editSwitch = (Switch) getView().findViewById (R.id.editMode);
        Boolean isChecked = editSwitch.isChecked(); //true

        Button deleteCharacterButton = (Button) getView().findViewById (R.id.delete_character_button);
        currentImage = (ImageView) getView().findViewById (R.id.currentDisplayCharacter);

        cName = (TextView) getView().findViewById (R.id.characterName);
        nameValue = cName.getText().toString();

        cAge = (TextView) getView().findViewById (R.id.characterAge);
        ageValue = cAge.getText().toString();

        cSpecies = (TextView) getView().findViewById (R.id.characterSpecies);
        speciesValue = cSpecies.getText().toString();

        cPersonality = (TextView) getView().findViewById (R.id.characterPersonality);
        personalityValue = cPersonality.getText().toString();

        cFamily = (TextView) getView().findViewById (R.id.characterFamily);
        familyValue = cFamily.getText().toString();

        cPowers = (TextView) getView().findViewById (R.id.characterPowers);
        powerValue = cPowers.getText().toString();

        cBiography = (TextView) getView().findViewById (R.id.characterBios);
        bioValue = cBiography.getText().toString();

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


        } else {
            //user is not in edit mode anymore.
        }
    }
}
