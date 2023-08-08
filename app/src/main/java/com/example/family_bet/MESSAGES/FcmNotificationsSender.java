package com.example.family_bet.MESSAGES;

import android.app.Activity;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mbridge.msdk.thrid.okhttp.MediaType;
import com.mbridge.msdk.thrid.okhttp.OkHttpClient;
import com.mbridge.msdk.thrid.okhttp.Request;
import com.mbridge.msdk.thrid.okhttp.RequestBody;
import com.mbridge.msdk.thrid.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class FcmNotificationsSender  {

    String userFcmToken;
    String title;
    String body;
    Context mContext;
    Activity mActivity;


    private RequestQueue requestQueue;
    private final String postUrl = "https://fcm.googleapis.com/fcm/send";
    private final String fcmServerKey ="AAAAb-XDssc:APA91bF6h4fFD12VJis-acSj14R0GKZJw4wSEIxawA7oiPfOrf6LP64HBWaC4ntUpbSprl9PHs6dO6NC0SAAwOyQSQXAFonr-dRo6hhCjtcch2gTu6qaDX9MfHMHSRPo_uT0gnV1B8Uo";
    public FcmNotificationsSender() {
        this.userFcmToken = userFcmToken;
        this.title = title;
        this.body = body;
        this.mContext = mContext;
        this.mActivity = mActivity;


    }

    public void SendNotifications() {

        requestQueue = Volley.newRequestQueue(mActivity);
        JSONObject mainObj = new JSONObject();
        try {
            mainObj.put("to", userFcmToken);
            JSONObject notiObject = new JSONObject();
            notiObject.put("title", title);
            notiObject.put("body", body);
           // notiObject.put("icon", "icon"); // enter icon that exists in drawable only



            mainObj.put("notification", notiObject);



        } catch (JSONException e) {
            e.printStackTrace();
        }




    }
    private final String SERVER_KEY = "AAAAb-XDssc:APA91bF6h4fFD12VJis-acSj14R0GKZJw4wSEIxawA7oiPfOrf6LP64HBWaC4ntUpbSprl9PHs6dO6NC0SAAwOyQSQXAFonr-dRo6hhCjtcch2gTu6qaDX9MfHMHSRPo_uT0gnV1B8Uo";
    private final String FCM_URL = "https://fcm.googleapis.com/fcm/send";

    private OkHttpClient client = new OkHttpClient();

    public void sendNotification(String topic,String title,String body1) throws IOException, JSONException {
        JsonObject data = new JsonObject();

        data.addProperty("to", topic);

        JsonObject notification = new JsonObject();
        notification.addProperty("title", title);
        notification.addProperty("body", body1);
        JsonElement element=notification.getAsJsonObject();
        data.add("notification", element);

        JsonObject dataPayload = new JsonObject();
        dataPayload.addProperty("custom_key", "custom_value");

        data.add("data", dataPayload);

        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = RequestBody.create(mediaType, data.toString());

       Request request = new Request.Builder()
                .url(FCM_URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "key=" + SERVER_KEY)
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());
    }

}