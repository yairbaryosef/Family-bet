package com.example.family_bet.View_Model.Tournament_activities.join;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.databinding.ActivityChoosingToursDetailsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Choosing_tours_details extends AppCompatActivity {
    ArrayList<String> arrayList;
    ListView list;
    FirebaseFirestore db;
    SearchView searchView;
    String activity,choosing="",country="",sport="",user="";
    ActivityChoosingToursDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityChoosingToursDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list=binding.list;

        activity=getIntent().getStringExtra("type");
        arrayList=new ArrayList<>();
        if(activity.equals(constants.country)){
            DatabaseReference db= FirebaseDatabase.getInstance().getReference(constants.country);
            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        arrayList.add(dataSnapshot.getKey());

                    }
                    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(Choosing_tours_details.this, android.R.layout.simple_list_item_1,arrayList);
                    list.setAdapter(arrayAdapter);
                    Init_Search_Bar(arrayList);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        else if(activity.equals(constants.user)){
            country=getIntent().getStringExtra(constants.country);
            sport=getIntent().getStringExtra(constants.sport);
            DatabaseReference db= FirebaseDatabase.getInstance().getReference(constants.country).child(country).child(sport);
            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        arrayList.add(dataSnapshot.getKey());

                    }
                    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(Choosing_tours_details.this, android.R.layout.simple_list_item_1,arrayList);
                    list.setAdapter(arrayAdapter);
                    Init_Search_Bar(arrayList);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else if(activity.equals(constants.tournament)){
            //init last choices
            country=getIntent().getStringExtra(constants.country);
            sport=getIntent().getStringExtra(constants.sport);
            user=getIntent().getStringExtra(constants.user);
            Toast.makeText(Choosing_tours_details.this, "in", Toast.LENGTH_SHORT).show();
            DatabaseReference db= FirebaseDatabase.getInstance().getReference(constants.country).child(country).child(sport).child(user);
            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                     for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        arrayList.add(dataSnapshot.getKey());

                    }
                    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(Choosing_tours_details.this, android.R.layout.simple_list_item_1,arrayList);
                    list.setAdapter(arrayAdapter);
                    Init_Search_Bar(arrayList);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else{
            country=getIntent().getStringExtra(constants.country);
            DatabaseReference db= FirebaseDatabase.getInstance().getReference(constants.country).child(country);
            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        arrayList.add(dataSnapshot.getKey());

                    }
                    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(Choosing_tours_details.this, android.R.layout.simple_list_item_1,arrayList);
                    list.setAdapter(arrayAdapter);
                    Init_Search_Bar(arrayList);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
   ArrayList<String> serach_ArrayList;

    /**
     * init search bar
     * @param arrayList
     */
    private void Init_Search_Bar(ArrayList<String> arrayList) {
        serach_ArrayList=new ArrayList<>(arrayList);
        binding.search.setQueryHint("search");
        binding.search.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                serach_ArrayList=new ArrayList<>();
                for (String tournament : arrayList) {
                    if ((tournament.toLowerCase().contains(newText.toLowerCase()))) {
                        serach_ArrayList.add(tournament);
                    }

                }
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(Choosing_tours_details.this, android.R.layout.simple_list_item_1,serach_ArrayList);
                list.setAdapter(arrayAdapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        choosing=parent.getItemAtPosition(position).toString();
                        Toast.makeText(Choosing_tours_details.this, choosing, Toast.LENGTH_SHORT).show();
                        if(activity.equals(constants.country)){

                            Intent intent=new Intent(Choosing_tours_details.this,Choosing_tours_details.class);
                            intent.putExtra(constants.country,choosing);
                            intent.putExtra("type",constants.sport);
                            startActivity(intent);
                        }
                        else if(activity.equals(constants.sport)){
                            Intent intent=new Intent(Choosing_tours_details.this,Choosing_tours_details.class);
                            intent.putExtra(constants.country,country);
                            intent.putExtra(constants.sport,choosing);
                            intent.putExtra("type",constants.user);
                            startActivity(intent);
                        }
                        else if(activity.equals(constants.tournament)){
                            Intent intent=new Intent(Choosing_tours_details.this,Join_tour.class);
                            intent.putExtra(constants.country,country);
                            intent.putExtra(constants.sport,sport);
                            intent.putExtra(constants.user,user);
                            intent.putExtra(constants.tournament,choosing);
                            try {


                                startActivity(intent);
                            }catch (Exception e){
                                Toast.makeText(Choosing_tours_details.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Intent intent=new Intent(Choosing_tours_details.this,Choosing_tours_details.class);
                            intent.putExtra(constants.country,country);
                            intent.putExtra(constants.sport,sport);
                            intent.putExtra(constants.user,choosing);
                            intent.putExtra("type",constants.tournament);
                            startActivity(intent);
                        }
                    }
                });
                return false;
            }
        });
    }




}