package com.example.family_bet.Classes.Game;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.family_bet.Classes.Bets.Bet_On_Game;
import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.Classes.Enteties.Predictor;
import com.example.family_bet.Classes.Sorts.MergeSort;
import com.example.family_bet.HTTP.MyHTTPServer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

public class Tournament_Controller {
    private Tournament tournament;
    public Tournament_Controller(Tournament tournament){
        this.tournament=tournament;
    }

    /**
     *
     * @param predictor
     */
    public void set_Total_Point(Predictor predictor) {

        predictor.setTotal_points(0);
        ArrayList<Bet_On_Game> bets=new ArrayList<>();
        for(Game g:tournament.getGames()){
            Bet_On_Game b=g.bets.get(predictor.getUsername());
            b.setGame(g);
            bets.add(b);
        }
        for (Bet_On_Game bet : bets) {
            try {
                Update_results(predictor,bet);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }

        }
        for(Bet_On_Game bet1:bets){
            bet1.setGame(null);
        }

    }
    /*
    sort Predictors
     */
    public ArrayList<Predictor> sort_Predictors(){
        int[] scores=new int[tournament.getPredictors().size()];
        Hashtable<Integer,ArrayList<Predictor>> hashtable=new Hashtable<>();

        int i=0;
        for(Predictor predictor:tournament.getPredictors()){
            int score=predictor.getTotal_points();

           if(hashtable.containsKey(score)){
               hashtable.get(score).add(predictor);
           }
           else {
               ArrayList<Predictor> predictorArrayList=new ArrayList<>();
               predictorArrayList.add(predictor);
               scores[i]=score;
               hashtable.put(scores[i],predictorArrayList);
           }
            i++;

        }
        MergeSort.sort(scores, 0, scores.length - 1);
        ArrayList<Predictor> sorted_predictors=new ArrayList<>();
        for(int j=0;j<hashtable.size();j++){
            sorted_predictors.addAll(hashtable.get(scores[hashtable.size()-1-j]));
        }

          return sorted_predictors;
    }
    /*
    announce winner
     */
    public Predictor announce_Winner(){
        sort_Predictors();
        return tournament.getPredictors().get(tournament.getPredictors().size()-1);
    }
  /*
  get predictor by username
   */
    public Predictor get_predictor_by_username(String user){
        for (Predictor p:tournament.getPredictors()) {
            if(p.getUsername().equals(user)){
                return p;
            }
        }
        return null;
    }


    /*
  announce champion
     */
    public void announce_Champion(String team){
        tournament.setChampion(team);

    }

   public Tournament_for_saved getTour_For_DATABAE(Context context){
       Tournament_for_saved tour=new Tournament_for_saved();
       tour.setGame(tournament.getGames().get(0));
       tour.setTour_name(tournament.getTour_name());
       tour.setPassword(tournament.getPassword());
       tour.setDealer(tournament.getDealer());
       SharedPreferences sp=context.getSharedPreferences("Counter_Port",0);
       int port=sp.getInt("port",0);
       MyHTTPServer m=new MyHTTPServer(tournament);
       tour.setHost(m.Ip());
       return  tour;
   }

    /**
     * update predictor result
     * @param predictor-predictor
     * @param bet-game bet
     */
   public void Update_results(Predictor predictor,Bet_On_Game bet){
       Game real_game = tournament.get_game(bet.getGame());
       Date date = real_game.getLast_date();
       date.setMinutes(date.getMinutes() - 30);
       Date current = Calendar.getInstance().getTime();
       if (current.getTime() < date.getTime()) {

       } else {


           if ((real_game.getScore_home_team() > real_game.getScore_away_team()) && (bet.getScore_home_team_bet() > bet.getScore_away_team_bet())) {
               System.out.println("home");
               if ((real_game.getScore_home_team() == bet.getScore_home_team_bet()) && real_game.getScore_away_team() == bet.getScore_away_team_bet()) {
                   bet.setStatus(constants.bool_bet);
                   predictor.setTotal_points(predictor.getTotal_points() + tournament.getScore_for_bool_bet());

               } else {
                   bet.setStatus(constants.right_bet);

                   predictor.setTotal_points(predictor.getTotal_points() + tournament.getScore_for_right_bet());

               }
           } else if ((real_game.getScore_home_team() < real_game.getScore_away_team()) && (bet.getScore_home_team_bet() < bet.getScore_away_team_bet())) {
               System.out.println("away");
               if ((real_game.getScore_home_team() == bet.getScore_home_team_bet()) && real_game.getScore_away_team() == bet.getScore_away_team_bet()) {
                   bet.setStatus(constants.bool_bet);
                   predictor.setTotal_points(predictor.getTotal_points() + tournament.getScore_for_bool_bet());

               } else {
                   bet.setStatus(constants.right_bet);
                   predictor.setTotal_points(predictor.getTotal_points() + tournament.getScore_for_right_bet());

               }
           } else if ((real_game.getScore_home_team() == real_game.getScore_away_team()) && (bet.getScore_home_team_bet() == bet.getScore_away_team_bet())) {
               System.out.println("draw");
               if ((real_game.getScore_home_team() == bet.getScore_home_team_bet()) && real_game.getScore_away_team() == bet.getScore_away_team_bet()) {
                   bet.setStatus(constants.bool_bet);
                   predictor.setTotal_points(predictor.getTotal_points() + tournament.getScore_for_bool_bet());

               } else {
                   bet.setStatus(constants.right_bet);
                   predictor.setTotal_points(predictor.getTotal_points() + tournament.getScore_for_right_bet());

               }

           } else {
               bet.setStatus(constants.wrong_bet);
           }
       }
   }
}
