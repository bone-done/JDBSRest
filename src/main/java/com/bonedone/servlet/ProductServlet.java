package com.bonedone.servlet;

import com.bonedone.model.Product;
import com.bonedone.service.Service;
import com.bonedone.service.impl.ProductServiceImpl;
import com.bonedone.util.RestUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductServlet extends HttpServlet {
    private Service service = new ProductServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = RestUtil.getFromJson(req, Product.class);
        service.create(product);
    }
}
