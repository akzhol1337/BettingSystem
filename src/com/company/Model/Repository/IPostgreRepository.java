package com.company.Model.Repository;

import com.company.Model.Entities.Event;
import com.company.Model.Entities.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IPostgreRepository {
    ArrayList<User> getAllUsers() throws Exception;
    User login(String email, String password) throws Exception;
    boolean register(String name, String email, String password, int age) throws Exception;
    ArrayList<Event> getAllEvents() throws Exception;
    ArrayList<Event> getEventsByCategory(String category) throws Exception;
    ArrayList<Event> getEventsByLeague(String category, String league) throws Exception;
    void changePassword(String ID, String newPassword) throws Exception;
    ArrayList<User> leaderboard() throws Exception;
    void makeOrdinaryBet(int amount, String userID, int eventID, boolean status) throws Exception;
    boolean makeExpressBet(int amount, String userID, ArrayList<Integer> eventsID) throws Exception;
    void changeBalance(int difference, boolean side, String userID) throws Exception;

    ArrayList<Double> getCoefficent(ArrayList<Integer> eventsID) throws Exception;

}
