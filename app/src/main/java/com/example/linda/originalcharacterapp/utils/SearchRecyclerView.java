package com.example.linda.originalcharacterapp.utils;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linda.originalcharacterapp.OtherProfileActivity;
import com.example.linda.originalcharacterapp.R;
import com.example.linda.originalcharacterapp.model.UserInformation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SearchRecyclerView extends RecyclerView.Adapter<SearchRecyclerView.ViewHolder> {
    private List<UserInformation> mDataset;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Context context;
    private UserInformation user;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView userimage;
        private TextView username;
        private TextView email;
        private Context context;

        private ViewHolder(View view) {
            super(view);
            userimage = (ImageView) view.findViewById(R.id.userimage);
            username = (TextView) view.findViewById(R.id.username_view);
            email = (TextView) view.findViewById(R.id.useremail_view);

        }

    }

    public SearchRecyclerView(List<UserInformation> myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    @Override
    public SearchRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchuser, parent, false);
        SearchRecyclerView.ViewHolder vh = new SearchRecyclerView.ViewHolder (view);
        return vh;
    }

    @Override
    public void onBindViewHolder(SearchRecyclerView.ViewHolder holder, int position) {

        UserInformation user = mDataset.get(position);
        holder.username.setText(user.getUsername());
        holder.email.setText(user.getEmail());

      //  Picasso.get ().load (user.getPhoto_id ()).placeholder (R.mipmap.ic_launcher).into (holder.image);

        //When user presses the other username, it takes to the other user's profile which will contain the user's gallery
        holder.username.setOnClickListener (new View.OnClickListener () {

            @Override
            public void onClick(View v) {
               AppCompatActivity activity = (AppCompatActivity) v.getContext ();
               OtherProfileActivity otherUser = new OtherProfileActivity ();
               // Intent intent = new Intent (context, OtherProfileActivity.class);
              //  context.startActivity(intent);
                activity.getSupportFragmentManager ().beginTransaction ().replace (R.id.fragment_container, otherUser).addToBackStack (null).commit ();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }



}
