package com.example.family_bet.DB.DB365;

import com.example.family_bet.Classes.Game.Game;
import com.example.family_bet.Classes.Game.team.Team;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GAME_INIT {

        public static ArrayList<Game> getGamesFromHtml(String url1) {
            url1 = "https://webws.365scores.com/web/games/fixtures/?appTypeId=5&langId=2&timezoneName=Asia/Jerusalem&userCountryId=6&competitions="+url1+"&showOdds=true";
            try {
                //connect to url
                 URL url = new URL(url1);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String output;
                while ((output = br.readLine()) != null) {
                    sb.append(output);
                }

                String jsonString = sb.toString();
               // System.out.println(jsonString);
                ArrayList<Game> games=new ArrayList<>();
                Gson gson=new Gson();
                JsonObject jsonObject=gson.fromJson(jsonString,JsonObject.class);
                System.out.println(jsonObject);
                JsonArray array=jsonObject.get("games").getAsJsonArray();
                String name=array.get(0).getAsJsonObject().get("competitionDisplayName").getAsString();
                System.out.println(name);
                for(JsonElement element:array){
                    Game game_=new Game();
                    JsonObject game=element.getAsJsonObject();
                     String date=game.get("startTime").getAsString();

                     //home
                    JsonObject homeCompetitor=game.get("homeCompetitor").getAsJsonObject();
                    String home_name=homeCompetitor.get("nameForURL").getAsString();
                    String home_pic="https://imagecache.365scores.com/image/upload/f_png,w_24,h_24,c_limit,q_auto:eco,dpr_3,d_Competitors:default1.png/v1/Competitors/"+homeCompetitor.get("id").getAsString();
                    Team team=new Team();
                    team.setPicture(home_pic);
                    team.setName(home_name);
                    game_.setHome_team(team);

                    homeCompetitor=game.get("awayCompetitor").getAsJsonObject();
                   home_name=homeCompetitor.get("nameForURL").getAsString();
                     home_pic="https://imagecache.365scores.com/image/upload/f_png,w_24,h_24,c_limit,q_auto:eco,dpr_3,d_Competitors:default1.png/v1/Competitors/"+homeCompetitor.get("id").getAsString();
                    team=new Team();
                    team.setPicture(home_pic);
                    team.setName(home_name);
                    game_.setAway_team(team);

                     try {
                         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
                         Date date1 = dateFormat.parse(date);
                         game_.setLast_date(date1);

                     }
                     catch (Exception e){

                     }
                     games.add(game_);

                }
               System.out.println(games);
                return games;

            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
return null;
        }

        public static void sync(ArrayList<Game> games,String url1){
            url1 = "https://webws.365scores.com/web/games/fixtures/?appTypeId=5&langId=2&timezoneName=Asia/Jerusalem&userCountryId=6&competitions="+url1+"&showOdds=true";
            try {
                //connect to url
                URL url = new URL(url1);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String output;
                while ((output = br.readLine()) != null) {
                    sb.append(output);
                }

                String jsonString = sb.toString();
                // System.out.println(jsonString);

                Gson gson=new Gson();
                JsonObject jsonObject=gson.fromJson(jsonString,JsonObject.class);
                System.out.println(jsonObject);
                JsonArray array=jsonObject.get("games").getAsJsonArray();
                String name=array.get(0).getAsJsonObject().get("competitionDisplayName").getAsString();
                System.out.println(name);
                for(JsonElement element:array){
                    Game game_=new Game();
                    JsonObject game=element.getAsJsonObject();
                    String date=game.get("startTime").getAsString();
                    
                        //home
                        JsonObject homeCompetitor = game.get("homeCompetitor").getAsJsonObject();
                        String home_name = homeCompetitor.get("nameForURL").getAsString();
                        String home_pic = "https://imagecache.365scores.com/image/upload/f_png,w_24,h_24,c_limit,q_auto:eco,dpr_3,d_Competitors:default1.png/v1/Competitors/" + homeCompetitor.get("id").getAsString();
                        Team team = new Team();
                        team.setPicture(home_pic);
                        team.setName(home_name);
                        game_.setHome_team(team);

                        homeCompetitor = game.get("awayCompetitor").getAsJsonObject();
                        home_name = homeCompetitor.get("nameForURL").getAsString();
                        home_pic = "https://imagecache.365scores.com/image/upload/f_png,w_24,h_24,c_limit,q_auto:eco,dpr_3,d_Competitors:default1.png/v1/Competitors/" + homeCompetitor.get("id").getAsString();
                        team = new Team();
                        team.setPicture(home_pic);
                        team.setName(home_name);
                        game_.setAway_team(team);

                        try {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
                            Date date1 = dateFormat.parse(date);
                            game_.setLast_date(date1);

                        } catch (Exception e) {

                        }
                        games.add(game_);


                }


            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }


}
