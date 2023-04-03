package com.example.family_bet.HTTP;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.Classes.Game.Tournament_for_saved;

public class HTTPServerService extends Service {
    private MyHTTPServer server;
    Tournament t;
    private static final String ports="ports";
    HTTPServerService(){
        t=new Tournament();
    }
   HTTPServerService(Tournament t){
       this.t=t;
   }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Start the MyHTTPServer
        try {


            server = new MyHTTPServer(t);
            SharedPreferences s=getSharedPreferences("counters",0);
            int port=s.getInt("ports",0);
            server.connect(port);
            SharedPreferences.Editor editor=s.edit();
            port++;
            editor.putInt(ports,port);
            editor.commit();

            return START_STICKY;
        }catch (Exception E){

        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Stop the MyHTTPServer when the service is destroyed
      //  server.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
