package com.nc.consoleapp.model.interfaces;

import java.sql.SQLException;

public interface ReadUpdateModel<T> {
    void get() throws SQLException;

    void get(int id) throws SQLException;

    void update(T object) throws SQLException;
}
