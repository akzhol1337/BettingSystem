package com.company.Model.DB;

import java.sql.Connection;
import java.sql.SQLException;

public interface IPostgresAdapter {
    Connection getConnection() throws SQLException, ClassNotFoundException;
}
