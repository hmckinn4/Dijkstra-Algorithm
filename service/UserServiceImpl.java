package com.solvd.fastestalgo.service;

import com.solvd.fastestalgo.DAO.jdbcimpl.UserDAOImpl;
import com.solvd.fastestalgo.binary.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public User logUser(String name, String startingTown, String destinationTown, String path) {
        User user = new User(1, name, startingTown, destinationTown, path); // 0 is the default value for the auto-incremented ID column
        UserDAOImpl userDAO = new UserDAOImpl();
        boolean success = userDAO.create(user);
        if (success) {
            logger.info("User logged successfully: " + user);
            return user;
        } else {
            logger.error("Failed to log user: " + user);
        }
        return null;
    }
}
