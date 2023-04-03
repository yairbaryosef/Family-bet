package com.example.family_bet.DB.Soccer;

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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AL_LEAGUE {
    /**
     * get the list of games of winner league that that are not start yet
     * @return list of games
     */
    public static ArrayList<Game> initAl_League() {

        try {
            //connect to url
            URL url = new URL("https://cdnapi.bamboo-video.com/api/football/round?format=json&iid=573881b7181f46ae4c8b4567&filter={%22seasonName%22:%2222/23%22}&useCache=false&ts=27950046");
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
            JsonObject gamesArray = gson.fromJson(jsonString, JsonObject.class);


                JsonArray data = gamesArray.get("data").getAsJsonArray();
                for(JsonElement gameElements : data){
                    JsonObject game1 = gameElements.getAsJsonObject();
                    JsonObject games_json = game1.get("games").getAsJsonObject();
                    JsonObject objects_json = games_json.get("objects").getAsJsonObject();
                    for(String key:objects_json.keySet()){
                        Game game=new Game();

                        JsonObject game_object = objects_json.get(key).getAsJsonObject();
                        //check if game is not done
                        try {


                            int hometeam_score = game_object.get("homeScore").getAsInt();


                        }
                        catch (Exception e){
                            //Home team
                            JsonObject hometeamId=game_object.get("homeTeamId").getAsJsonObject();
                            String home_name=hometeamId.get("name").getAsString();
                            String home_logo=hometeamId.get("logoUrl").getAsString();
                            Team home=new Team();
                            home.setName(home_name);
                            home.setPicture(home_logo);
                            game.setHome_team(home);

                            //Away team
                            hometeamId=game_object.get("awayTeamId").getAsJsonObject();
                            home_name=hometeamId.get("name").getAsString();
                            home_logo=hometeamId.get("logoUrl").getAsString();
                            home=new Team();
                            home.setName(home_name);
                            home.setPicture(home_logo);
                            game.setAway_team(home);

                            //date

                            String hour = game_object.get("hour").getAsString();

                            String[] hour_split=hour.split(":");
                            String date = game_object.get("stringDate").getAsString();

                            String[] date_split=new String[3];
                            String del="";
                            int j=0;
                            for(int i=0;i<date.substring(0,8).length();i++){

                                if(date.substring(i,i+1).equals(".")){
                                    date_split[j]=del;
                                    j++;
                                    del="";
                                }
                                else {
                                    del=del+date.substring(i,i+1);
                                }
                            }
                            date_split[2]=del;

                            Calendar calendar = Calendar.getInstance();

                           try {
                               calendar.set(Integer.valueOf(date_split[2]), Integer.valueOf(date_split[1]) - 1, Integer.valueOf(date_split[0]), Integer.valueOf(hour_split[0]), Integer.valueOf(hour_split[1]));
                           }
                           catch (Exception e1){
                               calendar.set(Integer.valueOf(date_split[2]), Integer.valueOf(date_split[1]) - 1, Integer.valueOf(date_split[0]));

                           }
                            Date date1=calendar.getTime();

                            game.setLast_date(date1);
                            games.add(game);


                        }





                    }

                }


            return  games;
            // Now you have the JSON data in the "json" variable

        } catch (Exception e) {
            e.printStackTrace();
        }








        return null;
    }



}
