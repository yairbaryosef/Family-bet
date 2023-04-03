package com.example.family_bet.View_Model.User_Activity.Delar_activity;

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
import com.example.family_bet.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Game_Update_Adapter extends ArrayAdapter<Game> {
Tournament tournament=new Tournament();
    public Game_Update_Adapter(@NonNull Context context, ArrayList<Game> arrayList, Tournament tournament) {
        super(context, R.layout.final_result, R.id.text3, arrayList);
         this.tournament=tournament;

    }

    ImageView home_team,away_team;
    TextView date,hour,home_name,away_name,score;

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return bet view
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Game game = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.final_result, parent, false);
        }
        home_name = convertView.findViewById(R.id.home_name);
        away_name = convertView.findViewById(R.id.away_name);
        home_team = convertView.findViewById(R.id.home);
        score = convertView.findViewById(R.id.score);
        away_team = convertView.findViewById(R.id.away);

        date = convertView.findViewById(R.id.date);
        hour = convertView.findViewById(R.id.hour);

        try {
            Picasso.get().load(game.getHome_team().getPicture()).into(home_team);
            Picasso.get().load(game.getAway_team().getPicture()).into(away_team);
            date.setText(constants.date_format_by_day(game.getLast_date()));
            hour.setText(constants.date_format_by_time(game.getLast_date()));
            home_name.setText(game.getHome_team().getName());
            away_name.setText(game.getAway_team().getName());


            TextView home_bet, away_bet;
            home_bet = convertView.findViewById(R.id.bet_home);
            away_bet = convertView.findViewById(R.id.bet_away);
            try {


                home_bet.setText(String.valueOf(game.getScore_home_team()));

                away_bet.setText(String.valueOf(game.getScore_away_team()));
            } catch (Exception e) {

            }
         /*   home_bet.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    try {
                        int i = Integer.valueOf(s.toString());
                        game.setScore_home_team(i);

                    } catch (Exception e) {
                        Toast.makeText(getContext(), "enter number", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            away_bet.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    try {
                        int i = Integer.valueOf(s.toString());
                        game.setScore_away_team(i);

                    } catch (Exception e) {
                        Toast.makeText(getContext(), "enter number", Toast.LENGTH_SHORT).show();
                    }
                }
            });*/


        }
        catch (Exception e){

        }


        return super.getView(position, convertView, parent);
    }




}
