package com.company.Model.Entities;

import java.util.Date;

public class Event {
    private int ID;
    private String category;
    private String firstPlayer;
    private String secondPlayer;
    private Date dateOfMatch;
    private double coeffWin1;
    private double coeffWin2;
    private double coeffDraw;
    private String location;
    private int moneyLimit;
    private String league;



    public Event(int ID, String category, String firstPlayer, String secondPlayer, Date dateOfMatch, double coeffWin1, double coeffWin2, double coeffDraw, String location, int moneyLimit, String league) {
        this.ID = ID;
        this.category = category;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.dateOfMatch = dateOfMatch;
        this.coeffWin1 = coeffWin1;
        this.coeffWin2 = coeffWin2;
        this.coeffDraw = coeffDraw;
        this.location = location;
        this.moneyLimit = moneyLimit;
        this.league = league;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(String firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public String getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(String secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public Date getDateOfMatch() {
        return dateOfMatch;
    }

    public void setDateOfMatch(Date dateOfMatch) {
        this.dateOfMatch = dateOfMatch;
    }

    public double getCoeffWin1() {
        return coeffWin1;
    }

    public void setCoeffWin1(double coeffWin1) {
        this.coeffWin1 = coeffWin1;
    }

    public double getCoeffWin2() {
        return coeffWin2;
    }

    public void setCoeffWin2(double coeffWin2) {
        this.coeffWin2 = coeffWin2;
    }

    public double getCoeffDraw() {
        return coeffDraw;
    }

    public void setCoeffDraw(double coeffDraw) {
        this.coeffDraw = coeffDraw;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMoneyLimit() {
        return moneyLimit;
    }

    public void setMoneyLimit(int moneyLimit) {
        this.moneyLimit = moneyLimit;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    @Override
    public String toString() {
        return "#" + ID + ": " + firstPlayer + " VS " + secondPlayer + " - W1: " + coeffWin1 + " X: " + coeffDraw + " W2: " + coeffWin2 + "\n";
    }


}
