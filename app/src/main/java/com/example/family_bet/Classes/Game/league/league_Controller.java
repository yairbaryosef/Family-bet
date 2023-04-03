package com.example.family_bet.Classes.Game.league;

import com.example.family_bet.Classes.Game.Game;
import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.Classes.Game.team.Team;
import com.example.family_bet.Classes.Sorts.Bucket_sort;

import java.util.ArrayList;
import java.util.HashMap;

public class league_Controller {
    private league league;
    ArrayList<Team> teams;
    public league_Controller(league l){
        league=l;
    }
    public void init_league(Tournament tournament){
        HashMap<String,Team> teamHashMap=new HashMap<>();
        for(Game g:tournament.getGames()){
            Team team=g.getAway_team();
           teamHashMap.put(team.getName(),team);
           team=g.getHome_team();
           teamHashMap.put(team.getName(),team);
        }
        ArrayList<Team> teams=new ArrayList<>();
        this.teams=teams;
        for(Team t:teamHashMap.values()){
        teams.add(t);
        }
        init_league_teams(teams);

    }
    /**
     * init the whole teams in the league
     * @param teams-list of all the teams in the league
     */
    public void init_league_teams(ArrayList<Team> teams){
        league.setTable(new league.points[teams.size()]);
        int i=0;
        for(Team t:teams){
            league.points p=new league.points();
            p.wins=0;
            p.draws=0;
            p.Total_points=0;
            p.team=t;
            league.getTable()[i]=p;
            league.getTeams().put(i,t.getName());
            i++;
        }
    }

    /**
     * init previous matches
     * @param team-team name
     * @param wins-team previous wins
     * @param draws-team previous draws
     */
    public void init_league_previous_games(String team,int wins,int draws){
        int team_position=league.getGet_team_by_name().get(team);
        league.points p=league.getTable()[team_position];
        p.draws=draws;
        p.wins=wins;
        p.Total_points=wins*league.points_per_win+draws* league.points_per_draw;

    }

    /**
     * sort teams by points
     */
    public void sort_teams_by_points(){
        int calculate_max_points=calculate_max_points();
        Bucket_sort.Buckets_sort(league,league.getTable(),calculate_max_points);
    }
    public int calculate_max_points(){
        int max_points=0;
        for(int i=0;i< league.getTable().length;i++){
            if(league.getTable()[i].Total_points>max_points){
                max_points=league.getTable()[i].Total_points;
            }
        }
        return max_points;
    }


}
