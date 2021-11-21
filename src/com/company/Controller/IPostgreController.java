package com.company.Controller;

import com.company.Model.Entities.Bet;
import com.company.Model.Entities.Event;
import com.company.Model.Entities.User;
import com.company.Model.Repository.IPostgreRepository;

import java.util.ArrayList;
import java.util.Map;

public interface IPostgreController {
    ArrayList<User> getAllUsers() throws Exception;
    User login(String email, String password) throws Exception;
    void changeBalance(String ID, int newBalance) throws Exception;
    void changeRank(String ID, String newRank) throws Exception;
    void removeEvent(int ID) throws Exception;
    void editEventInfo(int ID, String newInfo, short pick) throws Exception;
    void editEventCoefficient(int ID, double newCoefficient, short pick) throws Exception;
    boolean register(String name, String email, String password, int age) throws Exception;
    ArrayList<Event> getAllEvents() throws Exception;
    ArrayList<Event> getEventsByCategory(String category) throws Exception;
    ArrayList<Event> getEventsByLeague(String category, String league) throws Exception;
    void changePassword(String ID, String newPassword) throws Exception;
    ArrayList<User> leaderboard() throws Exception;
    ArrayList<Bet> getBetHistory(String userID) throws Exception;
}
