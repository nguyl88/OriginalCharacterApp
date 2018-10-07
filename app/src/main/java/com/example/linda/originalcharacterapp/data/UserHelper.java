package com.example.linda.originalcharacterapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.linda.originalcharacterapp.model.UserInformation;

import static android.content.ContentValues.TAG;

//One database helper

public class UserHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String USERDB_NAME = "userdatabase.db"; //main database

    public UserHelper(Context context) {
        super(context, USERDB_NAME, null, DATABASE_VERSION);
    }

    //User table
    private static final String SQL_CREATE_USERENTRIES =
            "CREATE TABLE " + UserContract.TABLE_NAME + " (" +
                    UserContract._ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                    UserContract.COLUMN_USERNAME + " TEXT," +
                    UserContract.COLUMN_EMAIL + " TEXT," +
                    UserContract.COLUMN_PASSWORD + " TEXT)"; //still have more to go

    //Character table
    private static final String SQL_CREATE_CHARACTER_ENTRIES =
            "CREATE TABLE " + CharacterContract.TABLE_NAME + "(" +
                    CharacterContract._ID + "INTEGER PRIMARY KEY NOT NULL," +
                    CharacterContract.COLUMN_CHARACTER_POST + "INTEGER," +
                    CharacterContract.COLUMN_IMAGE + " BLOB, "+
                    CharacterContract.COLUMN_NAME + " TEXT," +
                    CharacterContract.COLUMN_AGE + " TEXT," +
                    CharacterContract.COLUMN_SPECIES + " TEXT," +
                    CharacterContract.COLUMN_PERSONALITY + " TEXT" +
                    CharacterContract.COLUMN_FAMILY + " TEXT," +
                    CharacterContract.COLUMN_POWERS + " TEXT, " +
                    CharacterContract.COLUMN_BIOGRAPHY + " TEXT)";


    private static final String SQL_DELETE_USERENTRIES =
            "DROP TABLE IF EXISTS " + UserContract.TABLE_NAME;

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERENTRIES);
        db.execSQL(SQL_CREATE_CHARACTER_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_USERENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean insertUserData(UserInformation newUser) {
        SQLiteDatabase db = this.getWritableDatabase ();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UserContract.COLUMN_EMAIL, newUser.getEmail());
        values.put(UserContract.COLUMN_PASSWORD, newUser.getPassword());
        values.put(UserContract.COLUMN_USERNAME, newUser.getUsername());

        Log.d(TAG,"Adding new user" + newUser.getUsername().toString() + " to userdatabase");

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UserContract.TABLE_NAME, null, values);
        if (newRowId == -1) { // if the database is not properly inserted.
            return false;
        } else {
            return true;
        }
    }


  /*  public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase ();
        String query = "SELECT * FROM userdatabase";
        Cursor userdata = db.rawQuery(query,null);
        return userdata;
    }*/

}
