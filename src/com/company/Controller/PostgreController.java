package com.company.Controller;

import com.company.Model.Entities.Bet;
import com.company.Model.Entities.Event;
import com.company.Model.Entities.User;
import com.company.Model.Repository.IPostgreRepository;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class PostgreController implements IPostgreController{
    private final IPostgreRepository repo;

    public PostgreController(IPostgreRepository repo) {
        this.repo = repo;
    }

    public ArrayList<User> getAllUsers() throws Exception {
        return repo.getAllUsers();
    }

    public User login(String email, String password) throws Exception{
        return repo.login(email, password);
    }

    public boolean register(String name, String email, String password, int age) throws Exception{
        return repo.register(name, email, password, age);
    }

    @Override
    public ArrayList<Event> getAllEvents() throws Exception {
        return repo.getAllEvents();
    }

    @Override
    public ArrayList<Event> getEventsByCategory(String category) throws Exception {
        return repo.getEventsByCategory(category);
    }

    @Override
    public ArrayList<Event> getEventsByLeague(String category, String league) throws Exception {
        return repo.getEventsByLeague(category, league);
    }

    @Override
    public void changePassword(String ID, String newPassword) throws Exception {
        repo.changePassword(ID, newPassword);
    }

    @Override
    public ArrayList<User> leaderboard() throws Exception {
        return repo.leaderboard();
    }

    @Override
    public void makeOrdinaryBet(int amount, User user, int eventID, short pick) throws Exception {

        if(amount > user.getBalance()){
            System.out.println("Not enough money on balance!");
            return;
        }

        Random r = new Random();
        boolean outcome = r.nextBoolean();

        double coeff = repo.getCoefficentOrindary(eventID, pick);
        user.setTotalBets(user.getTotalBets()+1);

        System.out.println(coeff);
        repo.makeOrdinaryBet(amount, user.getID(), eventID, outcome, pick);

        if(outcome){
            int profit = (int)(amount * (coeff-1));
            user.setBalance(user.getBalance() + profit);
            user.setBetsWon(user.getBetsWon()+1);

            repo.changeBalance(profit, true, user.getID());
            repo.changeBetStatistics(user.getID(), profit, true);
        } else {
            user.setBalance(user.getBalance() - amount);

            repo.changeBalance(amount, false, user.getID());
            repo.changeBetStatistics(user.getID(), 0, false);
        }
    }

    @Override
    public void makeExpressBet(int amount, User user, ArrayList<Integer> eventsID, Map< Integer, Short > mapPick) throws Exception {
        if(amount > user.getBalance()){
            System.out.println("Not enough money on balance!");
            return;
        }
        boolean outcome = repo.makeExpressBet(amount, user.getID(), eventsID, mapPick);
        user.setTotalBets(user.getTotalBets()+1);
        if(outcome){
            double coeff = repo.getCoefficentExpress(eventsID, mapPick);
            int profit = (int)(amount * (coeff-1));
            user.setBalance(user.getBalance() + profit);
            repo.changeBalance(profit, true, user.getID());
            user.setBetsWon(user.getBetsWon()+1);
            user.setProfit(user.getProfit() + profit);

            repo.changeBetStatistics(user.getID(), profit, true);
        } else {
            user.setBalance(user.getBalance() - amount);
            repo.changeBalance(amount, false, user.getID());


            repo.changeBetStatistics(user.getID(), 0, false);
        }
    }

    @Override
    public ArrayList<Bet> getBetHistory(String userID) throws Exception {
        return repo.getBetHistory(userID);
    }
}
