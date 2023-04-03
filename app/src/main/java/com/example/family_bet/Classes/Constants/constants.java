package com.example.family_bet.Classes.Constants;

import android.content.Context;
import android.text.format.DateFormat;

import java.util.Date;

public class constants {
    public static final String right_bet="right bet";
    public static final String bool_bet="bool bet";
    public static final String wrong_bet="wrong bet";
    public static final String draw="draw";
    public static final String team="Team";
    public static final String country="Country";
    public static final String sport="Sport";

    public static final String winnerLeague="Winner League";
    public static final String user="User";
    public static final String table="table";
    public static final String results="My bets";
    public static final String Join_tour="join Tournament";
    public static final String add_tour="add Tournament";
    public static final String tournament="Tournaments";
    public static final String dealer="Dealer";
    public static final String add_team="add team";
    public static final String predictor="Predictor";
    public static String key="Key";
    public static final String winnerleague="WINNER LEAGUE";
    public static final String al_League="Al league";
    public static String count_tours="Count_Tours";

    public static String date_format_by_day(Date startdate){
       return String.valueOf(DateFormat.format("MM-dd-yyyy", startdate)) ;


    }
    public static String date_format_by_time(Date startdate){
        return String.valueOf(DateFormat.format("hh:mm aa", startdate)) ;


    }
    public static String get_Curret_User(Context context){
        return context.getSharedPreferences("User",0).getString("username",null);
    }


    /**
     * 12 top soccer teams and shortcuts
     */
    public static final String champions_League="UEFA Champions League";
    public static final String champions_League_shortcut="CL";

    public static final String portugol_League_1 ="Primeira Liga";
    public static final String portugol_League_1_shortcut="PPL";

    public static final String england_League_1 ="PREMIER LEAGUE";
    public static final String england_League_1_shortcut="PL";

    public static final String germany_League_1 ="Bundesliga";
    public static final String germany_League_1_shortcut="BL1";

    public static final String netherlands_League_1 ="Eredivisie";
    public static final String netherlands_League_1_shortcut="DED";

    public static final String brazil_League_1 ="Campeonato Brasileiro Série A";
    public static final String brazil_League_1_shortcut="BSA";

    public static final String spain_League_1 ="La Liga";
    public static final String spain_League_1_shortcut="PD";

    public static final String france_League_1 ="Ligue 1";
    public static final String france_League_1_shortcut="FL1";

    public static final String england_League_2 ="Championship";
    public static final String england_League_2_shortcut="ELC";

    public static final String euro_League_2 ="European Championship";
    public static final String euro_League_2_shortcut="EC";

    public static final String italy_League_1 ="Serie A";
    public static final String italy_League_1_shortcut="SA";

    public static final String south_america_cup_1 ="Copa Libertadores";
    public static final String south_america_cup_1_shortcut="CLI";
}
