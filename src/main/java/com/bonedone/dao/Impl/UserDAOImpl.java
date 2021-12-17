package com.bonedone.dao.Impl;

import com.bonedone.dao.UserDAO;
import com.bonedone.model.User;
import com.bonedone.util.Queries;
import com.bonedone.util.Role;
import com.bonedone.util.SQLConnection;
import lombok.extern.log4j.Log4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class UserDAOImpl implements UserDAO {
    @Override
    public void create(User user) {
        boolean withId = user.getId() != 0;
        final String SQL = withId ? Queries.CREATE_USER : Queries.CREATE_USER_WITHOUT_ID;
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getRole().name());
            if (withId) statement.setInt(6, user.getId());
            statement.execute();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    public static void main(String[] args) {
        UserDAOImpl dao = new UserDAOImpl();
//        dao.update(new User(
//                6,
//                "aaa",
//                "aaa",
//                "full",
//                "last",
//                Role.ADMIN
//        ));
        dao.deleteById(5);
//        System.out.println(dao.getAll());
    }

    @Override
    public User getById(int id) {
        User user = new User();
        final String SQL = Queries.GET_USER_BY_ID;
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, id);
            statement.execute();
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        if (user.getId() == 0 &&
                user.getEmail() == null &&
                user.getPassword() == null &&
                user.getFirstName() == null &&
                user.getLastName() == null &&
                user.getRole() == null) {
            NullPointerException exception = new NullPointerException("user is empty");
            log.error(exception);
            throw exception;
        } else return user;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        final String SQL = Queries.GET_ALL_USERS;
        try (Connection connection = SQLConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)) {
             while (resultSet.next()) {
                userList.add(
                        new User(
                                resultSet.getInt("id"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                Role.valueOf(resultSet.getString("role"))
                        )
                );
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return userList;
    }

    @Override
    public void deleteById(int id) {
        final String SQL = Queries.DELETE_USER;
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, id);
            statement.execute();
            log.info("user with ID " + id + " was deleted");
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void update(User user) {
        final String SQL = Queries.UPDATE_USER;
        if (isExist(user)) {
            try (Connection connection = SQLConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQL)) {
                statement.setString(1, user.getEmail());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getFirstName());
                statement.setString(4, user.getLastName());
                statement.setString(5, user.getRole().name());
                statement.setInt(6, user.getId());
                statement.execute();
                log.info("user with ID " + user.getId() + " was updated");
            } catch (SQLException e) {
                log.error(e);
            }
        } else {
            log.warn("user with ID " + user.getId() + " was NOT updated");
        }
    }

    private boolean isExist(User user) {
        final String SQL = Queries.USER_IS_EXIST;
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, user.getId());
            statement.execute();
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    log.warn("user with ID " + user.getId() + " NOT found");
                    return false;
                }
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return true;
    }
}
