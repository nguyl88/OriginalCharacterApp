package com.example.linda.originalcharacterapp;

//import android.app.ListFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.linda.originalcharacterapp.model.UserInformation;
import com.example.linda.originalcharacterapp.utils.SearchRecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends ListFragment implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {

    List<UserInformation> allUsers;
 //  private ArrayAdapter<String> mAdapter; //replace with recycler view adapter to pull all user ocs from the database
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;

    private DatabaseReference allReference;
    private FirebaseDatabase firebaseDatabase;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        mContext = getActivity();
        mRecyclerView = (RecyclerView) getView().findViewById (R.id.searchuserprofile);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager (this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setNestedScrollingEnabled(true);
        setHasOptionsMenu(true);
        searchUsers();

    }

    @Override
    public void onListItemClick(ListView listView, View v, int position, long id) {
        String item = (String) listView.getAdapter().getItem(position);
        if (getActivity() instanceof OnItem1SelectedListener) {
            ((OnItem1SelectedListener) getActivity()).OnItem1SelectedListener(item);
        }
        getFragmentManager().popBackStack();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_search, container, false);
        ListView listView = (ListView) layout.findViewById(android.R.id.list);
        TextView emptyTextView = (TextView) layout.findViewById(android.R.id.empty);
        listView.setEmptyView(emptyTextView);
        return layout;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search");

        super.onCreateOptionsMenu(menu, inflater);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText == null || newText.trim().isEmpty()) {
            resetSearch();
            return false;
        }
        List<UserInformation> filteredValues = new ArrayList<UserInformation>(allUsers);
        for (UserInformation user :allUsers) {
            String sUsername = user.getUsername();
            if (!user.getUsername().toLowerCase().contains(newText.toLowerCase())) {
                filteredValues.remove(user);
            }
        }
        mAdapter = new SearchRecyclerView (filteredValues, mContext);
        System.out.println("Filtering adapter...");
        mRecyclerView.setAdapter(mAdapter);

     //   mAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, filteredValues);
     //   setListAdapter(mAdapter);

        return false;
    }

    public void resetSearch() {
        mAdapter = new SearchRecyclerView (allUsers,mContext);
        System.out.println("REsetting adapter...");
        mRecyclerView.setAdapter(mAdapter);
        //setListAdapter(mAdapter);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return true;
    }

    public interface OnItem1SelectedListener {
        void OnItem1SelectedListener(String item);
    }

    private void searchUsers() {
        allReference = FirebaseDatabase.getInstance().getReference("User Account");
        allUsers = new ArrayList<> ();

        allReference.addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Loop 1: Goes through all the users
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren ()) {
                        String userKey = userSnapshot.getKey ();
                        UserInformation data = userSnapshot.child("users").getValue (UserInformation.class);
                        System.out.println("Data is added to users " + data.getUsername());
                        System.out.println("User key information is : " + userKey);
                        allUsers.add (data);
                    }

                    allUsers.add(new UserInformation("ertry","Linda","Linda2018@gmail.com", "password"));
                mAdapter = new SearchRecyclerView (allUsers,mContext);
                    System.out.println("Setting adapter...");
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged ();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //setListAdapter (mAdapter);
     }
    }