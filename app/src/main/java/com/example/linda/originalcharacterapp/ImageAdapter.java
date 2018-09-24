package com.example.linda.originalcharacterapp;

import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


public class ImageAdapter extends BaseAdapter{
    private Context context;
//    private String[] thumbNames;
    // references to our images
    private Integer[] mThumbIds;
  //  private LayoutInflater inflater;


    public ImageAdapter(Context c, Integer[] mThumbIds) {
        this.context = c;
        this.mThumbIds = mThumbIds;
       // this.thumbNames = thumbNames;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

     /*   View gridView = convertView;
        if(convertView == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = inflater.inflate(R.layout.activity_home,null);
        }
        ImageView images = (ImageView) gridView.findViewById(R.id.mThumbIds);
        TextView names = (TextView) gridView.findViewById(R.id.thumbNames);

        images.setImageResource(mThumbIds[position]);
        names.setText(thumbNames[position]);

        return gridView;*/
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }


}
