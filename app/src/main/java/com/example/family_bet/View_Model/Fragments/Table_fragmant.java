package com.example.family_bet.View_Model.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.family_bet.Classes.Enteties.Predictor;
import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.Classes.Game.Tournament_Controller;
import com.example.family_bet.R;
import com.example.family_bet.View_Model.User_Activity.Predictor_Activity.Profile_Adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;

public class Table_fragmant extends Fragment {
    private Tournament tournament;
    public Table_fragmant(Tournament tournament){
        this.tournament=tournament;
    }
   View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.table, container, false);
        ListView listView=v.findViewById(R.id.list);
        Hashtable<String,Profile_Adapter.profile> integerprofileHashtable=new Hashtable<>();
        ArrayList<Predictor> predictors=new ArrayList<>();
        Tournament_Controller tournament_controller=new Tournament_Controller(tournament);
        for(Predictor predictor:tournament.getPredictors()){
            Profile_Adapter.profile profile=new Profile_Adapter.profile();

            profile.resource=predictor.getPicture();
            profile.name=String.valueOf(String.valueOf(predictor.getTotal_points())+" "+predictor.getUsername());
            predictors.add(predictor);
            integerprofileHashtable.put(predictor.getUsername(),profile);
        }
    ArrayList<Predictor>  predictors_sorted=bucketSort(predictors);
      ArrayList<Profile_Adapter.profile> profiles=new ArrayList<>();
      for(Predictor p:predictors_sorted){
          profiles.add(0,integerprofileHashtable.get(p.getUsername()));
      }
        Profile_Adapter arrayAdapter=new Profile_Adapter(getContext(), profiles);
        listView.setAdapter(arrayAdapter);
        return v;
    }

    public  ArrayList<Predictor> bucketSort(ArrayList<Predictor> predictors) {
        // Determine the maximum totalPoints value in the list
        int maxPoints = Integer.MIN_VALUE;
        for (Predictor p : predictors) {
            if (p.getTotal_points() > maxPoints) {
                maxPoints = p.getTotal_points();
            }
        }

        // Create a list of buckets to hold the Predictor objects
        ArrayList<ArrayList<Predictor>> buckets = new ArrayList<ArrayList<Predictor>>();
        for (int i = 0; i <= maxPoints; i++) {
            buckets.add(new ArrayList<Predictor>());
        }

        // Add each Predictor to the appropriate bucket based on its totalPoints value
        for (Predictor p : predictors) {
            int bucketIndex = p.getTotal_points();
            buckets.get(bucketIndex).add(p);
        }

        // Sort the Predictors in each bucket by totalPoints using Collections.sort() with a Comparator
        Comparator<Predictor> comparator = new Comparator<Predictor>() {
            @Override
            public int compare(Predictor p1, Predictor p2) {
                return Integer.compare(p1.getTotal_points(), p2.getTotal_points());
            }
        };
        for (ArrayList<Predictor> bucket : buckets) {
            Collections.sort(bucket, comparator);
        }

        // Concatenate the sorted buckets into a single ArrayList and return it
        ArrayList<Predictor> sortedPredictors = new ArrayList<Predictor>();
        for (ArrayList<Predictor> bucket : buckets) {
            sortedPredictors.addAll(bucket);
        }
        return sortedPredictors;
    }
}
