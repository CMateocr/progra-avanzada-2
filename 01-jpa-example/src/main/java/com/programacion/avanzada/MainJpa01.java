package com.programacion.avanzada;

import com.programacion.avanzada.domain.entity.Author;
import com.programacion.avanzada.domain.entity.Book;
import com.programacion.avanzada.domain.repository.IAuthorRepository;
import com.programacion.avanzada.domain.repository.IBookRepository;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.math.BigDecimal;

public class MainJpa01 {
  public static void main(String[] args) {
    try(SeContainer container = SeContainerInitializer.newInstance().initialize()) {
      IAuthorRepository authorRepository = container.select(IAuthorRepository.class).get();
      IBookRepository bookRepository = container.select(IBookRepository.class).get();

      Author a1 = Author
              .builder()
              .name("Mateo 1")
              .version(1)
              .build();

      authorRepository.save(a1);

      Book b1 = Book.builder()
              .isbn("2")
              .price(BigDecimal.valueOf(19.99))
              .title("El manantial")
              .version(1)
              .build();

      b1.getAuthors().add( a1 );

      bookRepository.save( b1 );
    }
  }
}
