package com.example.linda.originalcharacterapp.model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linda.originalcharacterapp.CharacterView;
import com.example.linda.originalcharacterapp.R;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
        private Integer[] mDataset;
        private Context context;
     //   private ArrayList<CharacterInformation>;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            private ImageView image;
            private MyViewHolder(View view) {
                super(view);
               image = (ImageView) view.findViewById(R.id.recycleImage);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        //In this constructor, we will change it to image
        public RecycleViewAdapter(Integer[] myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public RecycleViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row, parent, false);

            MyViewHolder vh = new MyViewHolder(view);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.image.setScaleType (ImageView.ScaleType.CENTER_CROP);
            holder.image.setImageResource (mDataset[position]);

            holder.image.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CharacterView.class);;
                    context.startActivity(intent);
                }
            });

        }
        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }