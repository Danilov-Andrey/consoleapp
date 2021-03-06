package com.nc.consoleapp.repos;

import com.nc.consoleapp.entities.Copies;
import com.nc.consoleapp.repos.interfaces.ReadUpdateRepo;
import com.nc.consoleapp.view.View;
import com.nc.consoleapp.utils.DB;
import com.nc.consoleapp.utils.Output;

import java.sql.*;

public class CopiesRepo implements ReadUpdateRepo<Copies> {
    public static ReadUpdateRepo instance;
    public static ReadUpdateRepo getInstance(){
        if (instance == null){
            instance = new CopiesRepo();
        }
        return instance;
    }

    private String updateCopies = "UPDATE copies SET book_condition = ? , book_count = ? WHERE id = ?;";
    private String getCopiesById = "SELECT * FROM copies WHERE id = ?";
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
}
