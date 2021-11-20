package com.company.Model.Entities;

public class Bet {
    private int ID;
    private String userID;
    private int eventID;
    private int amount;
    private boolean status;

    public Bet(int ID, String userID, int eventID, int amount, boolean status) {
        this.ID = ID;
        this.userID = userID;
        this.eventID = eventID;
        this.amount = amount;
        this.status = status;
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

    @Override
    public String toString() {
        return "Bet{" +
                "ID=" + ID +
                ", userID='" + userID + '\'' +
                ", eventID=" + eventID +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }


}
