package com.bonedone.service;

import com.bonedone.model.User;

public interface UserService extends Service<User>{

    User getByEmailAndPassword(String email, String password);
}
