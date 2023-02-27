package com.solvd.fastestalgo.DAO;

import java.util.List;

public interface AbstractDAO<T> {

    boolean create(T t);

    T getById(long id);

    List<T> getAll();

    boolean update(T t);

    boolean delete(T t);
}