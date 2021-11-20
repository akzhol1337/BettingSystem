package com.company.Model.Entities;

public class Bet {
    private int ID;
    private String userID;
    private int eventID;
    private int amount;
    private boolean status;
    private short choice;
    private String team1;
    private String team2;
    private double coeff;

    public Bet(int ID, String userID, int eventID, int amount, boolean status, short choice, String team1, String team2, double coeff) {
        this.ID = ID;
        this.userID = userID;
        this.eventID = eventID;
        this.amount = amount;
        this.status = status;
        this.choice = choice;
        this.team1 = team1;
        this.team2 = team2;
        this.coeff = coeff;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public short getChoice() {
        return choice;
    }

    public void setChoice(short choice) {
        this.choice = choice;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public double getCoeff() {
        return coeff;
    }

    public void setCoeff(double coeff) {
        this.coeff = coeff;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "ID=" + ID +
                ", userID='" + userID + '\'' +
                ", eventID=" + eventID +
                ", amount=" + amount +
                ", status=" + status +
                ", choice=" + choice +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", coeff=" + coeff +
                '}';
    }
}
