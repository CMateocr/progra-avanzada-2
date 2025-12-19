package com.programacion.avanzada.infrastructure.repositories;

import com.programacion.avanzada.domain.entities.PurchaseOrder;
import com.programacion.avanzada.domain.repositories.IPurchaseOrderRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public class PurchaseOrderRepository extends AbstractRepository<PurchaseOrder, Long> implements IPurchaseOrderRepository {
  @Inject
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return this.em;
  }

  @Override
  protected Class<PurchaseOrder> getEntityClass() {
    return PurchaseOrder.class;
  }
}
