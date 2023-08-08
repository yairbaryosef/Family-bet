package com.example.family_bet.DB.Tours;

import com.example.family_bet.Classes.Game.Game;

public class Tour_for_Realtime {
    public String password;
    public Game first_Game;
    public String topic;
    public Tour_for_Realtime(){

    }
    public Tour_for_Realtime(String password,Game first_Game,String topic){
        this.password=password;
        this.first_Game=first_Game;
        this.topic=topic;
    }

}
