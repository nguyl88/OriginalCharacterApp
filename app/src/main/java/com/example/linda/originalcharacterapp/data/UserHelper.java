package com.example.linda.originalcharacterapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
Database helper class
 */
public class UserHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CharacterContract.TABLE_NAME + " (" +
                    CharacterContract.COLUMN_USERNAME + " INTEGER PRIMARY KEY," +
                    CharacterContract.COLUMN_NAME + " TEXT," +
                    CharacterContract.COLUMN_AGE + " TEXT)"; //still have more to go


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CharacterContract.TABLE_NAME;
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String USERDB_NAME = "userdatabase.db";

    public UserHelper(Context context) {
        super(context, USERDB_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
