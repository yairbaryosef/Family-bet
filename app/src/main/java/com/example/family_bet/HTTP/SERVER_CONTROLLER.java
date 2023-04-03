package com.example.family_bet.HTTP;

import com.example.family_bet.Classes.Game.Tournament;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SERVER_CONTROLLER {
    /**
     * GET THE TOURNAMENT FROM SERVER
     * @param host-SERVER HOST
     * @param port-SERVER PORT
     * @return-RETURN THE LAST UPDATED TOURNAMENT
     */
    public static Tournament read_from(String host, int port){
        Tournament tour=new Tournament();
        try {


            Socket socket = new Socket(host, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("read");


            //GET THE TOURNAMENT FROM THE SERVER
            boolean Not_done_read = true;
            String json = "";


            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message="";
            while(in.readLine()!=null) {
                message = in.readLine();
            }
            Gson gson = new Gson();
            tour = gson.fromJson(message, Tournament.class);
            System.out.println(message);
            //REGISTER
            socket.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return tour;
    }

    /**
     * WRITE TO SERVER THE UPDATE TOUR
     * @param host-SERVER HOST
     * @param port-SERVER PORT
     * @param tour-UPDATED TOUR
     */
    public static void write_TO(String host,int port,Tournament tour){

        try {


            Socket socket = new Socket(host, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Gson gson=new Gson();
            String json=gson.toJson(tour);
            out.println(json);
            socket.close();
        }catch (Exception e){}

    }
}
