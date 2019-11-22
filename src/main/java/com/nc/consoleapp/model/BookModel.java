package com.nc.consoleapp.model;

import com.nc.consoleapp.entities.Book;
import com.nc.consoleapp.model.view.View;
import com.nc.consoleapp.utils.DB;
import com.nc.consoleapp.utils.Output;

import java.sql.*;

public class BookModel implements Model<Book> {
    private static Model instance;

    public static Model getInstance(){
        if (instance == null){
            instance = new BookModel();
        }
        return  instance;
    }

    private String get = "SELECT book.id, book.name, book.year, author.first_name, author.last_name," +
            " copies.book_count, copies.book_condition, publisher.name, author.id, publisher.id, copies.id " +
            "from book join author on book.author_id = author.id " +
            "join copies on book.id = copies.book_id join publisher on book.publisher_id = publisher.id";
    private String update = "UPDATE book SET name = ? , year = ? WHERE id = ?;";
    private String check = "SELECT * FROM book WHERE id = ?";
    private String delete = "DELETE FROM book WHERE id = ?";

    private String authorCheck = "SELECT * FROM author where first_name = ? and last_name = ? ";
    private String publisherCheck = "SELECT * FROM publisher where name = ? ";
    private String createAuthor = "INSERT INTO author (first_name, last_name) VALUES (? , ?)";
    private String createPublisher  = "INSERT INTO publisher (name) VALUES (?)";
    private String createBook = "INSERT INTO book (name, year, author_id, publisher_id) VALUES (?, ?, ?, ?)";
    private String bookGetId = "SELECT * FROM book where name = ? and year = ? and author_id = ? and publisher_id = ? ";
    private String createCopies = "INSERT INTO copies (book_id, book_condition, book_count) VALUES (?, ?, ?)";
    private String checkBook = "SELECT * FROM book where name = ? and year = ? and author_id = ? and publisher_id = ? ";

    @Override
    public void get() throws SQLException {
        Connection connection = DB.getInstance().getConnection();;
        Statement statement = connection.createStatement();
        try {
            ResultSet response = statement.executeQuery(get);
            View.getInstance().renderBook(response);
        } catch (SQLException e){
            throw new SQLException(e);
        } finally {
            DB.getInstance().closeConnection(connection);
        }
    }

    @Override
    public void get(int id) throws SQLException {
        Connection connection = DB.getInstance().getConnection();
        Statement statement = connection.createStatement();
        String extraCondition =" and book.id = " + id;
        try {
            ResultSet response = statement.executeQuery(get + extraCondition);
            View.getInstance().renderBook(response);
        } catch (SQLException e){
            throw new SQLException(e);
        } finally {
            DB.getInstance().closeConnection(connection);
        }
    }

    @Override
    public void update(Book book) throws SQLException {
        Connection connection = DB.getInstance().getConnection();
        Statement statement = connection.createStatement();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setInt(2, book.getYear());
            preparedStatement.setInt(3, book.getId());
            preparedStatement.execute();
            Output.printSuccess();
        } catch (SQLException e){
            throw new SQLException(e);
        } finally {
            DB.getInstance().closeConnection(connection);
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = DB.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(check);
            preparedStatement.setInt(1, id);
            ResultSet response = preparedStatement.executeQuery();
            if (response.next()){
                preparedStatement = connection.prepareStatement(delete);
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
                Output.printSuccess();
            } else {
                Output.printExtraInfo();
            }
        } catch (SQLException e){
            throw new SQLException(e);
        } finally {
            DB.getInstance().closeConnection(connection);
        }
    }

    @Override
    public void create(Book book) throws SQLException {
        Connection connection = DB.getInstance().getConnection();
        int authorId;
        int bookId;
        int publisherId;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(authorCheck);
            preparedStatement.setString(1, book.getAuthorFirstName());
            preparedStatement.setString(2, book.getAuthorLastName());
            ResultSet response = preparedStatement.executeQuery();

            if (response.next()) {
                authorId = response.getInt("id");
            } else {
                preparedStatement = connection.prepareStatement(createAuthor);
                preparedStatement.setString(1, book.getAuthorFirstName());
                preparedStatement.setString(2, book.getAuthorLastName());
                preparedStatement.execute();

                preparedStatement = connection.prepareStatement(authorCheck);
                preparedStatement.setString(1, book.getAuthorFirstName());
                preparedStatement.setString(2, book.getAuthorLastName());
                response = preparedStatement.executeQuery();

                response.next();
                authorId = response.getInt("id");
            }

            preparedStatement = connection.prepareStatement(publisherCheck);
            preparedStatement.setString(1, book.getPublisherName());
            response = preparedStatement.executeQuery();

            if (response.next()) {
                publisherId = response.getInt("id");
            } else {
                preparedStatement = connection.prepareStatement(createPublisher);
                preparedStatement.setString(1, book.getPublisherName());
                preparedStatement.execute();

                preparedStatement = connection.prepareStatement(publisherCheck);
                preparedStatement.setString(1, book.getPublisherName());
                response = preparedStatement.executeQuery();
                response.next();
                publisherId = response.getInt("id");
            }

            preparedStatement = connection.prepareStatement(checkBook);
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setInt(2, book.getYear());
            preparedStatement.setInt(3, authorId);
            preparedStatement.setInt(4, publisherId);
            response = preparedStatement.executeQuery();

            if (response.next()) {
                System.out.println("Такая книга уже есть.");
            } else {
                preparedStatement = connection.prepareStatement(createBook);
                preparedStatement.setString(1, book.getBookName());
                preparedStatement.setInt(2, book.getYear());
                preparedStatement.setInt(3, authorId);
                preparedStatement.setInt(4, publisherId);
                preparedStatement.execute();

                preparedStatement = connection.prepareStatement(bookGetId);
                preparedStatement.setString(1, book.getBookName());
                preparedStatement.setInt(2, book.getYear());
                preparedStatement.setInt(3, authorId);
                preparedStatement.setInt(4, publisherId);
                response = preparedStatement.executeQuery();

                response.next();

                bookId = response.getInt("id");

                preparedStatement = connection.prepareStatement(createCopies);
                preparedStatement.setInt(1, bookId);
                preparedStatement.setInt(2, book.getCondition());
                preparedStatement.setInt(3, book.getCount());
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
