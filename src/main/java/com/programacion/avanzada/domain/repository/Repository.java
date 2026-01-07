package com.programacion.avanzada.domain.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, U> {
  T save(T entity);
  Optional<T> findById(U id);
  List<T> findAll();
  T delete(T entity);
  T update(T entity);
}
