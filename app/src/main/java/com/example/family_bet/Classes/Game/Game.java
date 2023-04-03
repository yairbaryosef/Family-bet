package com.example.family_bet.Classes.Game;

import com.example.family_bet.Classes.Bets.Bet_On_Game;
import com.example.family_bet.Classes.Game.team.Team;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class Game {
    private String type;
    private Team home_team;
    private Team away_team;
    private int score_home_team;
    private int score_away_team;
    private String winner;
    private int id;
    public HashMap<String, Bet_On_Game> bets;
    public String status;
    private Date last_date;
    private int score_for_right_bet;
    private int score_for_bool_bet;
    public Game(){
       bets=new HashMap<>();
       setScore_home_team(0);
       setScore_away_team(0);
       setLast_date(new Date(0));
    }
    public Game(String type,String home_team,String away_team){

    }

    public Game(String type, Team homeTeam, Team awayTeam, int scoreHomeTeam, int scoreAwayTeam, String winner, int id, HashMap<String, Bet_On_Game> bets, String status, Date lastDate, int scoreForRightBet, int scoreForBoolBet) {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getScore_for_right_bet() {
        return score_for_right_bet;
    }

    public void setScore_for_right_bet(int score_for_right_bet) {
        this.score_for_right_bet = score_for_right_bet;
    }

    public int getScore_for_bool_bet() {
        return score_for_bool_bet;
    }

    public void setScore_for_bool_bet(int score_for_bool_bet) {
        this.score_for_bool_bet = score_for_bool_bet;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setScore_home_team(int score_home_team) {
        this.score_home_team = score_home_team;
    }

    public void setScore_away_team(int score_away_team) {
        this.score_away_team = score_away_team;
    }

    public void setAway_team(Team away_team) {
        this.away_team = away_team;
    }

    public void setHome_team(Team home_team) {
        this.home_team = home_team;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWinner() {
        return winner;
    }

    public Team getHome_team() {
        return home_team;
    }

    public Team getAway_team() {
        return away_team;
    }

    public int getScore_home_team() {
        return score_home_team;
    }

    public int getScore_away_team() {
        return score_away_team;
    }

    public String getType() {
        return type;
    }

    public void setLast_date(Date last_date)
    {
        try {


            this.last_date = last_date;
        }
        catch (Exception e){
               this.last_date=new Date();
        }
    }

    public Date getLast_date() {
        return last_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(home_team, game.home_team) && Objects.equals(away_team, game.away_team) && Objects.equals(last_date, game.last_date);
    }

    public boolean is_equals(String home,String away){
        return Objects.equals(home_team, home) && Objects.equals(away_team, away);

    }


    @Override
    public int hashCode() {
        return Objects.hash(home_team, away_team, last_date);
    }

    @Override
    public String toString() {
        return "Game{" +
                "type='" + type + '\'' +
                ", home_team=" + home_team +
                ", away_team=" + away_team +
                ", score_home_team=" + score_home_team +
                ", score_away_team=" + score_away_team +
                ", winner='" + winner + '\'' +
                ", id=" + id +
                ", status='" + status + '\'' +
                ", last_date=" + last_date +
                ", score_for_right_bet=" + score_for_right_bet +
                ", score_for_bool_bet=" + score_for_bool_bet +
                '}';
    }


}
