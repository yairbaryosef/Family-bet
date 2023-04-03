package com.example.family_bet.DB.Soccer;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TOPS_SOCCER {



    public static void initLeague_games(String league,Tournament tournament){


        try {


           String url = "https://api.football-data.org/v4/competitions/" + league + "/matches?matches=1";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Auth-Token", "ec5aedbc2f3640b38a5c6d8f87bbb975");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String result = response.toString();

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
            init_games_from_json(jsonObject,tournament);



            //System.out.println(jsonObject.get("competition").getAsJsonObject().get("name"));
        }
        catch (Exception e){

        }

    }

    private static void init_games_from_json(JsonObject jsonObject,Tournament tournament) {
        tournament.type=jsonObject.get("competition").getAsJsonObject().get("name").getAsString();
        JsonArray matches = jsonObject.get("matches").getAsJsonArray();
        for (JsonElement element:matches) {
            JsonObject object=element.getAsJsonObject();
            if(!object.get("status").getAsString().equals("FINISHED")) {
                Game g = initGaME(object);
               // System.out.println(g);
                tournament.getGames().add(g);
            }
        }
    }


    /**
     * init one game from json
     * @param object
     * @return
     */
    private static Game initGaME(JsonObject object) {
        Game g=new Game();

        //init teams
        Team team=new Team();
        String hometeam = object.get("homeTeam").getAsJsonObject().get("name").getAsString();
        String homepic = object.get("homeTeam").getAsJsonObject().get("crest").getAsString();
        team.setName(hometeam);
        team.setPicture(homepic);
        g.setHome_team(team);
        hometeam = object.get("awayTeam").getAsJsonObject().get("name").getAsString();
        homepic = object.get("awayTeam").getAsJsonObject().get("crest").getAsString();
        team=new Team();
        team.setName(hometeam);
        team.setPicture(homepic);
        g.setAway_team(team);
        g.setScore_home_team(0);
        g.setScore_home_team(0);


        //date
        try {
            String date = object.get("utcDate").getAsString();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date dat1e = dateFormat.parse(date);

            g.setLast_date(dat1e);
          //  System.out.println(dat1e);
        }
        catch (Exception e){

        }
        return g;
    }

}

