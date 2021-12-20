package com.bonedone.servlet;

import com.bonedone.model.Bucket;
import com.bonedone.service.Service;
import com.bonedone.service.impl.BucketServiceImpl;
import com.bonedone.util.RestUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BucketServlet extends HttpServlet {
    private Service service = new BucketServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Bucket bucket = RestUtil.getFromJson(req, Bucket.class);
        service.create(bucket);
    }
}
