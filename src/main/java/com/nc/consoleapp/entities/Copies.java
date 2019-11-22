package com.nc.consoleapp.entities;

import com.nc.consoleapp.utils.Output;

public class Copies implements Entity {
    private int id;
    private int bookId;
    private int condition;
    private int count;

    public int getId() {
        return id;
    }

    public int getCondition() {
        return condition;
    }

    public int getCount() {
        return count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public void get(){
        Output.printInfo("id копии", id);
        Output.printInfo("Количество копии", count);
        Output.printInfo("Состояние", condition);
        Output.printInfo("id книги", bookId);
    }
}
