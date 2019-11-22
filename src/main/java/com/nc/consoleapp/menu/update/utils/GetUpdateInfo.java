package com.nc.consoleapp.menu.update.utils;

import com.nc.consoleapp.entities.Author;
import com.nc.consoleapp.entities.Book;
import com.nc.consoleapp.entities.Copies;
import com.nc.consoleapp.entities.Publisher;
import com.nc.consoleapp.model.AuthorModel;
import com.nc.consoleapp.model.BookModel;
import com.nc.consoleapp.model.CopiesModel;
import com.nc.consoleapp.model.PublisherModel;
import com.nc.consoleapp.utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetUpdateInfo {

    public static GetUpdateInfo instance;
    public static GetUpdateInfo getInstance(){
        if (instance == null){
            instance = new GetUpdateInfo();
        }
        return instance;
    }

    Scanner sc = new Scanner(System.in);

    public void getBookInfo() throws SQLException {
        int id;
        String name;
        int year;
        String checkBook = "SELECT * FROM book where id = ?;";
        Connection connection = DB.getInstance().getConnection();

        System.out.println("Введите id книги: ");
        id = sc.nextInt();
      try {
          PreparedStatement preparedStatement = connection.prepareStatement(checkBook);
          preparedStatement.setInt(1, id);
          ResultSet response = preparedStatement.executeQuery();

          if (response.next()) {
              id = response.getInt("id");

              sc.nextLine();

              System.out.println("Введите название книги: ");
              name = sc.nextLine();

              System.out.println("Введите дату издания: ");
              year = sc.nextInt();

              Book book = new Book();
              book.setId(id);
              book.setBookName(name);
              book.setYear(year);

              BookModel.getInstance().update(book);
          } else {
              System.out.println("Такой книги нет.");
          }

      } catch (SQLException e){
           e.printStackTrace();
      } finally {
          DB.getInstance().closeConnection(connection);
      }

    }

    public void getAuthorInfo() throws SQLException {
        int authorId;
        String authorFirstName;
        String authorLastName;
        String checkAuthor = "SELECT * FROM author WHERE id = ?;";
        Connection connection = DB.getInstance().getConnection();

        System.out.println("Введите id автора: ");
        authorId = sc.nextInt();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(checkAuthor);
            preparedStatement.setInt(1, authorId);
            ResultSet response = preparedStatement.executeQuery();

            if(response.next()){
                authorId = response.getInt("id");

                sc.nextLine();

                System.out.println("Введите имя автора: ");
                authorFirstName = sc.nextLine();

                System.out.println("Введите фамилию автора: ");
                authorLastName = sc.nextLine();

                Author author = new Author();
                author.setFirstName(authorFirstName);
                author.setLastName(authorLastName);
                author.setId(authorId);

                AuthorModel.getInstance().update(author);
            } else {
                System.out.println("Такого автора нет.");
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DB.getInstance().closeConnection(connection);
        }
    }

    public void getPublisherInfo() throws SQLException {
        int publisherId;
        String publisherName;
        Scanner sc = new Scanner(System.in);
        String checkPublisher = "SELECT * FROM publisher where id = ?;";
        Connection connection = DB.getInstance().getConnection();
        System.out.println("Введите id издателя: ");
        publisherId = sc.nextInt();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(checkPublisher);
            preparedStatement.setInt(1, publisherId);
            ResultSet response = preparedStatement.executeQuery();

            if(response.next()){
                publisherId = response.getInt("id");

                sc.nextLine();

                System.out.println("Введите имя издателя: ");
                publisherName = sc.nextLine();;

                Publisher publisher = new Publisher();

                publisher.setId(publisherId);
                publisher.setName(publisherName);

                PublisherModel.getInstance().update(publisher);
            } else {
                System.out.println("Такого издателя нет.");
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DB.getInstance().closeConnection(connection);
        }
    }

    public void getCopiesInfo() throws SQLException {
        int copiesId;
        int copiesCondition;
        int copiesCount;
        String checkCopies = "SELECT * FROM copies where id = ?;";
        Connection connection = DB.getInstance().getConnection();

        System.out.println("Введите id копии: ");
        copiesId = sc.nextInt();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(checkCopies);
            preparedStatement.setInt(1, copiesId);
            ResultSet response = preparedStatement.executeQuery();

            if(response.next()){
                copiesId = response.getInt("id");

                sc.nextLine();

                System.out.println("Введите состояние книги: ");
                copiesCondition = sc.nextInt();

                System.out.println("Введите количество: ");
                copiesCount = sc.nextInt();

                Copies copies = new Copies();
                copies.setId(copiesId);
                copies.setCount(copiesCount);
                copies.setCondition(copiesCondition);

                CopiesModel.getInstance().update(copies);
            } else {
                System.out.println("Таких копии нет.");
            }
        } catch (SQLException e){
           e.printStackTrace();
        }
    }
}