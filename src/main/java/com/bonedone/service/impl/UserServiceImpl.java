package com.bonedone.service.impl;

import com.bonedone.dao.Impl.UserDaoImpl;
import com.bonedone.dao.UserDao;
import com.bonedone.exception.PasswordDontMachException;
import com.bonedone.exception.UserIsNullException;
import com.bonedone.model.User;
import com.bonedone.service.UserService;
import com.bonedone.util.Role;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {
    private final UserDao dao = new UserDaoImpl();

    @Override
    public void create(User user) {
        if (Objects.isNull(user.getRole())) {
            user.setRole(Role.USER);
        }
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

    @SneakyThrows
    @Override
    public User getByEmailAndPassword(String email, String password) {
        User user = dao.getByEmail(email);
        if (user.getPassword().equals(password)) return user;
        else throw new PasswordDontMachException();
    }
}
