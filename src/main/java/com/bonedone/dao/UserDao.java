package com.bonedone.dao;

import com.bonedone.model.User;


public interface UserDao extends Dao<User>{

    default User getByEmail(String email) {
        return null;
    }
}
