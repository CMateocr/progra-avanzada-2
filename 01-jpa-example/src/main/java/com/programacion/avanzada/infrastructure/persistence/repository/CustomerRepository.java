package com.programacion.avanzada.infrastructure.persistence.repository;

import com.programacion.avanzada.domain.entity.Customer;
import com.programacion.avanzada.domain.repository.ICustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CustomerRepository implements ICustomerRepository {

  private final EntityManager entityManager;

  @Inject
  public CustomerRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Customer save(Customer entity) {
    if(entity.getId() != null) return this.update( entity );

    try {
      this.entityManager.getTransaction().begin();

      this.entityManager.persist( entity );

      this.entityManager.getTransaction().commit();

      return  entity;
    } catch (Exception e) {
      if( this.entityManager.getTransaction().isActive()) this.entityManager.getTransaction().rollback();

      throw new RuntimeException(e);
    }
  }

  @Override
  public Optional<Customer> findById(Long id) {
    return Optional.ofNullable( this.entityManager.find( Customer.class, id ) );
  }

  @Override
  public List<Customer> findAll() {
    return this.entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
  }

  @Override
  public Customer delete(Customer entity) {
    try {
      this.entityManager.getTransaction().begin();

      Customer customerManagedByEntityManager = this.entityManager.contains( entity ) ? entity : this.entityManager.merge( entity );

      this.entityManager.remove( customerManagedByEntityManager );

      this.entityManager.getTransaction().commit();

      return customerManagedByEntityManager;
    } catch (Exception e) {
      if( this.entityManager.getTransaction().isActive() ) this.entityManager.getTransaction().rollback();

      throw e;
    }

  }

  @Override
  public Customer update(Customer entity) {
    try {
      this.entityManager.getTransaction().begin();

      Customer customerUpdated = this.entityManager.merge( entity );

      this.entityManager.getTransaction().commit();

      return customerUpdated;
    } catch (Exception e) {
      if( this.entityManager.getTransaction().isActive() ) this.entityManager.getTransaction().rollback();
      throw e;
    }
  }
}
