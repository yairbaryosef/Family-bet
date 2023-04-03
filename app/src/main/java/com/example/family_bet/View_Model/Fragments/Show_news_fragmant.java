package com.example.family_bet.View_Model.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Show_news_fragmant extends Fragment {
    private Tournament tournament;
    int i;
    public Show_news_fragmant(Tournament tournament){
        this.tournament=tournament;
        i=0;
    }
   View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.webview, container, false);
        WebView web=v.findViewById(R.id.web);
        //nav
        BottomNavigationView navigationView=v.findViewById(R.id.navigation);
        navigationView.setItemIconTintList(null);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getTitle().toString().equals("next")) {
                    if(tournament.links.size()<=i+1){
                        i=0;
                    }
                    else {
                        i++;
                    }
                    if(tournament.links.size()>0) {
                        String link = tournament.links.get(i);
                        web.loadUrl(link);
                        web.getSettings().setJavaScriptEnabled(true);

                    }
                    else {
                        Toast.makeText(getContext(), "there is nothing to see", Toast.LENGTH_SHORT).show();
                    }



                }
                else {
                    if(tournament.links.size()>=i+1){
                        i=tournament.links.size()-1;
                    }
                    else {
                        i--;
                    }
                    if(tournament.links.size()>0) {
                        String link = tournament.links.get(i);
                        web.loadUrl(link);
                        web.getSettings().setJavaScriptEnabled(true);
                    }
                }
                return false;
            }
        });



        return v;
    }

}
