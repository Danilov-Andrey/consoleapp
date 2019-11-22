package com.nc.consoleapp.menu.create;

import com.nc.consoleapp.menu.Command;
import com.nc.consoleapp.menu.create.utils.CreateEntityCommands;
import com.nc.consoleapp.utils.Output;


import java.util.Scanner;

public class CreateMenuCommands implements Command {
    private static Command instance;

    public static Command getInstance(){
        if (instance == null){
            instance = new CreateMenuCommands();
        }
        return instance;
    }

    @Override
    public Object execute() {
        boolean isCreateMenuOpen = true;
        while(isCreateMenuOpen){
            Output.printSeparator();
            Output.printOption(1, "Добавить книгу.");
            Output.printOption(2, "Добавить автора.");
            Output.printOption(3, "Добавить издателя.");
            Output.printSeparator();
            Output.printOption(0, "Вернуться обратно.");
            Output.printInputSign();

            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();

                switch (option) {
                    case 1:
                        CreateEntityCommands.getInstance().createBook();
                        break;
                    case 2:
                        CreateEntityCommands.getInstance().createAuthor();
                        break;
                    case 3:
                        CreateEntityCommands.getInstance().createPublisher();
                        break;
                    case 0:
                        isCreateMenuOpen = false;
                        break;
                }

        }
        return null;
    }

}
