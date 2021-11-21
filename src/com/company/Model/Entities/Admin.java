package com.company.Model.Entities;

import java.util.ArrayList;

public class Admin {
    private int ID;
    private String login;
    private String password;
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Event> events = new ArrayList<>();

    public Admin(int ID, String login, String password) {
        this.ID = ID;
        this.login = login;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addUser(User user){
        users.add(user);
    }
    public void removeUser(User user){
        users.remove(user);
    }

    public void addEvent(Event event){
        events.add(event);
    }
    public void removeEvent(Event event){
        events.remove(event);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Event> getEventsID() {
        return events;
    }
    public Event findEventById(int ID){
        for(Event event : events){
            if(event.getID() == ID) {
                return event;
            }
        }
        return null;
    }

    public User findUserById(String ID){
        for(User user : users){
            if(user.getID() == ID){
                return user;
            }
        }
        return null;
    }
}
