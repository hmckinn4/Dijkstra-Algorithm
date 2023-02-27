package com.solvd.fastestalgo.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ConnectionPool {
    private static ConnectionPool instance;
    private List<Connection> connections;
    private int poolSize;
    private String url = DBConfigUtil.getProperty("url");
    private String username = DBConfigUtil.getProperty("username");
    private String password = DBConfigUtil.getProperty("password");

    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);


    private ConnectionPool() {
        // initialize variables such as poolSize, url, username, and password based on your database configuration
        poolSize = 10;
        DBConfigUtil.getProperty("url");
        DBConfigUtil.getProperty("username");
        DBConfigUtil.getProperty("password");
        connections = new ArrayList<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                Connection connection = DriverManager.getConnection(url, username, password);
                connections.add(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            } catch (ClassNotFoundException e) {
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        if (connections.isEmpty()) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                return connection;
            } catch (SQLException e) {
                logger.error("StackTrace", e);
            }
        }
        return connections.remove(connections.size() - 1);
    }

    public synchronized void releaseConnection(Connection connection) {
        connections.add(connection);
    }
}
