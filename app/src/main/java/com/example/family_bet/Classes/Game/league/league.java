package com.example.family_bet.Classes.Game.league;

import android.graphics.Point;

import com.example.family_bet.Classes.Game.team.Team;

import java.util.ArrayList;
import java.util.HashMap;

public class league {
    public static class points{
        public int Total_points;
        public Team team;
        public int wins;
        public int draws;
        public points(){

        }

        @Override
        public String toString() {
            return "points{" +
                    "Total_points=" + Total_points +
                    ", team=" + team +
                    ", wins=" + wins +
                    ", draws=" + draws +
                    '}';
        }
    }
    private String champion;
    private points[] table;
    public HashMap<Integer,String> teams;
    public HashMap<String,Integer> get_team_by_name;
    private String dealer;
    public int points_per_win;
    public int points_per_draw;

    public league(){
        teams=new HashMap<>();
        points_per_win=3;
        points_per_draw=1;
        get_team_by_name=new HashMap<>();
    }

    public points[] getTable() {
        return table;
    }

    public void setTable(points[] table) {
        this.table = table;
    }

    public void setChampion(String champion) {
        this.champion = champion;
    }

    public void setTeams(HashMap<Integer, String> teams) {
        this.teams = teams;
    }

    public HashMap<Integer, String> getTeams() {
        return teams;
    }

    public String getChampion() {
        return champion;
    }

    public String getDealer() {
        return dealer;
    }

    public HashMap<String, Integer> getGet_team_by_name() {
        return get_team_by_name;
    }

    public void setGet_team_by_name(HashMap<String, Integer> get_team_by_name) {
        this.get_team_by_name = get_team_by_name;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }
}
