package com.nc.consoleapp.menu.read;

import com.nc.consoleapp.menu.Command;
import com.nc.consoleapp.menu.GetIdCommand;
import com.nc.consoleapp.model.AuthorModel;
import com.nc.consoleapp.model.BookModel;
import com.nc.consoleapp.model.CopiesModel;
import com.nc.consoleapp.model.PublisherModel;
import com.nc.consoleapp.utils.Output;

import java.sql.SQLException;
import java.util.Scanner;


public class ReadMenuCommands implements Command {
    private static Command instance;

    public static Command getInstance(){
        if (instance == null){
            instance = new ReadMenuCommands();
        }
        return instance;
    }

    @Override
    public Object execute() {
        boolean isReadMenuOpen = true;
        Command<Integer> getId = GetIdCommand.getInstance();
        while(isReadMenuOpen){
            Output.printSeparator();
            Output.printOption(1, "Показать все книги.");
            Output.printOption(2, "Показать всех авторов.");
            Output.printOption(3, "Показать всеx издателей.");
            Output.printOption(4, "Показать все копии.");
            Output.printSeparator();
            Output.printOption(5, "Показать книгу по id.");
            Output.printOption(6, "Показать автора по id.");
            Output.printOption(7, "Показать издателя по id.");
            Output.printOption(8, "Показать копии по id.");
            Output.printSeparator();
            Output.printOption(0, "Вернуться обратно.");
            Output.printInputSign();

            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            try {
                switch (option){
                    case 1:
                        BookModel.getInstance().get();
                        break;
                    case 2:
                        AuthorModel.getInstance().get();
                        break;
                    case 3:
                        PublisherModel.getInstance().get();
                        break;
                    case 4:
                        CopiesModel.getInstance().get();
                        break;
                    case 5:
                        BookModel.getInstance().get(getId.execute());
                        break;
                    case 6:
                        AuthorModel.getInstance().get(getId.execute());
                        break;
                    case 7:
                        PublisherModel.getInstance().get(getId.execute());
                        break;
                    case 8:
                        CopiesModel.getInstance().get(getId.execute());
                        break;
                    case 0:
                        isReadMenuOpen = false;
                        break;
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

        }
        return null;
    }
}
