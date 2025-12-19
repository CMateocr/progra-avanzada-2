package com.programacion.avanzada;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.persistence.EntityManager;

public class Main {
  public static void main(String[] args) {
    try(SeContainer container = SeContainerInitializer.newInstance().initialize()) {
      EntityManager em = container.select(EntityManager.class).get();
    }
  }
}
