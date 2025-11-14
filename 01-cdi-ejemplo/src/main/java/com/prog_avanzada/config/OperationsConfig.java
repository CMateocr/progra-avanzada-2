package com.prog_avanzada.config;

import com.prog_avanzada.services.IOperations;
import com.prog_avanzada.services.OperationsService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class OperationsConfig {

  @Produces
  public IOperations operaciones() {
    return new OperationsService();
  }
}