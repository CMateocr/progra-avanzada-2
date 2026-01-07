package com.programacion.avanzada.infrastructure.persistence.repository;

import com.programacion.avanzada.domain.entity.Author;
import com.programacion.avanzada.domain.repository.IAuthorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AuthorRepository implements IAuthorRepository {
  private final EntityManager entityManager;

  @Inject
  public AuthorRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Author save(Author entity) {
    if(entity.getId() != null) {
      return this.update( entity );
    }

    try {
      this.entityManager.getTransaction().begin();

      this.entityManager.persist(entity);

      this.entityManager.getTransaction().commit();

      return entity;
    } catch (Exception e) {
      if(entityManager.getTransaction().isActive()) {
        entityManager.getTransaction().rollback();
      }

      throw e;
    }
  }

  @Override
  public Optional<Author> findById(Long id) {
    return Optional.ofNullable(this.entityManager.find(Author.class, id));
  }

  @Override
  public List<Author> findAll() {
    return this.entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
  }

  @Override
  public Author delete(Author entity) {
    try {
      this.entityManager.getTransaction().begin();

      Author authorManagedByEntityManager = this.entityManager.contains(entity) ? entity : this.entityManager.merge( entity );

      this.entityManager.remove( authorManagedByEntityManager );

      this.entityManager.getTransaction().commit();

      return authorManagedByEntityManager;
    } catch (Exception e) {
      if(this.entityManager.getTransaction().isActive()) this.entityManager.getTransaction().rollback();

      throw e;
    }
  }

  @Override
  public Author update(Author entity) {
    try {
      this.entityManager.getTransaction().begin();

      Author authorMerged = this.entityManager.merge(entity);

      this.entityManager.getTransaction().commit();

      return authorMerged;
    } catch (Exception e) {
      if(entityManager.getTransaction().isActive()) {
        entityManager.getTransaction().rollback();
      }

      throw e;
    }
  }
}
