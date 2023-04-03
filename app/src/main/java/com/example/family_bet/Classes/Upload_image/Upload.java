package com.example.family_bet.Classes.Upload_image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

public class Upload {
    public Upload(Context context,Bitmap bitmap, String url){
        InputStream inputStream=null;
        try {

            Toast.makeText(context, url, Toast.LENGTH_SHORT).show();

         inputStream = new URL(url).openStream();
        }
               catch (Exception e){
                   Toast.makeText(context, "eee", Toast.LENGTH_SHORT).show();
            }
              bitmap = BitmapFactory.decodeStream(inputStream);

        }



}
