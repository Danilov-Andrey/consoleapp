package com.nc.consoleapp.model.view;

import com.nc.consoleapp.entities.Author;
import com.nc.consoleapp.entities.Book;
import com.nc.consoleapp.entities.Copies;
import com.nc.consoleapp.entities.Publisher;
import com.nc.consoleapp.utils.Output;

import java.sql.ResultSet;
import java.sql.SQLException;

public class View {
    public static View instance;

    public static View getInstance(){
        if (instance == null){
            instance = new View();
        }
        return  instance;
    }

    public void renderBook(ResultSet response){
        try {
            if (!response.next()){
                Output.printExtraInfo();
            } else {
                System.out.println("Книги: ");
                response.beforeFirst();
                while(response.next()){
                    Book book = new Book();
                    book.setId(response.getInt(1));
                    book.setBookName(response.getString(2));
                    book.setYear(response.getInt(3));
                    book.setAuthorFirstName(response.getString(4));
                    book.setAuthorLastName(response.getString(5));
                    book.setCount(response.getInt(6));
                    book.setCondition(response.getInt(7));
                    book.setPublisherName(response.getString(8));
                    book.setAuthorId(response.getInt(9));
                    book.setPublisherId(response.getInt(10));
                    book.setCopiesId(response.getInt(11));
                    Output.printSeparator();
                    book.get();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void renderAuthor(ResultSet response){
        try {
            if (!response.next()){
                Output.printExtraInfo();
            } else {
                System.out.println("Авторы: ");
                response.beforeFirst();
                while(response.next()){
                    Author author = new Author();
                    author.setId(response.getInt(1));
                    author.setFirstName(response.getString(2));
                    author.setLastName(response.getString(3));
                    Output.printSeparator();
                    author.get();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void renderPublisher(ResultSet response){
        try {
            if (!response.next()){
                Output.printExtraInfo();
            } else {
                System.out.println("Издатели: ");
                response.beforeFirst();
                while(response.next()){
                    Publisher publisher = new Publisher();
                    publisher.setId(response.getInt(1));
                    publisher.setName(response.getString(2));
                    Output.printSeparator();
                    publisher.get();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void renderCopies(ResultSet response){
        try {
            if (!response.next()){
                Output.printExtraInfo();
            } else {
                System.out.println("Копии: ");
                response.beforeFirst();
                while(response.next()){
                    Copies copies = new Copies();
                    copies.setId(response.getInt(1));
                    copies.setBookId(response.getInt(2));
                    copies.setCondition(response.getInt(3));
                    copies.setCount(response.getInt(4));
                    Output.printSeparator();
                    copies.get();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
