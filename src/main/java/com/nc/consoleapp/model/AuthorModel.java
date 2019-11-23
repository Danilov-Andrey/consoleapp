package com.nc.consoleapp.model;

import com.nc.consoleapp.entities.Author;
import com.nc.consoleapp.model.view.View;
import com.nc.consoleapp.utils.DB;
import com.nc.consoleapp.utils.Output;

import java.sql.*;

public class AuthorModel implements Model<Author> {
    private static Model instance;

    public static Model getInstance(){
        if (instance == null){
            instance = new AuthorModel();
        }
        return  instance;
    }

    private String createAuthor = "INSERT INTO author (first_name, last_name) VALUES (?, ?);";
    private String checkAuthor = "SELECT * FROM author WHERE first_name = ? AND last_name = ? ;";
    private String deleteAuthor = "DELETE FROM author WHERE id = ? ;";
    private String changeAuthor = "UPDATE author SET first_name = ? , last_name = ? WHERE id = ?;";
    private String getAuthors = "SELECT * FROM author";
    private String getAuthor = "SELECT * FROM author WHERE id = ?";

    @Override
    public void get() throws SQLException {
        Connection connection = DB.getInstance().getConnection();
        Statement statement = connection.createStatement();
        try {
            ResultSet response = statement.executeQuery(getAuthors);
            View.getInstance().renderAuthor(response);
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
            PreparedStatement preparedStatement = connection.prepareStatement(getAuthor);
            preparedStatement.setInt(1, id);
            ResultSet response = preparedStatement.executeQuery();
            View.getInstance().renderAuthor(response);
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DB.getInstance().closeConnection(connection);
        }
    }

    @Override
    public void update(Author author) throws SQLException {
        Connection connection = DB.getInstance().getConnection();
        try {
            PreparedStatement  preparedStatement  = connection.prepareStatement(changeAuthor);
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.setInt(3, author.getId());
            preparedStatement.executeUpdate();
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
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(getAuthor);
            preparedStatement.setInt(1, id);
            ResultSet response = preparedStatement.executeQuery();
            if (response.next()){
                preparedStatement = connection.prepareStatement(deleteAuthor);
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
                Output.printSuccess();
            } else {
                System.out.println("Такого автора нет.");
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DB.getInstance().closeConnection(connection);
        }
    }

    @Override
    public void create(Author author) throws SQLException {
        Connection connection = DB.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(checkAuthor);
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            ResultSet response = preparedStatement.executeQuery();
            if(response.next()){
                System.out.println("Такой автор уже есть.");
            } else {
                preparedStatement = connection.prepareStatement(createAuthor);
                preparedStatement.setString(1, author.getFirstName());
                preparedStatement.setString(2, author.getLastName());
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
