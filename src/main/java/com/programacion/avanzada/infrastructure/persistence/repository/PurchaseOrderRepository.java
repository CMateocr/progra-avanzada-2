package com.programacion.avanzada.infrastructure.persistence.repository;

import com.programacion.avanzada.domain.entity.PurchaseOrder;
import com.programacion.avanzada.domain.repository.IPurchaseOrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@ApplicationScoped // <--- No olvides esto para que Weld lo encuentre
public class PurchaseOrderRepository implements IPurchaseOrderRepository {

  private final EntityManager entityManager;

  @Inject
  public PurchaseOrderRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public PurchaseOrder save(PurchaseOrder entity) {
    // Validación lógica: Una orden debe pertenecer a alguien
    if (entity.getCustomer() == null) {
      throw new IllegalArgumentException("No se puede guardar una PurchaseOrder sin un Customer asociado.");
    }

    // Si ya tiene ID, derivamos al update
    if (entity.getId() != null) {
      return this.update(entity);
    }

    try {
      this.entityManager.getTransaction().begin();

      // NOTA: Si 'lineItems' tiene elementos nuevos y NO tienes CascadeType.PERSIST
      // en la entidad, esos items NO se guardarán aquí, solo la orden.
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
    // find retorna null si no existe, Optional lo maneja elegante
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

      // Traemos la entidad al contexto de persistencia
      PurchaseOrder managed = this.entityManager.contains(entity) ? entity : this.entityManager.merge(entity);

      // ADVERTENCIA: Si hay LineItems asociados en la BD, esto fallará por Foreign Key
      // a menos que tengas CascadeType.REMOVE en la entidad o ON DELETE CASCADE en la BD.
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

      // Merge actualiza la orden.
      // Si modificaste la lista de lineItems, JPA intentará sincronizarla
      // (pero recuerda las reglas de Cascade).
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