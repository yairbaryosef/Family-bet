package com.example.family_bet.Classes.Game.team;

import java.util.Objects;

public class Team {
    private String name;
    private String picture;
    public Team(){

    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }

    public Team(String name, String picture){
        this.name=name;
        this.picture=picture;
    }
    /*
    getters
     */

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }
    /*
    setters
     */

    public void setName(String name) {
        this.name = name;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name) && Objects.equals(picture, team.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, picture);
    }
}
