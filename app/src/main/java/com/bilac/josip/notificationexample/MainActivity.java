package com.bilac.josip.notificationexample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String NOTIFICATION_CHANNEL_GENERAL_ID = "10001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.ibNotification)
    public void showNotification() {
        Intent resultIntent = new Intent(getApplicationContext(),MainActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(getApplicationContext(),0, resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder b = new NotificationCompat.Builder(getApplicationContext(),NOTIFICATION_CHANNEL_GENERAL_ID);
        b.setSmallIcon(R.mipmap.ic_launcher);
        b.setContentTitle("First notification").setContentText("We have made our first notification").setAutoCancel(false).setDefaults(Notification.DEFAULT_ALL).setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.ic_notifications).setSound(Settings.System.DEFAULT_NOTIFICATION_URI).setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND).setContentInfo("Cool").setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_GENERAL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.setVibrationPattern(new long[]{100,200,300,400,500,400,300,200,400});
            b.setChannelId(NOTIFICATION_CHANNEL_GENERAL_ID);
            mNotificationManager.createNotificationChannel(channel);
        }
        mNotificationManager.notify(0,b.build());
        //id 0 for new notification to overwrite previous one


    }

}
