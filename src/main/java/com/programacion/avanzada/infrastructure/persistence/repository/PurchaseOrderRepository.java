package com.programacion.avanzada.infrastructure.persistence.repository;

import com.programacion.avanzada.domain.entity.PurchaseOrder;
import com.programacion.avanzada.domain.repository.IPurchaseOrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PurchaseOrderRepository implements IPurchaseOrderRepository {

  private final EntityManager entityManager;

  @Inject
  public PurchaseOrderRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public PurchaseOrder save(PurchaseOrder entity) {
    if (entity.getCustomer() == null) {
      throw new IllegalArgumentException("No se puede guardar una PurchaseOrder sin un Customer asociado.");
    }

    if (entity.getId() != null) {
      return this.update(entity);
    }

    try {
      this.entityManager.getTransaction().begin();

      this.entityManager.persist(entity);

      this.entityManager.getTransaction().commit();
      return entity;
    } catch (Exception e) {
      if (this.entityManager.getTransaction().isActive()) {
        this.entityManager.getTransaction().rollback();
      }
      throw e;
    }
  }

  @Override
  public Optional<PurchaseOrder> findById(Long id) {

    return Optional.ofNullable(this.entityManager.find(PurchaseOrder.class, id));
  }

  @Override
  public List<PurchaseOrder> findAll() {
    return this.entityManager.createQuery("SELECT p FROM PurchaseOrder p", PurchaseOrder.class).getResultList();
  }

  @Override
  public PurchaseOrder delete(PurchaseOrder entity) {
    try {
      this.entityManager.getTransaction().begin();

      PurchaseOrder managed = this.entityManager.contains(entity) ? entity : this.entityManager.merge(entity);

      this.entityManager.remove(managed);

      this.entityManager.getTransaction().commit();
      return managed;
    } catch (Exception e) {
      if (this.entityManager.getTransaction().isActive()) {
        this.entityManager.getTransaction().rollback();
      }
      throw e;
    }
  }

  @Override
  public PurchaseOrder update(PurchaseOrder entity) {
    try {
      this.entityManager.getTransaction().begin();

      PurchaseOrder updated = this.entityManager.merge(entity);

      this.entityManager.getTransaction().commit();
      return updated;
    } catch (Exception e) {
      if (this.entityManager.getTransaction().isActive()) {
        this.entityManager.getTransaction().rollback();
      }
      throw e;
    }
  }
}