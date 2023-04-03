package com.example.family_bet.Classes.Game;

public class Tournament_for_saved {
    private String tour_name;
    private String password;
    private String host;
    private Game game;
    private String dealer;
    private int port;
    public Tournament_for_saved(){

    }

    public String getPassword() {
        return password;
    }

    public String getTour_name() {
        return tour_name;
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTour_name(String tour_name) {
        this.tour_name = tour_name;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDealer() {
        return dealer;
    }

    public Game getGame() {
        return game;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
