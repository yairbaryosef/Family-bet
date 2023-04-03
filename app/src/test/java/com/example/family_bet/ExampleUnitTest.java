package com.example.family_bet;

import com.example.family_bet.Classes.Game.Game;
import com.example.family_bet.Classes.Game.Tournament;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        String date="Feb 22, 2023 20:30:37";
        try {

            Tournament tournament=new Tournament();
            Game game=new Game();
            game.setLast_date(new Date());
            tournament.getGames().add(game);
            for(Game g: tournament.getGames()) {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");
                    Date date1 = formatter.parse(g.getLast_date().toString());
                    g.setLast_date(date1);

                } catch (Exception e) {

                }
            }
            System.out.println(tournament.getGames().get(0).getLast_date());
        }
        catch (Exception e){

        }
        ;
    }
}