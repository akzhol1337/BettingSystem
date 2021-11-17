package com.company.Model.Repository;



import com.company.Model.Entities.User;
import com.company.Model.DB.IPostgresAdapter;

import java.sql.Connection;
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
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getInt(8)));
            }

            return users;

        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
}
