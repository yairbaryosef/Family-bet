package com.example.family_bet.MESSAGES;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.DB.Firestore;
import com.google.gson.Gson;

public class Save_and_Get_Tour {


    public static Tournament t;
    public static String topic;
    /**
     * sent a update for the tour to all users who sign to topic <topic> <h1>topic</h1></topic>
     */
    public void sent(Tournament tour,String top){
        t=tour;
       topic=top;
        new Send_Tour().execute(tour);
    }
    private class Send_Tour extends AsyncTask<Object, ProgressDialog, String> {

        @Override
        protected String doInBackground(Object... voids) {
            try {

                Gson gson=new Gson();
             Thread t1=   new Thread(new Runnable() {
                    @Override
                    public void run() {
                       // ProgressDialog p= (ProgressDialog)  voids[0];
                        try {
                            Tournament t=(Tournament) voids[0];
                            int i = 0;
                            String send = gson.toJson(t);
                            FcmNotificationsSender sender = new FcmNotificationsSender();
                            String path=Firestore.getPath(t.getCountry(),t.getDealer(),t.getSport_Type(),t.getTour_name());
                            sender.sendNotification(topic,path, "restart");
                            //   Toast.makeText(MainActivity.this, String.valueOf(send.length()), Toast.LENGTH_SHORT).show();
                            while (i < send.length()) {
                                String current = "";
                                if (send.length() - i < 500) {
                                    current = send.substring(i, send.length());
                                    i = send.length();
                                } else {
                                    current = send.substring(i, i + 500);
                                    i = i + 500;
                                }


                                sender.sendNotification(topic, path, current);
                                double in = Double.valueOf(i / send.length());

                               //p.setMessage(String.valueOf(in) + " percent");
                            }
                          // p.dismiss();
                            //  sender.sendNotification("/topics/winner", "send",gson.toJson(t));
                        } catch (Exception e) {
                          //  p.setMessage(e.getMessage());
                            // Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
             t1.start();




            }catch (Exception e){

            }




            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            // handle the response here
        }
    }
}
