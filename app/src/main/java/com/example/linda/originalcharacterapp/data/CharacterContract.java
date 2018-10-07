package com.example.linda.originalcharacterapp.data;

import android.content.res.Resources;
import android.provider.BaseColumns;

public final class CharacterContract implements BaseColumns {

    public static final String TABLE_NAME = "usercharacter";
    public static final String _ID = "userid";
    public static final String COLUMN_CHARACTER_POST = "postid";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age"; //Gender will not be boolean for reasons.
    public static final String COLUMN_SPECIES = "species";
    public static final String COLUMN_PERSONALITY = "personality";
    public static final String COLUMN_FAMILY= "family";
    public static final String COLUMN_POWERS= "powers";
    public static final String COLUMN_BIOGRAPHY= "bio";


    /*
    Need to figure out how to add a file imageview to database
     */

}
