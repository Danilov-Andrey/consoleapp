package com.nc.consoleapp.menu.update;

import com.nc.consoleapp.menu.Command;
import com.nc.consoleapp.menu.GetIdCommand;
import com.nc.consoleapp.menu.update.utils.GetUpdateInfo;
import com.nc.consoleapp.model.BookModel;
import com.nc.consoleapp.utils.Output;

import java.sql.SQLException;
import java.util.Scanner;

public class UpdateMenuCommands implements Command {
    private static Command instance;

    public static Command getInstance(){
        if (instance == null){
            instance = new UpdateMenuCommands();
        }
        return instance;
    }

    @Override
    public Object execute() {
        boolean isUpdateMenuOpen = true;
        Command<Integer> getId = GetIdCommand.getInstance();
        while(isUpdateMenuOpen){
            Output.printSeparator();
            Output.printOption(1, "Обновить книгу.");
            Output.printOption(2, "Обновить автора.");
            Output.printOption(3, "Обновить издателя.");
            Output.printOption(4, "Обновить копии.");
            Output.printSeparator();
            Output.printOption(0, "Вернуться обратно.");
            Output.printInputSign();

            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            try {
                switch (option){
                    case 1:
                        GetUpdateInfo.getInstance().getBookInfo();
                        break;
                    case 2:
                        GetUpdateInfo.getInstance().getAuthorInfo();
                        break;
                    case 3:
                        GetUpdateInfo.getInstance().getPublisherInfo();
                        break;
                    case 4:
                        GetUpdateInfo.getInstance().getCopiesInfo();
                        break;
                    case 0:
                        isUpdateMenuOpen = false;
                        break;
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

        }
        return null;
    }
}
