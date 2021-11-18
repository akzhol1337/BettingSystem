package com.company.Model.Entities;

public class User {
    private String name;
    private String ID;
    private String email;
    private String password;
    private int age;
    private int totalBets;
    private int betsWon;
    private String ranking;
    private int balance;
    private int profit;

    public User(String name, String ID, String email, String password, int age, int totalBets, int betsWon, String ranking, int balance, int profit) {
        this.name = name;
        this.ID = ID;
        this.email = email;
        this.password = password;
        this.age = age;
        this.totalBets = totalBets;
        this.betsWon = betsWon;
        this.ranking = ranking;
        this.balance = balance;
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", ID='" + ID + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", totalBets=" + totalBets +
                ", betsWon=" + betsWon +
                ", ranking='" + ranking + '\'' +
                ", balance=" + balance +
                ", profit=" + profit;
    }
}
