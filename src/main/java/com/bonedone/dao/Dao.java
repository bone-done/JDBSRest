package com.bonedone.dao;

import java.util.List;

public interface Dao<T> {
    void create(T t);
    T getById(int id);
    List<T> getAll();
    void deleteById(int id);
    void update(T t);
}
