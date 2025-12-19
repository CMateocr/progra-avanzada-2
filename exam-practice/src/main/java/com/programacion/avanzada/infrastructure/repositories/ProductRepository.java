package com.programacion.avanzada.infrastructure.repositories;

import com.programacion.avanzada.domain.entities.Product;
import com.programacion.avanzada.domain.entities.PurchaseOrder;
import com.programacion.avanzada.domain.repositories.IProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

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

   public void createSomething() {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Ob> cq = cb.createQuery(Ob.class);

    Root<Product> root = cq.from( Product.class );

     Join<Product, PurchaseOrder> productPurchaseOrderJoin = root.join("purchaseOrders");

    cq.select( cb.construct(
            Ob.class,
            root.get("name"),
            cb.count( productPurchaseOrderJoin )
    ) );


    cq.groupBy(root.get("name"));


    var result = em.createQuery( cq ).getResultList();

     System.out.println(result);

//    Root<Product> root = cq.from(Product.class);
//
//    CriteriaQuery<Product> produdctsGt100 = cq.where(cb.gt(root.get("price"), 100.0));
//
//    var result = em.createQuery( produdctsGt100 ).getResultList();

//    System.out.println(result);


  }


}
