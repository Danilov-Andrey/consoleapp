package com.nc.consoleapp.menu.delete;
import com.nc.consoleapp.menu.Command;
import com.nc.consoleapp.menu.GetIdCommand;
import com.nc.consoleapp.repos.AuthorRepo;
import com.nc.consoleapp.repos.BookRepo;
import com.nc.consoleapp.repos.PublisherRepo;
import com.nc.consoleapp.utils.Output;

import java.sql.SQLException;
import java.util.Scanner;

public class DeleteMenuCommands implements Command {
    private static Command instance;

    public static Command getInstance(){
        if (instance == null){
            instance = new DeleteMenuCommands();
        }
        return instance;
    }

    @Override
    public Object execute() {
        boolean isDeleteMenuOpen = true;
        Command<Integer> getId = new GetIdCommand();
        while(isDeleteMenuOpen){
            Output.printSeparator();
            Output.printOption(1, "Удалить книгу.");
            Output.printOption(2, "Удалить автора.");
            Output.printOption(3, "Удалить издателя.");
            Output.printSeparator();
            Output.printOption(0, "Вернуться обратно.");
            Output.printInputSign();
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            try {
                switch (option){
                    case 1:
                        BookRepo.getInstance().delete(getId.execute());
                        break;
                    case 2:
                        AuthorRepo.getInstance().delete(getId.execute());
                        break;
                    case 3:
                        PublisherRepo.getInstance().delete(getId.execute());
                        break;
                    case 0:
                        isDeleteMenuOpen = false;
                        break;
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

}
