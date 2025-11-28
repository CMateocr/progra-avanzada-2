package com.programacion.avanzada;

import com.programacion.avanzada.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainJpa01 {
  public static void main(String[] args) {
    try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("avanzada")) {
      EntityManager em = emf.createEntityManager();

      Book book1 = em.find(Book.class, "978-0307474728");

      System.out.println(book1.getAuthors());

//      emf.close();
    } catch (Exception e) {
       throw new RuntimeException(e);
    }


  }
}

