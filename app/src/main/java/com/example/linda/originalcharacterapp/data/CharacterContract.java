package com.example.linda.originalcharacterapp.data;

import android.content.res.Resources;
import android.provider.BaseColumns;

public final class CharacterContract implements BaseColumns {

    private CharacterContract() {};
    public final static String _ID = BaseColumns._ID;
    public static final String COLUMN_CHARACTERPOST = "Integer";
    public static final String TABLE_NAME = "character";
    public static final String COLUMN_USERNAME = "username";
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
