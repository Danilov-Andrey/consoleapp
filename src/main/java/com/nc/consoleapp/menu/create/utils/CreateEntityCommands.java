package com.nc.consoleapp.menu.create.utils;

import com.nc.consoleapp.entities.Author;
import com.nc.consoleapp.entities.Book;
import com.nc.consoleapp.entities.Publisher;
import com.nc.consoleapp.model.AuthorModel;
import com.nc.consoleapp.model.BookModel;
import com.nc.consoleapp.model.PublisherModel;

import java.sql.SQLException;
import java.util.Scanner;

public class CreateEntityCommands {
    private static CreateEntityCommands instance;

    public static CreateEntityCommands getInstance(){
        if (instance == null){
            instance = new CreateEntityCommands();
        }
        return instance;
    }

    public void createBook() {
        Scanner sc = new Scanner(System.in);
        String author_first_name;
        String author_last_name;
        String name_publisher ;
        String book_name;
        int year;
        int condition;
        int count;

        Book book = new Book();

        System.out.println("Введите имя автора: ");
        author_first_name = sc.next();
        book.setAuthorFirstName(author_first_name);

        System.out.println("Введите фамилию автора: ");
        author_last_name = sc.next();
        book.setAuthorLastName(author_last_name);

        System.out.println("Введите издателя: ");
        name_publisher = sc.next();
        book.setPublisherName(name_publisher);

        sc.nextLine();

        System.out.println("Введите название книги: ");
        book_name = sc.nextLine();
        book.setBookName(book_name);

        System.out.println("Введите дату издания: ");
        year = sc.nextInt();
        book.setYear(year);

        System.out.println("Введите состояние книги: ");
        condition = sc.nextInt();
        book.setCondition(condition);

        System.out.println("Введите количество: ");
        count = sc.nextInt();
        book.setCount(count);

        try {
            BookModel.getInstance().create(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createAuthor(){
        String firstName;
        String lastName;
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите имя автора: ");
        firstName = sc.nextLine();

        System.out.println("Введите фамилию автора: ");
        lastName = sc.nextLine();

        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);

        try {
            AuthorModel.getInstance().create(author);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPublisher(){
        String name;
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите имя издателя: ");
        name = sc.nextLine();

        Publisher publisher = new Publisher();
        publisher.setName(name);

        try {
            PublisherModel.getInstance().create(publisher);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
