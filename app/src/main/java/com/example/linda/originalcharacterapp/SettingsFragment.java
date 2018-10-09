package com.example.linda.originalcharacterapp;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SettingsFragment extends Fragment {
    Button saveButton, logoutButton,deleteAccountButton;
    EditText txtUsername, txtEmail,txtPassword;
    MainUserActivity listener;
    //need database to change the user's data by email id and delete account
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);


        saveButton = (Button)getView().findViewById(R.id.save_setting);
        deleteAccountButton = (Button) getView().findViewById (R.id.deleteAccountbutton);
        logoutButton = (Button)getView().findViewById(R.id.logoutbutton);

        txtUsername = (EditText)getView().findViewById(R.id.txt_username);
        txtEmail = (EditText)getView().findViewById(R.id.txt_email);
        txtPassword = (EditText)getView().findViewById(R.id.txt_password);

    }
    // This method is called when the fragment is no longer connected to the Activity
    // Any references saved in onAttach should be nulled out here to prevent memory leaks.
    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach (context);
        if (context instanceof Activity) {
            this.listener = (MainUserActivity) context;
        }
    }

}
