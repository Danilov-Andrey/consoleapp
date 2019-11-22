package com.nc.consoleapp.menu;

import com.nc.consoleapp.utils.Output;

import java.util.Scanner;

public class GetIdCommand implements Command<Integer> {

    public static Command instance;
    public static Command getInstance(){
        if (instance == null){
            instance = new GetIdCommand();
        }
        return instance;
    }

    @Override
    public Integer execute() {
        Scanner sc = new Scanner(System.in);
        int id = 0;
        try {
            System.out.println("Введите id: ");
            id = sc.nextInt();
        } catch (Exception e){
            Output.printError("ввели неверное значение.");
            execute();
        }
        return id;
    }
}
