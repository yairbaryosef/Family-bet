package com.example.family_bet.View_Model.User_Activity.Predictor_Activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.Classes.Enteties.Predictor;
import com.example.family_bet.Classes.Game.Game;
import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.R;
import com.example.family_bet.View_Model.Fragments.Set_Bets_fragmant;
import com.example.family_bet.View_Model.Fragments.Show_news_fragmant;
import com.example.family_bet.View_Model.Fragments.Table_fragmant;
import com.example.family_bet.View_Model.User_Activity.User_Activity;
import com.example.family_bet.databinding.ActivityPredictorBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.yodo1.mas.Yodo1Mas;
import com.yodo1.mas.error.Yodo1MasError;
import com.yodo1.mas.helper.model.Yodo1MasAdBuildConfig;

import java.util.ArrayList;

public class Predictor_Activity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {
     ActivityPredictorBinding binding;
     Tournament tournament=new Tournament();
     String activity="";
     String username="";
     DrawerLayout drawer;
    // NavigationView navigationView;
     Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPredictorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init_tournament();

        drawer = findViewById(R.id.drawer_layout);
     //   navigationView=findViewById(R.id.menu);
      //  navigationView.setItemIconTintList(null);

      //  navigationView.setNavigationItemSelectedListener(this);
       username=constants.get_Curret_User(this);
        binding.navigation.setItemIconTintList(null);
        binding.fabButton.setImageTintList(null);
        binding.fabButton.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
        binding.navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                if(item.getTitle().toString().equals(constants.table)){

                    selectedFragment=new Table_fragmant(tournament);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    activity=constants.table;
                     return true;
                }
                else if(item.getTitle().toString().equals(constants.results)){
                    Predictor predictor=tournament.get_Predictor(username);
                    if(predictor!=null) {
                        selectedFragment = new Set_Bets_fragmant(tournament,predictor);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                        activity=constants.predictor;
                        return true;

                    }
                }
                else{
                    selectedFragment=new Show_news_fragmant(tournament);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    activity=constants.table;
                }
                return false;
            }
        });
        Yodo1MasAdBuildConfig config = new Yodo1MasAdBuildConfig.Builder().enableUserPrivacyDialog(true).build();
        Yodo1Mas.getInstance().setAdBuildConfig(config);
        Yodo1Mas.getInstance().initMas(this, "lOwAkhK2fP", new Yodo1Mas.InitListener() {
            @Override
            public void onMasInitSuccessful() {

                Yodo1Mas.getInstance().showInterstitialAd(Predictor_Activity.this);
            }

            @Override
            public void onMasInitFailed(@NonNull Yodo1MasError error) {
            }
        });
     //   ADS.show_Banner(this);
      binding.fabButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(v==binding.fabButton){
                  if(activity.equals(constants.predictor)) {
                      Predictor predictor = tournament.get_Predictor(username);
                      saveTour(predictor);
                  }

              }
          }
      });
    }

    /**
     * get Tour from DataBase Firestore
     */
    private void init_tournament() {
        gson=new Gson();
        String json=getIntent().getStringExtra(constants.tournament);
       tournament=gson.fromJson(json,Tournament.class);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getTitle().toString().equals(constants.table)){
           Fragment selectedFragment=new Table_fragmant(tournament);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            activity=constants.table;
        }
        else{
            Predictor predictor=tournament.get_Predictor(username);

                Fragment selectedFragment = new Set_Bets_fragmant(tournament,predictor);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            activity=constants.predictor;
                return true;

        }


        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    /**
     *
     * @param predictor
     */
    public void saveTour(Predictor predictor){
        int i=0;

       int j=0;
        for(Game g:tournament.getGames()){
               while (predictor.getBet_on_games().get(i).getGame()==null){
                   i++;
               }
               g.bets.get(predictor.getUsername()).setGame(null);
               g.bets.get(predictor.getUsername()).setScore_away_team_bet(predictor.getBet_on_games().get(i).getScore_away_team_bet());
               g.bets.get(predictor.getUsername()).setScore_home_team_bet(predictor.getBet_on_games().get(i).getScore_home_team_bet());


               i++;

        }
        predictor.bet_on_games=new ArrayList<>();
        Tournament.save_tournament(tournament);
        Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, User_Activity.class);
        startActivity(intent);
    }
}