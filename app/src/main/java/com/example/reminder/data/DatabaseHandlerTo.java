package com.example.reminder.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.reminder.model.Alarm;
import com.example.reminder.model.Email1;
import com.example.reminder.util.Util;
import com.example.reminder.util.Util2;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandlerTo extends SQLiteOpenHelper {
    public DatabaseHandlerTo(Context context ) {
        super(context, Util2.DATABASE_NAME, null, Util2.DATABASE_VERSION );
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ALARM_TABLE = " CREATE TABLE " + Util2.TABLE_NAME + "("
                + Util2.KEY_ID + " INTEGER PRIMARY KEY , " + Util2.KEY_SENDTO+ " TEXT , "
                + Util2.KEY_SUBJECT + " TEXT , " + Util2.KEY_MASSEG + " TEXT , " +Util2.KEY_DATE+ " TEXT " + " ) ";
          db.execSQL(CREATE_ALARM_TABLE); //creating our table

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Util2.TABLE_NAME);
        onCreate(db);




    }
    //Add Contact
    public void addemail(Email1 email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util2.KEY_SENDTO ,email.getSend_to());
        values.put(Util2.KEY_SUBJECT, email.getSubject());
        values.put(Util2.KEY_MASSEG, email.getMasseg());
        values.put(Util2.KEY_DATE, email.getDate());




        //Insert to row
        int count = (int) db.insert(Util2.TABLE_NAME, null, values);
        if(count!=-1){
            Log.d("DBHandler", "addContact: " + "item added");
        }
        db.close(); //closing db connection!



    }
    public void deleteEmail(int i) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util2.TABLE_NAME, Util2.KEY_ID + "=?",
                new String[]{String.valueOf(i)});

        db.close();
    }
    public List<Email1> getEmail() {
        List<Email1> EmailList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();



        String selectAll = "SELECT * FROM " + Util2.TABLE_NAME +"";
        Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through our data
        if (cursor.moveToFirst()) {
            do {

                Email1 email1 = new Email1(1, "213986","d","d","d:20");
                email1.setId(Integer.parseInt(cursor.getString(0)));
                email1.setSend_to(cursor.getString(1));
                email1.setSubject(cursor.getString(2));
                email1.setMasseg(cursor.getString(3));
                email1.setDate(cursor.getString(4));

                Log.d("IconClicked", "onClick: " );


                //add contact objects to our list
               EmailList.add(email1);
            }while (cursor.moveToNext());
        }


        return EmailList;
    }

}
