package com.example.family_bet.DB.WINNERLEAGUE;

import com.example.family_bet.Classes.Game.Game;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;

public class WinnerLeagueTest extends TestCase {
       @Test
       public void test(){
              WinnerLeague winnerLeague=new WinnerLeague();
            ArrayList<Game> games=winnerLeague.initWinner();
           System.out.println(games);

       }

}