package com.crave.repository;

import java.util.List;

public interface BaseRepository<T, ID> {
    T findById(ID id);

    List<T> findAll();

    T save(T entity);

    void deleteById(ID id);
}

