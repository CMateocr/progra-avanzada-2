package com.programacion.avanzada;

import com.programacion.avanzada.application.Library;
import com.programacion.avanzada.domain.entity.Author;
import com.programacion.avanzada.domain.entity.Book;
import com.programacion.avanzada.domain.entity.Inventory;
import com.programacion.avanzada.domain.entity.LineItem;
import com.programacion.avanzada.domain.repository.IBookRepository;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

public class MainJpa03 {
  public static void main(String[] args) {
    try(SeContainer container = SeContainerInitializer.newInstance().initialize()) {
      Library library = container.select(Library.class).get();
      IBookRepository bookRepository = container.select(IBookRepository.class).get();

      Book newBook = Book.builder()
              .isbn("978-0134685991")
              .title("Effective Java")
              .price(new BigDecimal("45.00"))
              .version(1)
              .build();

      Author author = Author.builder()
              .name("Joshua Bloch")
              .version(1)
              .build();

      LineItem item1 = LineItem.builder()
              .quantity(2)
              .build();

      LineItem item2 = LineItem.builder()
              .quantity(1)
              .build();

      Inventory inventory = Inventory.builder()
              .sold(0)
              .supplied(100)
              .version(1)
              .build();

      Book savedBook = library.createEntireBook(
              newBook,
              List.of(item1, item2),
              List.of(author),
              inventory
      );

      System.out.println("ISBN: " + savedBook.getIsbn());
      System.out.println("Título: " + savedBook.getTitle());
      System.out.println("Autores: " + savedBook.getAuthors().size());
      System.out.println("Items: " + savedBook.getLineItem().size());
      System.out.println("Stock Inicial: " + savedBook.getInventory().getSupplied());

      bookRepository.findAll().forEach(b -> {
        System.out.println("Persistido en DB: " + b.getTitle() + " con " + b.getLineItem().size() + " items.");
      });
    }

  }
}
