package com.example.family_bet.MESSAGES;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;

import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.DB.Firestore;
import com.example.family_bet.MainActivity;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    NotificationManager mNotificationManager;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


       if(remoteMessage.getNotification().getTitle().equals("notification")) {
// playing audio and vibration when user se reques
           Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
           Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
           r.play();
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
               r.setLooping(false);
           }

           // vibration
           Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
           long[] pattern = {100, 300, 300, 300};
           v.vibrate(pattern, -1);


           NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID");


           Intent resultIntent = new Intent(this, MainActivity.class);
           PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);


           builder.setContentTitle(remoteMessage.getNotification().getTitle());
           builder.setContentText(remoteMessage.getNotification().getBody());
           builder.setContentIntent(pendingIntent);
           builder.setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getNotification().getBody()));
           builder.setAutoCancel(true);
           builder.setPriority(Notification.PRIORITY_MAX);

           mNotificationManager =
                   (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);


           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
               String channelId = "Your_channel_id";
               NotificationChannel channel = new NotificationChannel(
                       channelId,
                       "Channel human readable title",
                       NotificationManager.IMPORTANCE_HIGH);
               mNotificationManager.createNotificationChannel(channel);
               builder.setChannelId(channelId);
           }


// notificationId is a unique int for each notification that you must define
           mNotificationManager.notify(100, builder.build());
       }
       else if(remoteMessage.getNotification().getBody().equals("restart")){
           SharedPreferences sp=getSharedPreferences(remoteMessage.getNotification().getTitle(),0);
           SharedPreferences.Editor editor=sp.edit();

           editor.putString("d","");
           editor.commit();
       }
       else if(remoteMessage.getNotification().getBody().equals("join")){
           Save_and_Get_Tour save_and_get_tour=new Save_and_Get_Tour();
           SharedPreferences sp=getSharedPreferences(remoteMessage.getNotification().getTitle(),0);
           String[] body=remoteMessage.getNotification().getBody().split("\\s");
           String s=sp.getString(body[1],"");
           Tournament t= Firestore.initTour_from_String(s);
           String path=Firestore.getPath(t.getCountry(),t.getDealer(),t.getSport_Type(),t.getTour_name());
           save_and_get_tour.sent(t,path);
       }
       else{

           SharedPreferences sp=getSharedPreferences(remoteMessage.getNotification().getTitle(),0);
           SharedPreferences.Editor editor=sp.edit();
           String s=sp.getString("d","");
           editor.putString("d",s+remoteMessage.getNotification().getBody());
           editor.commit();
       }


    }

}