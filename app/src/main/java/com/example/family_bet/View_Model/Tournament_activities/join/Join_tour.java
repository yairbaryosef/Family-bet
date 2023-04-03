package com.example.family_bet.View_Model.Tournament_activities.join;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.family_bet.Classes.Bets.Bet_On_Game;
import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.Classes.Enteties.Predictor;
import com.example.family_bet.Classes.Enteties.User;
import com.example.family_bet.Classes.Game.Game;
import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.DB.Firestore;
import com.example.family_bet.R;
import com.example.family_bet.View_Model.User_Activity.Tournament_Adapter;
import com.example.family_bet.View_Model.User_Activity.User_Activity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class Join_tour extends AppCompatActivity {
    ListView list;
    ArrayList<Tournament> tournaments;
    ArrayList<Tournament> tournaments_search;
    String country,username,sport,tournament1;
    User user=new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {



            setContentView(R.layout.activity_join_tour);
            tournaments = new ArrayList<>();
            country = getIntent().getStringExtra(constants.country);
            sport = getIntent().getStringExtra(constants.sport);
            username = getIntent().getStringExtra(constants.user);
            tournament1 = getIntent().getStringExtra(constants.tournament);
            tournaments_search = new ArrayList<>();
            init_user();
            init_tournaments();
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * init user
     */
    private void init_user() {
        String username= constants.get_Curret_User(this);
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference(constants.user);
        databaseReference.child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user=snapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /**
     * init
     */
    private void init_tournaments() {
        tour=new Tournament();

        DocumentReference tournamentsRef = Firestore.init_DocRef(country,username,sport,tournament1);
        tournamentsRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                try {


                    Tournament t = Firestore.getTour(documentSnapshot);
                    tournaments.add(t);
                   initTours();
                }catch (Exception e){
                    }
            }
        });
    }
    Tournament tour;
    Tournament tournament;

    /**
     * validation for entering the tournament
     * @param password-password for validation process
     */
    private void check_password(String password) {
        Dialog d=new Dialog(this);
        d.setContentView(R.layout.check_password);
        TextInputEditText pass=d.findViewById(R.id.password);
        TextView save=d.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==save){
                    if(pass.getText().toString().equals(password)){
                     try {


                         user.add_Tournament(Firestore.getPath(tournament.getCountry(),tournament.getDealer(),tournament.getSport_Type(),tournament.getTour_name()));
                         Predictor predictor = new Predictor();
                         predictor.setTotal_points(0);
                         predictor.setUsername(user.getUsername());
                         for (Game g : tournament.getGames()) {
                             Bet_On_Game b = new Bet_On_Game();

                             b.setIs_changed(true);

                             b.setScore_home_team_bet(0);
                             b.setScore_away_team_bet(0);
                             g.bets.put(user.getUsername(), b);
                         }

                         predictor.setPicture(user.getProfile());
                         tournament.add_prodictor(predictor);

                         Tournament.save_tournament(tournament);


                         //SAVE USER
                         User.Save_user(user);

                         //SAVE TOURNAMENT
                         //Tournament_for_saved tournament_for_saved=tournament_controller.getTour_For_DATABAE(Join_tour.this);


                         //MOVE TO USER ACTIVITY
                         Toast.makeText(Join_tour.this, "welcome!", Toast.LENGTH_SHORT).show();
                         d.dismiss();
                         Intent intent = new Intent(Join_tour.this, User_Activity.class);
                         startActivity(intent);
                     }
                     catch (Exception e){
                         Toast.makeText(Join_tour.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                     }
                    }
                }
            }
        });
        d.show();
    }

    /**
     * init all tours
     */
    public void initTours(){
        tournaments_search=new ArrayList<>(tournaments);
        Tournament_Adapter tournament_adapter=new Tournament_Adapter(Join_tour.this,tournaments_search);
        list=findViewById(R.id.list);
        list.setAdapter(tournament_adapter);

      list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                tournament=tournaments_search.get(position);
                if(!user.getTournaments_id().contains(tournament.getTour_name())) {
                    check_password(tournament.getPassword());
                }
                else{
                    Toast.makeText(Join_tour.this, "you are already on this tournament", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}