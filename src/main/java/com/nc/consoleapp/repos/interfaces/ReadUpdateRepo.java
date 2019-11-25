package com.nc.consoleapp.repos.interfaces;

import java.sql.SQLException;

public interface ReadUpdateRepo<T> {
    void get() throws SQLException;

    void get(int id) throws SQLException;

    void update(T object) throws SQLException;
}
