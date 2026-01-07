package com.programacion.avanzada.infrastructure.persistence.configuration;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class JPAProducer {
  @Produces
  @ApplicationScoped
  public EntityManagerFactory createEntityManagerFactory() {
    return Persistence.createEntityManagerFactory("avanzada");
  }

  @Produces
  public EntityManager createEntityManager(EntityManagerFactory emf) {
    return emf.createEntityManager();
  }

  public void closeEntityManager(@Disposes EntityManager em) {
    if (em.isOpen()) em.close();
  }

  public void closeEntityManagerFactory(@Disposes EntityManagerFactory emf) {
    if (emf.isOpen()) emf.close();
  }

}
