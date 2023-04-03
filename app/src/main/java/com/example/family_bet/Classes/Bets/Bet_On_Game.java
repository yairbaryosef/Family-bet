package com.example.family_bet.Classes.Bets;

import com.example.family_bet.Classes.Enteties.Predictor;
import com.example.family_bet.Classes.Game.Game;

public class Bet_On_Game extends Bet  {
   private Game game;
    private boolean init;
    private int score_home_team_bet;
    private int score_away_team_bet;
    private String status;
   private Predictor predictor;
   private boolean is_changed;
    public Bet_On_Game(){
     status="";
    }
    /*
    getters
     */

    public boolean isIs_changed() {
        return is_changed;
    }

    public String getStatus() {
        return status;
    }

    public Predictor getPredictor() {
        return predictor;
    }

    public int getScore_away_team_bet() {
        return score_away_team_bet;
    }



    public int getScore_home_team_bet() {
        return score_home_team_bet;
    }

    public Game getGame() {
        return game;
    }

    /*
    setters
     */

    public void setStatus(String status) {
        this.status = status;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setScore_away_team_bet(int score_away_team_bet) {
        this.score_away_team_bet = score_away_team_bet;
    }


    public void setScore_home_team_bet(int score_home_team_bet) {
        this.score_home_team_bet = score_home_team_bet;
    }

    public void setIs_changed(boolean is_changed) {
        this.is_changed = is_changed;
    }

    public void setPredictor(Predictor predictor) {
        this.predictor = predictor;
    }


}
