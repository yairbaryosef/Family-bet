package com.example.family_bet.Classes.Enteties;

import com.example.family_bet.Classes.Game.Tournament;

public class Dealer  {
    /*
    variables
     */
    private String username;
    private String password;
    private String tournament_id;
    private String tournament;
    /*
    constructors
     */
    public Dealer(){


    }
    public Dealer(String username,String password){
        this.username=username;
        this.password=password;
    }
    /*
    getters and setters
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(String tournament_id) {
        this.tournament_id = tournament_id;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }
}
