package com.programacion.avanzada.infrastructure.repositories;

import com.programacion.avanzada.domain.repositories.IBaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T, U> implements IBaseRepository<T, U> {

  protected abstract EntityManager getEntityManager();
  protected abstract Class<T> getEntityClass();

  @Override
  public Optional<T> findById(U id) {
    return Optional.ofNullable(this.getEntityManager().find(getEntityClass(), id));
  }

  @Override
  public List<T> findAll() {
    CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
    CriteriaQuery<T> cq = cb.createQuery( this.getEntityClass() );
    Root<T> root = cq.from(this.getEntityClass());

    CriteriaQuery<T> all = cq.select( root );

    return this.getEntityManager().createQuery( all ).getResultList();
  }

  @Override
  public T create(T entity) {
    try {
      this.getEntityManager().getTransaction().begin();

      this.getEntityManager().persist( entity );

      this.getEntityManager().getTransaction().commit();

      return entity;
    } catch (Exception e) {
      if(this.getEntityManager().getTransaction().isActive()) this.getEntityManager().getTransaction().rollback();

      throw new RuntimeException(e);
    }
  }

  @Override
  public T update(T entity) {
    try {
      this.getEntityManager().getTransaction().begin();

      T entityUpdated = this.getEntityManager().merge( entity );

      this.getEntityManager().getTransaction().commit();

      return entityUpdated;
    } catch (Exception e) {
      if(this.getEntityManager().getTransaction().isActive()) this.getEntityManager().getTransaction().rollback();

      throw new RuntimeException(e);
    }
  }

  @Override
  public T delete(T entity) {
    try {
      this.getEntityManager().getTransaction().begin();

      T entityManagedByEntityManager = this.getEntityManager().contains( entity ) ? entity : this.getEntityManager().merge( entity );

      this.getEntityManager().remove( entityManagedByEntityManager );

      this.getEntityManager().getTransaction().commit();

      return entityManagedByEntityManager;
    } catch (Exception e) {
      if(this.getEntityManager().getTransaction().isActive()) this.getEntityManager().getTransaction().rollback();

      throw new RuntimeException(e);
    }
  }
}
