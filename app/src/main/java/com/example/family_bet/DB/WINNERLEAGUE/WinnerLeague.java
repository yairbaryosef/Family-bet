package com.example.family_bet.DB.WINNERLEAGUE;

import com.example.family_bet.Classes.Game.Game;
import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.Classes.Game.team.Team;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WinnerLeague {

    public List<winnerLeague_Game> games;

    public WinnerLeague() {

    }

    /**
     * get the list of games of winner league that that are not start yet
     * @return list of games
     */
    public ArrayList<Game> initWinner() {

            try {
                //connect to url
                URL url = new URL("https://basket.co.il/pbp/json/games_all.json?1675672491225");
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
                ArrayList<Game> games=new ArrayList<>();
             // Now you can parse the JSON string into a JsonArray object
                Gson gson = new Gson();
                int game_id=0;
                JsonArray gamesArray = gson.fromJson(jsonString, JsonArray.class);
                for (JsonElement gameElement : gamesArray) {
                    JsonObject game = gameElement.getAsJsonObject();
                    JsonArray id = game.get("games").getAsJsonArray();
                    for(JsonElement gameElements : id) {
                        JsonObject game1 = gameElements.getAsJsonObject();
                        int home_team_score = game1.get("score_team1").getAsInt();
                        if(home_team_score==0){
                            Game game2=new Game();
                            game2.setHome_team(new Team(game1.get("team_name_1").getAsString(),game1.get("team_icon_1").getAsString()));
                            game2.setAway_team(new Team(game1.get("team_name_2").getAsString(),game1.get("team_icon_2").getAsString()));
                            String d=game1.get("game_date_txt").getAsString();
                             String[] split=d.split("/");

                             for(int i=0;i< split.length;i++){
                                 split[i].trim();
                                if(split[i].charAt(0)=='0'){
                                    split[i]=String.valueOf(split[i].charAt(1));
                                }

                             }
                            Calendar calendar = Calendar.getInstance();


                            calendar.set(Integer.valueOf(split[2]),Integer.valueOf(split[1]),Integer.valueOf(split[0]));
                            Date date = calendar.getTime();
                            game2.setLast_date(date);
                        game2.setId(game_id);
                            games.add(game2);

                        }
                        game_id++;
                    }
                }
               return  games;
                       // Now you have the JSON data in the "json" variable

            } catch (Exception e) {
                e.printStackTrace();
            }








      return null;
    }
   private static Game initGame_fromJson(JsonObject game1,int game_id ){
       Game game2=new Game();
       game2.setHome_team(new Team(game1.get("team_name_1").getAsString(),game1.get("team_icon_1").getAsString()));
       game2.setAway_team(new Team(game1.get("team_name_2").getAsString(),game1.get("team_icon_2").getAsString()));
       String d=game1.get("game_date_txt").getAsString();
       String[] split=d.split("/");

       for(int i=0;i< split.length;i++){
           split[i].trim();
           if(split[i].charAt(0)=='0'){
               split[i]=String.valueOf(split[i].charAt(1));
           }

       }
       Calendar calendar = Calendar.getInstance();


       calendar.set(Integer.valueOf(split[2]),Integer.valueOf(split[1]),Integer.valueOf(split[0]));
       Date date = calendar.getTime();
       game2.setLast_date(date);
       game2.setId(game_id);
        return game2;
   }


    public   static void Sync(Tournament tournament){
        try {
            //connect to url
            URL url = new URL("https://basket.co.il/pbp/json/games_all.json?1675672491225");
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
            ArrayList<Game> games = new ArrayList<>();
            // Now you can parse the JSON string into a JsonArray object
            Gson gson = new Gson();
            int game_id = 0;
            int j=0;
            JsonArray gamesArray = gson.fromJson(jsonString, JsonArray.class);
            for (JsonElement gameElement : gamesArray) {
                JsonObject game = gameElement.getAsJsonObject();
                JsonArray id = game.get("games").getAsJsonArray();
                for (JsonElement gameElements : id) {
                    JsonObject game1 = gameElements.getAsJsonObject();
                    try {


                        if (tournament.getGames().get(0).getId() <= game_id) {
                            Game g = tournament.getGames().get(j);
                            g.setScore_away_team(game1.get("score_team1").getAsInt());
                            g.setScore_home_team(game1.get("score_team2").getAsInt());
                            j++;
                        }
                    }
                    catch (Exception e){
                        tournament.getGames().add(initGame_fromJson(game1,game_id));
                    }
                    game_id++;
                }
            }

        }
        catch (Exception e){

        }
    }
    @Override
    public String toString() {
        return "WinnerLeague{" +

                '}';
    }
}

