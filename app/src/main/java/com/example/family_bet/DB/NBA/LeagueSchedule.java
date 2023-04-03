package com.example.family_bet.DB.NBA;

import java.util.ArrayList;
import com.example.family_bet.DB.NBA.NBA_SC;

public class LeagueSchedule {
    private String seasonYear;
    private String leagueId;
    private ArrayList<GameDates> gameDates;
    private ArrayList<Weeks> weeks;
    private ArrayList<NBA_SC.BroadcasterList> broadcasterList;
    public LeagueSchedule(){
        gameDates=new ArrayList<>();
        weeks=new ArrayList<>();
        broadcasterList=new ArrayList<>();
    }
    public String getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(String seasonYear) {
        this.seasonYear = seasonYear;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public ArrayList<GameDates> getGameDates() {
        return gameDates;
    }

    public void setGameDates(ArrayList<GameDates> gameDates) {
        this.gameDates = gameDates;
    }

    public ArrayList<Weeks> getWeeks() {
        return weeks;
    }

    public void setWeeks(ArrayList<Weeks> weeks) {
        this.weeks = weeks;
    }

    public ArrayList<NBA_SC.BroadcasterList> getBroadcasterList() {
        return broadcasterList;
    }

    public void setBroadcasterList(ArrayList<NBA_SC.BroadcasterList> broadcasterList) {
        this.broadcasterList = broadcasterList;
    }

    public class GameDates {
        private String gameDate;
        public ArrayList<NBAGames> games;

        public String getGameDate() {
            return gameDate;
        }

        public void setGameDate(String gameDate) {
            this.gameDate = gameDate;
        }
        public GameDates(){
            games=new ArrayList<>();
        }

        public ArrayList<NBAGames> getGames() {
            return games;
        }

        public void setGames(ArrayList<NBAGames> games) {
            this.games = games;
        }

        public class NBAGames {
            public void setArenaCity(String arenaCity) {
                this.arenaCity = arenaCity;
            }

            public void setArenaName(String arenaName) {
                this.arenaName = arenaName;
            }

            public void setArenaState(String arenaState) {
                this.arenaState = arenaState;
            }

            public void setAwayTeam(AwayTeam awayTeam) {
                this.awayTeam = awayTeam;
            }

            public void setAwayTeamTime(String awayTeamTime) {
                this.awayTeamTime = awayTeamTime;
            }

            public void setBranchLink(String branchLink) {
                this.branchLink = branchLink;
            }

            public void setBroadcasters(Broadcasters broadcasters) {
                this.broadcasters = broadcasters;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public void setGameDateEst(String gameDateEst) {
                this.gameDateEst = gameDateEst;
            }

            public void setGameDateTimeEst(String gameDateTimeEst) {
                this.gameDateTimeEst = gameDateTimeEst;
            }

            public void setGameDateTimeUTC(String gameDateTimeUTC) {
                this.gameDateTimeUTC = gameDateTimeUTC;
            }

            public void setGameDateUTC(String gameDateUTC) {
                this.gameDateUTC = gameDateUTC;
            }

            public void setGameSequence(int gameSequence) {
                this.gameSequence = gameSequence;
            }

            public void setGameStatus(int gameStatus) {
                this.gameStatus = gameStatus;
            }

            public void setGameStatusText(String gameStatusText) {
                this.gameStatusText = gameStatusText;
            }

            public void setGameTimeEst(String gameTimeEst) {
                this.gameTimeEst = gameTimeEst;
            }

            public void setGameTimeUTC(String gameTimeUTC) {
                this.gameTimeUTC = gameTimeUTC;
            }

            public void setHomeTeam(HomeTeam homeTeam) {
                this.homeTeam = homeTeam;
            }

            public void setHomeTeamTime(String homeTeamTime) {
                this.homeTeamTime = homeTeamTime;
            }

            public void setIfNecessary(boolean ifNecessary) {
                this.ifNecessary = ifNecessary;
            }

            public void setMonthNum(int monthNum) {
                this.monthNum = monthNum;
            }

            public void setWeekNumber(int weekNumber) {
                this.weekNumber = weekNumber;
            }

            public void setPointsLeaders(ArrayList<PointsLeaders> pointsLeaders) {
                this.pointsLeaders = pointsLeaders;
            }

            public void setPostponedStatus(String postponedStatus) {
                this.postponedStatus = postponedStatus;
            }

            public void setSeriesGameNumber(String seriesGameNumber) {
                this.seriesGameNumber = seriesGameNumber;
            }

            public void setSeriesText(String seriesText) {
                this.seriesText = seriesText;
            }

            public void setWeekName(String weekName) {
                this.weekName = weekName;
            }

            public int getGameSequence() {
                return gameSequence;
            }

            public String getSeriesGameNumber() {
                return seriesGameNumber;
            }

            public String getSeriesText() {
                return seriesText;
            }

            public String getGameDateEst() {
                return gameDateEst;
            }

            public String getGameTimeEst() {
                return gameTimeEst;
            }

            public String getGameDateTimeEst() {
                return gameDateTimeEst;
            }

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
            public Broadcasters broadcasters;
            public HomeTeam homeTeam;
            public AwayTeam awayTeam;
            public ArrayList<PointsLeaders> pointsLeaders;
            public NBAGames(){
                pointsLeaders=new ArrayList<>();
            }
            public String getGameId() {
                return gameId;
            }

            public void setGameId(String gameId) {
                this.gameId = gameId;
            }

            public String getGameCode() {
                return gameCode;
            }

            public void setGameCode(String gameCode) {
                this.gameCode = gameCode;
            }
        }
    }
    public class HomeTeam {
        private int teamId;
        private String teamName;
        private String teamCity;
        private String teamTricode;
        private String teamSlug;
        private int wins;
        private int losses;
        private int score;
        private int seed;

        public int getTeamId() {
            return teamId;
        }

        public void setTeamId(int teamId) {
            this.teamId = teamId;
        }

        public String getTeamName() {
            return teamName;
        }

        public void setTeamName(String teamName) {
            this.teamName = teamName;
        }

        public String getTeamCity() {
            return teamCity;
        }

        public void setTeamCity(String teamCity) {
            this.teamCity = teamCity;
        }

        public String getTeamTricode() {
            return teamTricode;
        }

        public void setTeamTricode(String teamTricode) {
            this.teamTricode = teamTricode;
        }

        public String getTeamSlug() {
            return teamSlug;
        }

        public void setTeamSlug(String teamSlug) {
            this.teamSlug = teamSlug;
        }

        public int getWins() {
            return wins;
        }

        public void setWins(int wins) {
            this.wins = wins;
        }

        public int getLosses() {
            return losses;
        }

        public void setLosses(int losses) {
            this.losses = losses;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getSeed() {
            return seed;
        }

        public void setSeed(int seed) {
            this.seed=seed;
        }

        @Override
        public String toString() {
            return "HomeTeam{" +
                    "teamId=" + teamId +
                    ", teamName='" + teamName + '\'' +
                    ", teamCity='" + teamCity + '\'' +
                    ", teamTricode='" + teamTricode + '\'' +
                    ", teamSlug='" + teamSlug + '\'' +
                    ", wins=" + wins +
                    ", losses=" + losses +
                    ", score=" + score +
                    ", seed=" + seed +
                    '}';
        }
    }
    public class AwayTeam {
        private int teamId;
        private String teamName;
        private String teamCity;
        private String teamTricode;
        private String teamSlug;
        private int wins;
        private int losses;
        private int score;
        private int seed;

        public int getTeamId() {
            return teamId;
        }

        public void setTeamId(int teamId) {
            this.teamId = teamId;
        }

        public String getTeamName() {
            return teamName;
        }

        public void setTeamName(String teamName) {
            this.teamName = teamName;
        }

        public String getTeamCity() {
            return teamCity;
        }

        public void setTeamCity(String teamCity) {
            this.teamCity = teamCity;
        }

        public String getTeamTricode() {
            return teamTricode;
        }

        public void setTeamTricode(String teamTricode) {
            this.teamTricode = teamTricode;
        }

        public String getTeamSlug() {
            return teamSlug;
        }

        public void setTeamSlug(String teamSlug) {
            this.teamSlug = teamSlug;
        }

        public int getWins() {
            return wins;
        }

        public void setWins(int wins) {
            this.wins = wins;
        }

        public int getLosses() {
            return losses;
        }

        public void setLosses(int losses) {
            this.losses = losses;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getSeed() {
            return seed;
        }

        public void setSeed(int seed) {
            this.seed=seed;
        }

        @Override
        public String toString() {
            return "HomeTeam{" +
                    "teamId=" + teamId +
                    ", teamName='" + teamName + '\'' +
                    ", teamCity='" + teamCity + '\'' +
                    ", teamTricode='" + teamTricode + '\'' +
                    ", teamSlug='" + teamSlug + '\'' +
                    ", wins=" + wins +
                    ", losses=" + losses +
                    ", score=" + score +
                    ", seed=" + seed +
                    '}';
        }
    }
    public class PointsLeaders {
        private int personId;
        private String firstName;
        private String lastName;
        private int teamId;
        private String teamCity;
        private String teamName;
        private String teamTricode;
        private double points;

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setPersonId(int personId) {
            this.personId = personId;
        }

        public void setPoints(double points) {
            this.points = points;
        }

        public void setTeamCity(String teamCity) {
            this.teamCity = teamCity;
        }

        public void setTeamId(int teamId) {
            this.teamId = teamId;
        }

        public void setTeamName(String teamName) {
            this.teamName = teamName;
        }

        public void setTeamTricode(String teamTricode) {
            this.teamTricode = teamTricode;
        }

        @Override
        public String toString() {
            return "PointsLeader{" +
                    "personId=" + personId +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", teamId=" + teamId +
                    ", teamCity='" + teamCity + '\'' +
                    ", teamName='" + teamName + '\'' +
                    ", teamTricode='" + teamTricode + '\'' +
                    ", points=" + points +
                    '}';
        }
    }
    public class Broadcasters {
        public NBA_SC.BroadcasterList nationalTvBroadcasters;
        public NBA_SC.BroadcasterList nationalRadioBroadcasters;
        public NBA_SC.BroadcasterList nationalOttBroadcasters;
        public NBA_SC.BroadcasterList homeTvBroadcasters;
        public NBA_SC.BroadcasterList homeRadioBroadcasters ;
        public NBA_SC.BroadcasterList homeOttBroadcasters;
        public NBA_SC.BroadcasterList awayTvBroadcasters ;
        public NBA_SC.BroadcasterList awayRadioBroadcasters ;
        public NBA_SC.BroadcasterList awayOttBroadcasters;
        public NBA_SC.BroadcasterList intlRadioBroadcasters ;
        public NBA_SC.BroadcasterList intlTvBroadcasters;
        public NBA_SC.BroadcasterList intlOttBroadcasters ;
        public Broadcasters(){

        }


    }

    public class Weeks{
        public int weekNumber;
        public String weekName;
        public String startDate;
        public String endDate;

        public void setWeekName(String weekName) {
            this.weekName = weekName;
        }

        public void setWeekNumber(int weekNumber) {
            this.weekNumber = weekNumber;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }
    }

}
