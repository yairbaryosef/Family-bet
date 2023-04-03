package com.example.family_bet.View_Model.User_Activity.Delar_activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.R;
import com.example.family_bet.View_Model.Fragments.Add_Link_fragmant;
import com.example.family_bet.databinding.ActivityAddNewsBinding;
import com.google.gson.Gson;

public class Add_News extends AppCompatActivity {
    ActivityAddNewsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAddNewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String tour=getIntent().getStringExtra(constants.tournament);
        Tournament tournament=new Gson().fromJson(tour,Tournament.class);
       Fragment selectedFragment=new Add_Link_fragmant(tournament);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

    }
}