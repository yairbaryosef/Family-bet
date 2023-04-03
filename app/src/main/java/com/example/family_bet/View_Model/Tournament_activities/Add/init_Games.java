package com.example.family_bet.View_Model.Tournament_activities.Add;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.Classes.Enteties.Dealer;
import com.example.family_bet.Classes.Enteties.User;
import com.example.family_bet.Classes.Game.Game;
import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.Classes.Game.team.Team;
import com.example.family_bet.DB.Firestore;
import com.example.family_bet.View_Model.User_Activity.User_Activity;
import com.example.family_bet.databinding.ActivityInitGamesBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

public class init_Games extends AppCompatActivity implements View.OnClickListener {
   Tournament tournament;
   Dealer dealer;
   ActivityInitGamesBinding binding;
   ArrayList<Game> games;
   Games_Adapter games_adapter;
   ArrayList<Team> teams;
   ArrayList<String> teams_names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityInitGamesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String tour_string=getIntent().getStringExtra(constants.tournament);
        String string_dealer=getIntent().getStringExtra(constants.dealer);
        Gson gson=new Gson();
        tournament=gson.fromJson(tour_string,Tournament.class);
        dealer=gson.fromJson(string_dealer,Dealer.class);
        init_listeners();
        init_user();
        teams=new ArrayList<>();
        teams_names=new ArrayList<>();
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference(constants.team);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    Team team = dataSnapshot.getValue(Team.class);
                    teams.add(team);
                    teams_names.add(team.getName());
                    Toast.makeText(init_Games.this, "in", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    /*
    init user
     */
    User user=new User();
    private void init_user() {
        String username=constants.get_Curret_User(this);
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference(constants.user);
        databaseReference.child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user=snapshot.getValue(User.class);
                Toast.makeText(init_Games.this, user.getUsername(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void init_listeners() {
        binding.add.setOnClickListener(this);
        binding.save.setOnClickListener(this);
        games=new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        if(v==binding.add){
            Game game=new Game();
            games.add(game);
            games_adapter=new Games_Adapter(this,games,teams_names,teams);
            binding.list.setAdapter(games_adapter);
        }
        if(v==binding.save){
            if(games.size()>0){

                tournament.setGames(games);
                tournament.setDealer(user.getUsername());
                Tournament.save_tournament(tournament);



                //SAVE USER
                user.setCount_tours(user.getCount_tours()+1);

                user.add_Tournament(Firestore.getPath(tournament.getCountry(),tournament.getDealer(),tournament.getSport_Type(),tournament.getTour_name()));
               DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference(constants.user);
                databaseReference.child(user.getUsername()).setValue(user);
                Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this, User_Activity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "add bets", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(games_adapter!=null) {
            games_adapter.notifyDataSetChanged();
        }
    }


}