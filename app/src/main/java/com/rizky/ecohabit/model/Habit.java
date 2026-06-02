package com.rizky.ecohabit.model;

public class Habit {

    int id;
    String title;
    int status;
    int points;

    public Habit(int id, String title, int status, int points) {

        this.id = id;
        this.title = title;
        this.status = status;
        this.points = points;
    }

    // GET ID
    public int getId() {
        return id;
    }

    // GET TITLE
    public String getTitle() {
        return title;
    }

    // GET STATUS
    public int getStatus() {
        return status;
    }

    // GET POINTS
    public int getPoints() {
        return points;
    }

    // SET STATUS
    public void setStatus(int status) {
        this.status = status;
    }
}