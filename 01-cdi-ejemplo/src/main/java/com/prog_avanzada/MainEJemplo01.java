package com.prog_avanzada;

import com.prog_avanzada.services.IOperations;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class MainEJemplo01 {
  public static void main(String[] args) {

    try (SeContainer contenedor = SeContainerInitializer.newInstance().initialize()) {
      System.out.println("Contenedor inicializado correctamente");

      IOperations opertations1 = contenedor.select(IOperations.class).get();

      IOperations opertations2 = contenedor.select(IOperations.class).get();

      System.out.println(opertations1);

      System.out.println(opertations2);

      int result = opertations1.sum(1,23);

      System.out.println(result);
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }
  }
}
