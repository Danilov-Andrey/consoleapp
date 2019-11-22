package com.nc.consoleapp.menu;

import com.nc.consoleapp.menu.create.CreateMenuCommands;
import com.nc.consoleapp.menu.delete.DeleteMenuCommands;
import com.nc.consoleapp.menu.read.ReadMenuCommands;
import com.nc.consoleapp.menu.update.UpdateMenuCommands;
import com.nc.consoleapp.utils.Output;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu implements Command {
    private static MainMenu instance;

    public static MainMenu getInstance(){
        if (instance == null){
            instance = new MainMenu();
        }
        return instance;
    }

    @Override
    public Object execute() {
        boolean isProgramWork = true;
        Scanner sc = new Scanner(System.in);
        try {
            while (isProgramWork) {
                Output.printSeparator();
                Output.printOption(1, "Создать.");
                Output.printOption(2, "Получить.");
                Output.printOption(3, "Обновить.");
                Output.printOption(4, "Удалить.");
                Output.printSeparator();
                Output.printOption(0, "Закрыть программу.");
                Output.printInputSign();
                int operationType = sc.nextInt();

                switch (operationType) {
                    case 1:
                        CreateMenuCommands.getInstance().execute();
                        break;
                    case 2:
                        ReadMenuCommands.getInstance().execute();
                        break;
                    case 3:
                        UpdateMenuCommands.getInstance().execute();
                        break;
                    case 4:
                        DeleteMenuCommands.getInstance().execute();
                        break;
                    case 0:
                        isProgramWork = false;
                        break;
                    default:
                        Output.printError("нет категории с таким номером.");
                        break;
                }
            }
        } catch (InputMismatchException e){
            Output.printError("ввели неверное значение.");
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
