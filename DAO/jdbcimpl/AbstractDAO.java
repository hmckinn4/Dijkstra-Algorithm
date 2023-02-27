package com.solvd.fastestalgo.DAO.jdbcimpl;

import com.solvd.fastestalgo.util.ConnectionPool;

import java.sql.Connection;

public abstract class AbstractDAO {
    public Connection getConnection() {
        return ConnectionPool.getInstance().getConnection();
    }

    public void releaseConnection(Connection connection) {
        ConnectionPool.getInstance().releaseConnection(connection);
    }
}
