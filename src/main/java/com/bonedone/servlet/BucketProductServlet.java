package com.bonedone.servlet;

import com.bonedone.model.BucketProduct;
import com.bonedone.service.Service;
import com.bonedone.service.impl.BucketProductServiceImpl;
import com.bonedone.util.RestUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product_bucket")
public class BucketProductServlet extends HttpServlet {
    private final Service<BucketProduct> service = new BucketProductServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BucketProduct bucketProduct = RestUtil.getFromJson(req, BucketProduct.class);
        service.create(bucketProduct);
    }
}
