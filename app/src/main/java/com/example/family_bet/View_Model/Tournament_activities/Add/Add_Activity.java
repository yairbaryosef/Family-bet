package com.example.family_bet.View_Model.Tournament_activities.Add;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.Classes.Enteties.Dealer;
import com.example.family_bet.Classes.Enteties.User;
import com.example.family_bet.Classes.Game.Game;
import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.DB.Firestore;
import com.example.family_bet.DB.Soccer.AL_LEAGUE;
import com.example.family_bet.DB.Soccer.TOPS_SOCCER;
import com.example.family_bet.DB.WINNERLEAGUE.WinnerLeague;
import com.example.family_bet.View_Model.User_Activity.Predictor_Activity.Profile_Adapter;
import com.example.family_bet.View_Model.User_Activity.User_Activity;
import com.example.family_bet.databinding.ActivityAddBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Add_Activity extends AppCompatActivity implements View.OnClickListener {
    ActivityAddBinding activityAddBinding;
   User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      activityAddBinding=ActivityAddBinding.inflate(getLayoutInflater());
      setContentView(activityAddBinding.getRoot());
      user=new User();
      String u=getIntent().getStringExtra(constants.user);
      Gson gson=new Gson();
      user=gson.fromJson(u,User.class);
      init_Listeners();
        init_leagues();
        init_sports();

    }
    Profile_Adapter.profile profile,sport;
    /**
     * init all leagues to add a tournament based on exist league
     */
    private void init_leagues() {
        profile=new Profile_Adapter.profile("","else");
        ArrayList<Profile_Adapter.profile> profiles=new ArrayList<>();
        profiles.add(profile);
        profiles.add(new Profile_Adapter.profile("WINNER LEAGUE",constants.winnerLeague));
        profiles.add(new Profile_Adapter.profile(constants.al_League,constants.al_League));
        initallLeagues(profiles);
        Profile_Adapter profile_adapter=new Profile_Adapter(this,profiles);
        activityAddBinding.league.setAdapter(profile_adapter);
        activityAddBinding.league.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                profile=profiles.get(position);
                Toast.makeText(Add_Activity.this, profile.name, Toast.LENGTH_SHORT).show();

            }


        });
    }

    private void initallLeagues(ArrayList<Profile_Adapter.profile> profiles) {
        profiles.add(new Profile_Adapter.profile("https://crests.football-data.org/CL.png",constants.champions_League));
        profiles.add(new Profile_Adapter.profile("https://crests.football-data.org/"+constants.brazil_League_1_shortcut+".png",constants.brazil_League_1_shortcut));
        profiles.add(new Profile_Adapter.profile("https://crests.football-data.org/"+constants.england_League_1_shortcut+".png",constants.england_League_1_shortcut));
        profiles.add(new Profile_Adapter.profile("https://crests.football-data.org/"+constants.england_League_2_shortcut+".png",constants.england_League_2_shortcut));
        profiles.add(new Profile_Adapter.profile("https://crests.football-data.org/"+constants.germany_League_1_shortcut+".png",constants.germany_League_1_shortcut));
        profiles.add(new Profile_Adapter.profile("https://crests.football-data.org/"+constants.france_League_1_shortcut+".png",constants.france_League_1_shortcut));
        profiles.add(new Profile_Adapter.profile("https://crests.football-data.org/"+constants.south_america_cup_1_shortcut+".png",constants.south_america_cup_1_shortcut));
        profiles.add(new Profile_Adapter.profile("https://crests.football-data.org/"+constants.spain_League_1_shortcut+".png",constants.spain_League_1_shortcut));
        profiles.add(new Profile_Adapter.profile("https://crests.football-data.org/"+constants.euro_League_2_shortcut+".png",constants.euro_League_2_shortcut));
        profiles.add(new Profile_Adapter.profile("https://crests.football-data.org/"+constants.netherlands_League_1_shortcut+".png",constants.netherlands_League_1_shortcut));
        profiles.add(new Profile_Adapter.profile("https://crests.football-data.org/"+constants.italy_League_1_shortcut+".png",constants.italy_League_1_shortcut));
        profiles.add(new Profile_Adapter.profile("https://crests.football-data.org/"+constants.portugol_League_1_shortcut+".png",constants.portugol_League_1_shortcut));
    }

    private void init_sports() {
        sport=new Profile_Adapter.profile("","else");
        ArrayList<Profile_Adapter.profile> profiles=new ArrayList<>();
        profiles.add(sport);
        profiles.add(new Profile_Adapter.profile("R.drawable.basketball","Basketball"));
        profiles.add(new Profile_Adapter.profile("R.drawable.soccer_ball","Soccer"));
        Profile_Adapter profile_adapter=new Profile_Adapter(this,profiles);
        activityAddBinding.sport.setAdapter(profile_adapter);
        activityAddBinding.sport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sport=profiles.get(position);
                Toast.makeText(Add_Activity.this, sport.name, Toast.LENGTH_SHORT).show();

            }


        });
    }

    private void init_Listeners() {
        activityAddBinding.add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==activityAddBinding.add){
            if(check_if_there_is_empty_field()){
               createTournament();
            }
        }
    }
    public void createTournament(){
        Tournament tournament=new Tournament();
        tournament.setSport_Type(sport.name);
        tournament.setTour_name(activityAddBinding.tourName.getText().toString());
        tournament.setPassword(activityAddBinding.password.getText().toString());
        tournament.setScore_for_bool_bet(Integer.valueOf(activityAddBinding.boolBet.getText().toString()));
        tournament.setScore_for_right_bet(Integer.valueOf(activityAddBinding.rightBet.getText().toString()));
        Dealer dealer=new Dealer();

        dealer.setTournament(tournament.getTour_name());
        SharedPreferences sharedPreferences=getSharedPreferences(constants.user,0);
        String username=sharedPreferences.getString("username",null);
        dealer.setUsername(username);


            tournament.setDealer(dealer.getUsername());
            progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("Loading");
            progressDialog.show();
             new LoadGamesTask().execute(tournament);





    }
    ProgressDialog progressDialog;
    public static Object lock;
    public static boolean isLock;
    private class LoadGamesTask extends AsyncTask<Tournament, Tournament, ArrayList<Game>> {

        @Override
        protected ArrayList<Game> doInBackground(Tournament ...tournament) {
            ArrayList<Game> games=new ArrayList<>();

            if(profile.name.equals(constants.winnerLeague)) {
               games = new WinnerLeague().initWinner();
                tournament[0].type=constants.winnerLeague;
                tournament[0].setGames(games);
            }
            else if(profile.name.equals(constants.al_League)){
                games= AL_LEAGUE.initAl_League();
                tournament[0].type=constants.al_League;
                tournament[0].setGames(games);
            }
            else{
                TOPS_SOCCER.initLeague_games(profile.name,tournament[0]);
            }


            CHECK_TOURNAMENT(tournament[0]);

             return games;
        }
    }


                /**
                 * check if tournament exist.
                 * if exist do nothing
                 * else keep init tournament
                 * @param tournament-the tournament on process

                 */
    private void CHECK_TOURNAMENT(Tournament tournament) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection(constants.tournament).document(tournament.getSport_Type()).collection(tournament.getDealer()).document(tournament.getTour_name());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (!documentSnapshot.exists()) {
                    // The document does not exist
                    initTour(tournament);
                } else {
                    // The document exists
                    Toast.makeText(Add_Activity.this, "Tournament is already exist", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }




    /**
     * check if there is empty field
     * @return if exist return false
     */
    private boolean check_if_there_is_empty_field() {

        if(activityAddBinding.boolBet.getText().length()<=0)
            return false;
        if(activityAddBinding.password.getText().length()<=0)
            return false;
        if(activityAddBinding.rightBet.getText().length()<=0)
            return false;
       if(activityAddBinding.tourName.getText().length()<=0)
           return  false;
       return true;
    }

    /**
     * do if tour name is not exist
     * @param tournament-tournament for save
     */
    public void initTour(Tournament tournament){
        if(profile.name.equals("else")) {
            Gson gson = new Gson();
            String tour_json = gson.toJson(tournament);

            Intent intent = new Intent(Add_Activity.this, init_Games.class);
            intent.putExtra(constants.tournament, tour_json);

            startActivity(intent);
        }
        else{
            user.add_Tournament(Firestore.getPath(tournament.getCountry(),tournament.getDealer(),tournament.getSport_Type(),tournament.getTour_name()));


            user.setCount_tours(user.getCount_tours()+1);
            User.Save_user(user);

            Tournament.save_tournament(tournament);

             progressDialog.dismiss();
            Toast.makeText(Add_Activity.this, "tour saved", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(Add_Activity.this, User_Activity.class);
            startActivity(intent);
        }

    }

}