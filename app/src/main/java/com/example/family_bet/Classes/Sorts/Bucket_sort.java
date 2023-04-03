package com.example.family_bet.Classes.Sorts;

import com.example.family_bet.Classes.Game.league.league;
import com.example.family_bet.Classes.Game.league.league_Controller;

import java.util.ArrayList;
import java.util.HashMap;

public class Bucket_sort {
    public static void Buckets_sort(league l,league.points[] array, int capacity){
        ArrayList<ArrayList<league.points>> update_table=new ArrayList<>();
        for(int i=0;i<capacity+1;i++){
            update_table.add(new ArrayList<>());
        }
        l.get_team_by_name=new HashMap<>();
        l.teams=new HashMap<>();
        for(int i=0;i<array.length;i++){
            update_table.get(array[i].Total_points).add(array[i]);

        }
        l.setTable(new league.points[array.length]);
        league.points[] league_teable=l.getTable();
        int position=0;
        for(int i=0;i<update_table.size();i++){
            if(!update_table.get(i).isEmpty()){
                for(league.points p:update_table.get(i)){
                    league_teable[position]=p;
                    l.get_team_by_name.put(p.team.getName(),position);
                    l.teams.put(position,p.team.getName());
                    position++;
                }
            }
        }
    }

    public static void main(String[] args) {
        league l=new league();
        l.setTable(new league.points[3]);
        for(int i=0;i<3;i++){
            league.points p=new league.points();
            p.Total_points=25;
            p.team.setName("team "+String.valueOf(i));
            l.getTable()[i]=p;
        }

        league_Controller league_controller=new league_Controller(l);
        Buckets_sort(l,l.getTable(),league_controller.calculate_max_points());
       System.out.println(l.getTable());
    }
}
