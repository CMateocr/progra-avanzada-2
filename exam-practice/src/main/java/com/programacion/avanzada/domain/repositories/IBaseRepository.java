package com.programacion.avanzada.domain.repositories;

import java.util.List;
import java.util.Optional;

public interface IBaseRepository<T, U> {
  Optional<T> findById(U id);
  List<T> findAll();
  T create(T entity);
  T update(T entity);
  T delete(T entity);
}
