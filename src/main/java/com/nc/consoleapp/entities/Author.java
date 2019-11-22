package com.nc.consoleapp.entities;

import com.nc.consoleapp.utils.Output;

public class Author implements Entity {
    private int id;
    private String firstName;
    private String lastName;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void get(){
        Output.printInfo("id", id);
        Output.printInfo("Имя", firstName);
        Output.printInfo("Фамилия", lastName);
    }
}
