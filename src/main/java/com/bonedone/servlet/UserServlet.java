package com.bonedone.servlet;

import com.bonedone.model.User;
import com.bonedone.service.Service;
import com.bonedone.service.impl.UserServiceImpl;
import com.bonedone.util.RestUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private final Service<User> service = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = RestUtil.getFromJson(req, User.class);
        service.create(user);
    }
}
