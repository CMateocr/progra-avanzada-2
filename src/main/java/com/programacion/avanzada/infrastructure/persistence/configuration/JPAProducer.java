package com.programacion.avanzada.infrastructure.persistence.configuration;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class JPAProducer {

  @Produces
  public EntityManager createEntityManager() {
    return Persistence.createEntityManagerFactory("avanzada").createEntityManager();
  }
}
