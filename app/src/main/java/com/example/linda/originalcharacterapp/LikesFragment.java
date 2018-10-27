package com.example.linda.originalcharacterapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.linda.originalcharacterapp.utils.RecycleViewAdapter;


public class LikesFragment extends Fragment {
    // references to the temporary images.
    private Integer[] testImages = new Integer[]{
            R.mipmap.crow, R.mipmap.hengfather,
            R.mipmap.futurecity, R.mipmap.rosalina,
            R.mipmap.yin, R.mipmap.zero,
            R.mipmap.crow, R.mipmap.hengfather,
            R.mipmap.blacksnow, R.mipmap.rosalina,
            R.mipmap.yin, R.mipmap.zero,

    };

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ImageView imageSelection;

    public static HomeFragment newInstance() {
        HomeFragment f = new HomeFragment ();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);

        mRecyclerView = (RecyclerView) getView().findViewById (R.id.imagegallery);
        // use this setting to improve performance if you know that changes in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager (this.getActivity());
        // mRecyclerView.setLayoutManager (mLayoutManager);
        mRecyclerView.setLayoutManager(new GridLayoutManager (this.getActivity(),2));

        // specify an adapter (see also next example)
        mAdapter = new RecycleViewAdapter (testImages);
        mRecyclerView.setAdapter(mAdapter);

    }
}
