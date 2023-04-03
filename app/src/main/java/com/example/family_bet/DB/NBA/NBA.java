package com.example.family_bet.DB.NBA;

import com.example.family_bet.Classes.Game.team.Team;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NBA {



public NBA(){

}
    boolean res;
public NBA_SC get_games_for_today() {
    try {
        String url_string = "https://cdn.nba.com/static/json/staticData/scheduleLeagueV2_18.json";
        URL url = new URL(url_string);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        int response = urlConnection.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuffer buffer = new StringBuffer();
        String inputLIne;
        String a = "";
        Gson gson = new Gson();
        while ((inputLIne = in.readLine()) != null) {
            buffer.append(inputLIne);

            System.out.println(buffer);


        }
        in.close();

       NBA_SC game=gson.fromJson(String.valueOf(buffer),  NBA_SC.class);
        System.out.println(game);
         return game;
    } catch (Exception e) {

    }
    return null;
   }

    /**
     * get all games for today
     */
    public class TodayGames {



        public TodayGames(){

       }
        public class Scoreboard {
            private Meta meta;

            @Override
            public String toString() {
                return "Scoreboard{" +
                        "meta=" + meta +
                        ", scoreboard=" + scoreboard +
                        '}';
            }

            private ScoreboardData scoreboard;
              Scoreboard(){}
            public  class Meta {
                private int version;
                private String request;
                private String time;
                private int code;
               Meta(){

               }

                @Override
                public String toString() {
                    return "Meta{" +
                            "version=" + version +
                            ", request='" + request + '\'' +
                            ", time='" + time + '\'' +
                            ", code=" + code +
                            '}';
                }

                public int getVersion() {
                    return version;
                }

                public void setVersion(int version) {
                    this.version = version;
                }


                public String getRequest() {
                    return request;
                }

                public void setRequest(String request) {
                    this.request = request;
                }


                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }


                public int getCode() {
                    return code;
                }

                public void setCode(int code) {
                    this.code = code;
                }
            }

            public  class ScoreboardData {
                private String gameDate;
                private String leagueId;
                private String leagueName;
                private ArrayList<NBAGame> games;

                @Override
                public String toString() {
                    return "ScoreboardData{" +
                            "gameDate='" + gameDate + '\'' +
                            ", leagueId='" + leagueId + '\'' +
                            ", leagueName='" + leagueName + '\'' +
                            ", games=" + games +
                            '}';
                }

                public String getGameDate() {
                    return gameDate;
                }

                public void setGameDate(String gameDate) {
                    this.gameDate = gameDate;
                }


                public String getLeagueId() {
                    return leagueId;
                }

                public void setLeagueId(String leagueId) {
                    this.leagueId = leagueId;
                }


                public String getLeagueName() {
                    return leagueName;
                }

                public void setLeagueName(String leagueName) {
                    this.leagueName = leagueName;
                }


                public List<NBAGame> getGames() {
                    return games;
                }

                public void setGames(ArrayList<NBAGame> games) {
                    this.games = games;
                }
            }


            public Meta getMeta() {
                return meta;
            }

            public ScoreboardData getScoreboard() {
                return scoreboard;
            }
            public ArrayList<com.example.family_bet.Classes.Game.Game> get_ALL_Games(){

                 ArrayList<com.example.family_bet.Classes.Game.Game> games=new ArrayList<>();
                 for(NBAGame g:scoreboard.games){
                     com.example.family_bet.Classes.Game.Game game=new com.example.family_bet.Classes.Game.Game();
                     game.setScore_away_team(g.awayTeam.score);
                     game.setScore_home_team(g.homeTeam.score);
                     Team team=new Team();
                     team.setName(g.homeTeam.teamCity+" "+g.homeTeam.teamName);
                     game.setHome_team(team);
                     team.setName(g.awayTeam.teamCity+" "+g.awayTeam.teamName);
                     game.setAway_team(team);

                 }

                 return  games;
            }
        }


    }


    public class NBAGame {
        @Override
        public String toString() {
            return "Game{" +
                    "gameId='" + gameId + '\'' +
                    ", gameCode='" + gameCode + '\'' +
                    ", gameStatus=" + gameStatus +
                    ", gameStatusText='" + gameStatusText + '\'' +
                    ", period=" + period +
                    ", gameClock='" + gameClock + '\'' +
                    ", gameTimeUTC='" + gameTimeUTC + '\'' +
                    ", gameEt='" + gameEt + '\'' +
                    ", regulationPeriods=" + regulationPeriods +
                    ", ifNecessary=" + ifNecessary +
                    ", seriesGameNumber='" + seriesGameNumber + '\'' +
                    ", seriesText='" + seriesText + '\'' +
                    ", homeTeam=" + homeTeam +
                    ", awayTeam=" + awayTeam +
                    ", gameLeaders=" + gameLeaders +
                    ", pbOdds=" + pbOdds +
                    '}';
        }

        private String gameId;
        private String gameCode;
        private int gameStatus;
        private String gameStatusText;
        private int period;
        private String gameClock;
        private String gameTimeUTC;
        private String gameEt;
        private int regulationPeriods;
        private boolean ifNecessary;
        private String seriesGameNumber;
        private String seriesText;
        private Team homeTeam;
        private Team awayTeam;
        private Leaders gameLeaders;
        private PointSpreadOdds pbOdds;

         class Team {
            private int teamId;
            private String teamName;
            private String teamCity;
            private String teamTricode;
            private int wins;
            private int losses;
            private int score;
            private int seed;
            private int inBonus;
            private int timeoutsRemaining;
            private List<Period> periods;

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

        public  class Leaders {
            private Leader homeLeaders;
            private Leader awayLeaders;

            public  class Leader {
                private int personId;
                private String name;
                private String jerseyNum;
                private String position;
                private String teamTricode;
                private String playerSlug;
                private int points;
                private int rebounds;
                private int assists;
            }

            @Override
            public String toString() {
                return "Leaders{" +
                        "homeLeaders=" + homeLeaders +
                        ", awayLeaders=" + awayLeaders +
                        '}';
            }
        }

        public class PointSpreadOdds {
            private String team;
            private float odds;
            private int suspended;
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


        public int getGameStatus() {
            return gameStatus;
        }

        public void setGameStatus(int gameStatus) {
            this.gameStatus = gameStatus;
        }


        public String getGameStatusText() {
            return gameStatusText;
        }

        public void setGameStatusText(String gameStatusText) {
            this.gameStatusText = gameStatusText;
        }


        public int getPeriod() {
            return period;
        }

        public void setPeriod(int period) {
            this.period = period;
        }


        public String getGameClock() {
            return gameClock;
        }


    }





}
