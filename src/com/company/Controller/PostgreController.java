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
}
