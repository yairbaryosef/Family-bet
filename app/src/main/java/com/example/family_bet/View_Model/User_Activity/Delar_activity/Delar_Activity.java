package com.example.family_bet.View_Model.User_Activity.Delar_activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.Classes.Enteties.Predictor;
import com.example.family_bet.Classes.Game.Game;
import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.Classes.Game.Tournament_Controller;
import com.example.family_bet.R;
import com.example.family_bet.databinding.ActivityDelarBinding;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class Delar_Activity extends AppCompatActivity implements View.OnClickListener {
    Tournament tournament=new Tournament();
   ActivityDelarBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDelarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init_tournament();
        Toast.makeText(this, tournament.getTour_name(), Toast.LENGTH_SHORT).show();
       Game_Update_Adapter game_update_adapter=new Game_Update_Adapter(this,tournament.getGames(),tournament);
       binding.list.setAdapter(game_update_adapter);
       binding.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Game game= tournament.getGames().get(position);
               update_game(game);
           }
       });
        binding.save.setOnClickListener(this);
    }

    private void update_game(Game game) {
        Dialog d=new Dialog(this);
        d.setContentView(R.layout.bet_card);
        init_views_for_dialog(d,game);

       d.setOnDismissListener(new DialogInterface.OnDismissListener() {
           @Override
           public void onDismiss(DialogInterface dialog) {
               Game_Update_Adapter game_update_adapter=new Game_Update_Adapter(Delar_Activity.this,tournament.getGames(),tournament);
               binding.list.setAdapter(game_update_adapter);
           }
       });
        d.show();

    }
    ImageView home_team,away_team;
    TextView date,hour,home_name,away_name,score;
    private void init_views_for_dialog(Dialog convertView,Game game) {
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
                        game.setScore_home_team(i);

                    } catch (Exception e) {
                        Toast.makeText(Delar_Activity.this, "enter number", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Delar_Activity.this, "enter number", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
        catch (Exception e){

        }
    }

    private void init_tournament() {
        String json=getIntent().getStringExtra(constants.tournament);
        Gson gson=new Gson();
        tournament=gson.fromJson(json,Tournament.class);
    }

    @Override
    public void onClick(View v) {
        if(v==binding.save){
            Tournament_Controller tournament_controller=new Tournament_Controller(tournament);
            for(Predictor predictor:tournament.getPredictors()){
                predictor.setTotal_points(0);
                tournament_controller.set_Total_Point(predictor);
            }
            Tournament.save_tournament(tournament);
            Toast.makeText(this, "tournament update", Toast.LENGTH_SHORT).show();
        }
    }
}