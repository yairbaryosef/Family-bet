package com.example.family_bet.Classes.Enteties;

import com.example.family_bet.Classes.Bets.Bet_On_Game;
import com.example.family_bet.Classes.Game.Tournament;

import java.util.ArrayList;
import java.util.Objects;

public class Predictor {
    private int total_points;
    public ArrayList<Bet_On_Game> bet_on_games;
    private Tournament tournament;
    private String champion;
    private String picture;
    /*
    username
     */
    private String username;
    /*
    get username
     */
    public String getUsername() {
        return username;
    }
    /*
    set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    private String password;
    public Predictor(){
      bet_on_games=new ArrayList<>();

    }
    public Predictor(String username,String password){
        this.username=username;
        this.password=password;
        bet_on_games=new ArrayList<>();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTotal_points() {
      return  total_points;
    }


    public void setTotal_points(int total_points) {
        this.total_points = total_points;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public ArrayList<Bet_On_Game> getBet_on_games() {
        return bet_on_games;
    }

    public void setBet_on_games(ArrayList<Bet_On_Game> bet_on_games) {
        this.bet_on_games = bet_on_games;
    }

    public String getChampion() {
        return champion;
    }

    public void setChampion(String champion) {
        this.champion = champion;
    }

    @Override
    public String toString() {
        return "Predictor{" +
                "total_points=" + total_points +
                ", bet_on_games=" + bet_on_games +
                ", tournament=" + tournament +
                ", champion='" + champion + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Predictor predictor = (Predictor) o;
        return total_points == predictor.total_points && bet_on_games.equals(predictor.bet_on_games) && tournament.equals(predictor.tournament) && champion.equals(predictor.champion) && username.equals(predictor.username) && password.equals(predictor.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total_points, bet_on_games, tournament, champion, username, password);
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
