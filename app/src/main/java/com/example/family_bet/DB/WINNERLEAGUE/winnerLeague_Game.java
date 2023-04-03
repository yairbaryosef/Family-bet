package com.example.family_bet.DB.WINNERLEAGUE;

public class winnerLeague_Game {
    public int id;
    public String ExternalID;
    public int game_type;
    public int GN;
    public int team1;
    public int team2;
    public String team_name_1;
    public String team_name_2;
    public String team_name_eng_1;
    public String team_name_eng_2;
    public String team_icon_1;
    public String team_icon_2;
    public String game_date_txt;
    public int game_year;
    public int score_team1;
    public int score_team2;
    public String game_time;
    public String liveChannel;
    public String pbp_link;
    public int isATC;
    public int isLive;
    public winnerLeague_Game(){

    }
    @Override
    public String toString() {
        return "winnerLeague_Game{" +
                "id=" + id +
                ", ExternalID='" + ExternalID + '\'' +
                ", game_type=" + game_type +
                ", GN=" + GN +
                ", team1=" + team1 +
                ", team2=" + team2 +
                ", team_name_1='" + team_name_1 + '\'' +
                ", team_name_2='" + team_name_2 + '\'' +
                ", team_name_eng_1='" + team_name_eng_1 + '\'' +
                ", team_name_eng_2='" + team_name_eng_2 + '\'' +
                ", team_icon_1='" + team_icon_1 + '\'' +
                ", team_icon_2='" + team_icon_2 + '\'' +
                ", game_date_txt='" + game_date_txt + '\'' +
                ", game_year=" + game_year +
                ", score_team1=" + score_team1 +
                ", score_team2=" + score_team2 +
                ", game_time='" + game_time + '\'' +
                ", liveChannel='" + liveChannel + '\'' +
                ", pbp_link='" + pbp_link + '\'' +
                ", isATC=" + isATC +
                ", isLive=" + isLive +
                '}';
    }


}

