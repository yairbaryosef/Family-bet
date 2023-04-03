package com.example.family_bet.DB.NBA;

import java.util.ArrayList;

import java.util.List;

public class NBA_SC {
    public class Meta {
        public int version;
        public String request;
        public String time;

        public Meta(int version, String request, String time) {
            this.version = version;
            this.request = request;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Meta{" +
                    "version=" + version +
                    ", request='" + request + '\'' +
                    ", time='" + time + '\'' +
                    '}';
        }
    }

   public Meta meta;
    public LeagueSchedule leagueSchedule;

    @Override
    public String toString() {
        return "NBA_SC{" +
                "meta=" + meta +
                ", leagueSchedule=" + leagueSchedule +
                '}';
    }

    public class LeagueSchedule {
        public String seasonYear;
        public String leagueId;
        public ArrayList<GameDates> gameDates;
        public ArrayList<Weeks> weeks;
        public ArrayList<BroadcasterList> broadcasterList;
         public LeagueSchedule(){
             gameDates=new ArrayList<>();
             weeks=new ArrayList<>();
             broadcasterList=new ArrayList<>();
         }
        public LeagueSchedule(String seasonYear, String leagueId, ArrayList<GameDates> gameDates,ArrayList<Weeks> weeks,ArrayList<BroadcasterList> broadcasterList) {
            this.seasonYear = seasonYear;
            this.leagueId = leagueId;
            this.gameDates = gameDates;
            this.weeks = weeks;
            this.broadcasterList = broadcasterList;
        }

        @Override
        public String toString() {
            return "LeagueSchedule{" +
                    "seasonYear='" + seasonYear + '\'' +
                    ", leagueId='" + leagueId + '\'' +
                    ", gameDates=" + gameDates +
                    ", weeks=" + weeks +
                    ", broadcasterList=" + broadcasterList +
                    '}';
        }
    }

    public class Weeks {
        public int weekNumber;
        public String weekName;
        public String startDate;
        public String endDate;

        public Weeks(int weekNumber, String weekName, String startDate, String endDate) {
        }
    }
    public class GameDates {
        public String gameDate;
        //public ArrayList<game> nbaGames;
        public ArrayList<NbaGame> NbaGames;

        public GameDates(){
           NbaGames =new ArrayList<>();
        }
        public GameDates(String gameDate, ArrayList<NbaGame> nbaNbaGames) {
            this.gameDate = gameDate;
           // this.nbaGames = nbaGames;

        }

        @Override
        public String toString() {
            return "GameDates{" +
                    "gameDate='" + gameDate + '\'' +
                    ", nbaGames=" +// nbaGames +
                    '}';
        }
    }
    public class Teams {
        public int teamId;
        public String teamName;
        public String teamCity;
        private String teamTricode;
        public int wins;
        public String teamSlug;
        private int losses;
        public int score;
        public int seed;
        private int inBonus;
        private int timeoutsRemaining;
        private List<NBA.NBAGame.Team.Period> periods;

        @Override
        public String toString() {
            return "Team{" +
                    "teamId=" + teamId +
                    ", teamName='" + teamName + '\'' +
                    ", teamCity='" + teamCity + '\'' +
                    ", teamTricode='" + teamTricode + '\'' +
                    ", wins=" + wins +
                    ", losses=" + losses +
                    ", score=" + score +
                    ", seed=" + seed +
                    ", inBonus=" + inBonus +
                    ", timeoutsRemaining=" + timeoutsRemaining +
                    ", periods=" + periods +
                    '}';
        }

        public  class Period {
            private int period;
            private String periodType;
            private int score;

            @Override
            public String toString() {
                return "Period{" +
                        "period=" + period +
                        ", periodType='" + periodType + '\'' +
                        ", score=" + score +
                        '}';
            }
        }
    }
    public class BroadcasterList{
        public int broadcasterId;
        public String broadcasterScope;
        public String broadcasterMedia;
        public String tapeDelayComments;
        public String   broadcasterVideoLink;
        public String broadcasterDisplay;
        public String broadcasterAbbreviation;
        public int regionId;

        public void setBroadcasterAbbreviation(String broadcasterAbbreviation) {
            this.broadcasterAbbreviation = broadcasterAbbreviation;
        }

        public void setBroadcasterDisplay(String broadcasterDisplay) {
            this.broadcasterDisplay = broadcasterDisplay;
        }

        public void setBroadcasterId(int broadcasterId) {
            this.broadcasterId = broadcasterId;
        }

        public void setRegionId(int regionId) {
            this.regionId = regionId;
        }
    }


}
