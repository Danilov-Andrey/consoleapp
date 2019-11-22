package com.nc.consoleapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.nc.consoleapp.consts.EnviromentVariables.*;

public class DB {
    private static DB instance;

    public static DB getInstance(){
        if (instance == null){
            instance = new DB();
        }
        return  instance;
    }

    public Connection getConnection() throws SQLException {
        String url = System.getenv(DB_URL);
        String userName = System.getenv(USER_NAME);
        String password = System.getenv(PASSWORD);
        try {
            Connection connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/dbtest?useTimezone=true&serverTimezone=UTC&useSSL=false", "root", "test");
            return connection;
        } catch (SQLException e){
            throw new SQLException(e);
        }
    }

    public void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
