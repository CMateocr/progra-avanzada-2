package com.programacion.avanzada;

import com.programacion.avanzada.application.ManualLibraryService;
import com.programacion.avanzada.domain.entity.Author;
import com.programacion.avanzada.domain.entity.Book;
import com.programacion.avanzada.domain.repository.IAuthorRepository;
import com.programacion.avanzada.domain.repository.IBookRepository;
import com.programacion.avanzada.domain.repository.ICustomerRepository;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.math.BigDecimal;

public class MainJpa01 {
  public static void main(String[] args) {
    try(SeContainer container = SeContainerInitializer.newInstance().initialize()) {
      var ma = container.select( ManualLibraryService.class ).get();

//      ma.createBookManual("lol", "Juan");
//      ma.createOrderManual(10L);
    }

  }
}
