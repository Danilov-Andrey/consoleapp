package com.nc.consoleapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.nc.consoleapp.consts.DatabaseVariables;

public class DB {
    private static DB instance;

    public static DB getInstance(){
        if (instance == null){
            instance = new DB();
        }
        return  instance;
    }

    public Connection getConnection() throws SQLException {
        String url = DatabaseVariables.DB_URL;
        String userName = DatabaseVariables.USER_NAME;
        String password = DatabaseVariables.PASSWORD;
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
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
