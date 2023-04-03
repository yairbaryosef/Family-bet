package com.example.family_bet.View_Model.User_Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.Classes.Game.Game;
import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.Classes.Game.Tournament_for_saved;
import com.example.family_bet.Classes.Game.team.Team;
import com.example.family_bet.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Tournament_Adapter extends ArrayAdapter<Tournament> {
    public Tournament_Adapter(@NonNull Context context, ArrayList<Tournament> arrayList) {
        super(context, R.layout.card_for_adapters, R.id.text3, arrayList);


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Tournament tournament=getItem(position);
        if(convertView==null){
              convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_for_adapters, parent, false);

        }
        //init views
        ImageView home_team,away_team;
        TextView date,hour,update,delete,subject,place;
        //init tournament des
        subject=convertView.findViewById(R.id.subject);
        place=convertView.findViewById(R.id.place);
        subject.setText(tournament.getTour_name());
        String user=constants.get_Curret_User(getContext());
        try {


            if (tournament.getDealer().equals(user)) {
                place.setText("dealer");
            }
        }catch (Exception e){
        }
        //init closest game


        home_team=convertView.findViewById(R.id.home);

        away_team=convertView.findViewById(R.id.away);
        date=convertView.findViewById(R.id.date);
        hour=convertView.findViewById(R.id.hour);

        try {
            Game game=tournament.getGames().get(0);
            Picasso.get().load(game.getHome_team().getPicture()).into(home_team);
            home_team.setImageBitmap(null);
            Picasso.get().load(game.getAway_team().getPicture()).into(away_team);
            away_team.setImageBitmap(null);
            date.setText(constants.date_format_by_day(game.getLast_date()));
            hour.setText(constants.date_format_by_time(game.getLast_date()));


        }
        catch (Exception e){

        }


        return super.getView(position, convertView, parent);
    }
}
