package com.example.family_bet.View_Model.User_Activity.Delar_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.family_bet.ADS;
import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.Classes.Enteties.User;
import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.DB.Firestore;
import com.example.family_bet.databinding.ActivityDealearMenuBinding;
import com.google.gson.Gson;

public class Dealear_Menu extends AppCompatActivity {
   ActivityDealearMenuBinding binding;
   User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDealearMenuBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        ADS.show_Interstitial(this);
        String tour=getIntent().getStringExtra(constants.tournament);
        String u=getIntent().getStringExtra(constants.user);
        user=new Gson().fromJson(u,User.class);
        //update results handily
        binding.results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dealear_Menu.this, Delar_Activity.class);

                intent.putExtra(constants.tournament, tour);
                startActivity(intent);
            }
        });
        binding.end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson=new Gson();
                Tournament tournament=gson.fromJson(tour,Tournament.class);
                String path= Firestore.getPath(tournament);
                if(user.getCount_tours()>0) {
                    user.setCount_tours(user.getCount_tours() - 1);
                    User.Save_user(user);
                }
                Firestore.delete(tournament);



            }
        });
        binding.news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dealear_Menu.this, Add_News.class);

                intent.putExtra(constants.tournament, tour);
                startActivity(intent);
            }
        });
        /*
        binding.sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson=new Gson();
                Tournament tournament=gson.fromJson(tour,Tournament.class);
                if(tournament.type.equals(constants.winnerLeague)) {
                    try {


                        WinnerLeague.Sync(tournament);
                        Tournament_Controller tournament_controller = new Tournament_Controller(tournament);
                        for (Predictor predictor : tournament.getPredictors()) {
                            predictor.setTotal_points(0);
                            tournament_controller.set_Total_Point(predictor);
                        }
                        Tournament.save_tournament(tournament);
                        Toast.makeText(Dealear_Menu.this, "sync", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){

                    }
                }
            }
        });*/
    }
}