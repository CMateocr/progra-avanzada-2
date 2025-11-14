package com.prog_avanzada.services;

import com.prog_avanzada.config.Sum;
import jakarta.enterprise.context.ApplicationScoped;

@Sum
@ApplicationScoped
public class OperationsService implements IOperations {

  @Override
  public Integer sum(Integer x, Integer y) {
    return x + y;
  }
}
