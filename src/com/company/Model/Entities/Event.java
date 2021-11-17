package com.company.Model.Entities;

import java.util.Date;

public class Event {
    private String category;
    private String firstPlayer;
    private String secondPlayer;
    private Date dateOfMatch;
    private double coeffWin1;
    private double coeffWin2;
    private String location;
    private int moneyLimit;
    private String league;

    public Event(String category, String firstPlayer, String secondPlayer, Date dateOfMatch, double coeffWin1, double coeffWin2, String location, int moneyLimit, String league) {
        this.category = category;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.dateOfMatch = dateOfMatch;
        this.coeffWin1 = coeffWin1;
        this.coeffWin2 = coeffWin2;
        this.location = location;
        this.moneyLimit = moneyLimit;
        this.league = league;
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
}
