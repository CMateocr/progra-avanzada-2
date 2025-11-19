package com.prog_avanzada;

import com.prog_avanzada.model.Book;
import com.prog_avanzada.services.interfaces.IBookRepository;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.util.Optional;

public class Main02 {
  public static void main(String[] args) {
    try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

      IBookRepository repo = container.select(IBookRepository.class).get();
      System.out.println("Inicio de pruebas");

      // 1. Create
      System.out.println("1. Insertando libro");
      Book nuevoLibro = Book.builder()
              .isbn("1")
              .title("Aprendiendo Java Avanzado")
              .price(45.50)
              .version(1)
              .build();

      repo.insert(nuevoLibro);
      System.out.println("libro insertado");

      // 2. Find
      System.out.println("2. Buscando libro");
      Optional<Book> encontrado = repo.findByIsbn("1");

      // Usamos ifPresent para no romper el programa si no existe
      encontrado.ifPresentOrElse(
              b -> System.out.println("Encontrado: " + b),
              () -> System.out.println("No se encontró el libro")
      );

      // 3. Update
      if (encontrado.isPresent()) {
        System.out.println("3. Actualizar");
        Book libroParaActualizar = encontrado.get();
        libroParaActualizar.setPrice(99.99);
        libroParaActualizar.setVersion(2);

        repo.updateBook(libroParaActualizar);

        repo.findByIsbn("1").ifPresent(b -> System.out.println("Libro actualizado: " + b));
      }

      // 4. Find all
      System.out.println("4. Listando todos los libros:");
      repo.findAll().forEach(System.out::println);

      // 5. Delete
      System.out.println("5. Eliminar");
      repo.delete("1");

      // Verificamos que se borro
      boolean existe = repo.findByIsbn("1").isPresent();
      System.out.println("Existe: " + existe);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}