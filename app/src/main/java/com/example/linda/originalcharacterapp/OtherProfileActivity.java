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

public class OtherProfileActivity extends Fragment {

    private TextView otherUser;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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
