package com.bonedone.service.impl;

import com.bonedone.dao.Dao;
import com.bonedone.dao.Impl.BucketDaoImpl;
import com.bonedone.dao.Impl.BucketProductDaoImpl;
import com.bonedone.model.Bucket;
import com.bonedone.model.BucketProduct;
import com.bonedone.service.BucketProductService;

import java.util.List;

public class BucketProductServiceImpl implements BucketProductService {
    private final Dao<BucketProduct> dao = new BucketProductDaoImpl();

    @Override
    public void create(BucketProduct bucketProduct) {
        dao.create(bucketProduct);
    }

    @Override
    public BucketProduct getById(int id) {
        return (BucketProduct) dao.getById(id);
    }

    @Override
    public List<BucketProduct> getAll() {
        return dao.getAll();
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);

    }

    @Override
    public void update(BucketProduct bucketProduct) {
        dao.update(bucketProduct);

    }
}
