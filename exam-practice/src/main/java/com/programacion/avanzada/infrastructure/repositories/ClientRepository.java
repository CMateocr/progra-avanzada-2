package com.programacion.avanzada.infrastructure.repositories;

import com.programacion.avanzada.domain.entities.Client;
import com.programacion.avanzada.domain.repositories.IClientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ClientRepository extends AbstractRepository<Client, Long> implements IClientRepository {

  private final EntityManager em;

  public ClientRepository(EntityManager em) {
    this.em = em;
  }

  @Override
  protected EntityManager getEntityManager() {
    return this.em;
  }

  @Override
  protected Class<Client> getEntityClass() {
    return Client.class;
  }
}
