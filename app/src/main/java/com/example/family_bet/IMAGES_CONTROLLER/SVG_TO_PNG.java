package com.example.family_bet.IMAGES_CONTROLLER;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;

import com.caverock.androidsvg.SVG;
import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.Classes.Game.team.Team;
import com.example.family_bet.View_Model.User_Activity.User_Activity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URL;

public class SVG_TO_PNG {


    public class SVGToPNGConvert {
        Context context;

        public SVGToPNGConvert(URL url) {
            try {

                this.context = context;
                SVG svg = SVG.getFromInputStream(url.openStream());
                Bitmap bitmap = Bitmap.createBitmap((int) svg.getDocumentWidth(), (int) svg.getDocumentHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                canvas.drawPicture(svg.renderToPicture());
                
            } catch (Exception e) {

            }


        }
    }
}
