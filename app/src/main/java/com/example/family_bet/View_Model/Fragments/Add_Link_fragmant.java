package com.example.family_bet.View_Model.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.R;
import com.example.family_bet.View_Model.User_Activity.User_Activity;
import com.google.android.material.textfield.TextInputEditText;

public class Add_Link_fragmant extends Fragment {
    private Tournament tournament;
    public Add_Link_fragmant(Tournament tournament){
        this.tournament=tournament;
    }
   View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.add_link, container, false);
        ListView listView=v.findViewById(R.id.list);
        TextInputEditText link=v.findViewById(R.id.link);
        v.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tournament.links.add(link.getText().toString());
                Tournament.save_tournament(tournament);
                Intent intent=new Intent(getContext(), User_Activity.class);
                startActivity(intent);
            }
        });
        ArrayAdapter<String >arrayAdapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,tournament.links);
        listView.setAdapter(arrayAdapter);
        return v;
    }

}
