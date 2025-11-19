package com.prog_avanzada.repository;

import java.util.List;
import java.util.Optional;

public interface RepositoryModel<T,U> {
    List<T> findAll();

    Optional<T> findById(U id);

    boolean save(T entity);

    boolean update(T entity);

    boolean deleteById(U id);
}