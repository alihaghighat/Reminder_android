package com.example.reminder.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.reminder.model.Email1;
import com.example.reminder.model.Location;
import com.example.reminder.util.Util2;
import com.example.reminder.util.Util3;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandlerToL extends SQLiteOpenHelper {
    public DatabaseHandlerToL(Context context ) {
        super(context, Util3.DATABASE_NAME, null, Util3.DATABASE_VERSION );
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_ALARM_TABLE = " CREATE TABLE " + Util3.TABLE_NAME + "("
                + Util3.KEY_ID + " INTEGER PRIMARY KEY , " + Util3.KEY_TITLE+ " TEXT , "
                + Util3.KEY_LATITUDE + " TEXT , " + Util3.KEY_LONGITUDE + " TEXT "  + " ) ";
          db.execSQL(CREATE_ALARM_TABLE); //creating our table


    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Util3.TABLE_NAME);
        onCreate(db);

    }
    //Add Contact
    public void addlocations(Location email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util3.KEY_TITLE ,email.getTitle());
        values.put(Util3.KEY_LATITUDE, email.getLatitude());
        values.put(Util3.KEY_LONGITUDE, email.getLongitude());





        //Insert to row
        int count = (int) db.insert(Util3.TABLE_NAME, null, values);
        if(count!=-1){
            Log.d("DBHandler", "addContact:555 " + "item added");
        }

        db.close(); //closing db connection!



    }
    public void delete(int i) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util3.TABLE_NAME, Util3.KEY_ID + "=?",
                new String[]{String.valueOf(i)});

        db.close();
    }
    public List<Location> getallLocation() {
        List<Location> LocationList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();



        String selectAll = "SELECT * FROM " + Util3.TABLE_NAME +"";
        Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through our data
        if (cursor.moveToFirst()) {
            do {

                Location email1 = new Location(1, "213986","d","d");
                email1.setId(Integer.parseInt(cursor.getString(0)));
                email1.setTitle(cursor.getString(1));
                email1.setLatitude(cursor.getString(2));
                email1.setLongitude(cursor.getString(3));

                Log.d("IconClicked", "onClick: " );


                //add contact objects to our list
                LocationList.add(email1);
            }while (cursor.moveToNext());
        }


        return LocationList;
    }

}
