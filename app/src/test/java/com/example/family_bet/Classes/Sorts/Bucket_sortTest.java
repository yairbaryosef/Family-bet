package com.example.family_bet.Classes.Sorts;

import com.example.family_bet.Classes.Game.league.league;
import com.example.family_bet.Classes.Game.league.league_Controller;
import com.example.family_bet.Classes.Game.team.Team;

import junit.framework.TestCase;

import org.junit.Assert;

public class Bucket_sortTest extends TestCase {

    public void testBuckets_sort() {
        league l=new league();
        l.setTable(new league.points[4]);
        for(int i=0;i<4;i++){
            league.points p=new league.points();
            p.Total_points=i;
            p.team=new Team();
            p.team.setName("team "+String.valueOf(i));
            l.getTable()[i]=p;

        }
         l.getTable()[0].Total_points=27;
        l.getTable()[1].Total_points=16;
        l.getTable()[2].Total_points=20;
        l.getTable()[3].Total_points=12;
        league_Controller league_controller=new league_Controller(l);

        Bucket_sort.Buckets_sort(l,l.getTable(),league_controller.calculate_max_points());
        for(int j=0;j<l.getTable().length;j++){
            System.out.println(l.getTable()[j]);
        }
        Assert.assertEquals(l.get_team_by_name.get("team "+String.valueOf(0)),new Integer(3));
        Assert.assertEquals("team "+String.valueOf(0),l.getTeams().get(3));
    }
}