package com.bonedone.service.impl;

import com.bonedone.dao.Dao;
import com.bonedone.dao.Impl.UserDAOImpl;
import com.bonedone.model.User;
import com.bonedone.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final Dao<User> dao = new UserDAOImpl();

    @Override
    public void create(User user) {
        dao.create(user);
    }

    @Override
    public User getById(int id) {
        return (User) dao.getById(id);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }

    @Override
    public void update(User user) {
        dao.update(user);
    }
}
