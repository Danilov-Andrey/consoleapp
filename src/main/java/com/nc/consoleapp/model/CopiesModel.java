package com.nc.consoleapp.model;

import com.nc.consoleapp.entities.Copies;
import com.nc.consoleapp.model.view.View;
import com.nc.consoleapp.utils.DB;
import com.nc.consoleapp.utils.Output;

import java.sql.*;

public class CopiesModel implements Model<Copies> {
    public static Model instance;
    public static Model getInstance(){
        if (instance == null){
            instance = new CopiesModel();
        }
        return instance;
    }

    private String updateCopies = "UPDATE copies SET book_condition = ? , book_count = ? WHERE id = ?;";
    private String getCopiesById = "SELECT * FROM copies where id = ?";
    private String getCopies = "SELECT * FROM copies";

    @Override
    public void get() throws SQLException {
        Connection connection = DB.getInstance().getConnection();
        Statement statement = connection.createStatement();
        try {
            ResultSet response = statement.executeQuery(getCopies);
            View.getInstance().renderCopies(response);
        } catch (SQLException e){
            e.printStackTrace();
        }  finally {
            DB.getInstance().closeConnection(connection);
        }
    }

    @Override
    public void get(int id) throws SQLException {
        Connection connection = DB.getInstance().getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(getCopiesById);
            preparedStatement.setInt(1, id);
            ResultSet response = preparedStatement.executeQuery();

            View.getInstance().renderCopies(response);

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DB.getInstance().closeConnection(connection);
        }
    }

    @Override
    public void update(Copies copies) throws SQLException {
        Connection connection = DB.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateCopies);
            preparedStatement.setInt(1, copies.getCondition());
            preparedStatement.setInt(2, copies.getCount());
            preparedStatement.setInt(3, copies.getId());

            preparedStatement.execute();

            Output.printSuccess();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DB.getInstance().closeConnection(connection);
        }
    }

    @Override
    public void delete(int id) throws SQLException {}

    @Override
    public void create(Copies copies) throws SQLException {}
}
