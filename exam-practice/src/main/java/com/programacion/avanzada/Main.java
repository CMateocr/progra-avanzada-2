package com.programacion.avanzada;

import com.programacion.avanzada.infrastructure.repositories.ProductRepository;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.persistence.EntityManager;

public class Main {
  public static void main(String[] args) {
    try(SeContainer container = SeContainerInitializer.newInstance().initialize()) {

      var r = container.select(ProductRepository.class).get();

      r.createSomething();


    }
  }
}
