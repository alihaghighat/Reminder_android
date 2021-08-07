package com.example.reminder;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.res.Resources;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class emailReceiver  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        int id = intent.getIntExtra("Email_ID",0);
        String sendto = intent.getStringExtra("sendto");
        String subject = intent.getStringExtra("subject");
        String Masseg = intent.getStringExtra("Masseg");


        JavaMailAPI javaMailAPI = new JavaMailAPI(context, sendto,subject,Masseg);

        javaMailAPI.execute();

        Log.d("test", "onCreate: " );

        NotificationCompat.Builder nb = notificationHelper.getChannelNotification()
                .setContentTitle("send Email "+sendto+" automatic")
                .setContentText("by bam++")
                .setAutoCancel(true)
                ;


        nb.setSound(Settings.System.DEFAULT_ALARM_ALERT_URI);

        notificationHelper.getManager().notify(id, nb.build());


    }
}
