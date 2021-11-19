package com.company.Controller;

import com.company.Model.Entities.Event;
import com.company.Model.Entities.User;
import com.company.Model.Repository.IPostgreRepository;

import java.util.ArrayList;

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
    public void makeOrdinaryBet(int amount, User user, int eventID) throws Exception {

        if(amount > user.getBalance()){
            System.out.println("Not enough money on balance!");
            return;
        }

        repo.makeOrdinaryBet(amount, user.getID(), eventID, true);
        user.setBalance(user.getBalance() - amount);
        repo.changeBalance(amount, false, user.getID());
    }

    @Override
    public void makeExpressBet(int amount, User user, ArrayList<Integer> eventsID) throws Exception {
        if(amount > user.getBalance()){
            System.out.println("Not enough money on balance!");
            return;
        }

        repo.makeExpressBet(amount, user.getID(), eventsID, true);
        user.setBalance(user.getBalance() - amount);
        repo.changeBalance(amount, false, user.getID());
    }
}
