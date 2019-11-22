package com.nc.consoleapp.entities;

import com.nc.consoleapp.utils.Output;

public class Book implements Entity {

    private static int id;
    private static String bookName;
    private static int year;
    private static String authorFirstName;
    private static String authorLastName;
    private static int count;
    private static String publisherName;
    private static int condition;
    private static int authorId;

    public static String getAuthorFirstName() {
        return authorFirstName;
    }

    public static String getAuthorLastName() {
        return authorLastName;
    }

    public static int getCount() {
        return count;
    }

    public static String getPublisherName() {
        return publisherName;
    }

    public static int getCondition() {
        return condition;
    }

    private static int publisherId;
    private static int copiesId;

    public static int getId() {
        return id;
    }

    public static String getBookName() {
        return bookName;
    }

    public static int getYear() {
        return year;
    }

    public static void setId(int id) {
        Book.id = id;
    }

    public static void setBookName(String name) {
        Book.bookName = name;
    }

    public static void setAuthorFirstName(String authorFirstName) {
        Book.authorFirstName = authorFirstName;
    }

    public static void setAuthorLastName(String authorLastName) {
        Book.authorLastName = authorLastName;
    }

    public static void setCount(int count) {
        Book.count = count;
    }

    public static void setPublisherName(String publisherName) {
        Book.publisherName = publisherName;
    }

    public static void setCondition(int condition) {
        Book.condition = condition;
    }

    public static void setAuthorId(int authorId) {
        Book.authorId = authorId;
    }

    public static void setPublisherId(int publisherId) {
        Book.publisherId = publisherId;
    }

    public static void setCopiesId(int copiesId) {
        Book.copiesId = copiesId;
    }

    public static void setYear(int year) {
        Book.year = year;
    }

    public void get(){
        Output.printInfo("id книги", id);
        Output.printInfo("Название", bookName);
        Output.printInfo("Год", year);
        Output.printInfo("id автора", authorId);
        Output.printInfo("Имя автора", authorFirstName);
        Output.printInfo("Фамилия автора", authorLastName);
        Output.printInfo("id издателя", publisherId);
        Output.printInfo("Имя издателя", publisherName);
        Output.printInfo("id копии", copiesId);
        Output.printInfo("Количество копии", count);
        Output.printInfo("Состояние", condition);
    }
}
