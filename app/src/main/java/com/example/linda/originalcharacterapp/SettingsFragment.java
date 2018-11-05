package com.example.linda.originalcharacterapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsFragment extends Fragment {
    private Button saveButton, logoutButton, deleteAccountButton;
    private EditText txtUsername, txtEmail, txtPassword;
    private MainUserActivity listener;
    private static final String TAG = "SettingsFragment";
    //need database to change the user's data by email id and delete account

    private FirebaseDatabase database;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_settings, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseAuth =  FirebaseAuth.getInstance();

        saveButton = (Button) getView ().findViewById (R.id.save_setting);
        deleteAccountButton = (Button) getView ().findViewById (R.id.deleteAccountbutton);

        logoutButton = (Button) getView ().findViewById (R.id.logoutbutton);

        txtUsername = (EditText) getView ().findViewById (R.id.txt_username);
        txtEmail = (EditText) getView ().findViewById (R.id.txt_email);
        txtPassword = (EditText) getView ().findViewById (R.id.txt_password);

        saveButton.setOnClickListener (new View.OnClickListener() {
            String newUsername, newEmail, newPassword;
            @Override
            public void onClick(View view) {
                if(firebaseAuth.getCurrentUser() != null) {
                    String name = firebaseUser.getDisplayName();
                    String email = firebaseUser.getEmail();

                    // Check if user's email is verified
                    boolean emailVerified = firebaseUser.isEmailVerified();
                    String uid = firebaseUser.getUid();//Check unique id

                    if(txtEmail.getText().toString() != null) {
                        newEmail = txtEmail.getText().toString();
                        firebaseUser.updateEmail(newEmail)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d(TAG, "User email address updated.");
                                        }
                                    }
                                });
                    }

                    else if (txtPassword.getText().toString() != null) {
                        newPassword = txtPassword.getText().toString();
                        firebaseUser.updatePassword(newPassword)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d(TAG, "User password updated.");
                                        }
                                    }
                                });
                    }
                    else {
                        Toast.makeText(getActivity(), "The field are empty", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        deleteAccountButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "User account deleted.");
                                    Intent intent = new Intent (getActivity (), Login.class); //returns to login page
                                    startActivity (intent);
                                }
                            }
                        });
            }
        });

        logoutButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if (firebaseAuth.getCurrentUser () != null)
                    firebaseAuth.signOut ();
                Intent intent = new Intent (getActivity (), Login.class); //returns to login page
                startActivity (intent);
            }
        });

    }

    // This method is called when the fragment is no longer connected to the Activity
    // Any references saved in onAttach should be nulled out here to prevent memory leaks.
    @Override
    public void onDetach() {
        super.onDetach ();
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
