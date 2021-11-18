package com.company.Model.Repository;



import com.company.Model.Entities.Event;
import com.company.Model.Entities.User;
import com.company.Model.DB.IPostgresAdapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PostgreRepository implements IPostgreRepository {
    private final IPostgresAdapter db;

    public PostgreRepository(IPostgresAdapter db) {
        this.db = db;
    }

    @Override
    public ArrayList<User> getAllUsers() throws Exception{
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        ArrayList < User > users = new ArrayList<>();

        try {
            con = db.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from users");

            while(rs.next()){
                users.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10)));
            }

            st.close();
            return users;

        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public User login(String email, String password) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        User account = null;

        try {
            con = db.getConnection();
            st = con.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");
            st.setString(1, email);
            st.setString(2, password);

            rs = st.executeQuery();

            while(rs.next()){
                account = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10));
                st.close();
                return account;
            }
            st.close();
            return null;

        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean register(String name, String email, String password, int age) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = db.getConnection();
            st = con.prepareStatement("SELECT COUNT(*) FROM users WHERE email=?");
            st.setString(1, email);

            rs = st.executeQuery();

            boolean exists = false;

            while(rs.next()){
                if(rs.getInt(1) == 1) exists = true;
            }

            if(exists) {
                st.close();
                return false;
            }
            st = con.prepareStatement("INSERT INTO users VALUES(?, ?, ?, ?, ?, 0, 0, 'Hazik', 0, 0)");
            st.setString(1, name);
            st.setString(2, email);
            st.setString(3, email);
            st.setString(4, password);
            st.setInt(5, age);

            st.executeUpdate();

            st.close();

            return true;


        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public ArrayList<Event> getAllEvents() throws Exception {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        ArrayList < Event > events = new ArrayList<>();

        try {
            con = db.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from events");



            while(rs.next()){
                events.add(new Event(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7), rs.getString(8), rs.getInt(9), rs.getString(10)));
            }

            st.close();
            return events;

        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public ArrayList<Event> getEventsByCategory(String category) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        ArrayList < Event > events = new ArrayList<>();

        try {
            con = db.getConnection();

            st = con.prepareStatement("SELECT * from events where category=?");
            st.setString(1, category);

            rs = st.executeQuery();

            while(rs.next()){
                events.add(new Event(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7), rs.getString(8), rs.getInt(9), rs.getString(10)));
            }

            st.close();
            return events;

        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public ArrayList<Event> getEventsByLeague(String category, String league) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        ArrayList < Event > events = new ArrayList<>();

        try {
            con = db.getConnection();

            st = con.prepareStatement("SELECT * from events where category=? AND league=?");
            st.setString(1, category);
            st.setString(2, league);

            rs = st.executeQuery();

            while(rs.next()){
                events.add(new Event(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7), rs.getString(8), rs.getInt(9), rs.getString(10)));
            }

            st.close();
            return events;

        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
