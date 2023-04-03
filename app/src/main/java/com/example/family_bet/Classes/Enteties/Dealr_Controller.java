package com.example.family_bet.Classes.Enteties;

import com.example.family_bet.Classes.Game.Game;
import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.Classes.Constants.constants;

public class Dealr_Controller {
    public Dealer dealer;
    public Dealr_Controller(Dealer dealer){this.dealer=dealer;}
    /*
    add game
     */
    public void add_game(Game game){
      //  dealer.getTournament().add_game(game);
    }
  /*  public void set_Score(int home,int away,String home_name,String away_name,int id){
        for(Game game: dealer.getTournament().getGames()){
            if(game.getId()==id){
                game.setScore_home_team(home);
                game.setScore_away_team(away);
                if(home>away){
                    game.setWinner(home_name);
                }
                else if(away>home){
                    game.setWinner(away_name);
                }
                else{
                    game.setWinner(constants.draw);
                }
                break;
            }

        }
    }*/

}
