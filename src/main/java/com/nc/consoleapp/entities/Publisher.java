package com.nc.consoleapp.entities;

import com.nc.consoleapp.utils.Output;

public class Publisher implements Entity {
    private int id;
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void get(){
        Output.printInfo("id издателя", id);
        Output.printInfo("Имя издателя", name);

    }
}
