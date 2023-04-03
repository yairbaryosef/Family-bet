package com.example.family_bet.Classes.Bets;

import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.Classes.Game.Tournament;

public class Bet_On_Game_Controller {
    private Bet_On_Game bet;
    private Tournament tournament;

    public Bet_On_Game_Controller(Bet_On_Game bet, Tournament tournament) {
        this.bet = bet;
        this.tournament = tournament;
    }

    /*
    add the points to the predictor if he has a wright bet
     */
    public int calculate_Points_from_bet() {
        int total = 0;
        if (bet.getStatus().equals(constants.right_bet)) {
            total = bet.getPredictor().getTotal_points() + tournament.getScore_for_right_bet();
            bet.getPredictor().setTotal_points(total);

        } else if (bet.getStatus().equals(constants.bool_bet)) {
            total = bet.getPredictor().getTotal_points() + tournament.getScore_for_bool_bet();
            bet.getPredictor().setTotal_points(total);

        }

        return total;
    }

    /*
    change status of bet
     */
    public void change_Status() {
        if (bet.getGame().getScore_home_team() > bet.getGame().getScore_away_team() && bet.getScore_away_team_bet() < bet.getScore_home_team_bet()) {
            if (bet.getGame().getScore_away_team() == bet.getScore_away_team_bet() && bet.getGame().getScore_home_team() == bet.getScore_home_team_bet())
                bet.setStatus(constants.bool_bet);
            else {
                bet.setStatus(constants.right_bet);
            }
        }
        else if (bet.getGame().getScore_home_team() < bet.getGame().getScore_away_team() && bet.getScore_away_team_bet() > bet.getScore_home_team_bet()) {
            if (bet.getGame().getScore_away_team() == bet.getScore_away_team_bet() && bet.getGame().getScore_home_team() == bet.getScore_home_team_bet())
                bet.setStatus(constants.bool_bet);
            else {
                bet.setStatus(constants.right_bet);
            }
        }
        else if (bet.getGame().getScore_home_team() == bet.getGame().getScore_away_team() && bet.getScore_away_team_bet() == bet.getScore_home_team_bet()) {
            if (bet.getGame().getScore_away_team() == bet.getScore_away_team_bet() && bet.getGame().getScore_home_team() == bet.getScore_home_team_bet())
                bet.setStatus(constants.bool_bet);
            else {
                bet.setStatus(constants.right_bet);
            }

        }
        else
            bet.setStatus(constants.wrong_bet);

    }

}
