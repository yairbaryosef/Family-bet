package com.example.family_bet.Game_Tests;

import com.example.family_bet.Classes.Bets.Bet_On_Game;
import com.example.family_bet.Classes.Enteties.Predictor;
import com.example.family_bet.Classes.Game.Game;
import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.Classes.Game.Tournament_Controller;
import com.example.family_bet.Classes.Game.team.Team;
import com.example.family_bet.View_Model.User_Activity.Predictor_Activity.Profile_Adapter;

import org.junit.Assert;
import org.junit.Test;

public class Tournament_Test {

    @Test
    public void set_Total_Points() {
        Tournament tournament = new Tournament();
        tournament.setScore_for_right_bet(5);
        tournament.setScore_for_bool_bet(10);
        Game game = new Game();
        game.setScore_home_team(100);
        game.setScore_away_team(110);
        tournament.getGames().add(game);
        Game game2 = new Game();
        game2.setScore_home_team(133);
        game2.setScore_away_team(132);
        Team team = new Team();
        team.setName("הפועל חולון");
        game2.setHome_team(team);
        tournament.getGames().add(game2);
        Tournament_Controller tournament_controller = new Tournament_Controller(tournament);
        Predictor predictor = new Predictor();
        predictor.setTotal_points(0);
        Bet_On_Game bet = new Bet_On_Game();
        bet.setIs_changed(false);
        bet.setStatus("");
        bet.setScore_away_team_bet(110);
        bet.setScore_home_team_bet(100);
        bet.setGame(game);
        predictor.getBet_on_games().add(bet);
        Bet_On_Game bet2 = new Bet_On_Game();
        bet2.setIs_changed(false);
        bet2.setStatus("");
        bet2.setScore_away_team_bet(110);
        bet2.setScore_home_team_bet(100);
        bet2.setGame(game2);
        predictor.getBet_on_games().add(bet2);
        tournament.getPredictors().add(predictor);
        tournament_controller.set_Total_Point(predictor);
        predictor = new Predictor();
        predictor.setTotal_points(0);
        bet = new Bet_On_Game();
        bet.setIs_changed(false);
        bet.setStatus("");
        bet.setScore_away_team_bet(110);
        bet.setScore_home_team_bet(100);
        bet.setGame(game);
        predictor.getBet_on_games().add(bet);
        bet2 = new Bet_On_Game();
        bet2.setIs_changed(false);
        bet2.setStatus("");
        bet2.setScore_away_team_bet(134);
        bet2.setScore_home_team_bet(152);
        bet2.setGame(game2);
        predictor.getBet_on_games().add(bet2);
        tournament.getPredictors().add(predictor);
        tournament_controller.set_Total_Point(predictor);
        System.out.println(String.valueOf(tournament.getPredictors().get(0).getTotal_points()));
        System.out.println(tournament.getPredictors().get(0).getBet_on_games().get(0).getStatus());
        Assert.assertEquals(10, tournament.getPredictors().get(0).getTotal_points());
        System.out.println(String.valueOf(tournament.getPredictors().get(1).getTotal_points()));
        System.out.println(tournament.getPredictors().get(1).getBet_on_games().get(1).getStatus());
        Assert.assertEquals(15, tournament.getPredictors().get(1).getTotal_points());
        tournament_controller.sort_Predictors();
        Assert.assertNotEquals(true, tournament.getPredictors().get(0).getTotal_points() < tournament.getPredictors().get(1).getTotal_points());

        for (Predictor pr : tournament.getPredictors()) {
            Profile_Adapter.profile profile = new Profile_Adapter.profile();
            tournament_controller.set_Total_Point(pr);
            profile.name = String.valueOf(String.valueOf(pr.getTotal_points()) + " " + pr.getUsername());

        }

        for (Predictor P:tournament.getPredictors()) {
            System.out.println(P);
        }
    }


}
