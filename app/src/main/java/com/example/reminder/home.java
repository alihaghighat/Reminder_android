package com.example.reminder;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.reminder.data.DatabaseHandler;
import com.example.reminder.data.DatabaseHandlerTo;
import com.example.reminder.data.DatabaseHandlerToL;

public class home extends AppCompatActivity {
    long pressedTime;
    public static FragmentTransaction transaction;
    public static DatabaseHandler db;
    public static DatabaseHandlerTo db2;
    public static DatabaseHandlerToL db3;



    ImageButton homebtn,locationbtn,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        db = new DatabaseHandler(home.this);
        db2 = new DatabaseHandlerTo(home.this);
       db3 = new DatabaseHandlerToL(home.this);
       // db3.onUpgrade(db3.getWritableDatabase(),1,15);
        //db.onUpgrade(db.getWritableDatabase(),1,15);
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.farmain, alarmOnFragment.getInstance());
        transaction.commit();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.farmain, alarmOnFragment.getInstance());
        transaction.commit();
        Intent serviceIntent = new Intent(this, MyService.class);

        MyService.enqueueWork(this, serviceIntent);


        homebtn=findViewById(R.id.home_btn);
        locationbtn=findViewById(R.id.loction_btn);
        email=findViewById(R.id.email_btn);

        locationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.farmain, locationsFragment.getInstance());
                transaction.commit();
                homebtn.animate().alpha((float) 0.8).setDuration(1000).setStartDelay(1000);
                email.animate().alpha((float) 0.8).setDuration(1000).setStartDelay(1000);
                locationbtn.animate().alpha(1).setDuration(1000).setStartDelay(1000);
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.farmain, alarmOnFragment.getInstance());
                transaction.commit();
                locationbtn.animate().alpha((float) 0.8).setDuration(1000).setStartDelay(1000);
                email.animate().alpha((float) 0.8).setDuration(1000).setStartDelay(1000);
                homebtn.animate().alpha(1).setDuration(1000).setStartDelay(1000);
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.farmain, EmailFragment.getInstance());
                transaction.commit();
                locationbtn.animate().alpha((float) 0.8).setDuration(1000).setStartDelay(1000);
                homebtn.animate().alpha((float) 0.8).setDuration(1000).setStartDelay(1000);
                email.animate().alpha(1).setDuration(1000).setStartDelay(1000);
            }
        });




    }






}