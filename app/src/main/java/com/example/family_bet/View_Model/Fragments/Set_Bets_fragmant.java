package com.example.family_bet.View_Model.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.family_bet.Classes.Bets.Bet_On_Game;
import com.example.family_bet.Classes.Enteties.Predictor;
import com.example.family_bet.Classes.Game.Game;
import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Set_Bets_fragmant extends Fragment {
    private Tournament tournament=new Tournament();
    private Predictor predictor=new Predictor();
    public Set_Bets_fragmant(Tournament tournament,Predictor predictor){
        this.tournament=tournament;
        this.predictor=predictor;
    }

    View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.table, container, false);
        ListView listView = v.findViewById(R.id.list);


       // FloatingActionButton save = v.findViewById(R.id.save);


        //init all bets
        ArrayList<Bet_On_Game> bets = new ArrayList<>();
        int i=1;
        for (Game game : tournament.getGames()) {
       try {
             if(i%4==0){
                 Bet_On_Game bet=new Bet_On_Game();
                 bet.setStatus("AD");
                 bets.add(bet);
             }

                 Bet_On_Game b = game.bets.get(predictor.getUsername());
                 Date date = game.getLast_date();
                 date.setMinutes(date.getMinutes() - 30);
                 Date current = Calendar.getInstance().getTime();
                 if (current.getTime() < date.getTime()) {
                     b.setIs_changed(true);
                 } else {
                     b.setIs_changed(false);
                 }
                 date.setMinutes(date.getMinutes() + 30);
                 b.setGame(game);
                 bets.add(b);

             i++;
       }

       catch (Exception e){

       }
        }


    predictor.setBet_on_games(bets);
       Bets_Adapter bets_adapter=new Bets_Adapter(getContext(), predictor.getBet_on_games(),tournament);
        listView.setAdapter(bets_adapter);

        return v;
    }
    public class Save extends AsyncTask<Tournament,Tournament,String>{

        @Override
        protected String doInBackground(Tournament... strings) {

            return "";
        }
    }
}

