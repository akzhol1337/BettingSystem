package com.company.Model.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresAdapter implements IPostgresAdapter{

    private static PostgresAdapter instance;

    private PostgresAdapter() {

    }

    public static PostgresAdapter getInstance(){
        if(instance == null){
            instance = new PostgresAdapter();
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String connectionURL = "jdbc:postgresql://localhost:5432/BettingSystem";
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(connectionURL, "postgres", "1234");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
