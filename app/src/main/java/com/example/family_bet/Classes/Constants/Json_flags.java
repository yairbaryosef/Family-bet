package com.example.family_bet.Classes.Constants;

import com.example.family_bet.View_Model.User_Activity.Predictor_Activity.Profile_Adapter;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Json_flags {
    public static JsonObject jsonObject;
    public static String fileName="java/com/example/family_bet/Classes/Constants/flags.json";
    public static void Save(){
       String url1 = "https://cdn.jsdelivr.net/npm/country-flag-emoji-json@2.0.0/dist/by-code.json";
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

                     File file = new File("java/com/example/family_bet/Classes/Constants/flags.json");

                     // Create a new FileWriter object to write to the file
                     FileWriter writer = new FileWriter(file);

                     // Write the string content to the file
                     writer.write(jsonString);

                     // Close the FileWriter object to release resources
                     writer.close();
        }
        catch (Exception e){

        }
    }

    public  ArrayList<Profile_Adapter.profile> getAsProfiles(){
        try {


            JsonParser parser = new JsonParser();
            Object obj = parser.parse(new InputStreamReader(getClass().getResourceAsStream("/com/example/family_bet/Classes/Constants/flags.json")));

                JsonObject jsonElements = (JsonObject) obj;
                ArrayList<Profile_Adapter.profile> profiles=new ArrayList<>();

                for (String element : jsonElements.keySet()) {
                    JsonObject country = jsonElements.get(element).getAsJsonObject();
                    String name = country.get("name").getAsString();
                    String picture = country.get("image").getAsString();
                    Profile_Adapter.profile profile = new Profile_Adapter.profile();
                    profile.name = name;
                    profile.resource = picture;
                    profiles.add(profile);

                }
            return profiles;
        }
        catch (Exception e){
            return null;
        }
    }
}
