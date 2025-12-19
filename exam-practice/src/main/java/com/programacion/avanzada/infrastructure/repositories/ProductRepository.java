package com.programacion.avanzada.infrastructure.repositories;

import com.programacion.avanzada.domain.entities.Product;
import com.programacion.avanzada.domain.repositories.IProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ProductRepository extends AbstractRepository<Product, Long> implements IProductRepository {

  @Inject
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return this.em;
  }

  @Override
  protected Class<Product> getEntityClass() {
    return Product.class;
  }
}
