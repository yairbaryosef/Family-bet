package com.example.family_bet.View_Model.User_Activity.Delar_activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.Classes.Enteties.Predictor;
import com.example.family_bet.Classes.Game.Game;
import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.Classes.Game.Tournament_Controller;
import com.example.family_bet.R;
import com.example.family_bet.databinding.ActivityDelarBinding;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class Delar_Activity extends AppCompatActivity implements View.OnClickListener {
    Tournament tournament=new Tournament();
   ActivityDelarBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDelarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init_tournament();
        Toast.makeText(this, tournament.getTour_name(), Toast.LENGTH_SHORT).show();
       Game_Update_Adapter game_update_adapter=new Game_Update_Adapter(this,tournament.getGames(),tournament);
       binding.list.setAdapter(game_update_adapter);
       binding.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Game game= tournament.getGames().get(position);
               update_game(game);
           }
       });
        binding.save.setOnClickListener(this);
    }

    private void update_game(Game game) {
        Dialog d=new Dialog(this);
        d.setContentView(R.layout.bet_card_for_update);
        init_views_for_dialog(d,game);

       d.setOnDismissListener(new DialogInterface.OnDismissListener() {
           @Override
           public void onDismiss(DialogInterface dialog) {
               Game_Update_Adapter game_update_adapter=new Game_Update_Adapter(Delar_Activity.this,tournament.getGames(),tournament);
               binding.list.setAdapter(game_update_adapter);
           }
       });
        d.show();

    }
    ImageView home_team,away_team;
    TextView date,hour,home_name,away_name,score;

    /**
     * init all views to update game details
     * @param convertView
     * @param game
     */
    private void init_views_for_dialog(Dialog convertView,Game game) {
        home_name = convertView.findViewById(R.id.home_name);
        away_name = convertView.findViewById(R.id.away_name);
        home_team = convertView.findViewById(R.id.home);
        score = convertView.findViewById(R.id.score);
        away_team = convertView.findViewById(R.id.away);

        date = convertView.findViewById(R.id.date);
        hour = convertView.findViewById(R.id.hour);

        try {
            Picasso.get().load(game.getHome_team().getPicture()).into(home_team);
            Picasso.get().load(game.getAway_team().getPicture()).into(away_team);
            date.setText(constants.date_format_by_day(game.getLast_date()));
            hour.setText(constants.date_format_by_time(game.getLast_date()));
            home_name.setText(game.getHome_team().getName());
            away_name.setText(game.getAway_team().getName());


            TextView home_bet, away_bet;
            home_bet = convertView.findViewById(R.id.bet_home);
            away_bet = convertView.findViewById(R.id.bet_away);
            try {


                home_bet.setText(String.valueOf(game.getScore_home_team()));

                away_bet.setText(String.valueOf(game.getScore_away_team()));
            } catch (Exception e) {

            }
            home_bet.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    try {
                        int i = Integer.valueOf(s.toString());
                        game.setScore_home_team(i);

                    } catch (Exception e) {
                        Toast.makeText(Delar_Activity.this, "enter number", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            away_bet.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    try {
                        int i = Integer.valueOf(s.toString());
                        game.setScore_away_team(i);

                    } catch (Exception e) {
                        Toast.makeText(Delar_Activity.this, "enter number", Toast.LENGTH_SHORT).show();
                    }
                }
            });



        }
        catch (Exception e){

        }
        try {
            CircleImageView datePicker = convertView.findViewById(R.id.date_picker);

            //set date
            datePicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v == datePicker) {

                        createdialogDATE(game);


                    }
                }
            });
        }
        catch (Exception e){

        }
    }

    private void init_tournament() {
        String json=getIntent().getStringExtra(constants.tournament);
        Gson gson=new Gson();
        tournament=gson.fromJson(json,Tournament.class);
    }

    @Override
    public void onClick(View v) {
        if(v==binding.save){
            Tournament_Controller tournament_controller=new Tournament_Controller(tournament);
            for(Predictor predictor:tournament.getPredictors()){
                predictor.setTotal_points(0);
                tournament_controller.set_Total_Point(predictor);
            }
            Tournament.save_tournament(tournament);
            Toast.makeText(this, "tournament update", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *
     */
    public void createdialogDATE(Game game)
    {


        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        final int hour=calendar.get(Calendar.HOUR);
        final int minute=calendar.get(Calendar.MINUTE);
        DatePickerDialog datePickerDialog=new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,listener
                ,year,month,day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
        listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                datePickerDialog.dismiss();
                calendar_meet=Calendar.getInstance();
                TimePickerDialog timePickerDialog=new TimePickerDialog(Delar_Activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        calendar_meet.set(year,month,dayOfMonth,hourOfDay,minute);
                        java.util.Date d=calendar.getTime();
                        date.setText(constants.date_format_by_day(calendar_meet.getTime()) + constants.date_format_by_time(calendar_meet.getTime()));
                      game.setLast_date(calendar_meet.getTime());
                        String dTE= (String) DateFormat.format("hh:mm aa",calendar_meet);

                    }
                },12,0,false);

                timePickerDialog.updateTime(calendar_meet.getTime().getHours(),calendar_meet.getTime().getMinutes());
                timePickerDialog.show();


            }
        };

    }
    DatePickerDialog.OnDateSetListener listener;
    Calendar calendar_meet;

}