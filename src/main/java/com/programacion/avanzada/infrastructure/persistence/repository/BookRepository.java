package com.programacion.avanzada.infrastructure.persistence.repository;

import com.programacion.avanzada.domain.entity.Book;
import com.programacion.avanzada.domain.repository.IBookRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookRepository implements IBookRepository {

  private final EntityManager em;

  @Inject
  public BookRepository(EntityManager em) {
    this.em = em;
  }

  @Override
  public Book save(Book entity) {
    if(this.findById( entity.getIsbn() ).isPresent()) throw new RuntimeException("Book with ISBN " + entity.getAuthors() + " already exists");

    try {
      this.em.getTransaction().begin();

      this.em.persist( entity );

      this.em.getTransaction().commit();

      return entity;
    } catch (Exception e) {
      if(this.em.getTransaction().isActive()) this.em.getTransaction().rollback();
      throw  e;
    }
  }

  @Override
  public Optional<Book> findById(String id) {
    return Optional.ofNullable( this.em.find( Book.class, id ) );
  }

  @Override
  public List<Book> findAll() {
    return this.em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
  }

  @Override
  public Book delete(Book entity) {
    try {
      this.em.getTransaction().begin();

      Book bookManagedByEntityManager = this.em.contains(entity) ? entity : this.em.merge( entity );

      this.em.remove( bookManagedByEntityManager );

      this.em.getTransaction().commit();

      return bookManagedByEntityManager;
    } catch (Exception e) {
      if( this.em.getTransaction().isActive() ) this.em.getTransaction().rollback();

      throw e;
    }
  }

  @Override
  public Book update(Book entity) {
    try {
      this.em.getTransaction().begin();

      Book updatedBook = this.em.merge( entity );

      this.em.getTransaction().commit();

      return updatedBook;
    } catch (Exception e) {
      if( this.em.getTransaction().isActive()) this.em.getTransaction().rollback();

      throw new RuntimeException(e);
    }
  }
}
