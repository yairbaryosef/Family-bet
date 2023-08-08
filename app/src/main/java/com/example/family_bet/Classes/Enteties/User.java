package com.example.family_bet.Classes.Enteties;

import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.DB.Tours.Tour_for_Realtime;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String name;
    private String profile;
    public ArrayList<Tour_for_Realtime> tour_for_realtimes;
    private ArrayList<Predictor> predictors;
    private ArrayList<String> tournaments_id;
    private int count_tours;
    public User(){
        tournaments_id=new ArrayList<>();
        tour_for_realtimes=new ArrayList<>();
    }
    public User(String username,String password,String name){
        this.username=username;
        this.password=password;
        this.name=name;
        count_tours=0;
        tour_for_realtimes=new ArrayList<>();
        tournaments_id=new ArrayList<>();
        Save_user(this);

    }

    public static void Save_user(User user) {
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference(constants.user).child(user.username);
        databaseReference.setValue(user);

    }
    /*
    getters
     */

    public String getName() {
        return name;
    }

    public ArrayList<Predictor> getPredictors() {
        return predictors;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public ArrayList<String> getTournaments_id() {
        return tournaments_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add_Tournament(String tournament){tournaments_id.add(tournament);}

    public String getProfile() {
        return profile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getCount_tours() {
        return count_tours;
    }

    public void setCount_tours(int count_tours) {
        this.count_tours = count_tours;
    }
}
