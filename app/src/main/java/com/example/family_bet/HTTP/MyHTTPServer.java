package com.example.family_bet.HTTP;

import com.example.family_bet.Classes.Game.Tournament;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

public class MyHTTPServer{
    public Tournament tour;
    private String url;
    //Database
    Properties prop;
   Gson gson;
   public MyHTTPServer(){

   }
   public MyHTTPServer(Tournament tournament){
       tour=tournament;
       gson=new Gson();
      prop = new Properties();
       try {

          String tojson=gson.toJson(tour);
          prop.setProperty(tour.getTour_name(),tojson);
           url=tour.getTour_name();
       }
       catch (Exception e){

       }


   }

    /**\
     * start connection to the server
     * @param port-port

     * @throws IOException
     */
    public void connect(int port) throws IOException {
        ServerSocket server = new ServerSocket(port);
        System.out.println("Listening on port " + port);

        while (true) {
            Socket client = server.accept();
            System.out.println("Accepted connection from " + client.getRemoteSocketAddress());
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

            String request = in.readLine();
            System.out.println(request);
            if (request.equals("read")) {
                Gson gson=new Gson();
                String response=gson.toJson(readData());
                out.println(response);
                out.flush();
            }
            else {
                String newTour = in.readLine();
                updateTour(newTour);
            }

         client.close();

        }
    }




    public void updateTour(String newTour) {

      /*  Gson gson=new Gson();

        Tournament tournament=gson.fromJson(newTour,Tournament.class);
        int index=0;
        url=tournament.getTour_name();
        String json=prop.getProperty(url);
        tour=gson.fromJson(json,Tournament.class);
        //IF PREDICTOR EXIST UPDATE ONLY THE PREDICTOR
         if(tour.predictorHashMap.containsKey(tournament.type)){
             for(Predictor p:tour.getPredictors()){
                 if(p.getUsername().equals(tournament.type)){
                     p=tournament.predictorHashMap.get(tournament.last_user);
                     tour.predictorHashMap.put(tournament.last_user,tournament.predictorHashMap.get(tournament.last_user));
                 }
             }
         }
         //ELSE
         else{
             tour.predictorHashMap.put(tournament.last_user,tournament.predictorHashMap.get(tournament.last_user));
             tour.getPredictors().add(tournament.predictorHashMap.get(tournament.last_user));
         }
         writeData(tour);
*/

    }

    /**
     * write tournament to file
     * @param tournament
     */
    public void writeData(Tournament tournament){
        try {

            String data=gson.toJson(tournament);
            prop.setProperty(url,data);
        }catch (Exception e){}

    }

    /**
     * get the tournament
     * @return string represent the tournament
     */
    public Tournament readData(){
       String string="";
       try{

           string=prop.getProperty(url);
           Tournament tournament=gson.fromJson(string,Tournament.class);
           return tournament;

       }catch (Exception e){

       }
       return null;
    }


    public String Ip(){
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String hostAddress = inetAddress.getHostAddress();
            System.out.println("Local host address: " + hostAddress);
            return hostAddress;
        } catch (UnknownHostException e) {
            System.err.println("Could not get the IP address of the local machine.");
            e.printStackTrace();
            return "";
        }

    }



}
