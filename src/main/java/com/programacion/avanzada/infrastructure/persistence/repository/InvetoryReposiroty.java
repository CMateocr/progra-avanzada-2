package com.programacion.avanzada.infrastructure.persistence.repository;

import com.programacion.avanzada.domain.entity.Inventory;
import com.programacion.avanzada.domain.repository.IInventoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class InvetoryReposiroty implements IInventoryRepository {
  private final EntityManager entityManager;

  @Inject
  public InvetoryReposiroty(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Inventory save(Inventory entity) {
    if(entity.getBook() == null) throw new IllegalArgumentException("Cant save inventory with no book");

    if(entity.getBookIsbn() != null && this.findById( entity.getBookIsbn() ).isPresent()) return this.update( entity );

    try {
      this.entityManager.getTransaction().begin();

      this.entityManager.persist( entity );

      this.entityManager.getTransaction().commit();

      return entity;
    } catch (Exception e) {
      if(this.entityManager.getTransaction().isActive()) this.entityManager.getTransaction().rollback();

      throw e;
    }
  }

  @Override
  public Optional<Inventory> findById(String id) {
    return Optional.ofNullable( this.entityManager.find( Inventory.class, id ) );
  }

  @Override
  public List<Inventory> findAll() {
    return this.entityManager.createQuery("SELECT i FROM Inventory", Inventory.class).getResultList();
  }

  @Override
  public Inventory delete(Inventory entity) {
    try {
      this.entityManager.getTransaction().begin();

      Inventory inventoryManagedByEntityManager = this.entityManager.contains( entity ) ? entity : this.entityManager.merge( entity );

      this.entityManager.remove( inventoryManagedByEntityManager );

      return inventoryManagedByEntityManager;
    } catch (Exception e) {
      if(this.entityManager.getTransaction().isActive()) this.entityManager.getTransaction().rollback();

      throw new RuntimeException(e);
    }
  }

  @Override
  public Inventory update(Inventory entity) {
    try {
      this.entityManager.getTransaction().begin();

      Inventory inventoryUpdated = this.entityManager.merge( entity );

      this.entityManager.getTransaction().commit();

      return inventoryUpdated;
    } catch (Exception e) {
      if(this.entityManager.getTransaction().isActive()) this.entityManager.getTransaction().rollback();

      throw new RuntimeException(e);
    }
  }
}
