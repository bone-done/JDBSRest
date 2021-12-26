package com.bonedone.service.impl;

import com.bonedone.dao.Dao;
import com.bonedone.dao.Impl.ProductDaoImpl;
import com.bonedone.model.Product;
import com.bonedone.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final Dao<Product> dao = new ProductDaoImpl();

    @Override
    public void create(Product product) {
        dao.create(product);
    }

    @Override
    public Product getById(int id) {
        return (Product) dao.getById(id);
    }

    @Override
    public List<Product> getAll() {
        return dao.getAll();
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);

    }

    @Override
    public void update(Product product) {
        dao.update(product);

    }
}
