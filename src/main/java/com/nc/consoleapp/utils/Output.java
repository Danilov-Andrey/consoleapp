package com.nc.consoleapp.utils;

public class Output {
    public static void printOption(int number, String value){
        System.out.println(number + ". " + value);
    }
    public static void printError(String  error){
        printSeparator();
        System.out.println("Ошибка: " + error);
    }
    public static void printSeparator(){
        System.out.println("-----");
    }

    public static void printInputSign(){
        System.out.print("> ");
    }

    public static void printInfo(String title, String info){
        System.out.println(title + " - " + info);
    }

    public static void printInfo(String title, int info){
        System.out.println(title + " - " + info);
    }

    public static void printSuccess() {
        System.out.println("Успешно.");
    }

    public static void printExtraInfo() {
        System.out.println("Ничего не найдено.");
    }
}
