package com.example.linda.originalcharacterapp;

import android.net.Uri;
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
import android.widget.TextView;

import com.example.linda.originalcharacterapp.utils.RecycleViewAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment {
    private Integer[] testImages = new Integer[]{
            R.drawable.search, R.mipmap.ghostfinder101avatar,
            R.mipmap.ghostfinderchibis, R.drawable.setting,
            R.drawable.setting, R.drawable.setting,
            R.drawable.setting, R.drawable.search,
            R.drawable.logout, R.drawable.logout,
            R.drawable.setting, R.drawable.logout,
            R.drawable.logout, R.drawable.search,

    };

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;

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

        mLayoutManager = new LinearLayoutManager (this.getActivity());
        mRecyclerView.setLayoutManager(new GridLayoutManager (this.getActivity(),2));

           // specify an adapter (see also next example)
        mAdapter = new RecycleViewAdapter (testImages); //where the image is inserted
        mRecyclerView.setAdapter(mAdapter);

}

    private void viewGallery() {

    }
    /*private ArrayList<CharacterInformation> prepareData() {
        ArrayList<CharacterInformation> theimage = new ArrayList<>();
        for(int i = 0; i < testImages.length; i++){
            CharacterInformation createList = new CharacterInformation ();
            createList.setCharacterImage (testImages[i]);
            theimage.add(createList);
        }
        return theimage;
    }*/

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static class viewGalleryHolder extends RecyclerView.ViewHolder {
        View itemView;
        public viewGalleryHolder(View itemView) {
            super(itemView);

            itemView = itemView;
        }

        public void setTitle(String title) {
            TextView postTitle = (TextView) itemView.findViewById (R.id.characterName);
            postTitle.setText(title);
        }

        public void setCharacterInfo() {

        }

    }


}


