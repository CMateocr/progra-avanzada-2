package com.programacion.avanzada.infrastructure.persistence.repository;

import com.programacion.avanzada.domain.entity.LineItem;
import com.programacion.avanzada.domain.entity.LineItemId;
import com.programacion.avanzada.domain.repository.ILineItemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class LineItemRepository implements ILineItemRepository {
  private final EntityManager entityManager;

  @Inject
  public LineItemRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public LineItem save(LineItem entity) {
    if( entity.getPurchaseOrder() == null && entity.getIdx() == null) {
      throw new IllegalArgumentException("need purchase order and idx to save");
    }

    try {
      this.entityManager.getTransaction().begin();

      LineItem lineItemSaved = this.entityManager.merge( entity );

      this.entityManager.getTransaction().commit();

      return lineItemSaved;
    } catch (Exception e) {
      if(this.entityManager.getTransaction().isActive()) this.entityManager.getTransaction().rollback();

      throw new RuntimeException(e);
    }
  }

  @Override
  public Optional<LineItem> findById(LineItemId id) {
    return Optional.ofNullable( this.entityManager.find(LineItem.class, id) );
  }

  @Override
  public List<LineItem> findAll() {
    return this.entityManager.createQuery("SELECT l FROM LineItem l", LineItem.class).getResultList();
  }

  @Override
  public LineItem delete(LineItem entity) {
    try {
      this.entityManager.getTransaction().begin();

      LineItem managed = this.entityManager.merge(entity);

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
  public LineItem update(LineItem entity) {
    return this.save( entity );
  }
}
