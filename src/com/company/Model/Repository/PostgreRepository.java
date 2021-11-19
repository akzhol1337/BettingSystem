package com.company.Model.Repository;



import com.company.Model.Entities.Event;
import com.company.Model.Entities.User;
import com.company.Model.DB.IPostgresAdapter;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

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
                events.add(new Event(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getString(9), rs.getInt(10), rs.getString(11)));
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
                events.add(new Event(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getString(9), rs.getInt(10), rs.getString(11)));
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
                events.add(new Event(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getString(9), rs.getInt(10), rs.getString(11)));
            }

            st.close();
            return events;

        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void changePassword(String ID, String newPassword) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = db.getConnection();

            st = con.prepareStatement("UPDATE users SET password = ? WHERE id = ?");
            st.setString(1, newPassword);
            st.setString(2, ID);

            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public ArrayList<User> leaderboard() throws Exception {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        ArrayList < User > users = new ArrayList<>();

        try {
            con = db.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from users ORDER BY profit DESC LIMIT 10");

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
    public void makeOrdinaryBet(int amount, String userID, int eventID, boolean status) throws Exception{
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = db.getConnection();

            st = con.prepareStatement("INSERT INTO betHistory values((SELECT MAX(betid)+1 from bethistory), ?, ?, ?, ?)");
            st.setString(1, userID);
            st.setInt(2, eventID);
            st.setInt(3, amount);
            st.setBoolean(4, status);

            st.executeUpdate();
            st.close();

        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void changeBalance(int difference, boolean side, String userID) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = db.getConnection();

            String sql = "UPDATE users SET balance = balance" + (side ? "+" : "-") + "? WHERE id = ?";


            st = con.prepareStatement(sql);
            st.setInt(1, difference);
            st.setString(2, userID);

            st.executeUpdate();
            st.close();

        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean makeExpressBet(int amount, String userID, ArrayList<Integer> eventsID) throws Exception {
        Connection con = null;
        Statement st1 = null;

        ResultSet rs = null;

        try {
            con = db.getConnection();

            st1 = con.createStatement();

            String sql = "INSERT INTO bethistory VALUES ";

            Random r = new Random();

            boolean outcome = true;
            double totalCoeff = 1;

            for(int i = 0; i < eventsID.size(); i++){
                boolean currentOutcome = r.nextBoolean();
                if(!currentOutcome){
                    outcome = false;
                }
                if(i != 0) {
                    sql += ", ";
                }
                sql += "(" + "(SELECT MAX(betid)+1 from bethistory)" + ", '" + userID + "', " + eventsID.get(i) + ", " + amount + ", " + outcome + " )";
            }

            sql += ";";

            st1.executeUpdate(sql);
            st1.close();
            return outcome;
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public ArrayList<Double> getCoefficent(ArrayList<Integer> eventsID) throws Exception {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        User account = null;

        try {
            con = db.getConnection();
            String sql = "SELECT coeffwin1, coeffwin2, coeffdraw FROM events WHERE id IN (";

            for(int i = 0; i < eventsID.size(); i++){
                if(i != eventsID.size() - 1) {
                    sql += i + ", ";
                } else {
                    sql += ")";
                }
            }
            st = con.createStatement();

            ArrayList<Double> Coefficients = new ArrayList<Double>();

            return Coefficients;

        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
