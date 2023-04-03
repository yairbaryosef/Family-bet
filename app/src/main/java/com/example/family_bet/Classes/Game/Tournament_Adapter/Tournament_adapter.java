package com.example.family_bet.Classes.Game.Tournament_Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.Classes.Enteties.Predictor;
import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.Classes.Game.Tournament_Controller;
import com.example.family_bet.R;
import com.squareup.picasso.Picasso;

public class Tournament_adapter extends ArrayAdapter<Tournament> {
    public Tournament_adapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Tournament tournament=getItem(position);
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_for_adapters, parent, false);

        }

        ImageView imageView=convertView.findViewById(R.id.image);
        TextView subject=convertView.findViewById(R.id.subject);
        TextView place=convertView.findViewById(R.id.place);
        try {
            Picasso.get().load(tournament.getTour_picture()).into(imageView);
            subject.setText(tournament.getTour_name());
            SharedPreferences sp=getContext().getSharedPreferences(constants.user,0);
            String username=sp.getString("username",null);
            Tournament_Controller tournament_controller=new Tournament_Controller(tournament);
            Predictor predictor=tournament_controller.get_predictor_by_username(username);
            if(predictor!=null){
                place.setText(String.valueOf(tournament.getPredictors().indexOf(predictor)));
            }
        }
        catch (Exception e){

        }
        return super.getView(position, convertView, parent);
    }


}
