package com.example.family_bet.View_Model.Tournament_activities.Add;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.Classes.Game.Game;
import com.example.family_bet.Classes.Game.team.Team;
import com.example.family_bet.R;
import com.example.family_bet.View_Model.User_Activity.Predictor_Activity.Profile_Adapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class Games_Adapter extends ArrayAdapter<Game> {
    ArrayList<Game> games;
    ArrayList<Team> teams;
    ArrayList<String> teams_names;
    public Games_Adapter(@NonNull Context context, ArrayList<Game> arrayList,ArrayList<String> teams_names,ArrayList<Team> teams) {
        super(context, R.layout.game_bet, R.id.text3, arrayList);
        games=arrayList;
        this.teams=teams;
        this.teams_names=teams_names;

    }
    ImageView home_team,away_team;
    TextView date,hour,update,delete,home_name,away_name,score;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Game game=getItem(position);
        if(convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.game_bet, parent, false);
        }
        home_name=convertView.findViewById(R.id.home_name);
        away_name=convertView.findViewById(R.id.away_name);
        home_team=convertView.findViewById(R.id.home);
       score=convertView.findViewById(R.id.score);
        away_team=convertView.findViewById(R.id.away);
        date=convertView.findViewById(R.id.date);
        hour=convertView.findViewById(R.id.hour);
        update=convertView.findViewById(R.id.update);
        delete=convertView.findViewById(R.id.delete);
        try {
            Picasso.get().load(game.getHome_team().getPicture()).into(home_team);
            Picasso.get().load(game.getAway_team().getPicture()).into(away_team);
            date.setText(constants.date_format_by_day(game.getLast_date()));
            hour.setText(constants.date_format_by_time(game.getLast_date()));

        }
        catch (Exception e){

        }
        //delete game
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==delete){
                    remove(game);
                    games.remove(game);
                }
            }
        });

        /*
        update game details
         */
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(v==update){

                     update_details(game);
                 }
            }
        });

        return super.getView(position, convertView, parent);
    }
    /*
    update game details
     */
    DatePickerDialog.OnDateSetListener listener;
    Calendar calendar_meet;
    public void createdialogDATE()
    {


        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        final int hour=calendar.get(Calendar.HOUR);
        final int minute=calendar.get(Calendar.MINUTE);
        DatePickerDialog    datePickerDialog=new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,listener
                ,year,month,day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
        listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                datePickerDialog.dismiss();
                calendar_meet=Calendar.getInstance();
                TimePickerDialog timePickerDialog=new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        calendar_meet.set(year,month,dayOfMonth,hourOfDay,minute);
                        java.util.Date d=calendar.getTime();
                        date1.setText(constants.date_format_by_day(calendar_meet.getTime()) + constants.date_format_by_time(calendar_meet.getTime()));
                        String dTE= (String) DateFormat.format("hh:mm aa",calendar_meet);

                    }
                },12,0,false);

                timePickerDialog.updateTime(calendar_meet.getTime().getHours(),calendar_meet.getTime().getMinutes());
                timePickerDialog.show();


            }
        };

    }
    Team team;
    TextView date1;
    //update game details
    @SuppressLint("ResourceAsColor")
    private void update_details(Game game) {
        try {

             team=new Team();
            Dialog d = new Dialog(getContext());

            d.setContentView(R.layout.game_details);
            AutoCompleteTextView away, home;
            away = d.findViewById(R.id.away);
            home = d.findViewById(R.id.home);
            CircleImageView homeImage, awayImage;
            homeImage = d.findViewById(R.id.home_image);
            awayImage = d.findViewById(R.id.away_image);

            try {
                ArrayList<Profile_Adapter.profile> profiles=new ArrayList<>();
                for(Team team:teams){
                    Profile_Adapter.profile p=new Profile_Adapter.profile();
                    p.name=team.getName();
                    p.resource=team.getPicture();
                    profiles.add(p);
                }
                Profile_Adapter arrayAdapter = new Profile_Adapter(getContext(), profiles);
                away.setAdapter(arrayAdapter);

                home.setAdapter(arrayAdapter);


                home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                         team=teams.get(position);
                         game.setHome_team(team);
                         try{
                             Picasso.get().load(team.getPicture()).into(homeImage);
                         }
                         catch (Exception c){}
                    }
                });
                away.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        team=teams.get(position);
                        game.setAway_team(team);
                        try{
                            Picasso.get().load(team.getPicture()).into(awayImage);
                        }
                        catch (Exception c){}
                    }
                });
            } catch (Exception e) {

            }
            CircleImageView datePicker = d.findViewById(R.id.date_picker);
           date1 = d.findViewById(R.id.date);
            //set date
            datePicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v == datePicker) {

                        createdialogDATE();


                    }
                }
            });
            TextView save = d.findViewById(R.id.save);
            //save details
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v == save) {
                        if (away.getText().length() > 0 && home.getText().length() > 0) {
                            game.getAway_team().setName(away.getText().toString());
                            game.getHome_team().setName(home.getText().toString());
                            d.dismiss();
                        } else {
                            Toast.makeText(getContext(), "empty field", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

            d.show();
        }catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



}
