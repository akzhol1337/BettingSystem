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
        String connectionURL = "jdbc:postgresql://ec2-34-194-119-178.compute-1.amazonaws.com:5432/d6i0u20epksrj0";
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(connectionURL, "glpeslkjefmldw", "2ff26e34d362e4066a37f620bb299d0f9b00d1efd5a1c274c30ab43ebd8b5f4e");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
