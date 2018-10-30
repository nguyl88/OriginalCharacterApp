package com.example.linda.originalcharacterapp.utils;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.linda.originalcharacterapp.DisplayCharacter;
import com.example.linda.originalcharacterapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
        private Integer[] mDataset;
    //    private ArrayList<Integer> mDataset;
        private Context context;
     //  private ArrayList<CharacterInformation>;
     private FirebaseFirestore firebaseFirestore;
     private FirebaseAuth firebaseAuth;

        public static class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            private ImageView image;
            private MyViewHolder(View view) {
                super(view);
               image = (ImageView) view.findViewById(R.id.recycleImage);
            }
        }

        public RecycleViewAdapter(Integer[] myDataset) {
            mDataset = myDataset;
        }

        @Override
        public RecycleViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row, parent, false);
            MyViewHolder vh = new MyViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            holder.image.setScaleType (ImageView.ScaleType.CENTER_CROP);
            holder.image.setImageResource (mDataset[position]);

            holder.image.setOnClickListener (new View.OnClickListener () {


                @Override
                public void onClick(View v) {
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    DisplayCharacter ocFragment = new DisplayCharacter();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ocFragment).addToBackStack(null).commit();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDataset.length;
        }

    public void setCharacterImage(String downloadUri, String thumbUri){
    }
    }