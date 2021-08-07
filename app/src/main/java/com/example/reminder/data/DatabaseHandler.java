package com.example.reminder.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.reminder.R;
import com.example.reminder.model.Alarm;
import com.example.reminder.util.Util;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context ) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION );
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ALARM_TABLE = " CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY , " + Util.KEY_TIME + " TEXT , "
                + Util.KEY_DES + " TEXT , " + Util.KEY_TITLE + " TEXT , " +Util.KEY_URL+ " TEXT , " + Util.KEY_PRIORITY+" INTEGER , "
                +Util.KEY_WEEK + " INTEGER "+" ) ";
          db.execSQL(CREATE_ALARM_TABLE); //creating our table

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Util.TABLE_NAME);
        onCreate(db);




    }
    //Add Contact
    public void addArlam(Alarm Arlam) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_TITLE, Arlam.getTilte());
        values.put(Util.KEY_TIME, Arlam.getTime());
        values.put(Util.KEY_DES, Arlam.getDes());
        values.put(Util.KEY_WEEK, Arlam.getWeek());
        values.put(Util.KEY_URL, Arlam.getUrl());
        values.put(Util.KEY_PRIORITY, Arlam.getPriority());


        //Insert to row
        int count = (int) db.insert(Util.TABLE_NAME, null, values);
        if(count!=-1){
            Log.d("DBHandler", "addContact: " + "item added");
        }
        db.close(); //closing db connection!



    }
    //Get a contact

    //Delete single contact
    public void deleteArlam(int i) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?",
                new String[]{String.valueOf(i)});

        db.close();
    }
    //Get all alarm saturday
    public List<Alarm> getAlram1() {
        List<Alarm> alarmsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();



        String selectAll = "SELECT * FROM " + Util.TABLE_NAME +" WHERE week = 1 ";
        Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through our data
        if (cursor.moveToFirst()) {
            do {

                Alarm alarml = new Alarm(1,1, "213986","d","d","d:20",1);
                alarml.setId(Integer.parseInt(cursor.getString(0)));
                alarml.setWeek(Integer.parseInt(cursor.getString(6)));
                alarml.setUrl(cursor.getString(4));
                alarml.setTilte(cursor.getString(3));
                alarml.setDes(cursor.getString(2));
                alarml.setTime(cursor.getString(1));
                alarml.setPriority(Integer.parseInt(cursor.getString(5)));

                Log.d("IconClicked", "onClick: " );


                //add contact objects to our list
                alarmsList.add(alarml);
            }while (cursor.moveToNext());
        }


        return alarmsList;
    }
    //Get all alarm sunday
    public List<Alarm> getAlram2() {
        List<Alarm> alarmsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();



        String selectAll = "SELECT * FROM " + Util.TABLE_NAME +" WHERE week = 2 ";
        Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through our data
        if (cursor.moveToFirst()) {
            do {

                Alarm alarml = new Alarm(1,1, "213986","d","d","d:20",1);
                alarml.setId(Integer.parseInt(cursor.getString(0)));
                alarml.setWeek(Integer.parseInt(cursor.getString(6)));
                alarml.setUrl(cursor.getString(4));
                alarml.setTilte(cursor.getString(3));
                alarml.setDes(cursor.getString(2));
                alarml.setTime(cursor.getString(1));
                alarml.setPriority(Integer.parseInt(cursor.getString(5)));

                Log.d("IconClicked", "onClick: " );


                //add contact objects to our list
                alarmsList.add(alarml);
            }while (cursor.moveToNext());
        }


        return alarmsList;
    }
    // get all alarm monday
    public List<Alarm> getAlram3() {
        List<Alarm> alarmsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();


        String selectAll = "SELECT * FROM " + Util.TABLE_NAME +" WHERE week = 3 ";
        Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through our data
        if (cursor.moveToFirst()) {
            do {

                Alarm alarml = new Alarm(1,1, "213986","d","d","d:20",1);
                alarml.setId(Integer.parseInt(cursor.getString(0)));
                alarml.setWeek(Integer.parseInt(cursor.getString(6)));
                alarml.setUrl(cursor.getString(4));
                alarml.setTilte(cursor.getString(3));
                alarml.setDes(cursor.getString(2));
                alarml.setTime(cursor.getString(1));
                alarml.setPriority(Integer.parseInt(cursor.getString(5)));

                Log.d("IconClicked", "onClick: " );


                //add contact objects to our list
                alarmsList.add(alarml);
            }while (cursor.moveToNext());
        }


        return alarmsList;
    }
    // get all alarm tuesday
    public List<Alarm> getAlram4() {
        List<Alarm> alarmsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();


        String selectAll = "SELECT * FROM " + Util.TABLE_NAME +" WHERE week = 4 ";
        Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through our data
        if (cursor.moveToFirst()) {
            do {

                Alarm alarml = new Alarm(1,1, "213986","d","d","d:20",1);
                alarml.setId(Integer.parseInt(cursor.getString(0)));
                alarml.setWeek(Integer.parseInt(cursor.getString(6)));
                alarml.setUrl(cursor.getString(4));
                alarml.setTilte(cursor.getString(3));
                alarml.setDes(cursor.getString(2));
                alarml.setTime(cursor.getString(1));
                alarml.setPriority(Integer.parseInt(cursor.getString(5)));

                Log.d("IconClicked", "onClick: " );


                //add contact objects to our list
                alarmsList.add(alarml);
            }while (cursor.moveToNext());
        }


        return alarmsList;
    }
    // get all alarm wednesday
    public List<Alarm> getAlram5() {
        List<Alarm> alarmsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();


        String selectAll = "SELECT * FROM " + Util.TABLE_NAME +" WHERE week = 5 ";
        Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through our data
        if (cursor.moveToFirst()) {
            do {

                Alarm alarml = new Alarm(1,1, "213986","d","d","d:20",1);
                alarml.setId(Integer.parseInt(cursor.getString(0)));
                alarml.setWeek(Integer.parseInt(cursor.getString(6)));
                alarml.setUrl(cursor.getString(4));
                alarml.setTilte(cursor.getString(3));
                alarml.setDes(cursor.getString(2));
                alarml.setTime(cursor.getString(1));
                alarml.setPriority(Integer.parseInt(cursor.getString(5)));

                Log.d("IconClicked", "onClick: " );


                //add contact objects to our list
                alarmsList.add(alarml);
            }while (cursor.moveToNext());
        }


        return alarmsList;
    }
    // get all alarm thursday
    public List<Alarm> getAlram6() {
        List<Alarm> alarmsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();


        String selectAll = "SELECT * FROM " + Util.TABLE_NAME +" WHERE week = 6 ";
        Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through our data
        if (cursor.moveToFirst()) {
            do {

                Alarm alarml = new Alarm(1,1, "213986","d","d","d:20",1);
                alarml.setId(Integer.parseInt(cursor.getString(0)));
                alarml.setWeek(Integer.parseInt(cursor.getString(6)));
                alarml.setUrl(cursor.getString(4));
                alarml.setTilte(cursor.getString(3));
                alarml.setDes(cursor.getString(2));
                alarml.setTime(cursor.getString(1));
                alarml.setPriority(Integer.parseInt(cursor.getString(5)));

                Log.d("IconClicked", "onClick: " );


                //add contact objects to our list
                alarmsList.add(alarml);
            }while (cursor.moveToNext());
        }


        return alarmsList;
    }

    // get all alarm friday
    public List<Alarm> getAlram7() {
        List<Alarm> alarmsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();


        String selectAll = "SELECT * FROM " + Util.TABLE_NAME +" WHERE week = 7 ";
        Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through our data
        if (cursor.moveToFirst()) {
            do {

                Alarm alarml = new Alarm(1,1, "213986","d","d","d:20",1);
                alarml.setId(Integer.parseInt(cursor.getString(0)));
                alarml.setWeek(Integer.parseInt(cursor.getString(6)));
                alarml.setUrl(cursor.getString(4));
                alarml.setTilte(cursor.getString(3));
                alarml.setDes(cursor.getString(2));
                alarml.setTime(cursor.getString(1));
                alarml.setPriority(Integer.parseInt(cursor.getString(5)));

                Log.d("IconClicked", "onClick: " );


                //add contact objects to our list
                alarmsList.add(alarml);
            }while (cursor.moveToNext());
        }


        return alarmsList;
    }
}
