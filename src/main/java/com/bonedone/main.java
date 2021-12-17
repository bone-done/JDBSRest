package com.bonedone;

import com.bonedone.dao.Impl.ProductDAOImpl;
import com.bonedone.dao.Impl.UserDAOImpl;
import com.bonedone.model.Product;
import com.bonedone.model.User;
import com.bonedone.util.Role;

public class main {
    public static void main(String[] args) {
        UserDAOImpl userDAO = new UserDAOImpl();
        ProductDAOImpl productDAO = new ProductDAOImpl();
        Product product = new Product(
          "name",
          "des",
          10
        );
        User user = new User(
                "mail",
                "pass",
                "full",
                "last",
                Role.ADMIN);

        productDAO.create(product);
        userDAO.create(user);

    }
}
