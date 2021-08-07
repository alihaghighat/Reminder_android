package com.example.reminder;


import android.Manifest;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.JobIntentService;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.reminder.data.DatabaseHandlerToL;

import java.util.List;

public class MyService extends JobIntentService {
    android.location.Location Location1;

    private LocationListener mLocationListener;
    private LocationManager mLocationManager;
    public static DatabaseHandlerToL db;
    List<com.example.reminder.model.Location> locationList;

    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, MyService.class, 1, intent);
    }


    @Override
    public void onCreate() {
        super.onCreate();


        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull android.location.Location location) {
                Location1 = new android.location.Location(LocationManager.GPS_PROVIDER);
                Location1.setLatitude(location.getLatitude());
                Location1.setLongitude(location.getLongitude());


            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }
        };

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location1 = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
                0, mLocationListener);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        if (Location1 == null) {
            Location1 = new android.location.Location(LocationManager.GPS_PROVIDER);
            Log.d("testLocation", "onCreate: ");
        }
        db = new DatabaseHandlerToL(this);

        locationList = db.getallLocation();
        for (com.example.reminder.model.Location temp : locationList) {

            if (isStopped()) return;

            if (distance(temp,Location1) < 10.0) {
                sendNotif(temp);
                db.delete(temp.getId());

            }
        }



        SystemClock.sleep(2000);
        Intent serviceIntent = new Intent(this, MyService.class);
        MyService.enqueueWork(this, serviceIntent);
    }

    public double distance(com.example.reminder.model.Location location, android.location.Location currentCoordinate) {
        double lat1 = Float.parseFloat(location.getLatitude());
        double lng1 = Float.parseFloat(location.getLongitude());
        double lat2 = currentCoordinate.getLatitude();
        double lng2 = currentCoordinate.getLongitude();
        Log.d("testLocation", "lg: "+lat1);
        double earthRadius = 3958.75;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;

        int meterConversion = 1609;

        return dist * meterConversion;
    }
    public void sendNotif(com.example.reminder.model.Location location) {
        NotificationHelper notificationHelper = new NotificationHelper(this);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification()
                .setContentTitle("You have a coordinate alarm with the following position")
                .setContentText(location.getTitle())
                .setAutoCancel(true);
        nb.setSound(Settings.System.DEFAULT_ALARM_ALERT_URI);

        notificationHelper.getManager().notify(location.getId(), nb.build());
    }



}
