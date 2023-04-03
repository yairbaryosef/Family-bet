package com.example.family_bet.View_Model.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.family_bet.Classes.Bets.Bet_On_Game;
import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.R;
import com.squareup.picasso.Picasso;
import com.yodo1.mas.nativeads.Yodo1MasNativeAdView;

import java.util.ArrayList;

public class Bets_Adapter extends ArrayAdapter<Bet_On_Game> {

    public Bets_Adapter(@NonNull Context context, ArrayList<Bet_On_Game> arrayList, Tournament tournament) {
        super(context, R.layout.game_bet, R.id.text3, arrayList);
       i=1;

    }
    int i;

    ImageView home_team,away_team;
    TextView date,hour,home_name,away_name,score;

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return bet view
     */
    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Bet_On_Game game=getItem(position);

        if(!game.getStatus().equals("AD")) {

            //check if the user can bet
            if (game.isIs_changed())
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.bet_card, parent, false);
            else
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.final_result, parent, false);

        }
           else{
               convertView = LayoutInflater.from(getContext()).inflate(R.layout.yodo1_native_ad, parent, false);

           }

        if(!game.getStatus().equals("AD")) {

            try {
                home_name = convertView.findViewById(R.id.home_name);
                away_name = convertView.findViewById(R.id.away_name);
                home_team = convertView.findViewById(R.id.home);
                score = convertView.findViewById(R.id.score);
                away_team = convertView.findViewById(R.id.away);
                date = convertView.findViewById(R.id.date);
                hour = convertView.findViewById(R.id.hour);
                Picasso.get().load(game.getGame().getHome_team().getPicture()).into(home_team);
                Picasso.get().load(game.getGame().getAway_team().getPicture()).into(away_team);
                date.setText(constants.date_format_by_day(game.getGame().getLast_date()));
                hour.setText(constants.date_format_by_time(game.getGame().getLast_date()));
                home_name.setText(game.getGame().getHome_team().getName());
                away_name.setText(game.getGame().getAway_team().getName());
                score.setText(String.valueOf(game.getGame().getScore_away_team()) + ":" + String.valueOf(game.getGame().getScore_home_team()));
                //  Toast.makeText(getContext(), String.valueOf(game.isIs_changed()), Toast.LENGTH_SHORT).show();
                // if (game.isIs_changed()) {


                try {


                    if (game.isIs_changed()) {
                        EditText home_bet, away_bet;
                        home_bet = convertView.findViewById(R.id.bet_home);
                        away_bet = convertView.findViewById(R.id.bet_away);
                        home_bet.setText(String.valueOf(game.getScore_home_team_bet()));

                        away_bet.setText(String.valueOf(game.getScore_away_team_bet()));
                        //init earlier bets
                        home_bet.addTextChangedListener(new TextWatcher() {
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
                                    game.setScore_home_team_bet(i);

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
                                    game.setScore_away_team_bet(i);


                                } catch (Exception e) {
                                    Toast.makeText(getContext(), "enter number", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        TextView home_bet, away_bet;
                        home_bet = convertView.findViewById(R.id.bet_home);
                        away_bet = convertView.findViewById(R.id.bet_away);
                        home_bet.setText(String.valueOf(game.getScore_home_team_bet()));

                        away_bet.setText(String.valueOf(game.getScore_away_team_bet()));
                        if (!game.isIs_changed()) {
                            if (game.getStatus().equals(constants.bool_bet)) {
                                home_bet.setTextColor(R.color.colorgreen);
                                away_bet.setTextColor(R.color.colorgreen);
                            } else if (game.getStatus().equals(constants.right_bet)) {
                                home_bet.setTextColor(R.color.grey);
                                away_bet.setTextColor(R.color.grey);
                            } else
                                home_bet.setTextColor(R.color.red);
                            away_bet.setTextColor(R.color.red);
                        }
                    }
                } catch (Exception e) {

                }

            } catch (Exception e) {

            }
        }
        else{
            try {


                initAD(convertView);
            }
            catch (Exception e){

            }
        }




        return super.getView(position, convertView, parent);
    }

    private void initAD(View convertView) {

        Yodo1MasNativeAdView nativeAdView = convertView.findViewById(R.id.yodo1_mas_native);
        nativeAdView.loadAd();
    }


}
