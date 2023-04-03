package com.example.family_bet.Classes.Game;

import android.content.Context;

import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.Classes.Enteties.Predictor;
import com.example.family_bet.Classes.Game.league.league;
import com.example.family_bet.DB.Firestore;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Tournament {
    private String dealer;
    public String type;
    private String country;
    public ArrayList<String> links;
    private String sport_Type;
    private ArrayList<Predictor> predictors;
    private ArrayList<String> participants;
    public HashMap<String,Predictor> predictorHashMap;
    private ArrayList<Game> games;

    private league league;
    private int champion_points;
    private String champion;
    private int score_for_right_bet;
    private int score_for_bool_bet;
    private long tournament_Id;
    private Predictor Winner;
    private String tour_picture;
    private String tour_name;
    private String password;
    public Tournament(){
        games=new ArrayList<>();
        predictors=new ArrayList<>();
        participants=new ArrayList<>();
        predictorHashMap=new HashMap<>();
       country="c";
       sport_Type="S";
       dealer="d";
links=new ArrayList<>();


    }

    public Tournament(Tournament tournament) {
        this.dealer = tournament.dealer;
        this.type = tournament.type;
        this.predictors = tournament.predictors;
        this.participants = tournament.participants;
        this.predictorHashMap = tournament.predictorHashMap;
        this.games = tournament.games;
        this.league = tournament.league;
        this.champion_points = tournament.champion_points;
        this.champion = tournament.champion;
        this.score_for_right_bet = tournament.score_for_right_bet;
        this.score_for_bool_bet = tournament.score_for_bool_bet;
        this.tournament_Id = tournament.tournament_Id;
        Winner = tournament.Winner;
        this.tour_picture = tournament.tour_picture;
        this.tour_name = tournament.tour_name;
        this.password = tournament.password;

    }
    /*
    getters
     */

    public String getTour_name() {
        return tour_name;
    }

    public String getPassword() {
        return password;
    }

    public String getTour_picture() {
        return tour_picture;
    }

    public Predictor getWinner() {
        return Winner;
    }

    public ArrayList<Game> getGames() {
        return games;

    }

    public ArrayList<Predictor> getPredictors() {
        return predictors;
    }

    public String getDealer() {
        return dealer;
    }

    public long getTournament_Id() {
        return tournament_Id;
    }

    public int getScore_for_bool_bet() {
        return score_for_bool_bet;
    }

    public int getScore_for_right_bet() {
        return score_for_right_bet;
    }
    /*
    setters
     */

    public void setTour_picture(String tour_picture) {
        this.tour_picture = tour_picture;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public void setPredictors(ArrayList<Predictor> predictors) {
        this.predictors = predictors;
    }

    public void setTournament_Id(long tournament_Id) {
        this.tournament_Id = tournament_Id;
    }

    public void setScore_for_bool_bet(int score_for_bool_bet) {
        this.score_for_bool_bet = score_for_bool_bet;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setScore_for_right_bet(int score_for_right_bet) {
        this.score_for_right_bet = score_for_right_bet;
    }

    public void setTour_name(String tour_name) {
        this.tour_name = tour_name;
    }

    public void setWinner(Predictor winner) {
        Winner = winner;
    }
    /*
  add game
   */
    public void add_game(Game game){
        this.games.add(game);
    }
    /*
    add predictor
     */
    public void add_prodictor(Predictor predictor){predictors.add(predictor);}
    /*
    add participants
     */
    public void add_participant(String user){participants.add(user);}
    /*
    save tournament to database
     */
    public static void save_Tournament(Context context,Tournament tournament){
        Tournament_Controller tournament_controller=new Tournament_Controller(tournament);
        Tournament_for_saved tournament_for_saved=tournament_controller.getTour_For_DATABAE(context);
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference(constants.tournament);
        databaseReference.child(tournament.getTour_name()).setValue(tournament_for_saved);

    }
    public static void save_tournament(Tournament tournament){
        for(Game g: tournament.getGames()) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");
                Date date1 = formatter.parse(g.getLast_date().toString());
                g.setLast_date(date1);

            } catch (Exception e) {

            }
        }
        HashMap<String,String> tournamentHashMap=new HashMap<>();
        Gson gson=new Gson();
        String s=gson.toJson(tournament);
        tournamentHashMap.put(constants.tournament,s);
        Firestore.save(tournamentHashMap);
    }
    public static void delete_Tournament(Tournament tournament){
        Firestore.delete(tournament);
    }

    public com.example.family_bet.Classes.Game.league.league getLeague() {
        return league;
    }

    public void setLeague(com.example.family_bet.Classes.Game.league.league league) {
        this.league = league;
    }
   public Tournament(HashMap<String,Object> hashMap){
        tour_name=(String) hashMap.get("tour_name");

   }
    /**
     *
     * @param username get predictor by username
     */
    public Predictor get_Predictor(String username){
        Predictor predictor=null;
        for(Predictor p:getPredictors()){
            if(p.getUsername().equals(username))
                return p;

        }
        return  null;
    }

    public Game get_game(Game game){
        for(Game game1:games){
            if(game.equals(game1)){
                return  game1;
            }
        }
        return null;
    }

    public String getChampion() {
        return champion;
    }

    public void setChampion(String champion) {
        this.champion = champion;
    }

    public int getChampion_points() {
        return champion_points;
    }

    public void setChampion_points(int champion_points) {
        this.champion_points = champion_points;
    }

    public String getCountry() {
        return country;
    }

    public String getSport_Type() {
        return sport_Type;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSport_Type(String sport_Type) {
        this.sport_Type = sport_Type;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "dealer='" + dealer + '\'' +
                ", last_user='" + type + '\'' +
                ", predictors=" + predictors +
                ", participants=" + participants +
                ", predictorHashMap=" + predictorHashMap +
                ", games=" + games +
                ", league=" + league +
                ", champion_points=" + champion_points +
                ", champion='" + champion + '\'' +
                ", score_for_right_bet=" + score_for_right_bet +
                ", score_for_bool_bet=" + score_for_bool_bet +
                ", tournament_Id=" + tournament_Id +
                ", Winner=" + Winner +
                ", tour_picture='" + tour_picture + '\'' +
                ", tour_name='" + tour_name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public ArrayList<String> getPath() {
        ArrayList<String> path=new ArrayList<String>();
        path.add(country);
        path.add(sport_Type);
        path.add(dealer);
        path.add(tour_name);
        return  path;

    }




}

