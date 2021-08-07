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

public class AlertReceiver  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);

        int Type=intent.getIntExtra("type",0);
        PendingIntent contentIntent;
        if(Type==0){
            int id = intent.getIntExtra("alarm_ID",0);
            String url = intent.getStringExtra("url");
            Uri uri = Uri.parse("http://"+url); // missing 'http://' will cause crashed
            Intent intentURL = new Intent(Intent.ACTION_VIEW, uri);
            intentURL.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            contentIntent = PendingIntent.getActivity(context.getApplicationContext(),
                    0, intentURL, PendingIntent.FLAG_UPDATE_CURRENT);

        }else{
            int week = intent.getIntExtra("week",0);
            Intent weekIntent = new Intent(context, home.class);
            weekIntent.putExtra("week",week);
            weekIntent.setFlags( Intent.FLAG_ACTIVITY_SINGLE_TOP);
            contentIntent = PendingIntent.getActivity(context.getApplicationContext(),
                    0, weekIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }



        int id = intent.getIntExtra("alarm_ID",0);

        NotificationCompat.Builder nb = notificationHelper.getChannelNotification()
                .setContentTitle(intent.getStringExtra("title"))
                .setContentText(intent.getStringExtra("des"))
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                ;

//
        nb.setSound(Settings.System.DEFAULT_ALARM_ALERT_URI);

        notificationHelper.getManager().notify(id, nb.build());

    }
}
