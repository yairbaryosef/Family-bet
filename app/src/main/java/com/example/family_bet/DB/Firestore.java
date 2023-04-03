package com.example.family_bet.DB;

import androidx.annotation.NonNull;

import com.example.family_bet.Classes.Bets.Bet_On_Game;
import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.Classes.Enteties.Predictor;
import com.example.family_bet.Classes.Game.Game;
import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.Classes.Game.team.Team;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Firestore {
    public static void initDocRef(DocumentReference ref,Tournament o){
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        ref=firestore.collection(constants.country).document(o.getCountry()).collection(constants.sport).document(o.getSport_Type()).collection(constants.user).document(o.getDealer()).collection(constants.tournament).document(o.getTour_name());
    }

    /**
     * init docref for all
     * @param country
     * @param dealer
     * @param sport
     * @param tour
     * @return DocumentReference (FireStore)
     */
    public static DocumentReference init_DocRef(String country,String dealer,String sport,String tour){
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference ref=firestore.collection(constants.tournament).document(country+"_"+sport+"_"+dealer+"_"+tour);
        return ref;
    }

    /**
     * return path using
     * @param country
     * @param dealer
     * @param sport
     * @param tour
     * @return path to tour
     */
    public static String getPath(String country,String dealer,String sport,String tour){
        return country+"_"+sport+"_"+dealer+"_"+tour;
    }

    /**
     * return path using
     * @param tournament-variables
     * @return string represent the path to tour
     */
    public static String getPath(Tournament tournament){
        return tournament.getCountry()+"_"+tournament.getSport_Type()+"_"+tournament.getDealer()+"_"+tournament.getTour_name();
    }


    public static Tournament getTour_FromDoc(DocumentSnapshot queryDocumentSnapshots){
        String t=(String) queryDocumentSnapshots.get(constants.tournament);
        Gson gson=new Gson();
        Tournament tournament=gson.fromJson(t,Tournament.class);
        return tournament;
    }
    /**
     * save tournament to database
     * @param tours

     */
    public static void save(HashMap<String,String> tours){
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        Gson gson=new Gson();
        Tournament o=gson.fromJson(tours.get(constants.tournament),Tournament.class);

           tours.put(constants.key,getPath(o.getCountry(),o.getDealer(),o.getSport_Type(),o.getTour_name()));
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference(constants.country);
        databaseReference.child(o.getCountry()).child(o.getSport_Type()).child(o.getDealer()).child(o.getTour_name()).setValue("y");
    //    DocumentReference docRef =   firestore.collection(constants.country).document(o.getCountry()).collection(constants.sport).document(o.getSport_Type()).collection(constants.user).document(o.getDealer()).collection(constants.tournament).document(o.getTour_name());
        DocumentReference docRef =init_DocRef(o.getCountry(),o.getDealer(),o.getSport_Type(),o.getTour_name());
        docRef.set(tours).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        //Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static  Tournament getTour(DocumentSnapshot documentSnapshot){

        Map<String,Object> hashMap=documentSnapshot.getData();
        String json=(String) hashMap.get(constants.tournament);
        Gson gson=new Gson();
        Tournament tournament=new Tournament();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray gamesArray = jsonObject.get("games").getAsJsonArray();
        for(JsonElement element:gamesArray){
           JsonObject g= element.getAsJsonObject();
           Game game=initGame_From_json(g);
           tournament.getGames().add(game);
        }
        tournament.setDealer(jsonObject.get("dealer").getAsString());
        tournament.setTour_name(jsonObject.get("tour_name").getAsString());
        try {


            JsonArray arr = jsonObject.get("links").getAsJsonArray();
            for (JsonElement element : arr) {
                tournament.links.add(element.getAsString());
            }
        }
        catch (Exception e){

        }
        try {

            tournament.setSport_Type(jsonObject.get("sport_Type").getAsString());
            JsonArray arr=jsonObject.get("predictors").getAsJsonArray();
            for(JsonElement element:arr){
                JsonObject g= element.getAsJsonObject();
                Predictor game=initPre_Json(g);
                tournament.getPredictors().add(game);
            }

            tournament.setScore_for_right_bet(jsonObject.get("score_for_right_bet").getAsInt());
            tournament.setScore_for_bool_bet(jsonObject.get("score_for_bool_bet").getAsInt());
            tournament.setPassword(jsonObject.get("password").getAsString());
        }
        catch (Exception exception){

        }
        try {
            tournament.type=jsonObject.get("type").getAsString();
        }
        catch (Exception e){

        }


       // Tournament t=gson.fromJson(json,Tournament.class);
        return tournament;
    }

    /**
     * init predictor from json
     * @param p
     * @return predictor
     */
    public static Predictor initPre_Json(JsonObject p){
         Predictor predictor=new Predictor();
         predictor.setTotal_points(p.get("total_points").getAsInt());
        predictor.setUsername(p.get("username").getAsString());
        try {


            // predictor.setPassword(p.get("password").getAsString());
            predictor.setPicture(p.get("picture").getAsString());
        }
        catch (Exception e){

        }
        return predictor;
    }
    /**
     * init game from json
     * @param g
     * @return game
     */
    public static Game initGame_From_json(JsonObject g){
        try {
            Game game = new Game();
            String date = g.get("last_date").getAsString();
            try {
                DateFormat format = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss", Locale.ENGLISH);
                Date date1 = format.parse(date);
                game.setLast_date(date1);

            } catch (Exception e) {
                System.out.println();
            }

            //home team
            JsonObject hometeam=g.get("home_team").getAsJsonObject();
            Team home=new Team();
            home.setName(hometeam.get("name").getAsString());
            home.setPicture(hometeam.get("picture").getAsString());
            game.setHome_team(home);

            //away team
            JsonObject team=g.get("away_team").getAsJsonObject();
             home=new Team();
            home.setName(team.get("name").getAsString());
            home.setPicture(team.get("picture").getAsString());
            game.setAway_team(home);

           // game.setType(g.get("type").getAsString());
            game.setScore_home_team(g.get("score_home_team").getAsInt());
            game.setScore_away_team(g.get("score_away_team").getAsInt());
            game.setScore_for_right_bet(g.get("score_for_right_bet").getAsInt());
            game.setScore_for_right_bet(g.get("score_for_bool_bet").getAsInt());
            JsonObject bets = g.get("bets").getAsJsonObject();
            for (String key : bets.keySet()) {
                Bet_On_Game b = new Bet_On_Game();
                JsonObject bet_on_game = bets.get(key).getAsJsonObject();
              //  b.setStatus(bet_on_game.get("status").getAsString());
                b.setScore_home_team_bet(bet_on_game.get("score_home_team_bet").getAsInt());
                b.setScore_away_team_bet(bet_on_game.get("score_away_team_bet").getAsInt());
                b.setIs_changed(bet_on_game.get("is_changed").getAsBoolean());
                game.bets.put(key, b);
            }
            return game;
        }
        catch (Exception e){

        }
        return new Game();
    }
    /**
     * delete tournament from database
     * @param o

     */
    public static void delete(Tournament o){



        DocumentReference docRef =init_DocRef(o.getCountry(),o.getDealer(),o.getSport_Type(),o.getTour_name());
                docRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void documentReference) {
                        //  Toast.makeText(context, "sucess", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        //Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
