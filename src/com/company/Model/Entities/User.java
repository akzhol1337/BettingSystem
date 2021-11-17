package com.company.Model.Entities;

public class User {
    private int ID;
    private String email;
    private String password;
    private int totalBets;
    private int betsWon;
    private String ranking;
    private int cash;
    private int profit;

    public User(int ID, String email, String password, int totalBets, int betsWon, String ranking, int cash, int profit) {
        this.ID = ID;
        this.email = email;
        this.password = password;
        this.totalBets = totalBets;
        this.betsWon = betsWon;
        this.ranking = ranking;
        this.cash = cash;
        this.profit = profit;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTotalBets() {
        return totalBets;
    }

    public void setTotalBets(int totalBets) {
        this.totalBets = totalBets;
    }

    public int getBetsWon() {
        return betsWon;
    }

    public void setBetsWon(int betsWon) {
        this.betsWon = betsWon;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }
}