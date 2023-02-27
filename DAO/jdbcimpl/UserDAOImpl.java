package com.solvd.fastestalgo.DAO.jdbcimpl;

import com.solvd.fastestalgo.DAO.UserDAO;
import com.solvd.fastestalgo.binary.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends AbstractDAO implements UserDAO {

    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);

    private static final String SQL_QUERY_CREATE_USER = "INSERT INTO User (name, starting_town, destination_town, path) VALUES (?,?,?,?)";

    private static final String SQL_QUERY_GET_USER_BY_ID = "SELECT * FROM User WHERE id = ?";

    private static final String SQL_QUERY_GET_ALL_USERS = "SELECT * FROM User";

    private static final String SQL_QUERY_UPDATE_USER = "UPDATE User SET name = ?, starting_town = ?, destination_town = ?, path = ? WHERE id = ?";

    private static final String SQL_QUERY_DELETE_USER = "DELETE FROM User WHERE id = ?";

    @Override
    public boolean create(User user) {
        Connection connection = getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_CREATE_USER)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getStartingTown());
            preparedStatement.setString(3, user.getDestinationTown());
            preparedStatement.setString(4, user.getPath());

            logger.debug("Executing SQL statement: " + preparedStatement.toString());

            int result = preparedStatement.executeUpdate();
            connection.commit();
            return result > 0;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return false;
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public User getById(long id) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            logger.info("Getting user with ID " + id);
            preparedStatement = connection.prepareStatement(SQL_QUERY_GET_USER_BY_ID);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String startingTown = resultSet.getString("starting_town");
                String destinationTown = resultSet.getString("destination_town");
                String path = resultSet.getString("path");
                logger.info("User with ID " + id + " found: " + name + " " + startingTown + " " + destinationTown + " " + path);
                return new User(userId, name, startingTown, destinationTown, path);
            } else {
                logger.info("User with ID " + id + " not found");
                return null;
            }
        } catch (SQLException e) {
            logger.error("Get user by ID failed", e);
            return null;
        } finally {
            releaseConnection(connection);
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<User> getAll() {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_QUERY_GET_ALL_USERS);
            resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String startingTown = resultSet.getString("starting_town");
                String destinationTown = resultSet.getString("destination_town");
                String path = resultSet.getString("path");
                users.add(new User(userId, name, startingTown, destinationTown, path));
            }
            return users;
        } catch (SQLException e) {
            logger.error("Get all users query failed");
            return null;
        } finally {
            releaseConnection(connection);
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean update(User user) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_QUERY_UPDATE_USER);
            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getStartingTown());
            preparedStatement.setString(3, user.getDestinationTown());
            preparedStatement.setString(4, user.getPath());
            preparedStatement.setInt(5, user.getId());
            int result = preparedStatement.executeUpdate();
            connection.commit();
            return result > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            releaseConnection(connection);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean delete(User user) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_QUERY_DELETE_USER);
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, user.getId());
            int result = preparedStatement.executeUpdate();
            connection.commit();
            return result > 0;
        } catch (SQLException e) {
            logger.error("Delete user failed");
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return false;
        } finally {
            releaseConnection(connection);
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}