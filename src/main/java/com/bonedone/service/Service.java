package com.bonedone.service;

import java.util.List;

public interface Service<T> {
    void create(T t);
    T getById(int id);
    List<T> getAll();
    void deleteById(int id);
    void update(T t);
}
