package com.example.family_bet.DB.SOccer;

import com.example.family_bet.Classes.Constants.constants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Soccer_EST extends TestCase {
   volatile boolean sleep;
    public void testGet_games_for_today() throws Exception {

        try {


            String url = "https://api.football-data.org/v4/competitions/" + constants.champions_League_shortcut + "/matches?matches=1";

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
            //System.out.println(jsonObject.get("competition").getAsJsonObject().get("emblem").getAsString());



            //System.out.println(jsonObject.get("competition").getAsJsonObject().get("name"));
        }
        catch (Exception e){

        }
    }


}