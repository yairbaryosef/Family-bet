package com.example.family_bet.View_Model.User_Activity.Predictor_Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Profile_Adapter extends ArrayAdapter<Profile_Adapter.profile> {
    public static class profile{
        public String resource;
        public String name;
        public Date date;
        public int sent;

        public profile(){
            Calendar calendar=Calendar.getInstance();
            date=calendar.getTime();
            sent=0;
        }
        public profile(String resource,String name){
           this.name=name;
           sent=0;
           this.resource=resource;
        }

        @Override
        public String toString() {
            return "profile{" +
                    "resource='" + resource + '\'' +
                    ", name='" + name + '\'' +
                    ", date=" + date +
                    ", sent=" + sent +
                    '}';
        }
    }
    public Profile_Adapter(@NonNull Context context, ArrayList<profile> arrayList) {
        super(context, R.layout.subject,R.id.text3, arrayList);

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        profile profile=getItem(position);
        if(convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.subject, parent, false);
        }

        ImageView imageView=(ImageView) convertView.findViewById(R.id.img);

        TextView textView=(TextView) convertView.findViewById(R.id.subject);

imageView.setBackground(null);

        textView.setText(profile.name);

        try {
            try {
               Integer i= Integer.valueOf(profile.resource);
               imageView.setImageResource(i);

            }
            catch (Exception e) {


                if (profile.resource.equals("")) {
                    imageView.setImageResource(R.drawable.trophy);
                } else if (profile.resource.equals("WINNER LEAGUE")) {
                    imageView.setImageResource(R.drawable.winner);
                }
                else if(profile.resource.equals(constants.al_League)){
                    imageView.setImageResource(R.drawable.al_league);
                }
                else {
                    Picasso.get().load(profile.resource).into(imageView);
                }
            }
        }
        catch (Exception e){

        }
        try {


            RequestOptions requestOptions = new RequestOptions()
                    .format(DecodeFormat.PREFER_ARGB_8888) // optional, to improve quality
                    .override(Target.SIZE_ORIGINAL); // optional, to prevent scaling

            Glide.with(getContext())
                    .load(profile.resource)
                    .apply(requestOptions)
                    .into(imageView);
        }
        catch (Exception e1){
            imageView.setImageResource(R.drawable.trophy);
        }


        return super.getView(position,convertView, parent);
    }

}
