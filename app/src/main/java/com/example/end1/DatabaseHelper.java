package com.example.end1;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {
     // create Name for the Database
    public static final String DATABASE_NAME = "users.db";
     // Create Name for the table in the database
    public static final String TABLE_NAME = "users_data";
    // Create variable foe Column 1 (ID)
    public static final String COL1 = "ID";
     // // Create variable foe Column 2 (First Name )
    public static final String COL2 = "FIRSTNAME";
    // // Create variable foe Column 3 (Last Name)
    public static final String COL3 = "LASTNAME";
    // Create variable foe Column 4 (Food)
    public static final String COL4 = "FAVFOOD";

    // Function for super Context
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {// create table inside (id - primary key )( first name - last name - food )
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " FIRSTNAME TEXT, LASTNAME TEXT, FAVFOOD TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }
  // function ADD type Boolean
    public boolean addData(String fName, String lName, String fFood) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, fName);
        contentValues.put(COL3, lName);
        contentValues.put(COL4, fFood);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    // Function Delete type integer By (id)
    public Integer deleteContactById (String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.v("georgeLog","Calling cursor");
        //Cursor res =  db.rawQuery( "select * from contacts where name="+name+"", null );
        Log.v("georgeLog","Cursor returned");
        return db.delete("users_data",
                "ID = ? ",
                new String[] { id });
    }
    // Function Update type integer link class User
    public int updateContact(User contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL2, contact.getFirstName());
        values.put(COL3, contact.getLastName());
        values.put(COL4, contact.getEmail());



        return db.update(TABLE_NAME, values, COL1 + " = ?",
                new String[] {contact.getID()});
    }
    //query for 1 week repeats
    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }
    // // Function Arraylist All
    public ArrayList<String> getAllContacts() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        // Query from contancts
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COL1)));
            array_list.add(res.getString(res.getColumnIndex(COL2)));
            array_list.add(res.getString(res.getColumnIndex(COL3)));
            array_list.add(res.getString(res.getColumnIndex(COL4)));

            res.moveToNext();
        }
        return array_list;
    }

}
