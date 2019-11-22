package com.nc.consoleapp;

import com.nc.consoleapp.menu.Command;
import com.nc.consoleapp.menu.MainMenu;

public class App {
    public static void main(String[] args) {
        Command mainMenu = MainMenu.getInstance();
        mainMenu.execute();
    }
}
