package com.example.linda.originalcharacterapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.linda.originalcharacterapp.model.UserInformation;

public class OtherProfileActivity extends Fragment {

    private TextView otherUser;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private static final String USER_INFO = "USERINFO";
    private RecyclerView.LayoutManager mLayoutManager;

    public static final OtherProfileActivity newInstance(UserInformation user) {
        OtherProfileActivity fragment = new OtherProfileActivity ();
        System.out.println("trasferring user SUCCESS " + user.getUser_id () );
        Bundle args = new Bundle ();
        args.putParcelable (USER_INFO ,user);
        fragment.setArguments (args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);
        otherUser = (TextView) getView().findViewById(R.id.otheruser_title);
        mRecyclerView = (RecyclerView)getView().findViewById (R.id.imagegallery);
    //    mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager (this.getActivity());
      //  mRecyclerView.setLayoutManager(mLayoutManager);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_otherprofile, container, false);
    }
}
