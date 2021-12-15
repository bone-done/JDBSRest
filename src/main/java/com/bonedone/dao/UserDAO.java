package com.bonedone.dao;

import com.bonedone.model.User;

import java.util.List;

public interface UserDAO {
    void create(User user);
    void update(User user);
    void delete(User user);
    User getUserById (int id);
    List<User> getAllUsers ();
}
