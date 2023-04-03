package com.example.family_bet.DB.NBA;

import java.util.ArrayList;
import java.util.Date;

public class NbaGame {
    public String gameId;
    public String gameCode;
    public int gameStatus;
    public String gameStatusText;
    public int gameSequence;
    public String gameDateEst;
    public String gameTimeEst;
    public String gameDateTimeEst;
    public String gameDateUTC;
    public String gameTimeUTC;
    public String gameDateTimeUTC;
    public String awayTeamTime;
    public String homeTeamTime;
    public String day;
    public int monthNum;
    public int weekNumber;
    public String weekName;
    public boolean ifNecessary;
    public String seriesGameNumber;
    public String seriesText;
    public String arenaName;
    public String arenaState;
    public String arenaCity;
    public String postponedStatus;
    public String branchLink;
    public LeagueSchedule.Broadcasters broadcasters;
    public NBA_SC.Teams homeTeam;
    public NBA_SC.Teams awayTeam;
    public ArrayList<LeagueSchedule.PointsLeaders> pointsLeaders;
    public NbaGame(){

    }
    public NbaGame(String gameId, String gameCode, int gameStatus, String gameStatusText, int gameSequence, String gameDateEst, String gameTimeEst, String gameDateTimeEst, String gameDateUTC, String gameTimeUTC, String gameDateTimeUTC, String awayTeamTime, String homeTeamTime, String day, int monthNum, int weekNumber, String weekName, boolean ifNecessary, String seriesGameNumber, String seriesText, String arenaName, String arenaState, String arenaCity, String postponedStatus, String branchLink, LeagueSchedule.Broadcasters broadcasters, NBA_SC.Teams homeTeam, NBA_SC.Teams awayTeam, ArrayList<LeagueSchedule.PointsLeaders> pointsLeaders) {
        this.gameId = gameId;
        this.gameCode = gameCode;
        this.gameStatus = gameStatus;
    }

    @Override
    public String toString() {
        return "NbaGame{" +
                "gameId='" + gameId + '\'' +
                ", gameCode='" + gameCode + '\'' +
                ", gameStatus=" + gameStatus +
                ", gameStatusText='" + gameStatusText + '\'' +
                ", gameSequence=" + gameSequence +
                ", gameDateEst='" + gameDateEst + '\'' +
                ", gameTimeEst='" + gameTimeEst + '\'' +
                ", gameDateTimeEst='" + gameDateTimeEst + '\'' +
                ", gameDateUTC='" + gameDateUTC + '\'' +
                ", gameTimeUTC='" + gameTimeUTC + '\'' +
                ", gameDateTimeUTC='" + gameDateTimeUTC + '\'' +
                ", awayTeamTime='" + awayTeamTime + '\'' +
                ", homeTeamTime='" + homeTeamTime + '\'' +
                ", day='" + day + '\'' +
                ", monthNum=" + monthNum +
                ", weekNumber=" + weekNumber +
                ", weekName='" + weekName + '\'' +
                ", ifNecessary=" + ifNecessary +
                ", seriesGameNumber='" + seriesGameNumber + '\'' +
                ", seriesText='" + seriesText + '\'' +
                ", arenaName='" + arenaName + '\'' +
                ", arenaState='" + arenaState + '\'' +
                ", arenaCity='" + arenaCity + '\'' +
                ", postponedStatus='" + postponedStatus + '\'' +
                ", branchLink='" + branchLink + '\'' +
                ", broadcasters=" + broadcasters +
                ", homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                ", pointsLeaders=" + pointsLeaders +
                '}';
    }
}

