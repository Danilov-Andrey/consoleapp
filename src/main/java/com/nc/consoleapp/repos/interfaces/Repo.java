package com.nc.consoleapp.repos.interfaces;

import java.sql.SQLException;

public interface Repo<T> {
    void get() throws SQLException;

    void get(int id) throws SQLException;

    void update(T object) throws SQLException;

    void delete(int id) throws  SQLException;

    void create(T object) throws SQLException;
}
