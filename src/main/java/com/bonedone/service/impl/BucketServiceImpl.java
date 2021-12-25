package com.bonedone.service.impl;

import com.bonedone.dao.Dao;
import com.bonedone.dao.Impl.BucketDaoImpl;
import com.bonedone.model.Bucket;
import com.bonedone.service.BucketService;

import java.util.List;

public class BucketServiceImpl implements BucketService {
    private final Dao<Bucket> dao = new BucketDaoImpl();

    @Override
    public void create(Bucket bucket) {
        dao.create(bucket);
    }

    @Override
    public Bucket getById(int id) {
        return (Bucket) dao.getById(id);
    }

    @Override
    public List<Bucket> getAll() {
        return dao.getAll();
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);

    }

    @Override
    public void update(Bucket bucket) {
        dao.update(bucket);

    }
}
