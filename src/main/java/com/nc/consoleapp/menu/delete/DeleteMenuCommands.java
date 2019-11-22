package com.nc.consoleapp.menu.delete;

import com.nc.consoleapp.entities.Author;
import com.nc.consoleapp.entities.Publisher;
import com.nc.consoleapp.menu.Command;
import com.nc.consoleapp.menu.GetIdCommand;
import com.nc.consoleapp.model.AuthorModel;
import com.nc.consoleapp.model.BookModel;
import com.nc.consoleapp.model.PublisherModel;
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
                        BookModel.getInstance().delete(getId.execute());
                        break;
                    case 2:
                        AuthorModel.getInstance().delete(getId.execute());
                        break;
                    case 3:
                        PublisherModel.getInstance().delete(getId.execute());
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
