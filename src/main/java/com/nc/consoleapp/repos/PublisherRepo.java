package com.nc.consoleapp.repos;

import com.nc.consoleapp.entities.Publisher;
import com.nc.consoleapp.repos.interfaces.Repo;
import com.nc.consoleapp.view.View;
import com.nc.consoleapp.utils.DB;
import com.nc.consoleapp.utils.Output;

import java.sql.*;

public class PublisherRepo implements Repo<Publisher> {
    private static Repo instance;

    public static Repo getInstance(){
        if (instance == null){
            instance = new PublisherRepo();
        }
        return  instance;
    }

    private String getPublishers = "SELECT * FROM publisher;";
    private String getPublisher = "SELECT * FROM publisher WHERE id = ?;";
    private String updatePublisher = "UPDATE publisher SET name = ? WHERE id = ?;";
    private String createPublisher = "INSERT INTO publisher (name) VALUES (?);";
    private String deletePublisher = "DELETE FROM publisher WHERE id = ?;";
    private String checkPublisher = "SELECT  * FROM publisher WHERE name = ?;";

    @Override
    public void get() throws SQLException {
        Connection connection = DB.getInstance().getConnection();
        Statement statement = connection.createStatement();
        try {
            ResultSet response = statement.executeQuery(getPublishers);
            View.getInstance().renderPublisher(response);
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DB.getInstance().closeConnection(connection);
        }
    }

    @Override
    public void get(int id) throws SQLException {
        Connection connection = DB.getInstance().getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(getPublisher);
            preparedStatement.setInt(1, id);
            ResultSet response = preparedStatement.executeQuery();
            View.getInstance().renderPublisher(response);
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DB.getInstance().closeConnection(connection);
        }
    }

    @Override
    public void update(Publisher publisher) throws SQLException {
        Connection connection = DB.getInstance().getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(updatePublisher);
            preparedStatement.setString(1, publisher.getName());
            preparedStatement.setInt(2, publisher.getId());
            preparedStatement.execute();
            Output.printSuccess();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DB.getInstance().closeConnection(connection);
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = DB.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getPublisher);
            preparedStatement.setInt(1, id);
            ResultSet response = preparedStatement.executeQuery();
            if (response.next()){
                preparedStatement = connection.prepareStatement(deletePublisher);
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
                Output.printSuccess();
            } else {
                Output.printExtraInfo();
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DB.getInstance().closeConnection(connection);
        }
    }

    @Override
    public void create(Publisher publisher) throws SQLException {
        Connection connection = DB.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(checkPublisher);
            preparedStatement.setString(1, publisher.getName());
            ResultSet response = preparedStatement.executeQuery();
            if(response.next()){
                System.out.println("Такой издатель уже есть.");
            } else {
                preparedStatement = connection.prepareStatement(createPublisher);
                preparedStatement.setString(1, publisher.getName());
                preparedStatement.execute();
                Output.printSuccess();
            }
        } catch (SQLException e){
           e.printStackTrace();
        } finally {
            DB.getInstance().closeConnection(connection);
        }
    }
}
